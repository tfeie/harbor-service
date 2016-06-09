package com.the.harbor.api.user.param;

import java.util.List;

import com.the.harbor.base.vo.Response;

public class UserSystemTagQueryResp extends Response {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户标识
	 */
	private String userId;

	/**
	 * 用户最终选择的系统兴趣标签集合
	 */
	private List<UserTag> systemInterestTags;

	/**
	 * 用户最终选择的系统技能标签集合
	 */
	private List<UserTag> systemSkillTags;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<UserTag> getSystemInterestTags() {
		return systemInterestTags;
	}

	public void setSystemInterestTags(List<UserTag> systemInterestTags) {
		this.systemInterestTags = systemInterestTags;
	}

	public List<UserTag> getSystemSkillTags() {
		return systemSkillTags;
	}

	public void setSystemSkillTags(List<UserTag> systemSkillTags) {
		this.systemSkillTags = systemSkillTags;
	}

}
