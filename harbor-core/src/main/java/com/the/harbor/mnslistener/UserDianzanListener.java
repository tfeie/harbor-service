package com.the.harbor.mnslistener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.model.Message;
import com.the.harbor.commons.components.aliyuncs.mns.MNSSettings;
import com.the.harbor.commons.components.aliyuncs.mns.MessageReceiver;
import com.the.harbor.commons.components.globalconfig.GlobalSettings;
import com.the.harbor.commons.util.StringUtil;
import com.the.harbor.service.interfaces.IUserInterfactionSV;

public class UserDianzanListener implements InitializingBean {

	private static final Logger LOG = LoggerFactory.getLogger(UserDianzanListener.class);

	protected static MNSClient sMNSClient;

	@Autowired
	IUserInterfactionSV userInterfactionSV;

	@Override
	public void afterPropertiesSet() throws Exception {
		// 为了防止阻塞dubbo服务启动，这里采用一个异步线程唤醒消息接收工作线程
		Thread thread = new Thread(new Runnable() {
			public void run() {
				runThread(userInterfactionSV);
			}
		});
		thread.start();
	}

	public static void runThread(final IUserInterfactionSV userInterfactionSV) {
		CloudAccount account = new CloudAccount(MNSSettings.getMNSAccessKeyId(), MNSSettings.getMNSAccessKeySecret(),
				MNSSettings.getMNSAccountEndpoint());
		sMNSClient = account.getMNSClient();

		Thread thread1 = new Thread(new Runnable() {
			public void run() {
				WorkerFunc(1, userInterfactionSV);
			}
		});
		Thread thread2 = new Thread(new Runnable() {
			public void run() {
				WorkerFunc(2, userInterfactionSV);
			}
		});
		Thread thread3 = new Thread(new Runnable() {
			public void run() {
				WorkerFunc(3, userInterfactionSV);
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
			LOG.error("点赞消息工作线程启动失败", e);
		}
	}

	public static void WorkerFunc(int workerId, IUserInterfactionSV userInterfactionSV) {
		String queueName = GlobalSettings.getDianzanQueueName();
		MessageReceiver receiver = new MessageReceiver(workerId, sMNSClient, queueName);
		while (true) {
			Message message = receiver.receiveMessage();
			LOG.info("Thread" + workerId + " GOT ONE MESSAGE! " + message.getMessageId());
			try {
				if (!StringUtil.isBlank(message.getMessageBody())) {
					userInterfactionSV.process(message.getMessageBody());
				}
			} catch (Exception ex) {
				LOG.error("点赞消息处理失败", ex);
			}
			try {
				sMNSClient.getQueueRef(queueName).deleteMessage(message.getReceiptHandle());
			} catch (Exception ex) {
				LOG.error("消息删除失败", ex);
			}

		}
	}

}