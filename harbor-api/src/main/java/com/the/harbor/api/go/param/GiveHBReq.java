package com.the.harbor.api.go.param;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.the.harbor.api.be.IBeSV;
import com.the.harbor.api.go.IGoSV;

public class GiveHBReq implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "活动参加流水为空", groups = { IGoSV.GiveHaibei.class })
	private String goOrderId;

	@NotBlank(message = "活动类型为空", groups = { IGoSV.GiveHaibei.class })
	private String goType;

	@NotBlank(message = "打赏用户为空", groups = { IGoSV.GiveHaibei.class })
	private String userId;

	@NotNull(message = "打赏海贝数量为空", groups = { IBeSV.GiveHaibei.class })
	private int count;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
