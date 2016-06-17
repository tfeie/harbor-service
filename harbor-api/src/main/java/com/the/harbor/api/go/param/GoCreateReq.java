package com.the.harbor.api.go.param;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class GoCreateReq implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userId;

	private String goType;

	private String topic;

	private String inviteMembers;

	private Timestamp expectedStartTime;

	private String expectedDuration;

	private String payMode;

	private long fixedPrice;

	private String orgMode;

	private String location;

	private String myStory;

	// 活动明细
	private List<GoDetail> goDetails;

	// 获取标签
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

	public Timestamp getExpectedStartTime() {
		return expectedStartTime;
	}

	public void setExpectedStartTime(Timestamp expectedStartTime) {
		this.expectedStartTime = expectedStartTime;
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

	public long getFixedPrice() {
		return fixedPrice;
	}

	public void setFixedPrice(long fixedPrice) {
		this.fixedPrice = fixedPrice;
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

}
