package com.the.harbor.dao.mapper.bo;

import java.sql.Timestamp;

public class HyBeLikes {
    private String likesId;

    private String beId;

    private String userId;

    private Timestamp createDate;

    private String dianzanUserId;

    public String getLikesId() {
        return likesId;
    }

    public void setLikesId(String likesId) {
        this.likesId = likesId == null ? null : likesId.trim();
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

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public String getDianzanUserId() {
        return dianzanUserId;
    }

    public void setDianzanUserId(String dianzanUserId) {
        this.dianzanUserId = dianzanUserId == null ? null : dianzanUserId.trim();
    }
}