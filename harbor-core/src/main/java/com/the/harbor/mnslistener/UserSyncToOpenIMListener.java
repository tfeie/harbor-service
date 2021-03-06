package com.the.harbor.mnslistener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.model.Message;
import com.the.harbor.api.user.param.DoIMUserSync;
import com.the.harbor.base.exception.SystemException;
import com.the.harbor.commons.components.aliyuncs.mns.MNSSettings;
import com.the.harbor.commons.components.aliyuncs.mns.MessageReceiver;
import com.the.harbor.commons.components.globalconfig.GlobalSettings;
import com.the.harbor.commons.indices.mq.MNSRecordHandle;
import com.the.harbor.commons.util.StringUtil;
import com.the.harbor.service.interfaces.IUserInterfactionSV;

public class UserSyncToOpenIMListener implements InitializingBean {

	private static final Logger LOG = LoggerFactory.getLogger(UserSyncToOpenIMListener.class);

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
			LOG.error("IM账户同步工作线程启动失败", e);
		}
	}

	public static void WorkerFunc(int workerId, IUserInterfactionSV userInterfactionSV) {
		String queueName = GlobalSettings.getIMUserQueueName();
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
					DoIMUserSync notify = JSONObject.parseObject(message.getMessageBody(), DoIMUserSync.class);
					mqId = notify.getMqId();
					mqType = notify.getMqType();
					try {
						userInterfactionSV.userSync2OpenIM(notify);
					} catch (Exception ex) {
						LOG.error("同步用户信息到OpenIM失败", ex);
						throw new SystemException("同步用户信息到IM失败:" + ex.getMessage());
					}
					try {
						userInterfactionSV.userSync2OpenSearch(notify);
					} catch (Exception ex) {
						LOG.error("同步用户信息到OpenSearch失败", ex);
						throw new SystemException("同步用户信息到OpenSearch失败:" + ex.getMessage());
					}
				}
			} catch (Exception ex) {
				LOG.error("消息处理失败", ex);
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
