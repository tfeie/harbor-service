package com.the.harbor.api.go.param;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.the.harbor.api.go.IGoSV;

/**
 * 活动预约者确认活动结束
 * 
 * @author zhangchao
 *
 */
public class GoOrderFinishReq implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "活动预约流水为空", groups = { IGoSV.FinishGoOrder.class })
	private String goOrderId;

	@NotNull(message = "活动预约者为空", groups = { IGoSV.FinishGoOrder.class })
	private String userId;

	public String getGoOrderId() {
		return goOrderId;
	}

	public void setGoOrderId(String goOrderId) {
		this.goOrderId = goOrderId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
