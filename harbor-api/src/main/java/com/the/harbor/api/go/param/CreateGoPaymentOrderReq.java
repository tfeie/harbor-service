package com.the.harbor.api.go.param;

import org.hibernate.validator.constraints.NotBlank;

import com.the.harbor.api.go.IGoSV;
import com.the.harbor.base.enumeration.hypaymentorder.BusiType;
import com.the.harbor.base.enumeration.hypaymentorder.PayType;
import com.the.harbor.base.validator.StringEnum;
import com.the.harbor.base.vo.Response;

public class CreateGoPaymentOrderReq extends Response {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户
	 */
	@NotBlank(message = "用户不能为空", groups = { IGoSV.CreateGoPaymentOrder.class })
	private String userId;

	/**
	 * 业务类型
	 */
	@NotBlank(message = "业务类型不能为空", groups = { IGoSV.CreateGoPaymentOrder.class })
	@StringEnum(enumClazz = BusiType.class, message = "业务类型传入不在约定内", groups = { IGoSV.CreateGoPaymentOrder.class })
	private String busiType;

	/**
	 * 支付金额
	 */
	private long payAmount;

	/**
	 * 支付类型
	 */
	@NotBlank(message = "支付类型不能为空", groups = { IGoSV.CreateGoPaymentOrder.class })
	@StringEnum(enumClazz = PayType.class, message = "支付类型传入不在约定内", groups = { IGoSV.CreateGoPaymentOrder.class })
	private String payType;

	/**
	 * 支付摘要
	 */
	private String summary;

	@NotBlank(message = "活动预约流水", groups = { IGoSV.CreateGoPaymentOrder.class })
	private String goOrderId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBusiType() {
		return busiType;
	}

	public void setBusiType(String busiType) {
		this.busiType = busiType;
	}

	public long getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(long payAmount) {
		this.payAmount = payAmount;
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

	public String getGoOrderId() {
		return goOrderId;
	}

	public void setGoOrderId(String goOrderId) {
		this.goOrderId = goOrderId;
	}
	
	

}
