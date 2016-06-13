package com.the.harbor.api.pay.param;

import com.the.harbor.base.vo.Response;

public class CreatePaymentOrderResp extends Response {

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
