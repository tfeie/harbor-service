package com.the.harbor.api.user.param;

import java.util.List;

import com.the.harbor.base.vo.Response;

public class UserTuijianQueryResp extends Response {

	private static final long serialVersionUID = 1L;

	private List<UserViewInfo> userInfos;

	public List<UserViewInfo> getUserInfos() {
		return userInfos;
	}

	public void setUserInfos(List<UserViewInfo> userInfos) {
		this.userInfos = userInfos;
	}

}
