package com.the.harbor.api.user.param;

import java.io.Serializable;

public class UserInviteInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String inviteCode;

    private String userId;

    private String inviteUserId;

    private String status;

	public String getInviteCode() {
		return inviteCode;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getInviteUserId() {
		return inviteUserId;
	}

	public void setInviteUserId(String inviteUserId) {
		this.inviteUserId = inviteUserId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    
}
