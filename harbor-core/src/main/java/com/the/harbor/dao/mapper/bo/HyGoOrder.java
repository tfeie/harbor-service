package com.the.harbor.dao.mapper.bo;

import java.sql.Timestamp;

public class HyGoOrder {
    private String orderId;

    private String userId;

    private String goId;

    private String goType;

    private String orderStatus;

    private String questions;

    private String selfIntro;

    private String payOrderId;

    private Timestamp confirmDate;

    private String expectedTime1;

    private String expectedTime2;

    private String expectedLocation1;

    private String expectedLocation2;

    private String confirmTime;

    private String confirmLocation;

    private Timestamp createDate;

    private Timestamp payStsDate;

    private Timestamp confirmStsDate;

    private Timestamp stsDate;

    private String helpValue;

    private int giveHb;

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

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions == null ? null : questions.trim();
    }

    public String getSelfIntro() {
        return selfIntro;
    }

    public void setSelfIntro(String selfIntro) {
        this.selfIntro = selfIntro == null ? null : selfIntro.trim();
    }

    public String getPayOrderId() {
        return payOrderId;
    }

    public void setPayOrderId(String payOrderId) {
        this.payOrderId = payOrderId == null ? null : payOrderId.trim();
    }

    public Timestamp getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(Timestamp confirmDate) {
        this.confirmDate = confirmDate;
    }

    public String getExpectedTime1() {
        return expectedTime1;
    }

    public void setExpectedTime1(String expectedTime1) {
        this.expectedTime1 = expectedTime1 == null ? null : expectedTime1.trim();
    }

    public String getExpectedTime2() {
        return expectedTime2;
    }

    public void setExpectedTime2(String expectedTime2) {
        this.expectedTime2 = expectedTime2 == null ? null : expectedTime2.trim();
    }

    public String getExpectedLocation1() {
        return expectedLocation1;
    }

    public void setExpectedLocation1(String expectedLocation1) {
        this.expectedLocation1 = expectedLocation1 == null ? null : expectedLocation1.trim();
    }

    public String getExpectedLocation2() {
        return expectedLocation2;
    }

    public void setExpectedLocation2(String expectedLocation2) {
        this.expectedLocation2 = expectedLocation2 == null ? null : expectedLocation2.trim();
    }

    public String getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(String confirmTime) {
        this.confirmTime = confirmTime == null ? null : confirmTime.trim();
    }

    public String getConfirmLocation() {
        return confirmLocation;
    }

    public void setConfirmLocation(String confirmLocation) {
        this.confirmLocation = confirmLocation == null ? null : confirmLocation.trim();
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getPayStsDate() {
        return payStsDate;
    }

    public void setPayStsDate(Timestamp payStsDate) {
        this.payStsDate = payStsDate;
    }

    public Timestamp getConfirmStsDate() {
        return confirmStsDate;
    }

    public void setConfirmStsDate(Timestamp confirmStsDate) {
        this.confirmStsDate = confirmStsDate;
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

    public int getGiveHb() {
        return giveHb;
    }

    public void setGiveHb(int giveHb) {
        this.giveHb = giveHb;
    }
}