package com.the.harbor.api.user.param;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.the.harbor.api.user.IUserSV;

public class UserStatusReq implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "用户不能为空", groups = { IUserSV.UserStatus.class })
	private String userId;
	
	@NotBlank(message = "用户状态不能为空", groups = { IUserSV.UserStatus.class })
	private String status;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
