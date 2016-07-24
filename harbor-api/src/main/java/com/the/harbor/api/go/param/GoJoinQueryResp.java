package com.the.harbor.api.go.param;

import com.the.harbor.base.vo.Response;

/**
 * 查询预约信息
 * 
 * @author zhangchao
 *
 */
public class GoJoinQueryResp extends Response {

	private static final long serialVersionUID = 1L;

	private GoJoin goJoin;

	public GoJoin getGoJoin() {
		return goJoin;
	}

	public void setGoJoin(GoJoin goJoin) {
		this.goJoin = goJoin;
	}

}
