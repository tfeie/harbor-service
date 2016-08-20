package com.the.harbor.api.user.param;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.the.harbor.api.user.IUserSV;

public class UserTuijianQueryReq implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "用户不能为空", groups = { IUserSV.QueryTuijianUsers.class })
	private String userId;

	private String keyword;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

}
