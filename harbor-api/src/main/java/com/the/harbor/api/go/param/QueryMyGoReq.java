package com.the.harbor.api.go.param;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.the.harbor.api.go.IGoSV;
import com.the.harbor.base.enumeration.hygo.GoType;
import com.the.harbor.base.validator.StringEnum;

public class QueryMyGoReq implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "用户为空", groups = { IGoSV.QueryMyGoes.class })
	private String userId;

	@NotNull(message = "活动分类为空", groups = { IGoSV.QueryMyGoes.class })
	@StringEnum(enumClazz = GoType.class, message = "活动分类传入不合规", groups = { IGoSV.QueryMyGoes.class })
	private String goType;
	
	//是否显示查询已经隐藏的
	private boolean queryhide;

	private Integer pageNo;

	private Integer pageSize;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public boolean isQueryhide() {
		return queryhide;
	}

	public void setQueryhide(boolean queryhide) {
		this.queryhide = queryhide;
	}
	
	

}
