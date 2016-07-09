package com.the.harbor.dao.mapper.bo;

public class HyUserHbAssets {
    private String assetsId;

    private String userId;

    private long totalBeishang;

    private long totalJiangli;

    private long totalDashang;

    private long totalGongyi;

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

    public long getTotalBeishang() {
        return totalBeishang;
    }

    public void setTotalBeishang(long totalBeishang) {
        this.totalBeishang = totalBeishang;
    }

    public long getTotalJiangli() {
        return totalJiangli;
    }

    public void setTotalJiangli(long totalJiangli) {
        this.totalJiangli = totalJiangli;
    }

    public long getTotalDashang() {
        return totalDashang;
    }

    public void setTotalDashang(long totalDashang) {
        this.totalDashang = totalDashang;
    }

    public long getTotalGongyi() {
        return totalGongyi;
    }

    public void setTotalGongyi(long totalGongyi) {
        this.totalGongyi = totalGongyi;
    }
}