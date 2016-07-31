package com.the.harbor.api.go.param;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Go implements Serializable {

	private static final long serialVersionUID = 1L;

	private String goId;

	private String userId;

	private String goType;

	private String topic;

	private String inviteMembers;

	private String expectedStartTime;

	private String expectedDuration;

	private String payMode;

	private long fixedPrice;

	private String orgMode;

	private String location;

	private String myStory;

	private String status;

	private Timestamp createDate;

	private Timestamp endDate;

	// 活动类型
	private String goTypeName;

	// 内容摘要
	private String contentSummary;

	// 固定金额元
	private String fixPriceYuan;

	// 支付方式类型
	private String payModeName;

	// 组织形式
	private String orgModeName;

	// 活动明细
	private List<GoDetail> goDetails;

	// 活动标签
	private List<GoTag> goTags;

	// 发布用户信息
	private String abroadCountryName;

	private String abroadCountryRGB;

	private String atCityName;

	private String industryName;

	private String userStatus;

	private String userStatusName;

	private String title;

	private String wxHeadimg;

	private String enName;

	private String homePageBg;

	public String employmentInfo;

	public String getEmploymentInfo() {
		List<String> list = new ArrayList<String>();
		if (this.getIndustryName() != null && !"".equals(this.getIndustryName().trim())) {
			list.add(this.getIndustryName());
		}
		if (this.getTitle() != null && !"".equals(this.getTitle().trim())) {
			list.add(this.getTitle());
		}
		if (this.getAtCityName() != null && !"".equals(this.getAtCityName().trim())) {
			list.add(this.getAtCityName());
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i)).append("/");
		}
		if (sb.toString().length() == 0) {
			employmentInfo = "";
		} else {
			employmentInfo = sb.toString().substring(0, sb.toString().length() - 1);
		}
		return employmentInfo;
	}

	public void setEmploymentInfo(String employmentInfo) {
		this.employmentInfo = employmentInfo;
	}

	// 浏览总数
	private long viewCount;

	// 收藏记录总数
	private long favorCount;

	// GROUP参加人数
	private long joinCount;

	// 创建时间格式显示
	private String createTimeStr;

	// 创建时间时间差
	private String createTimeInterval;

	public String getGoId() {
		return goId;
	}

	public void setGoId(String goId) {
		this.goId = goId;
	}

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

	public String getExpectedStartTime() {
		return expectedStartTime;
	}

	public void setExpectedStartTime(String expectedStartTime) {
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	public String getGoTypeName() {
		return goTypeName;
	}

	public void setGoTypeName(String goTypeName) {
		this.goTypeName = goTypeName;
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

	public String getPayModeName() {
		return payModeName;
	}

	public void setPayModeName(String payModeName) {
		this.payModeName = payModeName;
	}

	public String getOrgModeName() {
		return orgModeName;
	}

	public void setOrgModeName(String orgModeName) {
		this.orgModeName = orgModeName;
	}

	public long getViewCount() {
		return viewCount;
	}

	public void setViewCount(long viewCount) {
		this.viewCount = viewCount;
	}

	public long getFavorCount() {
		return favorCount;
	}

	public void setFavorCount(long favorCount) {
		this.favorCount = favorCount;
	}

	public long getJoinCount() {
		return joinCount;
	}

	public void setJoinCount(long joinCount) {
		this.joinCount = joinCount;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public String getCreateTimeInterval() {
		return createTimeInterval;
	}

	public void setCreateTimeInterval(String createTimeInterval) {
		this.createTimeInterval = createTimeInterval;
	}

	public String getAbroadCountryName() {
		return abroadCountryName;
	}

	public void setAbroadCountryName(String abroadCountryName) {
		this.abroadCountryName = abroadCountryName;
	}

	public String getAtCityName() {
		return atCityName;
	}

	public void setAtCityName(String atCityName) {
		this.atCityName = atCityName;
	}

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	public String getUserStatusName() {
		return userStatusName;
	}

	public void setUserStatusName(String userStatusName) {
		this.userStatusName = userStatusName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWxHeadimg() {
		return wxHeadimg;
	}

	public void setWxHeadimg(String wxHeadimg) {
		this.wxHeadimg = wxHeadimg;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getContentSummary() {
		return contentSummary;
	}

	public void setContentSummary(String contentSummary) {
		this.contentSummary = contentSummary;
	}

	public String getFixPriceYuan() {
		return fixPriceYuan;
	}

	public void setFixPriceYuan(String fixPriceYuan) {
		this.fixPriceYuan = fixPriceYuan;
	}

	public String getHomePageBg() {
		return homePageBg;
	}

	public void setHomePageBg(String homePageBg) {
		this.homePageBg = homePageBg;
	}

	public String getAbroadCountryRGB() {
		return abroadCountryRGB;
	}

	public void setAbroadCountryRGB(String abroadCountryRGB) {
		this.abroadCountryRGB = abroadCountryRGB;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

}
