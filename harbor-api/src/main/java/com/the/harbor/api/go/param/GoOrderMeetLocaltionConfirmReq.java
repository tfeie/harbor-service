package com.the.harbor.api.go.param;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.the.harbor.api.go.IGoSV;

/**
 * 确认预约见面地点
 * 
 * @author zhangchao
 *
 */
public class GoOrderMeetLocaltionConfirmReq implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "活动预约流水为空", groups = { IGoSV.ConfirmGoOrderMeetLocaltion.class })
	private String goOrderId;

	@NotNull(message = "活动预约者为空", groups = { IGoSV.ConfirmGoOrderMeetLocaltion.class })
	private String userId;

	@NotNull(message = "确认活动时间为空", groups = { IGoSV.ConfirmGoOrderMeetLocaltion.class })
	private String confirmTime;

	@NotNull(message = "确认活动地点为空", groups = { IGoSV.ConfirmGoOrderMeetLocaltion.class })
	private String confirmLocation;

	public String getGoOrderId() {
		return goOrderId;
	}

	public void setGoOrderId(String goOrderId) {
		this.goOrderId = goOrderId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(String confirmTime) {
		this.confirmTime = confirmTime;
	}

	public String getConfirmLocation() {
		return confirmLocation;
	}

	public void setConfirmLocation(String confirmLocation) {
		this.confirmLocation = confirmLocation;
	}

}
