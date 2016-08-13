package com.the.harbor.dao.mapper.bo;

import java.sql.Timestamp;

public class HyBe {
    private String beId;

    private String userId;

    private String status;

    private Timestamp createDate;

    private Timestamp invalidDate;

    private String topFlag;

    private Timestamp topDate;

    private String hideFlag;

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

    public Timestamp getInvalidDate() {
        return invalidDate;
    }

    public void setInvalidDate(Timestamp invalidDate) {
        this.invalidDate = invalidDate;
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
}