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

/**
 * 用户互动行为消息监听。<br>
 * 1.BE点赞<br>
 * 2.GO浏览<br>
 * 3.GO搜藏<br>
 * 
 * @author zhangchao
 *
 */
public class UserInteractionListener implements InitializingBean {

	private static final Logger LOG = LoggerFactory.getLogger(UserInteractionListener.class);

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
			LOG.error("用户交互消息工作线程启动失败", e);
		}
	}

	public static void WorkerFunc(int workerId, IUserInterfactionSV userInterfactionSV) {
		MessageReceiver receiver = new MessageReceiver(workerId, sMNSClient,
				GlobalSettings.getUserInteractionQueueName());
		while (true) {
			Message message = receiver.receiveMessage();
			LOG.info("Thread" + workerId + " GOT ONE MESSAGE! " + message.getMessageId());
			boolean success = false;
			try {
				if (!StringUtil.isBlank(message.getMessageBody())) {
					userInterfactionSV.process(message.getMessageBody());
				}
				success = true;
			} catch (Exception ex) {
				success = false;
				LOG.error("用户交互消息消费失败", ex);
			}
			if (success) {
				// 如果记录入库成功，则需要从MNS删除消息
				sMNSClient.getQueueRef(GlobalSettings.getUserInteractionQueueName())
						.deleteMessage(message.getReceiptHandle());
			}
		}
	}

}
