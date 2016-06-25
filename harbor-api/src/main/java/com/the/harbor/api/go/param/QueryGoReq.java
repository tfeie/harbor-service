package com.the.harbor.api.go.param;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.the.harbor.api.go.IGoSV;
import com.the.harbor.base.enumeration.hygo.GoType;
import com.the.harbor.base.validator.StringEnum;

public class QueryGoReq implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "活动分类为空", groups = { IGoSV.QueryGoes.class })
	@StringEnum(enumClazz = GoType.class, message = "活动分类传入不合规", groups = { IGoSV.QueryGoes.class })
	private String goType;

	// 活动标签
	private String goTag;

	// 搜索关键字
	private String searchKey;

	private Integer pageNo;

	private Integer pageSize;

	public String getGoTag() {
		return goTag;
	}

	public void setGoTag(String goTag) {
		this.goTag = goTag;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public String getGoType() {
		return goType;
	}

	public void setGoType(String goType) {
		this.goType = goType;
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
