package com.the.harbor.dao.mapper.bo;

import java.sql.Timestamp;

public class HyUserFriend {
    private String recordId;

    private String userId;

    private String friendId;

    private String status;

    private String applyMq;

    private Timestamp createDate;

    private Timestamp stsDate;

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId == null ? null : recordId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId == null ? null : friendId.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getApplyMq() {
        return applyMq;
    }

    public void setApplyMq(String applyMq) {
        this.applyMq = applyMq == null ? null : applyMq.trim();
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
}