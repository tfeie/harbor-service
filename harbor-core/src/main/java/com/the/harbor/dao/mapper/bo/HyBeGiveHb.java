package com.the.harbor.dao.mapper.bo;

import java.sql.Timestamp;

public class HyBeGiveHb {
    private String giveId;

    private String beId;

    private String busiType;

    private String payUserId;

    private String targetUserId;

    private long amount;

    private Timestamp tradeDate;

    public String getGiveId() {
        return giveId;
    }

    public void setGiveId(String giveId) {
        this.giveId = giveId == null ? null : giveId.trim();
    }

    public String getBeId() {
        return beId;
    }

    public void setBeId(String beId) {
        this.beId = beId == null ? null : beId.trim();
    }

    public String getBusiType() {
        return busiType;
    }

    public void setBusiType(String busiType) {
        this.busiType = busiType == null ? null : busiType.trim();
    }

    public String getPayUserId() {
        return payUserId;
    }

    public void setPayUserId(String payUserId) {
        this.payUserId = payUserId == null ? null : payUserId.trim();
    }

    public String getTargetUserId() {
        return targetUserId;
    }

    public void setTargetUserId(String targetUserId) {
        this.targetUserId = targetUserId == null ? null : targetUserId.trim();
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public Timestamp getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Timestamp tradeDate) {
        this.tradeDate = tradeDate;
    }
}