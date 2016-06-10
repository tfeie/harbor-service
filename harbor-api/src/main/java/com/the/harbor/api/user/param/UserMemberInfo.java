package com.the.harbor.api.user.param;

import java.sql.Timestamp;

import com.the.harbor.base.vo.Response;

public class UserMemberInfo extends Response {

	private static final long serialVersionUID = 1L;

	private String userId;

	private String memberLevel;

	private Timestamp effDate;

	private Timestamp expDate;

	private String effDateStr;

	private String expDateStr;
	
	/**
	 * 是否到期
	 */
	private boolean expflag;

	/**
	 * 会员状态描述
	 */
	private String desc;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMemberLevel() {
		return memberLevel;
	}

	public void setMemberLevel(String memberLevel) {
		this.memberLevel = memberLevel;
	}

	public Timestamp getEffDate() {
		return effDate;
	}

	public void setEffDate(Timestamp effDate) {
		this.effDate = effDate;
	}

	public Timestamp getExpDate() {
		return expDate;
	}

	public void setExpDate(Timestamp expDate) {
		this.expDate = expDate;
	}

	public String getEffDateStr() {
		return effDateStr;
	}

	public void setEffDateStr(String effDateStr) {
		this.effDateStr = effDateStr;
	}

	public String getExpDateStr() {
		return expDateStr;
	}

	public void setExpDateStr(String expDateStr) {
		this.expDateStr = expDateStr;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public boolean isExpflag() {
		return expflag;
	}

	public void setExpflag(boolean expflag) {
		this.expflag = expflag;
	}
	
	

}
