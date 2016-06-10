package com.the.harbor.api.user.param;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.the.harbor.api.user.IUserSV;

public class UserMemberQuery implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "用户标识不能为空", groups = { IUserSV.QueryUserMemberInfo.class })
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
