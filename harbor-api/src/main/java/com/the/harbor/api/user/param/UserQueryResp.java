package com.the.harbor.api.user.param;

import com.the.harbor.base.vo.Response;

public class UserQueryResp extends Response {

	private static final long serialVersionUID = 1L;

	private UserInfo userInfo;

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

}
