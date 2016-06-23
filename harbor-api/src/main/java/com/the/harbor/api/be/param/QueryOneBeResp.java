package com.the.harbor.api.be.param;

import com.the.harbor.base.vo.Response;

public class QueryOneBeResp extends Response {

	private static final long serialVersionUID = 1L;

	private Be be;

	public Be getBe() {
		return be;
	}

	public void setBe(Be be) {
		this.be = be;
	}

}
