package com.the.harbor.api.go.param;

import com.the.harbor.base.vo.Response;

/**
 * 判断用户是否参加某个活动
 * 
 * @author zhangchao
 *
 */
public class GoOrderCheckResp extends Response {

	private static final long serialVersionUID = 1L;

	/**
	 * 参与状态 1:自己发布的活动本人不可预约 2:已经预约 3：可以预约
	 */
	private String join;

	/**
	 * 说明
	 */
	private String remark;

	/**
	 * 预约订单号.join=2
	 */
	private String orderId;

	/**
	 * 预约状态.join=2
	 */
	private String orderStatus;

	public String getJoin() {
		return join;
	}

	public void setJoin(String join) {
		this.join = join;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

}
