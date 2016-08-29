package com.the.harbor.mnslistener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.model.Message;
import com.the.harbor.api.go.param.Go;
import com.the.harbor.base.enumeration.mns.MQType;
import com.the.harbor.commons.components.aliyuncs.mns.MNSSettings;
import com.the.harbor.commons.components.aliyuncs.mns.MessageReceiver;
import com.the.harbor.commons.components.globalconfig.GlobalSettings;
import com.the.harbor.commons.indices.mq.MNSRecordHandle;
import com.the.harbor.commons.redisdata.util.HyGoUtil;
import com.the.harbor.commons.util.StringUtil;
import com.the.harbor.service.interfaces.IGoBusiSV;

public class GoIndexBuildListener implements InitializingBean {

	private static final Logger LOG = LoggerFactory.getLogger(GoIndexBuildListener.class);

	protected static MNSClient sMNSClient;

	@Autowired
	private transient IGoBusiSV goBusiSV;

	@Override
	public void afterPropertiesSet() throws Exception {
		// 为了防止阻塞dubbo服务启动，这里采用一个异步线程唤醒消息接收工作线程
		Thread thread = new Thread(new Runnable() {
			public void run() {
				runThread(goBusiSV);
			}
		});
		thread.start();
	}

	public static void runThread(final IGoBusiSV goBusiSV) {
		CloudAccount account = new CloudAccount(MNSSettings.getMNSAccessKeyId(), MNSSettings.getMNSAccessKeySecret(),
				MNSSettings.getMNSAccountEndpoint());
		sMNSClient = account.getMNSClient();

		Thread thread1 = new Thread(new Runnable() {
			public void run() {
				WorkerFunc(1, goBusiSV);
			}
		});
		Thread thread2 = new Thread(new Runnable() {
			public void run() {
				WorkerFunc(2, goBusiSV);
			}
		});
		Thread thread3 = new Thread(new Runnable() {
			public void run() {
				WorkerFunc(3, goBusiSV);
			}
		});

		thread1.start();
		thread2.start();
		thread3.start();

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// keep looping util the end of world
		try {
			thread1.join();
			thread2.join();
			thread3.join();
		} catch (InterruptedException e) {
			LOG.error("GO索引异步构建存储工作线程启动失败", e);
		}
	}

	public static void WorkerFunc(int workerId, IGoBusiSV goBusiSV) {
		String queueName = GlobalSettings.getGoIndexBuildQueueName();
		MessageReceiver receiver = new MessageReceiver(workerId, sMNSClient, queueName);
		while (true) {
			boolean success = true;
			String error = null;
			String mqId = null;
			String mqType = null;
			Message message = receiver.receiveMessage();
			LOG.info("Thread" + workerId + " GOT ONE MESSAGE! " + message.getMessageId());
			try {
				if (!StringUtil.isBlank(message.getMessageBody())) {
					Go go = JSONObject.parseObject(message.getMessageBody(), Go.class);
					mqId = go.getGoId();
					mqType = MQType.MQ_HY_GO_NEW.getValue();
					// 写入REDIS
					HyGoUtil.recordGo(go.getGoId(), JSON.toJSONString(go));
					// 写入OPENSEARCH索引
					goBusiSV.pushGoToOpenSearch(go.getGoId());
				}
			} catch (Exception ex) {
				LOG.error("GO索引构建MNS消息失败", ex);
				success = false;
				error = ex.getMessage();
			}
			try {
				if (success) {
					if (!StringUtil.isBlank(message.getReceiptHandle())) {
						sMNSClient.getQueueRef(queueName).deleteMessage(message.getReceiptHandle());
					}
				}
				if (!StringUtil.isBlank(mqId) && !StringUtil.isBlank(mqType)) {
					MNSRecordHandle.processMNSRecord(mqId, mqType, success, error);
				}

			} catch (Exception ex) {
				LOG.error("消息删除失败", ex);
			}
		}
	}

}
