package com.the.harbor.api.go.param;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class GoJoin implements Serializable {

	private static final long serialVersionUID = 1L;

	private String orderId;

	private String userId;

	private String goId;

	private String goType;

	private String orderStatus;

	private Timestamp createDate;

	private Timestamp stsDate;

	private String helpValue;

	private String payOrderId;

	private int giveHb;

	// 发起时间的小时时间差
	private int diffHours;

	// 是否有帮助翻译
	private String helpValueName;

	// 活动标题
	private String topic;

	// 活动费用
	private long fixedPrice;

	// 预约记录状态
	private String orderStatusName;

	// 活动发起者
	private String publishUserId;

	// 组织形式
	private String orgModeName;

	// 多少人见过
	private int orderCount;

	// 以下是活动参与者的用户信息
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

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getGoId() {
		return goId;
	}

	public void setGoId(String goId) {
		this.goId = goId;
	}

	public String getGoType() {
		return goType;
	}

	public void setGoType(String goType) {
		this.goType = goType;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Timestamp getStsDate() {
		return stsDate;
	}

	public void setStsDate(Timestamp stsDate) {
		this.stsDate = stsDate;
	}

	public String getHelpValue() {
		return helpValue;
	}

	public void setHelpValue(String helpValue) {
		this.helpValue = helpValue;
	}

	public String getPayOrderId() {
		return payOrderId;
	}

	public void setPayOrderId(String payOrderId) {
		this.payOrderId = payOrderId;
	}

	public int getGiveHb() {
		return giveHb;
	}

	public void setGiveHb(int giveHb) {
		this.giveHb = giveHb;
	}

	public String getHelpValueName() {
		return helpValueName;
	}

	public void setHelpValueName(String helpValueName) {
		this.helpValueName = helpValueName;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public long getFixedPrice() {
		return fixedPrice;
	}

	public void setFixedPrice(long fixedPrice) {
		this.fixedPrice = fixedPrice;
	}

	public String getOrderStatusName() {
		return orderStatusName;
	}

	public void setOrderStatusName(String orderStatusName) {
		this.orderStatusName = orderStatusName;
	}

	public String getPublishUserId() {
		return publishUserId;
	}

	public void setPublishUserId(String publishUserId) {
		this.publishUserId = publishUserId;
	}

	public String getOrgModeName() {
		return orgModeName;
	}

	public void setOrgModeName(String orgModeName) {
		this.orgModeName = orgModeName;
	}

	public int getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}

	public String getAbroadCountryName() {
		return abroadCountryName;
	}

	public void setAbroadCountryName(String abroadCountryName) {
		this.abroadCountryName = abroadCountryName;
	}

	public String getAbroadCountryRGB() {
		return abroadCountryRGB;
	}

	public void setAbroadCountryRGB(String abroadCountryRGB) {
		this.abroadCountryRGB = abroadCountryRGB;
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

	public String getHomePageBg() {
		return homePageBg;
	}

	public void setHomePageBg(String homePageBg) {
		this.homePageBg = homePageBg;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public int getDiffHours() {
		return diffHours;
	}

	public void setDiffHours(int diffHours) {
		this.diffHours = diffHours;
	}

}
