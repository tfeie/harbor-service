package com.the.harbor.api.be.param;

import com.the.harbor.base.vo.Response;

public class BeCreateResp extends Response {

	private static final long serialVersionUID = 1L;

	private String beId;

	public String getBeId() {
		return beId;
	}

	public void setBeId(String beId) {
		this.beId = beId;
	}

}
