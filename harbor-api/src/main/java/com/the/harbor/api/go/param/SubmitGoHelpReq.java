package com.the.harbor.api.go.param;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.the.harbor.api.go.IGoSV;

public class SubmitGoHelpReq implements Serializable {

	private static final long serialVersionUID = 1L;
	@NotBlank(message = "活动参加流水为空", groups = { IGoSV.SubmitGoHelp.class })
	private String goOrderId;

	@NotBlank(message = "活动类型为空", groups = { IGoSV.SubmitGoHelp.class })
	private String goType;

	@NotBlank(message = "用户为空", groups = { IGoSV.SubmitGoHelp.class })
	private String userId;

	@NotBlank(message = "是否有帮助不能为空", groups = { IGoSV.SubmitGoHelp.class })
	private String helpValue;

	public String getGoOrderId() {
		return goOrderId;
	}

	public void setGoOrderId(String goOrderId) {
		this.goOrderId = goOrderId;
	}

	public String getGoType() {
		return goType;
	}

	public void setGoType(String goType) {
		this.goType = goType;
	}

	public String getHelpValue() {
		return helpValue;
	}

	public void setHelpValue(String helpValue) {
		this.helpValue = helpValue;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
