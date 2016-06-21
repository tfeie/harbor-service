package com.the.harbor.api.go.param;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.the.harbor.api.go.IGoSV;

/**
 * 设置活动预约见面地点
 * 
 * @author zhangchao
 *
 */
public class GoOrderMeetLocaltionReq implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "活动预约流水为空", groups = { IGoSV.SetGoOrderMeetLocaltion.class })
	private String goOrderId;

	@NotNull(message = "活动发布者为空", groups = { IGoSV.SetGoOrderMeetLocaltion.class })
	private String publishUserId;

	@NotNull(message = "第一个预期时间为空", groups = { IGoSV.SetGoOrderMeetLocaltion.class })
	private String expectedTime1;

	@NotNull(message = "第一个预期地点为空", groups = { IGoSV.SetGoOrderMeetLocaltion.class })
	private String expectedTime2;

	private String expectedLocation1;

	private String expectedLocation2;

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

	public String getExpectedTime1() {
		return expectedTime1;
	}

	public void setExpectedTime1(String expectedTime1) {
		this.expectedTime1 = expectedTime1;
	}

	public String getExpectedTime2() {
		return expectedTime2;
	}

	public void setExpectedTime2(String expectedTime2) {
		this.expectedTime2 = expectedTime2;
	}

	public String getExpectedLocation1() {
		return expectedLocation1;
	}

	public void setExpectedLocation1(String expectedLocation1) {
		this.expectedLocation1 = expectedLocation1;
	}

	public String getExpectedLocation2() {
		return expectedLocation2;
	}

	public void setExpectedLocation2(String expectedLocation2) {
		this.expectedLocation2 = expectedLocation2;
	}

}
