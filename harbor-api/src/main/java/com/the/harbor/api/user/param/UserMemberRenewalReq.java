package com.the.harbor.api.user.param;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.the.harbor.api.user.IUserSV;

/**
 * 会员续期
 * 
 * @author zhangchao
 *
 */
public class UserMemberRenewalReq implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户标识
	 */
	@NotBlank(message = "用户不能为空", groups = { IUserSV.UserMemberRenewal.class })
	private String userId;

	/**
	 * 微信标识
	 */
	@NotBlank(message = "微信不能为空", groups = { IUserSV.UserMemberRenewal.class })
	private String openId;

	/**
	 * 购买月份
	 */
	private int payMonth;

	/**
	 * 支付业务凭证流水
	 */
	@NotBlank(message = "支付业务凭证流水", groups = { IUserSV.UserMemberRenewal.class })
	private String payOrderId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public int getPayMonth() {
		return payMonth;
	}

	public void setPayMonth(int payMonth) {
		this.payMonth = payMonth;
	}

	public String getPayOrderId() {
		return payOrderId;
	}

	public void setPayOrderId(String payOrderId) {
		this.payOrderId = payOrderId;
	}

}
