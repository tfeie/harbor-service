package com.the.harbor.api.go.param;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.the.harbor.api.go.IGoSV;

public class DeleteGoReq implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "活动ID为空", groups = { IGoSV.DeleteGo.class })
	private String goId;

	@NotNull(message = "操作用户为空", groups = { IGoSV.DeleteGo.class })
	private String userId;

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
