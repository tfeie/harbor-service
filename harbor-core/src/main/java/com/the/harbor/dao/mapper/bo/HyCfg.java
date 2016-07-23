package com.the.harbor.dao.mapper.bo;

public class HyCfg {
    private String cfgKey;

    private String cfgValue;

    private String cfgDesc;

    public String getCfgKey() {
        return cfgKey;
    }

    public void setCfgKey(String cfgKey) {
        this.cfgKey = cfgKey == null ? null : cfgKey.trim();
    }

    public String getCfgValue() {
        return cfgValue;
    }

    public void setCfgValue(String cfgValue) {
        this.cfgValue = cfgValue == null ? null : cfgValue.trim();
    }

    public String getCfgDesc() {
        return cfgDesc;
    }

    public void setCfgDesc(String cfgDesc) {
        this.cfgDesc = cfgDesc == null ? null : cfgDesc.trim();
    }
}