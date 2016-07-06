package com.the.harbor.api.go.param;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.the.harbor.api.go.IGoSV;

public class CheckUserOrderGoReq implements Serializable {

	private static final long serialVersionUID = 1L;
	@NotNull(message = "活动预约流水为空", groups = { IGoSV.CheckUserOrderGo.class })
	private String goOrderId;

	@NotNull(message = "活动为空", groups = { IGoSV.CheckUserOrderGo.class })
	private String goId;

	@NotNull(message = "用户为空", groups = { IGoSV.CheckUserOrderGo.class })
	private String userId;

	public String getGoOrderId() {
		return goOrderId;
	}

	public void setGoOrderId(String goOrderId) {
		this.goOrderId = goOrderId;
	}

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

}
