package com.the.harbor.dao.mapper.bo;

import java.sql.Timestamp;

public class HyGo {
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

    private String topFlag;

    private Timestamp topDate;

    private String hideFlag;

    private String offlineProvince;

    private String offlineCity;

    private String onlineNet;

    private String onlinePic;

    public String getGoId() {
        return goId;
    }

    public void setGoId(String goId) {
        this.goId = goId == null ? null : goId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getGoType() {
        return goType;
    }

    public void setGoType(String goType) {
        this.goType = goType == null ? null : goType.trim();
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic == null ? null : topic.trim();
    }

    public String getInviteMembers() {
        return inviteMembers;
    }

    public void setInviteMembers(String inviteMembers) {
        this.inviteMembers = inviteMembers == null ? null : inviteMembers.trim();
    }

    public String getExpectedStartTime() {
        return expectedStartTime;
    }

    public void setExpectedStartTime(String expectedStartTime) {
        this.expectedStartTime = expectedStartTime == null ? null : expectedStartTime.trim();
    }

    public String getExpectedDuration() {
        return expectedDuration;
    }

    public void setExpectedDuration(String expectedDuration) {
        this.expectedDuration = expectedDuration == null ? null : expectedDuration.trim();
    }

    public String getPayMode() {
        return payMode;
    }

    public void setPayMode(String payMode) {
        this.payMode = payMode == null ? null : payMode.trim();
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
        this.orgMode = orgMode == null ? null : orgMode.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getMyStory() {
        return myStory;
    }

    public void setMyStory(String myStory) {
        this.myStory = myStory == null ? null : myStory.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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

    public String getTopFlag() {
        return topFlag;
    }

    public void setTopFlag(String topFlag) {
        this.topFlag = topFlag == null ? null : topFlag.trim();
    }

    public Timestamp getTopDate() {
        return topDate;
    }

    public void setTopDate(Timestamp topDate) {
        this.topDate = topDate;
    }

    public String getHideFlag() {
        return hideFlag;
    }

    public void setHideFlag(String hideFlag) {
        this.hideFlag = hideFlag == null ? null : hideFlag.trim();
    }

    public String getOfflineProvince() {
        return offlineProvince;
    }

    public void setOfflineProvince(String offlineProvince) {
        this.offlineProvince = offlineProvince == null ? null : offlineProvince.trim();
    }

    public String getOfflineCity() {
        return offlineCity;
    }

    public void setOfflineCity(String offlineCity) {
        this.offlineCity = offlineCity == null ? null : offlineCity.trim();
    }

    public String getOnlineNet() {
        return onlineNet;
    }

    public void setOnlineNet(String onlineNet) {
        this.onlineNet = onlineNet == null ? null : onlineNet.trim();
    }

    public String getOnlinePic() {
        return onlinePic;
    }

    public void setOnlinePic(String onlinePic) {
        this.onlinePic = onlinePic == null ? null : onlinePic.trim();
    }
}