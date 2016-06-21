package com.the.harbor.api.go.param;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.the.harbor.api.go.IGoSV;

public class GoOrderConfirmReq implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "活动预约流水为空", groups = { IGoSV.ConfirmGoOrder.class })
	private String goOrderId;

	@NotNull(message = "活动发起用户为空", groups = { IGoSV.ConfirmGoOrder.class })
	private String publishUserId;

	@NotNull(message = "确认标记为空", groups = { IGoSV.ConfirmGoOrder.class })
	private String ackFlag;

	public String getGoOrderId() {
		return goOrderId;
	}

	public void setGoOrderId(String goOrderId) {
		this.goOrderId = goOrderId;
	}

	public String getPublishUserId() {
		return publishUserId;
	}

	public void setPublishUserId(String publishUserId) {
		this.publishUserId = publishUserId;
	}

	public String getAckFlag() {
		return ackFlag;
	}

	public void setAckFlag(String ackFlag) {
		this.ackFlag = ackFlag;
	}
	
	

}
