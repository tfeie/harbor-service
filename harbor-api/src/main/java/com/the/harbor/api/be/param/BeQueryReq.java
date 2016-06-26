package com.the.harbor.api.be.param;

import java.io.Serializable;

public class BeQueryReq implements Serializable {

	private static final long serialVersionUID = 1L;

	// BE标签
	private String beTag;

	// 搜索关键字
	private String searchKey;

	private Integer pageNo;

	private Integer pageSize;

	public String getBeTag() {
		return beTag;
	}

	public void setBeTag(String beTag) {
		this.beTag = beTag;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

}
