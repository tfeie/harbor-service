package com.the.harbor.api.user.param;

import java.io.Serializable;
import java.sql.Timestamp;

public class UserInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userId;

	private String userType;

	private String hyId;

	private String enName;

	private String chName;

	private String sex;

	private String wxOpenid;

	private String wxHeadimg;

	private String abroadCountry;

	private String abroadUniversity;

	private String atCountry;

	private String atProvince;

	private String atCity;

	private String signature;

	private String mobilePhone;

	private String email;

	private String maritalStatus;

	private String constellation;

	private String company;

	private String industry;

	private String title;

	private Timestamp regDate;

	private String userStatus;

	private String idcardPhoto;

	private String overseasPhoto;

	private Timestamp submitCertDate;

	private Timestamp certificationDate;

	private String accessPermission;

	private Timestamp updateDate;

	private String memberLevel;

	private Timestamp effDate;

	private Timestamp expDate;

	private String wxNickname;

	private String certRemark;

	private String homePageBg;

	private String authSts;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getHyId() {
		return hyId;
	}

	public void setHyId(String hyId) {
		this.hyId = hyId;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getChName() {
		return chName;
	}

	public void setChName(String chName) {
		this.chName = chName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getWxOpenid() {
		return wxOpenid;
	}

	public void setWxOpenid(String wxOpenid) {
		this.wxOpenid = wxOpenid;
	}

	public String getWxHeadimg() {
		return wxHeadimg;
	}

	public void setWxHeadimg(String wxHeadimg) {
		this.wxHeadimg = wxHeadimg;
	}

	public String getAbroadCountry() {
		return abroadCountry;
	}

	public void setAbroadCountry(String abroadCountry) {
		this.abroadCountry = abroadCountry;
	}

	public String getAbroadUniversity() {
		return abroadUniversity;
	}

	public void setAbroadUniversity(String abroadUniversity) {
		this.abroadUniversity = abroadUniversity;
	}

	public String getAtCountry() {
		return atCountry;
	}

	public void setAtCountry(String atCountry) {
		this.atCountry = atCountry;
	}

	public String getAtProvince() {
		return atProvince;
	}

	public void setAtProvince(String atProvince) {
		this.atProvince = atProvince;
	}

	public String getAtCity() {
		return atCity;
	}

	public void setAtCity(String atCity) {
		this.atCity = atCity;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getConstellation() {
		return constellation;
	}

	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getIdcardPhoto() {
		return idcardPhoto;
	}

	public void setIdcardPhoto(String idcardPhoto) {
		this.idcardPhoto = idcardPhoto;
	}

	public String getOverseasPhoto() {
		return overseasPhoto;
	}

	public void setOverseasPhoto(String overseasPhoto) {
		this.overseasPhoto = overseasPhoto;
	}

	public Timestamp getSubmitCertDate() {
		return submitCertDate;
	}

	public void setSubmitCertDate(Timestamp submitCertDate) {
		this.submitCertDate = submitCertDate;
	}

	public Timestamp getCertificationDate() {
		return certificationDate;
	}

	public void setCertificationDate(Timestamp certificationDate) {
		this.certificationDate = certificationDate;
	}

	public String getAccessPermission() {
		return accessPermission;
	}

	public void setAccessPermission(String accessPermission) {
		this.accessPermission = accessPermission;
	}

	public Timestamp getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	public String getMemberLevel() {
		return memberLevel;
	}

	public void setMemberLevel(String memberLevel) {
		this.memberLevel = memberLevel;
	}

	public Timestamp getEffDate() {
		return effDate;
	}

	public void setEffDate(Timestamp effDate) {
		this.effDate = effDate;
	}

	public Timestamp getExpDate() {
		return expDate;
	}

	public void setExpDate(Timestamp expDate) {
		this.expDate = expDate;
	}

	public String getWxNickname() {
		return wxNickname;
	}

	public void setWxNickname(String wxNickname) {
		this.wxNickname = wxNickname;
	}

	public String getCertRemark() {
		return certRemark;
	}

	public void setCertRemark(String certRemark) {
		this.certRemark = certRemark;
	}

	public String getHomePageBg() {
		return homePageBg;
	}

	public void setHomePageBg(String homePageBg) {
		this.homePageBg = homePageBg;
	}

	public String getAuthSts() {
		return authSts;
	}

	public void setAuthSts(String authSts) {
		this.authSts = authSts;
	}

}
