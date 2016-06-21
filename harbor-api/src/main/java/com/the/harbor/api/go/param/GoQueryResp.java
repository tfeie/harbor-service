package com.the.harbor.api.go.param;

import com.the.harbor.base.vo.Response;

public class GoQueryResp extends Response {

	private static final long serialVersionUID = 1L;

	private Go go;

	public Go getGo() {
		return go;
	}

	public void setGo(Go go) {
		this.go = go;
	}

}
