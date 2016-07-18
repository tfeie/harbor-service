package com.the.harbor.api.be.param;

import java.io.Serializable;

public class BeQueryReq implements Serializable {

	private static final long serialVersionUID = 1L;

	// 聚合标签
	private String polyTagId;

	// 实际标签
	private String tagId;

	// 搜索关键字
	private String searchKey;

	private Integer pageNo;

	private Integer pageSize;



	public String getPolyTagId() {
		return polyTagId;
	}

	public void setPolyTagId(String polyTagId) {
		this.polyTagId = polyTagId;
	}

	public String getTagId() {
		return tagId;
	}

	public void setTagId(String tagId) {
		this.tagId = tagId;
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
