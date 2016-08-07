package com.the.harbor.api.go.param;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.the.harbor.api.go.IGoSV;

public class DeleteGoReq implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "活动ID为空", groups = { IGoSV.DeleteGo.class })
	private String goId;

	public String getGoId() {
		return goId;
	}

	public void setGoId(String goId) {
		this.goId = goId;
	}
	
	

}
