package com.the.harbor.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.dubbo.common.json.JSON;
import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.model.Message;
import com.the.harbor.commons.components.aliyuncs.mns.MNSSettings;
import com.the.harbor.commons.components.aliyuncs.mns.MessageReceiver;
import com.the.harbor.commons.components.globalconfig.GlobalSettings;
import com.the.harbor.commons.redisdata.def.DoNotify;
import com.the.harbor.commons.util.StringUtil;
import com.the.harbor.service.interfaces.INotifySV;
import com.the.harbor.service.interfaces.ISmsSendRecordSV;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class ReceiveSMSSendTest {

	@Autowired
	ISmsSendRecordSV builder;

	@Autowired
	INotifySV notifySV;

	@Test
	public void smsRecord() {
		CloudAccount account = new CloudAccount(MNSSettings.getMNSAccessKeyId(), MNSSettings.getMNSAccessKeySecret(),
				MNSSettings.getMNSAccountEndpoint());
		MNSClient sMNSClient = account.getMNSClient();
		MessageReceiver receiver = new MessageReceiver(1, sMNSClient, GlobalSettings.getNotifyQueueName());
		while (true) {
			Message message = receiver.receiveMessage();
			boolean success = false;
			try {
				if (!StringUtil.isBlank(message.getMessageBody())) {
					DoNotify notify = JSON.parse(message.getMessageBody(), DoNotify.class);
					notifySV.process(notify);
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
