package com.the.harbor.api.go.param;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.the.harbor.api.go.IGoSV;

public class GoQueryReq implements Serializable {

	private static final long serialVersionUID = 1L;
	@NotNull(message = "活动为空", groups = { IGoSV.QueryGo.class })
	private String goId;

	public String getGoId() {
		return goId;
	}

	public void setGoId(String goId) {
		this.goId = goId;
	}

}
