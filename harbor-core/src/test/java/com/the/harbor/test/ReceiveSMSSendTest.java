package com.the.harbor.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.CloudQueue;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.common.ClientException;
import com.aliyun.mns.common.ServiceException;
import com.aliyun.mns.model.Message;
import com.the.harbor.commons.components.aliyuncs.mns.MNSFactory;
import com.the.harbor.commons.components.aliyuncs.mns.MNSSettings;
import com.the.harbor.commons.components.aliyuncs.mns.MessageReceiver;
import com.the.harbor.commons.components.aliyuncs.sms.param.SMSSendResponse;
import com.the.harbor.commons.components.globalconfig.GlobalSettings;
import com.the.harbor.commons.util.BeanUtils;
import com.the.harbor.commons.util.StringUtil;
import com.the.harbor.dao.mapper.bo.HySmsSend;
import com.the.harbor.service.interfaces.ISmsSendRecordSV;
import com.the.harbor.service.interfaces.IUserInterfactionSV;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class ReceiveSMSSendTest {

	@Autowired
	ISmsSendRecordSV builder;
	
	@Autowired
	IUserInterfactionSV userInterfactionSV;

	@Test
	public void smsRecord() {
		CloudAccount account = new CloudAccount(MNSSettings.getMNSAccessKeyId(), MNSSettings.getMNSAccessKeySecret(),
				MNSSettings.getMNSAccountEndpoint());
		MNSClient sMNSClient = account.getMNSClient();
		MessageReceiver receiver = new MessageReceiver(1, sMNSClient,
				GlobalSettings.getUserInteractionQueueName());
		while (true) {
			Message message = receiver.receiveMessage();
			boolean success = false;
			try {
				if (!StringUtil.isBlank(message.getMessageBody())) {
					userInterfactionSV.process(message.getMessageBody());
				}
				success = true;
			} catch (Exception ex) {
				success = false;
			}
			if (success) {
				// 如果记录入库成功，则需要从MNS删除消息
				sMNSClient.getQueueRef(GlobalSettings.getUserInteractionQueueName())
						.deleteMessage(message.getReceiptHandle());
			}
		}

	}

}
