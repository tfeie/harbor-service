package com.the.harbor.dao.mapper.bo;

import java.sql.Timestamp;

public class HyGoJoin {
    private String orderId;

    private String userId;

    private String goId;

    private String goType;

    private String orderStatus;

    private Timestamp createDate;

    private Timestamp stsDate;

    private String helpValue;

    private String payOrderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getGoId() {
        return goId;
    }

    public void setGoId(String goId) {
        this.goId = goId == null ? null : goId.trim();
    }

    public String getGoType() {
        return goType;
    }

    public void setGoType(String goType) {
        this.goType = goType == null ? null : goType.trim();
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus == null ? null : orderStatus.trim();
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

    public String getHelpValue() {
        return helpValue;
    }

    public void setHelpValue(String helpValue) {
        this.helpValue = helpValue == null ? null : helpValue.trim();
    }

    public String getPayOrderId() {
        return payOrderId;
    }

    public void setPayOrderId(String payOrderId) {
        this.payOrderId = payOrderId == null ? null : payOrderId.trim();
    }
}