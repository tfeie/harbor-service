package com.the.harbor.api.go.param;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.the.harbor.api.go.IGoSV;

public class UpdateGoJoinPayReq implements Serializable {

	private static final long serialVersionUID = 1L;
	@NotNull(message = "活动参加流水为空", groups = { IGoSV.UpdateGoJoinPay.class })
	private String goOrderId;

	@NotNull(message = "活动支付流水为空", groups = { IGoSV.UpdateGoJoinPay.class })
	private String payOrderId;

	@NotNull(message = "活动支付状态为空", groups = { IGoSV.UpdateGoJoinPay.class })
	private String payStatus;

	public String getGoOrderId() {
		return goOrderId;
	}

	public void setGoOrderId(String goOrderId) {
		this.goOrderId = goOrderId;
	}

	public String getPayOrderId() {
		return payOrderId;
	}

	public void setPayOrderId(String payOrderId) {
		this.payOrderId = payOrderId;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

}
