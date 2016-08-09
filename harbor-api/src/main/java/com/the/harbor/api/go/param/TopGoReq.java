package com.the.harbor.api.go.param;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.the.harbor.api.go.IGoSV;

public class TopGoReq implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "活动ID为空", groups = { IGoSV.TopGo.class })
	private String goId;

	/**
	 * true为置顶 false为取消置顶
	 */
	private boolean top;

	public String getGoId() {
		return goId;
	}

	public void setGoId(String goId) {
		this.goId = goId;
	}

	public boolean isTop() {
		return top;
	}

	public void setTop(boolean top) {
		this.top = top;
	}

}
