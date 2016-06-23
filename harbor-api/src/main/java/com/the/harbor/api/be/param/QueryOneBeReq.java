package com.the.harbor.api.be.param;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.the.harbor.api.be.IBeSV;

public class QueryOneBeReq implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "BE标识为空", groups = { IBeSV.QueryOneBe.class })
	private String beId;

	public String getBeId() {
		return beId;
	}

	public void setBeId(String beId) {
		this.beId = beId;
	}

}
