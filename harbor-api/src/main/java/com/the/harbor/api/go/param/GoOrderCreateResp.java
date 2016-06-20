package com.the.harbor.api.go.param;

import com.the.harbor.base.vo.Response;

/**
 * 预约订单流水
 * 
 * @author zhangchao
 *
 */
public class GoOrderCreateResp extends Response {

	private static final long serialVersionUID = 1L;

	/**
	 * 预约订单流失
	 */
	private String orderId;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

}
