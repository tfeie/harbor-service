package com.the.harbor.api.be.param;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class Be implements Serializable {

	private static final long serialVersionUID = 1L;

	private String beId;

	private String userId;

	private String status;

	private Timestamp createDate;

	private Timestamp invalidDate;

	private List<BeDetail> beDetails;

	private List<BeTag> beTags;

	// 发布用户信息
	private String abroadCountryName;

	private String abroadCountryRGB;

	private String atCityName;

	private String industryName;

	private String userStatusName;

	private String title;

	private String wxHeadimg;

	private String enName;

	// 创建时间时间差
	private String createTimeInterval;

	// 点赞数量
	private long dianzanCount;

	// 评论数量
	private long commentCount;

	// 打赏海贝数量
	private long giveHaibeiCount;

	// 首个文本内容摘要
	private String contentSummary;

	// 首个图片地址
	private String imageURL;

	// 是否发表了文本
	private boolean hastext;

	// 是否发表了图片
	private boolean hasimg;

	// 是否显示发表日期DD/MM
	private boolean showMMdd;

	// 发表日期的dd/mm
	private String mmdd;

	public String getBeId() {
		return beId;
	}

	public void setBeId(String beId) {
		this.beId = beId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public Timestamp getInvalidDate() {
		return invalidDate;
	}

	public void setInvalidDate(Timestamp invalidDate) {
		this.invalidDate = invalidDate;
	}

	public List<BeDetail> getBeDetails() {
		return beDetails;
	}

	public void setBeDetails(List<BeDetail> beDetails) {
		this.beDetails = beDetails;
	}

	public List<BeTag> getBeTags() {
		return beTags;
	}

	public void setBeTags(List<BeTag> beTags) {
		this.beTags = beTags;
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

	public String getCreateTimeInterval() {
		return createTimeInterval;
	}

	public void setCreateTimeInterval(String createTimeInterval) {
		this.createTimeInterval = createTimeInterval;
	}

	public long getDianzanCount() {
		return dianzanCount;
	}

	public void setDianzanCount(long dianzanCount) {
		this.dianzanCount = dianzanCount;
	}

	public long getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(long commentCount) {
		this.commentCount = commentCount;
	}

	public long getGiveHaibeiCount() {
		return giveHaibeiCount;
	}

	public void setGiveHaibeiCount(long giveHaibeiCount) {
		this.giveHaibeiCount = giveHaibeiCount;
	}

	public String getContentSummary() {
		return contentSummary;
	}

	public void setContentSummary(String contentSummary) {
		this.contentSummary = contentSummary;
	}

	public boolean isHastext() {
		return hastext;
	}

	public void setHastext(boolean hastext) {
		this.hastext = hastext;
	}

	public boolean isHasimg() {
		return hasimg;
	}

	public void setHasimg(boolean hasimg) {
		this.hasimg = hasimg;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public boolean isShowMMdd() {
		return showMMdd;
	}

	public void setShowMMdd(boolean showMMdd) {
		this.showMMdd = showMMdd;
	}

	public String getMmdd() {
		return mmdd;
	}

	public void setMmdd(String mmdd) {
		this.mmdd = mmdd;
	}

	public String getAbroadCountryRGB() {
		return abroadCountryRGB;
	}

	public void setAbroadCountryRGB(String abroadCountryRGB) {
		this.abroadCountryRGB = abroadCountryRGB;
	}

}
