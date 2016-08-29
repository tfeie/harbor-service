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
import com.the.harbor.api.be.param.Be;
import com.the.harbor.base.enumeration.mns.MQType;
import com.the.harbor.commons.components.aliyuncs.mns.MNSSettings;
import com.the.harbor.commons.components.aliyuncs.mns.MessageReceiver;
import com.the.harbor.commons.components.globalconfig.GlobalSettings;
import com.the.harbor.commons.indices.mq.MNSRecordHandle;
import com.the.harbor.commons.redisdata.util.HyBeUtil;
import com.the.harbor.commons.util.StringUtil;
import com.the.harbor.service.interfaces.IBeBusiSV;

public class BeIndexBuildListener implements InitializingBean {

	private static final Logger LOG = LoggerFactory.getLogger(BeIndexBuildListener.class);

	protected static MNSClient sMNSClient;

	@Autowired
	private transient IBeBusiSV beBusiSV;

	@Override
	public void afterPropertiesSet() throws Exception {
		// 为了防止阻塞dubbo服务启动，这里采用一个异步线程唤醒消息接收工作线程
		Thread thread = new Thread(new Runnable() {
			public void run() {
				runThread(beBusiSV);
			}
		});
		thread.start();
	}

	public static void runThread(final IBeBusiSV beBusiSV) {
		CloudAccount account = new CloudAccount(MNSSettings.getMNSAccessKeyId(), MNSSettings.getMNSAccessKeySecret(),
				MNSSettings.getMNSAccountEndpoint());
		sMNSClient = account.getMNSClient();

		Thread thread1 = new Thread(new Runnable() {
			public void run() {
				WorkerFunc(1, beBusiSV);
			}
		});
		Thread thread2 = new Thread(new Runnable() {
			public void run() {
				WorkerFunc(2, beBusiSV);
			}
		});
		Thread thread3 = new Thread(new Runnable() {
			public void run() {
				WorkerFunc(3, beBusiSV);
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
			LOG.error("BE索引异步构建存储工作线程启动失败", e);
		}
	}

	public static void WorkerFunc(int workerId, IBeBusiSV beBusiSV) {
		String queueName = GlobalSettings.getBeIndexBuildQueueName();
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
					Be be = JSONObject.parseObject(message.getMessageBody(), Be.class);
					mqId = be.getBeId();
					mqType = MQType.MQ_HY_BE_NEW.getValue();
					// 写入REDIS缓存
					HyBeUtil.recordBe(be.getBeId(), JSON.toJSONString(be));
					// 写入OPENSEARCH索引
					beBusiSV.pushBeToOpenSearch(be.getBeId());
				}
			} catch (Exception ex) {
				LOG.error("BE索引构建MNS消息失败", ex);
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
