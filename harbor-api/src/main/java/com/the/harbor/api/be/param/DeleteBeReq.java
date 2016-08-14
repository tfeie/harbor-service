package com.the.harbor.api.be.param;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.the.harbor.api.be.IBeSV;

public class DeleteBeReq implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "BE标识为空", groups = { IBeSV.DeleteBe.class })
	private String beId;

	@NotNull(message = "操作用户为空", groups = { IBeSV.DeleteBe.class })
	private String userId;

	public String getBeId() {
		return beId;
	}

	public void setBeId(String beId) {
		this.beId = beId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
