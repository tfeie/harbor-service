package com.the.harbor.api.be.param;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.the.harbor.api.be.IBeSV;

public class TopBeReq implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "BE标识为空", groups = { IBeSV.TopBe.class })
	private String beId;

	private boolean top;

	public String getBeId() {
		return beId;
	}

	public void setBeId(String beId) {
		this.beId = beId;
	}

	public boolean isTop() {
		return top;
	}

	public void setTop(boolean top) {
		this.top = top;
	}

}
