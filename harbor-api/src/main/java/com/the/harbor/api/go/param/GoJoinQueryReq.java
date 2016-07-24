package com.the.harbor.api.go.param;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.the.harbor.api.go.IGoSV;

/**
 * 获取预约记录
 * 
 * @author zhangchao
 *
 */
public class GoJoinQueryReq implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "活动预约流水为空", groups = { IGoSV.QueryGoJoin.class })
	private String goOrderId;

	@NotNull(message = "用户为空", groups = { IGoSV.QueryUserJoinGo.class })
	private String userId;

	@NotNull(message = "活动为空", groups = { IGoSV.QueryUserJoinGo.class })
	private String goId;

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

	public String getGoId() {
		return goId;
	}

	public void setGoId(String goId) {
		this.goId = goId;
	}

}
