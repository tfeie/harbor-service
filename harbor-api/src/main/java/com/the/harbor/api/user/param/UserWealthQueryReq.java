package com.the.harbor.api.user.param;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.the.harbor.api.user.IUserSV;

public class UserWealthQueryReq implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户标识
	 */
	@NotBlank(message = "用户标识不能为空", groups = { IUserSV.QueryUserWealth.class })
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
