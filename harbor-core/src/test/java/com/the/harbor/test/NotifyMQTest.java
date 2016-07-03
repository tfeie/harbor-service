package com.the.harbor.test;

import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.model.Message;
import com.the.harbor.commons.components.aliyuncs.mns.MNSSettings;
import com.the.harbor.commons.components.aliyuncs.mns.MessageReceiver;
import com.the.harbor.commons.components.globalconfig.GlobalSettings;
import com.the.harbor.commons.util.StringUtil;

public class NotifyMQTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CloudAccount account = new CloudAccount(MNSSettings.getMNSAccessKeyId(), MNSSettings.getMNSAccessKeySecret(),
				MNSSettings.getMNSAccountEndpoint());
		MNSClient sMNSClient = account.getMNSClient();
		MessageReceiver receiver = new MessageReceiver(1, sMNSClient, GlobalSettings.getNotifyQueueName());
		while (true) {
			Message message = receiver.receiveMessage();
			boolean success = false;
			try {
				if (!StringUtil.isBlank(message.getMessageBody())) {
					System.out.println(message.getMessageBody());
				}
				success = true;
			} catch (Exception ex) {
				success = false;
			}
			if (success) {
				// 如果记录入库成功，则需要从MNS删除消息
				sMNSClient.getQueueRef(GlobalSettings.getNotifyQueueName()).deleteMessage(message.getReceiptHandle());
			}
		}

	}

}
