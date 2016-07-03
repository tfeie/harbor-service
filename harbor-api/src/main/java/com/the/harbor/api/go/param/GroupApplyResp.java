package com.the.harbor.api.go.param;

import com.the.harbor.base.vo.Response;

/**
 * GROUP活动申请返回
 * 
 * @author zhangchao
 *
 */
public class GroupApplyResp extends Response {

	private static final long serialVersionUID = 1L;

	// 申请订单号
	private String orderId;

	// 是否需要支付
	private boolean needPay;

	// 支付金额 元
	private String payAmount;

	// 需要支付时候的支付订单流水号
	private String payOrderId;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public boolean isNeedPay() {
		return needPay;
	}

	public void setNeedPay(boolean needPay) {
		this.needPay = needPay;
	}

	public String getPayOrderId() {
		return payOrderId;
	}

	public void setPayOrderId(String payOrderId) {
		this.payOrderId = payOrderId;
	}

	public String getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}

}
