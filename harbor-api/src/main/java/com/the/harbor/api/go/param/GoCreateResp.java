package com.the.harbor.api.go.param;

import com.the.harbor.base.vo.Response;

public class GoCreateResp extends Response {

	private static final long serialVersionUID = 1L;

	/**
	 * 活动标识
	 */
	private String goId;

	public String getGoId() {
		return goId;
	}

	public void setGoId(String goId) {
		this.goId = goId;
	}

}
