package com.the.harbor.mnslistener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.model.Message;
import com.the.harbor.commons.components.aliyuncs.mns.MNSSettings;
import com.the.harbor.commons.components.aliyuncs.mns.MessageReceiver;
import com.the.harbor.commons.components.aliyuncs.sms.param.SMSSendResponse;
import com.the.harbor.commons.components.globalconfig.GlobalSettings;
import com.the.harbor.commons.indices.mq.MNSRecordHandle;
import com.the.harbor.commons.util.BeanUtils;
import com.the.harbor.commons.util.StringUtil;
import com.the.harbor.dao.mapper.bo.HySmsSend;
import com.the.harbor.service.interfaces.ISmsSendRecordSV;

public class SMSRecordListener implements InitializingBean {

	private static final Logger LOG = LoggerFactory.getLogger(SMSRecordListener.class);

	protected static MNSClient sMNSClient;

	@Autowired
	ISmsSendRecordSV builder;

	@Override
	public void afterPropertiesSet() throws Exception {
		// 为了防止阻塞dubbo服务启动，这里采用一个异步线程唤醒消息接收工作线程
		Thread thread = new Thread(new Runnable() {
			public void run() {
				runThread(builder);
			}
		});
		thread.start();
	}

	public static void runThread(final ISmsSendRecordSV builder) {
		CloudAccount account = new CloudAccount(MNSSettings.getMNSAccessKeyId(), MNSSettings.getMNSAccessKeySecret(),
				MNSSettings.getMNSAccountEndpoint());
		sMNSClient = account.getMNSClient();

		Thread thread1 = new Thread(new Runnable() {
			public void run() {
				WorkerFunc(1, builder);
			}
		});
		Thread thread2 = new Thread(new Runnable() {
			public void run() {
				WorkerFunc(2, builder);
			}
		});
		Thread thread3 = new Thread(new Runnable() {
			public void run() {
				WorkerFunc(3, builder);
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
			e.printStackTrace();
			LOG.error("短信接收记录存储工作线程启动失败", e);
		}
	}

	public static void WorkerFunc(int workerId, ISmsSendRecordSV builder) {
		String queueName = GlobalSettings.getSMSRecordQueueName();
		MessageReceiver receiver = new MessageReceiver(workerId, sMNSClient, queueName);
		while (true) {
			boolean success = true;
			String error = null;
			String mqId = null;
			String mqType = null;
			Message message = receiver.receiveMessage();
			LOG.info("Thread" + workerId + " GOT ONE MESSAGE! " + message.getMessageId());
			System.out.println(message.getMessageBody());
			try {
				if (!StringUtil.isBlank(message.getMessageBody())) {
					SMSSendResponse resp = JSONObject.parseObject(message.getMessageBody(), SMSSendResponse.class);
					mqId = resp.getMqId();
					mqType = resp.getMqType();
					
					HySmsSend record = new HySmsSend();
					BeanUtils.copyProperties(record, resp);
					builder.saveSmsSendRecord(record);
				}
			} catch (Exception ex) {
				LOG.error("短信发送记录MNS消息失败", ex);
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
