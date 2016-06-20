package com.the.harbor.api.go.param;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.the.harbor.api.go.IGoSV;

/**
 * 判断用户是否参加某个活动
 * 
 * @author zhangchao
 *
 */
public class GoOrderCheckReq implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "用户ID为空", groups = { IGoSV.CheckUserJoinGo.class })
	private String userId;

	@NotNull(message = "参与的活动ID为空", groups = { IGoSV.CheckUserJoinGo.class })
	private String goId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getGoId() {
		return goId;
	}

	public void setGoId(String goId) {
		this.goId = goId;
	}

}
