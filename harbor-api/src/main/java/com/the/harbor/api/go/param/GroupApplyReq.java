package com.the.harbor.api.go.param;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.the.harbor.api.go.IGoSV;

/**
 * GROUP活动报名
 * 
 * @author zhangchao
 *
 */
public class GroupApplyReq implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "用户ID为空", groups = { IGoSV.ApplyGroup.class })
	private String userId;

	@NotNull(message = "参与的活动ID为空", groups = { IGoSV.ApplyGroup.class })
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
