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
	 * 是否参与
	 */
	private boolean join;

	/**
	 * 说明
	 */
	private String remark;

	public boolean isJoin() {
		return join;
	}

	public void setJoin(boolean join) {
		this.join = join;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
