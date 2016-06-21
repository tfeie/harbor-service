package com.the.harbor.api.go.param;

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

	public GoOrder getGoOrder() {
		return goOrder;
	}

	public void setGoOrder(GoOrder goOrder) {
		this.goOrder = goOrder;
	}

}
