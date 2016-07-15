package com.the.harbor.mnslistener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.model.Message;
import com.the.harbor.api.go.param.Go;
import com.the.harbor.commons.components.aliyuncs.mns.MNSSettings;
import com.the.harbor.commons.components.aliyuncs.mns.MessageReceiver;
import com.the.harbor.commons.components.elasticsearch.ElasticSearchFactory;
import com.the.harbor.commons.components.globalconfig.GlobalSettings;
import com.the.harbor.commons.indices.def.HarborIndex;
import com.the.harbor.commons.indices.def.HarborIndexType;
import com.the.harbor.commons.util.StringUtil;

public class GoIndexBuildListener implements InitializingBean {

	private static final Logger LOG = LoggerFactory.getLogger(GoIndexBuildListener.class);

	protected static MNSClient sMNSClient;

	@Override
	public void afterPropertiesSet() throws Exception {
		// 为了防止阻塞dubbo服务启动，这里采用一个异步线程唤醒消息接收工作线程
		Thread thread = new Thread(new Runnable() {
			public void run() {
				runThread();
			}
		});
		thread.start();
	}

	public static void runThread() {
		CloudAccount account = new CloudAccount(MNSSettings.getMNSAccessKeyId(), MNSSettings.getMNSAccessKeySecret(),
				MNSSettings.getMNSAccountEndpoint());
		sMNSClient = account.getMNSClient();

		Thread thread1 = new Thread(new Runnable() {
			public void run() {
				WorkerFunc(1);
			}
		});
		Thread thread2 = new Thread(new Runnable() {
			public void run() {
				WorkerFunc(2);
			}
		});
		Thread thread3 = new Thread(new Runnable() {
			public void run() {
				WorkerFunc(3);
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

	public static void WorkerFunc(int workerId) {
		String queueName = GlobalSettings.getGoIndexBuildQueueName();
		MessageReceiver receiver = new MessageReceiver(workerId, sMNSClient, queueName);
		while (true) {
			Message message = receiver.receiveMessage();
			LOG.info("Thread" + workerId + " GOT ONE MESSAGE! " + message.getMessageId());
			try {
				if (!StringUtil.isBlank(message.getMessageBody())) {
					Go go = JSONObject.parseObject(message.getMessageBody(), Go.class);
					ElasticSearchFactory.addIndex(HarborIndex.HY_GO_DB.getValue(), HarborIndexType.HY_GO.getValue(),
							go.getGoId(), message.getMessageBody());
				}
			} catch (Exception ex) {
				LOG.error("GO索引构建MNS消息失败", ex);
			}
			try {
				sMNSClient.getQueueRef(queueName).deleteMessage(message.getReceiptHandle());
			} catch (Exception ex) {
				LOG.error("消息删除失败", ex);
			}
		}
	}

}
