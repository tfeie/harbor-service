package com.the.harbor.api.user.param;

import com.the.harbor.base.vo.Response;

public class UserViewResp extends Response {

	private static final long serialVersionUID = 1L;

	private UserViewInfo userInfo;

	public UserViewInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserViewInfo userInfo) {
		this.userInfo = userInfo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
