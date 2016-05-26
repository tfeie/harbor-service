package com.the.harbor.dao.mapper.bo;

import java.sql.Timestamp;

public class HyUser {
    private String userId;

    private String userType;

    private String hyId;

    private String enName;

    private String chName;

    private String sex;

    private String weixin;

    private String headIcon;

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

    private String isMember;

    private Timestamp effDate;

    private Timestamp expDate;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    public String getHyId() {
        return hyId;
    }

    public void setHyId(String hyId) {
        this.hyId = hyId == null ? null : hyId.trim();
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName == null ? null : enName.trim();
    }

    public String getChName() {
        return chName;
    }

    public void setChName(String chName) {
        this.chName = chName == null ? null : chName.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin == null ? null : weixin.trim();
    }

    public String getHeadIcon() {
        return headIcon;
    }

    public void setHeadIcon(String headIcon) {
        this.headIcon = headIcon == null ? null : headIcon.trim();
    }

    public String getAbroadCountry() {
        return abroadCountry;
    }

    public void setAbroadCountry(String abroadCountry) {
        this.abroadCountry = abroadCountry == null ? null : abroadCountry.trim();
    }

    public String getAbroadUniversity() {
        return abroadUniversity;
    }

    public void setAbroadUniversity(String abroadUniversity) {
        this.abroadUniversity = abroadUniversity == null ? null : abroadUniversity.trim();
    }

    public String getAtCountry() {
        return atCountry;
    }

    public void setAtCountry(String atCountry) {
        this.atCountry = atCountry == null ? null : atCountry.trim();
    }

    public String getAtProvince() {
        return atProvince;
    }

    public void setAtProvince(String atProvince) {
        this.atProvince = atProvince == null ? null : atProvince.trim();
    }

    public String getAtCity() {
        return atCity;
    }

    public void setAtCity(String atCity) {
        this.atCity = atCity == null ? null : atCity.trim();
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature == null ? null : signature.trim();
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone == null ? null : mobilePhone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus == null ? null : maritalStatus.trim();
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation == null ? null : constellation.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry == null ? null : industry.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
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
        this.userStatus = userStatus == null ? null : userStatus.trim();
    }

    public String getIdcardPhoto() {
        return idcardPhoto;
    }

    public void setIdcardPhoto(String idcardPhoto) {
        this.idcardPhoto = idcardPhoto == null ? null : idcardPhoto.trim();
    }

    public String getOverseasPhoto() {
        return overseasPhoto;
    }

    public void setOverseasPhoto(String overseasPhoto) {
        this.overseasPhoto = overseasPhoto == null ? null : overseasPhoto.trim();
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
        this.accessPermission = accessPermission == null ? null : accessPermission.trim();
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    public String getIsMember() {
        return isMember;
    }

    public void setIsMember(String isMember) {
        this.isMember = isMember == null ? null : isMember.trim();
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
}