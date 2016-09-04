package com.the.harbor.api.go.param;

import java.util.List;

import com.the.harbor.base.vo.Response;

/**
 * 查询预约信息
 * 
 * @author zhangchao
 *
 */
public class GoOrderQueryResp extends Response {

	private static final long serialVersionUID = 1L;

	private GoOrder goOrder;

	private List<GoOrder> goOrders;

	public GoOrder getGoOrder() {
		return goOrder;
	}

	public void setGoOrder(GoOrder goOrder) {
		this.goOrder = goOrder;
	}

	public List<GoOrder> getGoOrders() {
		return goOrders;
	}

	public void setGoOrders(List<GoOrder> goOrders) {
		this.goOrders = goOrders;
	}

}
