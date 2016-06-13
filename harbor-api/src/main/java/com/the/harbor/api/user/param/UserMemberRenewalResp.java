package com.the.harbor.api.user.param;

import com.the.harbor.base.vo.Response;

/**
 * 会员续期结果
 * 
 * @author zhangchao
 *
 */
public class UserMemberRenewalResp extends Response {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户标识
	 */
	private String userId;

	/**
	 * 英文名称
	 */
	private String enName;

	/**
	 * 中午
	 */
	private String chName;

	/**
	 * 购买月份
	 */
	private int payMonth;

	/**
	 * 最后到期日期
	 */
	private String expDate;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getChName() {
		return chName;
	}

	public void setChName(String chName) {
		this.chName = chName;
	}

	public int getPayMonth() {
		return payMonth;
	}

	public void setPayMonth(int payMonth) {
		this.payMonth = payMonth;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

}
