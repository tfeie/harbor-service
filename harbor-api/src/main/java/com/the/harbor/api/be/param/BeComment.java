package com.the.harbor.api.be.param;

import java.io.Serializable;
import java.sql.Timestamp;

public class BeComment implements Serializable {

	private static final long serialVersionUID = 1L;

	private String commentId;

	private String beId;

	private String userId;

	private String content;

	private Timestamp createDate;

	private String parentCommentId;

	private String parentUserId;
	
	private String status;

	// 发布时间差描述
	private String createTimeInteval;

	// 以下是发表人的信息
	private String userStatus;

	private String userStatusName;

	private String abroadCountryName;

	private String abroadCountryRGB;

	private String wxHeadimg;

	private String enName;

	// 以下是上级发布人的信息
	private String puserStatus;

	
	private String puserStatusName;

	private String pabroadCountryName;

	private String pabroadCountryRGB;

	private String pwxHeadimg;

	private String penName;

	// 是否是回复的评论
	private boolean isreply;

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId == null ? null : commentId.trim();
	}

	public String getBeId() {
		return beId;
	}

	public void setBeId(String beId) {
		this.beId = beId == null ? null : beId.trim();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getParentCommentId() {
		return parentCommentId;
	}

	public void setParentCommentId(String parentCommentId) {
		this.parentCommentId = parentCommentId == null ? null : parentCommentId.trim();
	}

	public String getParentUserId() {
		return parentUserId;
	}

	public void setParentUserId(String parentUserId) {
		this.parentUserId = parentUserId == null ? null : parentUserId.trim();
	}

	public String getCreateTimeInteval() {
		return createTimeInteval;
	}

	public void setCreateTimeInteval(String createTimeInteval) {
		this.createTimeInteval = createTimeInteval;
	}

	public String getUserStatusName() {
		return userStatusName;
	}

	public void setUserStatusName(String userStatusName) {
		this.userStatusName = userStatusName;
	}

	public String getAbroadCountryName() {
		return abroadCountryName;
	}

	public void setAbroadCountryName(String abroadCountryName) {
		this.abroadCountryName = abroadCountryName;
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

	public String getPuserStatusName() {
		return puserStatusName;
	}

	public void setPuserStatusName(String puserStatusName) {
		this.puserStatusName = puserStatusName;
	}

	public String getPabroadCountryName() {
		return pabroadCountryName;
	}

	public void setPabroadCountryName(String pabroadCountryName) {
		this.pabroadCountryName = pabroadCountryName;
	}

	public String getPwxHeadimg() {
		return pwxHeadimg;
	}

	public void setPwxHeadimg(String pwxHeadimg) {
		this.pwxHeadimg = pwxHeadimg;
	}

	public String getPenName() {
		return penName;
	}

	public void setPenName(String penName) {
		this.penName = penName;
	}

	public boolean isIsreply() {
		return isreply;
	}

	public void setIsreply(boolean isreply) {
		this.isreply = isreply;
	}

	public String getAbroadCountryRGB() {
		return abroadCountryRGB;
	}

	public void setAbroadCountryRGB(String abroadCountryRGB) {
		this.abroadCountryRGB = abroadCountryRGB;
	}

	public String getPabroadCountryRGB() {
		return pabroadCountryRGB;
	}

	public void setPabroadCountryRGB(String pabroadCountryRGB) {
		this.pabroadCountryRGB = pabroadCountryRGB;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getPuserStatus() {
		return puserStatus;
	}

	public void setPuserStatus(String puserStatus) {
		this.puserStatus = puserStatus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	
}
