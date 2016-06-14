package com.the.harbor.api.user.param;

import java.util.List;

import com.the.harbor.base.vo.Response;

public class UserTagQueryResp extends Response {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户标识
	 */
	private String userId;

	/**
	 * 用户最终选择的所有兴趣标签集合
	 */
	private List<UserTag> interestTags;

	/**
	 * 用户最终选择的所有技能标签集合
	 */
	private List<UserTag> skillTags;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<UserTag> getInterestTags() {
		return interestTags;
	}

	public void setInterestTags(List<UserTag> interestTags) {
		this.interestTags = interestTags;
	}

	public List<UserTag> getSkillTags() {
		return skillTags;
	}

	public void setSkillTags(List<UserTag> skillTags) {
		this.skillTags = skillTags;
	}

}
