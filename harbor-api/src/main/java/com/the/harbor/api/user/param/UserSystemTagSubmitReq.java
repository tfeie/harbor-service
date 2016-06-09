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
	private List<UserTag> interestSelectedTags;

	private List<UserTag> skillSelectedTags;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<UserTag> getInterestSelectedTags() {
		return interestSelectedTags;
	}

	public void setInterestSelectedTags(List<UserTag> interestSelectedTags) {
		this.interestSelectedTags = interestSelectedTags;
	}

	public List<UserTag> getSkillSelectedTags() {
		return skillSelectedTags;
	}

	public void setSkillSelectedTags(List<UserTag> skillSelectedTags) {
		this.skillSelectedTags = skillSelectedTags;
	}

}
