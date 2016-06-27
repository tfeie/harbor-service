package com.the.harbor.dao.mapper.bo;

import java.sql.Timestamp;

public class HyUserFans {
    private String fansId;

    private String userId;

    private String fansUserId;

    private String status;

    private Timestamp createDate;

    private Timestamp stsChgDate;

    public String getFansId() {
        return fansId;
    }

    public void setFansId(String fansId) {
        this.fansId = fansId == null ? null : fansId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getFansUserId() {
        return fansUserId;
    }

    public void setFansUserId(String fansUserId) {
        this.fansUserId = fansUserId == null ? null : fansUserId.trim();
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

    public Timestamp getStsChgDate() {
        return stsChgDate;
    }

    public void setStsChgDate(Timestamp stsChgDate) {
        this.stsChgDate = stsChgDate;
    }
}