package com.the.harbor.api.go.param;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.the.harbor.api.go.IGoSV;
import com.the.harbor.base.enumeration.hygo.GoType;
import com.the.harbor.base.enumeration.hygo.OrgMode;
import com.the.harbor.base.validator.StringEnum;

public class GoCreateReq implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "发起用户为空", groups = { IGoSV.CreateGo.class })
	private String userId;

	@NotNull(message = "请选择活动分类", groups = { IGoSV.CreateGo.class })
	@StringEnum(enumClazz = GoType.class, message = "活动分类传入不合规", groups = { IGoSV.CreateGo.class })
	private String goType;

	@NotNull(message = "请输入主题", groups = { IGoSV.CreateGo.class })
	private String topic;

	private String inviteMembers;

	private String expectedStartTime;

	@NotNull(message = "请输入活动预期持续时长", groups = { IGoSV.CreateGo.class })
	private String expectedDuration;

	@NotNull(message = "请选择付费模式", groups = { IGoSV.CreateGo.class })
	private String payMode;

	private String price;

	@NotNull(message = "请选择活动组织形式", groups = { IGoSV.CreateGo.class })
	@StringEnum(enumClazz = OrgMode.class, message = "活动组织形式不合规", groups = { IGoSV.CreateGo.class })
	private String orgMode;

	private String location;

	private String myStory;
	
	// ONO活动海牛的故事
	private List<GoStory> goStories;

	// 活动明细
	@NotNull(message = "请填写活动明细", groups = { IGoSV.CreateGo.class })
	private List<GoDetail> goDetails;

	// 获取标签
	@NotNull(message = "请选择活动标签", groups = { IGoSV.CreateGo.class })
	private List<GoTag> goTags;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getGoType() {
		return goType;
	}

	public void setGoType(String goType) {
		this.goType = goType;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getInviteMembers() {
		return inviteMembers;
	}

	public void setInviteMembers(String inviteMembers) {
		this.inviteMembers = inviteMembers;
	}

	public String getExpectedDuration() {
		return expectedDuration;
	}

	public void setExpectedDuration(String expectedDuration) {
		this.expectedDuration = expectedDuration;
	}

	public String getPayMode() {
		return payMode;
	}

	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}

	public String getOrgMode() {
		return orgMode;
	}

	public void setOrgMode(String orgMode) {
		this.orgMode = orgMode;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getMyStory() {
		return myStory;
	}

	public void setMyStory(String myStory) {
		this.myStory = myStory;
	}

	public List<GoDetail> getGoDetails() {
		return goDetails;
	}

	public void setGoDetails(List<GoDetail> goDetails) {
		this.goDetails = goDetails;
	}

	public List<GoTag> getGoTags() {
		return goTags;
	}

	public void setGoTags(List<GoTag> goTags) {
		this.goTags = goTags;
	}

	public String getExpectedStartTime() {
		return expectedStartTime;
	}

	public void setExpectedStartTime(String expectedStartTime) {
		this.expectedStartTime = expectedStartTime;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public List<GoStory> getGoStories() {
		return goStories;
	}

	public void setGoStories(List<GoStory> goStories) {
		this.goStories = goStories;
	}
	
	

}
