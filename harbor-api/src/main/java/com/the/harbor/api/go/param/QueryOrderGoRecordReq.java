package com.the.harbor.api.go.param;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.the.harbor.api.go.IGoSV;

/**
 * 查询一个活动的所有预约记录
 * 
 * @author zhangchao
 *
 */
public class QueryOrderGoRecordReq implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "活动类型为空", groups = { IGoSV.QueryOrderGoRecords.class })
	private String goType;

	@NotNull(message = "活动标识为空", groups = { IGoSV.QueryOrderGoRecords.class })
	private String goId;

	public String getGoType() {
		return goType;
	}

	public void setGoType(String goType) {
		this.goType = goType;
	}

	public String getGoId() {
		return goId;
	}

	public void setGoId(String goId) {
		this.goId = goId;
	}

}
