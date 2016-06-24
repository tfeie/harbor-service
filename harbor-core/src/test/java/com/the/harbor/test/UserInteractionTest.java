package com.the.harbor.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.mns.client.CloudQueue;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.common.ClientException;
import com.aliyun.mns.common.ServiceException;
import com.aliyun.mns.model.Message;
import com.the.harbor.api.be.param.DoBeLikes;
import com.the.harbor.api.be.param.DoBeLikes.HandleType;
import com.the.harbor.api.go.param.DoGoFavorite;
import com.the.harbor.api.go.param.DoGoView;
import com.the.harbor.base.enumeration.mns.MQType;
import com.the.harbor.commons.components.aliyuncs.mns.MNSFactory;
import com.the.harbor.commons.components.globalconfig.GlobalSettings;
import com.the.harbor.commons.util.DateUtil;
import com.the.harbor.commons.util.UUIDUtil;

public class UserInteractionTest {

	private static final Logger LOG = LoggerFactory.getLogger(UserInteractionTest.class);

	public static void main(String[] args) {
		buildGoFavMQ();
	}

	private static void buildBeLikesMQ() {
		MNSClient client = MNSFactory.getMNSClient();
		try {
			CloudQueue queue = client.getQueueRef(GlobalSettings.getUserInteractionQueueName());
			for (int i = 0; i < 10; i++) {
				Message message = new Message();
				DoBeLikes body = new DoBeLikes();
				body.setBeId("363249A5345741C58F8A585EF0B68B3D");
				body.setUserId("hy00000" + i);
				body.setTime(DateUtil.getSysDate());
				body.setHandleType(HandleType.CANCEL.name());
				body.setMqId(UUIDUtil.genId32());
				body.setMqType(MQType.MQ_HY_BE_LIKES.getValue());
				message.setMessageBody(JSONObject.toJSONString(body));
				queue.putMessage(message);
			}
		} catch (ClientException ce) {
			LOG.error("Something wrong with the network connection between client and MNS service."
					+ "Please check your network and DNS availablity.", ce);
		} catch (ServiceException se) {
			if (se.getErrorCode().equals("QueueNotExist")) {
				LOG.error("Queue is not exist.Please create before use", se);
			} else if (se.getErrorCode().equals("TimeExpired")) {
				LOG.error("The request is time expired. Please check your local machine timeclock", se);
			}
			LOG.error("SMS  message put in Queue error", se);
		} catch (Exception e) {
			LOG.error("Unknown exception happened!", e);
		}
		client.close();

	}

	private static void buildGoFavMQ() {
		MNSClient client = MNSFactory.getMNSClient();
		try {
			CloudQueue queue = client.getQueueRef(GlobalSettings.getUserInteractionQueueName());
			for (int i = 0; i < 10; i++) {
				Message message = new Message();
				DoGoFavorite body = new DoGoFavorite();
				body.setGoId("959F9B0CAEFD4C47B7AF78232E1E9FE2");
				body.setUserId("hy00000" + i);
				body.setTime(DateUtil.getSysDate());
				body.setHandleType(DoGoFavorite.HandleType.CANCEL.name());
				body.setMqId(UUIDUtil.genId32());
				body.setMqType(MQType.MQ_HY_GO_FAVORITE.getValue());
				message.setMessageBody(JSONObject.toJSONString(body));
				queue.putMessage(message);
			}
		} catch (ClientException ce) {
			LOG.error("Something wrong with the network connection between client and MNS service."
					+ "Please check your network and DNS availablity.", ce);
		} catch (ServiceException se) {
			if (se.getErrorCode().equals("QueueNotExist")) {
				LOG.error("Queue is not exist.Please create before use", se);
			} else if (se.getErrorCode().equals("TimeExpired")) {
				LOG.error("The request is time expired. Please check your local machine timeclock", se);
			}
			LOG.error("SMS  message put in Queue error", se);
		} catch (Exception e) {
			LOG.error("Unknown exception happened!", e);
		}
		client.close();

	}

	private static void buildGoViewMQ() {
		MNSClient client = MNSFactory.getMNSClient();
		try {
			CloudQueue queue = client.getQueueRef(GlobalSettings.getUserInteractionQueueName());
			for (int i = 0; i < 10; i++) {
				Message message = new Message();
				DoGoView body = new DoGoView();
				body.setGoId("959F9B0CAEFD4C47B7AF78232E1E9FE2");
				body.setUserId("hy00000" + i);
				body.setTime(DateUtil.getSysDate());
				body.setMqId(UUIDUtil.genId32());
				body.setMqType(MQType.MQ_HY_GO_VIEWS.getValue());
				message.setMessageBody(JSONObject.toJSONString(body));
				queue.putMessage(message);
			}
		} catch (ClientException ce) {
			LOG.error("Something wrong with the network connection between client and MNS service."
					+ "Please check your network and DNS availablity.", ce);
		} catch (ServiceException se) {
			if (se.getErrorCode().equals("QueueNotExist")) {
				LOG.error("Queue is not exist.Please create before use", se);
			} else if (se.getErrorCode().equals("TimeExpired")) {
				LOG.error("The request is time expired. Please check your local machine timeclock", se);
			}
			LOG.error("SMS  message put in Queue error", se);
		} catch (Exception e) {
			LOG.error("Unknown exception happened!", e);
		}
		client.close();

	}
}
