package com.the.harbor.api.go.param;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.the.harbor.api.go.IGoSV;

public class UpdateGoOrderPayReq implements Serializable {

	private static final long serialVersionUID = 1L;
	@NotNull(message = "活动预约流水为空", groups = { IGoSV.UpdateGoOrderPay.class })
	private String goOrderId;

	@NotNull(message = "活动支付流水为空", groups = { IGoSV.UpdateGoOrderPay.class })
	private String payOrderId;

	@NotNull(message = "活动支付状态为空", groups = { IGoSV.UpdateGoOrderPay.class })
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
