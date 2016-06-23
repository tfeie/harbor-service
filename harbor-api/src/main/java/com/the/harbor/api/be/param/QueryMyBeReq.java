package com.the.harbor.api.be.param;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.the.harbor.api.be.IBeSV;

public class QueryMyBeReq implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "用户为空", groups = { IBeSV.QueryMyBe.class })
	private String userId;

	@NotNull(message = "微信识别码为空", groups = { IBeSV.QueryMyBe.class })
	private String openId;

	private Integer pageNo;

	private Integer pageSize;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
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
