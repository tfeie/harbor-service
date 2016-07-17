package com.the.harbor.dao.mapper.bo;

public class HyTags {
    private String tagId;

    private String tagType;

    private String tagName;

    private int sortId;

    private String status;

    private String scopeType;

    private String tagCat;

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId == null ? null : tagId.trim();
    }

    public String getTagType() {
        return tagType;
    }

    public void setTagType(String tagType) {
        this.tagType = tagType == null ? null : tagType.trim();
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName == null ? null : tagName.trim();
    }

    public int getSortId() {
        return sortId;
    }

    public void setSortId(int sortId) {
        this.sortId = sortId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getScopeType() {
        return scopeType;
    }

    public void setScopeType(String scopeType) {
        this.scopeType = scopeType == null ? null : scopeType.trim();
    }

    public String getTagCat() {
        return tagCat;
    }

    public void setTagCat(String tagCat) {
        this.tagCat = tagCat == null ? null : tagCat.trim();
    }
}