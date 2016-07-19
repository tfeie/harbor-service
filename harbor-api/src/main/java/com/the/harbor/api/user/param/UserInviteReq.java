package com.the.harbor.api.user.param;

public class UserInviteReq implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private UserInviteInfo userInviteInfo;

	public UserInviteInfo getUserInviteInfo() {
		return userInviteInfo;
	}

	public void setUserInviteInfo(UserInviteInfo userInviteInfo) {
		this.userInviteInfo = userInviteInfo;
	}
}
