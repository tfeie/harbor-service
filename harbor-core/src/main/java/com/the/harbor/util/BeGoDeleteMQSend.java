package com.the.harbor.util;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.mns.client.CloudQueue;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.common.ClientException;
import com.aliyun.mns.common.ServiceException;
import com.aliyun.mns.model.Message;
import com.the.harbor.api.be.param.DoBeDelete;
import com.the.harbor.api.go.param.DoGoDelete;
import com.the.harbor.base.enumeration.mns.MQType;
import com.the.harbor.commons.components.aliyuncs.mns.MNSFactory;
import com.the.harbor.commons.components.globalconfig.GlobalSettings;
import com.the.harbor.commons.indices.mq.MNSRecord;
import com.the.harbor.commons.indices.mq.MNSRecordHandle;
import com.the.harbor.commons.indices.mq.MNSRecordThread;
import com.the.harbor.commons.util.UUIDUtil;

public class BeGoDeleteMQSend {

	private static final Logger LOG = Logger.getLogger(BeGoDeleteMQSend.class);

	public static void sendMQ(DoBeDelete body) {
		MNSClient client = MNSFactory.getMNSClient();
		String sendStatus = MNSRecord.Status.SEND_SUCCESS.name();
		String sendError = null;
		try {
			CloudQueue queue = client.getQueueRef(GlobalSettings.getBeDeleteQueueName());
			Message message = new Message();
			body.setMqId(UUIDUtil.genId32());
			body.setMqType(MQType.MQ_HY_BE_DELETE.getValue());
			message.setMessageBody(JSONObject.toJSONString(body));
			queue.putMessage(message);
		} catch (ClientException ce) {
			LOG.error("Something wrong with the network connection between client and MNS service."
					+ "Please check your network and DNS availablity.", ce);
		} catch (ServiceException se) {
			if (se.getErrorCode().equals("QueueNotExist")) {
				LOG.error("Queue is not exist.Please create before use", se);
			} else if (se.getErrorCode().equals("TimeExpired")) {
				LOG.error("The request is time expired. Please check your local machine timeclock", se);
			}
			LOG.error("be delete message put in Queue error", se);
		} catch (Exception e) {
			LOG.error("Unknown exception happened!", e);
		}

		MNSRecord mns = new MNSRecord();
		mns.setMqId(body.getMqId());
		mns.setMqType(body.getMqType());
		mns.setSendStatus(sendStatus);
		mns.setSendError(sendError);
		mns.setMqBody(body);
		MNSRecordHandle.sendMNSRecord(mns);
		client.close();
	}

	public static void sendMQ(DoGoDelete body) {
		MNSClient client = MNSFactory.getMNSClient();
		String sendStatus = MNSRecord.Status.SEND_SUCCESS.name();
		String sendError = null;
		try {
			CloudQueue queue = client.getQueueRef(GlobalSettings.getGoDeleteQueueName());
			Message message = new Message();
			body.setMqId(UUIDUtil.genId32());
			body.setMqType(MQType.MQ_HY_GO_DELETE.getValue());
			message.setMessageBody(JSONObject.toJSONString(body));
			queue.putMessage(message);
		} catch (ClientException ce) {
			LOG.error("Something wrong with the network connection between client and MNS service."
					+ "Please check your network and DNS availablity.", ce);
		} catch (ServiceException se) {
			if (se.getErrorCode().equals("QueueNotExist")) {
				LOG.error("Queue is not exist.Please create before use", se);
			} else if (se.getErrorCode().equals("TimeExpired")) {
				LOG.error("The request is time expired. Please check your local machine timeclock", se);
			}
			LOG.error("go delete message put in Queue error", se);
		} catch (Exception e) {
			LOG.error("Unknown exception happened!", e);
		}

		MNSRecord mns = new MNSRecord();
		mns.setMqId(body.getMqId());
		mns.setMqType(body.getMqType());
		mns.setSendStatus(sendStatus);
		mns.setSendError(sendError);
		mns.setMqBody(body);
		MNSRecordHandle.sendMNSRecord(mns);
		client.close();
	}
}
