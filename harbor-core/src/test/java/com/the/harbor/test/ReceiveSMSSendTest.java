package com.the.harbor.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.mns.client.CloudQueue;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.common.ClientException;
import com.aliyun.mns.common.ServiceException;
import com.aliyun.mns.model.Message;
import com.the.harbor.commons.components.aliyuncs.mns.MNSFactory;
import com.the.harbor.commons.components.aliyuncs.sms.param.SMSSendResponse;
import com.the.harbor.commons.components.globalconfig.GlobalSettings;
import com.the.harbor.commons.util.BeanUtils;
import com.the.harbor.dao.mapper.bo.HySmsSend;
import com.the.harbor.service.interfaces.ISmsSendRecordSV;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class ReceiveSMSSendTest {

	@Autowired
	ISmsSendRecordSV builder;

	@Test
	public void smsRecord() {
		MNSClient client = MNSFactory.getMNSClient();
		while (true) {
			HySmsSend record = null;
			try {
				CloudQueue queue = client.getQueueRef(GlobalSettings.getSMSRecordQueueName());
				Message popMsg = queue.popMessage();
				String message = popMsg.getMessageBody();
				SMSSendResponse resp = JSONObject.parseObject(message, SMSSendResponse.class);
				record = new HySmsSend();
				BeanUtils.copyProperties(record, resp);
				queue.deleteMessage(popMsg.getReceiptHandle());
				System.out.println("delete message successfully.\n");
			} catch (ClientException ce) {
				System.out.println("Something wrong with the network connection between client and MNS service."
						+ "Please check your network and DNS availablity.");
				ce.printStackTrace();
			} catch (ServiceException se) {
				if (se.getErrorCode().equals("QueueNotExist")) {
					System.out.println("Queue is not exist.Please create queue before use");
				} else if (se.getErrorCode().equals("TimeExpired")) {
					System.out.println("The request is time expired. Please check your local machine timeclock");
				}
				se.printStackTrace();
			} catch (Exception e) {
				System.out.println("Unknown exception happened!");
				e.printStackTrace();
			}

			// client.close();
			// 写表
			if (record != null)
				builder.saveSmsSendRecord(record);
		}

	}

}
