package com.the.harbor.api.go.param;

import com.the.harbor.base.vo.Response;

public class CheckUserOrderGoResp extends Response {

	private static final long serialVersionUID = 1L;

	private boolean checkflag;

	public boolean isCheckflag() {
		return checkflag;
	}

	public void setCheckflag(boolean checkflag) {
		this.checkflag = checkflag;
	}

}
