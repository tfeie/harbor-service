package com.the.harbor.api.go.param;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.the.harbor.api.go.IGoSV;

/**
 * 获取预约记录
 * 
 * @author zhangchao
 *
 */
public class GoOrderQueryReq implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "活动预约流水为空", groups = { IGoSV.QueryGoOrder.class })
	private String goOrderId;

	public String getGoOrderId() {
		return goOrderId;
	}

	public void setGoOrderId(String goOrderId) {
		this.goOrderId = goOrderId;
	}

}
