package com.the.harbor.api.go.param;

import com.the.harbor.base.vo.Response;

public class CreateGoPaymentOrderResp extends Response {

	private static final long serialVersionUID = 1L;

	/**
	 * 支付交易订单 32位
	 */
	private String payOrderId;

	public String getPayOrderId() {
		return payOrderId;
	}

	public void setPayOrderId(String payOrderId) {
		this.payOrderId = payOrderId;
	}
}
