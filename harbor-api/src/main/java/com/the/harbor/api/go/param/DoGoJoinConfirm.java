package com.the.harbor.api.go.param;

import java.io.Serializable;

import com.the.harbor.base.vo.MNSBody;

public class DoGoJoinConfirm extends MNSBody {

	private static final long serialVersionUID = 1L;

	public enum HandleType implements Serializable {
		AGREE, REJECT;
	}

	private String goId;

	private String userId;

	private String handleType;

	private String topic;

	private String publishUserName;

	public String getGoId() {
		return goId;
	}

	public void setGoId(String goId) {
		this.goId = goId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getHandleType() {
		return handleType;
	}

	public void setHandleType(String handleType) {
		this.handleType = handleType;
	}

	public String getPublishUserName() {
		return publishUserName;
	}

	public void setPublishUserName(String publishUserName) {
		this.publishUserName = publishUserName;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

}
