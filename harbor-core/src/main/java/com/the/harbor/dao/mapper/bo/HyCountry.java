package com.the.harbor.dao.mapper.bo;

public class HyCountry {
    private String countryCode;

    private String countryName;

    private String countryRgb;

    private int sortId;

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode == null ? null : countryCode.trim();
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName == null ? null : countryName.trim();
    }

    public String getCountryRgb() {
        return countryRgb;
    }

    public void setCountryRgb(String countryRgb) {
        this.countryRgb = countryRgb == null ? null : countryRgb.trim();
    }

    public int getSortId() {
        return sortId;
    }

    public void setSortId(int sortId) {
        this.sortId = sortId;
    }
}