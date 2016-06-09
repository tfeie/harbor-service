package com.the.harbor.api.user.param;

import java.io.Serializable;
import java.util.List;

public class UserSystemTagSubmitReq implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户标识
	 */
	private String userId;

	/**
	 * 用户最终选择的系统标签集合
	 */
	private List<UserTag> systemTags;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<UserTag> getSystemTags() {
		return systemTags;
	}

	public void setSystemTags(List<UserTag> systemTags) {
		this.systemTags = systemTags;
	}
	
	

}
