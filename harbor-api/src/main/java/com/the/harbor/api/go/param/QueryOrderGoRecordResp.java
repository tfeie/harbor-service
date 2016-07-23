package com.the.harbor.api.go.param;

import java.util.List;

import com.the.harbor.base.vo.Response;

public class QueryOrderGoRecordResp extends Response {

	private static final long serialVersionUID = 1L;

	// ONO活动预约信息
	private List<GoOrder> goOrders;

	// GROUP活动参加信息
	private List<GoJoin> goJoins;

	public List<GoOrder> getGoOrders() {
		return goOrders;
	}

	public void setGoOrders(List<GoOrder> goOrders) {
		this.goOrders = goOrders;
	}

	public List<GoJoin> getGoJoins() {
		return goJoins;
	}

	public void setGoJoins(List<GoJoin> goJoins) {
		this.goJoins = goJoins;
	}

}
