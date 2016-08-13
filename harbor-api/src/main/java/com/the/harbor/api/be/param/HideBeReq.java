package com.the.harbor.api.be.param;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.the.harbor.api.be.IBeSV;

public class HideBeReq implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "BE标识为空", groups = { IBeSV.HideBe.class })
	private String beId;

	private boolean hide;
	
	public String getBeId() {
		return beId;
	}

	public void setBeId(String beId) {
		this.beId = beId;
	}

	public boolean isHide() {
		return hide;
	}

	public void setHide(boolean hide) {
		this.hide = hide;
	}
	
	

}
