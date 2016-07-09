package com.the.harbor.dao.mapper.bo;

import java.sql.Timestamp;

public class HyUserAssets {
    private String assetsId;

    private String userId;

    private String assetsType;

    private String assetsUnit;

    private long balance;

    private String assetsStatus;

    private Timestamp createDate;

    private Timestamp chgDate;

    private String chgDesc;

    private long totalExpenditure;

    private long totalIncome;

    public String getAssetsId() {
        return assetsId;
    }

    public void setAssetsId(String assetsId) {
        this.assetsId = assetsId == null ? null : assetsId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getAssetsType() {
        return assetsType;
    }

    public void setAssetsType(String assetsType) {
        this.assetsType = assetsType == null ? null : assetsType.trim();
    }

    public String getAssetsUnit() {
        return assetsUnit;
    }

    public void setAssetsUnit(String assetsUnit) {
        this.assetsUnit = assetsUnit == null ? null : assetsUnit.trim();
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public String getAssetsStatus() {
        return assetsStatus;
    }

    public void setAssetsStatus(String assetsStatus) {
        this.assetsStatus = assetsStatus == null ? null : assetsStatus.trim();
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getChgDate() {
        return chgDate;
    }

    public void setChgDate(Timestamp chgDate) {
        this.chgDate = chgDate;
    }

    public String getChgDesc() {
        return chgDesc;
    }

    public void setChgDesc(String chgDesc) {
        this.chgDesc = chgDesc == null ? null : chgDesc.trim();
    }

    public long getTotalExpenditure() {
        return totalExpenditure;
    }

    public void setTotalExpenditure(long totalExpenditure) {
        this.totalExpenditure = totalExpenditure;
    }

    public long getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(long totalIncome) {
        this.totalIncome = totalIncome;
    }
}