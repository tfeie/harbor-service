package com.the.harbor.api.user.param;

import org.hibernate.validator.constraints.NotBlank;

import com.the.harbor.api.user.IUserSV;
import com.the.harbor.base.enumeration.hypaymentorder.PayType;
import com.the.harbor.base.validator.StringEnum;
import com.the.harbor.base.vo.Response;

public class CreateUserBuyMemberOrderReq extends Response {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户
	 */
	@NotBlank(message = "用户不能为空", groups = { IUserSV.CreateUserBuyMember.class })
	private String userId;

	/**
	 * 支付金额
	 */
	private long payAmount;

	/**
	 * 购买月
	 */
	private int buyMonths;

	/**
	 * 发起方签名串,防止重复发起
	 */
	@NotBlank(message = "发起方签名不能为空", groups = { IUserSV.CreateUserBuyMember.class })
	private String fromSign;

	/**
	 * 支付类型
	 */
	@NotBlank(message = "支付类型不能为空", groups = { IUserSV.CreateUserBuyMember.class })
	@StringEnum(enumClazz = PayType.class, message = "支付类型传入不在约定内", groups = { IUserSV.CreateUserBuyMember.class })
	private String payType;

	/**
	 * 支付摘要
	 */
	private String summary;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public long getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(long payAmount) {
		this.payAmount = payAmount;
	}

	public int getBuyMonths() {
		return buyMonths;
	}

	public void setBuyMonths(int buyMonths) {
		this.buyMonths = buyMonths;
	}

	public String getFromSign() {
		return fromSign;
	}

	public void setFromSign(String fromSign) {
		this.fromSign = fromSign;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

}
