package com.the.harbor.dao.mapper.bo;

import java.sql.Timestamp;

public class HyNotify {
    private String notifyId;

    private String notifyType;

    private String senderType;

    private String senderId;

    private String accepterType;

    private String accepterId;

    private String title;

    private String content;

    private String status;

    private String link;

    private Timestamp createDate;

    private Timestamp stsDate;

    public String getNotifyId() {
        return notifyId;
    }

    public void setNotifyId(String notifyId) {
        this.notifyId = notifyId == null ? null : notifyId.trim();
    }

    public String getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(String notifyType) {
        this.notifyType = notifyType == null ? null : notifyType.trim();
    }

    public String getSenderType() {
        return senderType;
    }

    public void setSenderType(String senderType) {
        this.senderType = senderType == null ? null : senderType.trim();
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId == null ? null : senderId.trim();
    }

    public String getAccepterType() {
        return accepterType;
    }

    public void setAccepterType(String accepterType) {
        this.accepterType = accepterType == null ? null : accepterType.trim();
    }

    public String getAccepterId() {
        return accepterId;
    }

    public void setAccepterId(String accepterId) {
        this.accepterId = accepterId == null ? null : accepterId.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link == null ? null : link.trim();
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