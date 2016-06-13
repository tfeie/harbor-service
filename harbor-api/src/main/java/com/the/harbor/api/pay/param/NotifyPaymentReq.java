package com.the.harbor.api.pay.param;

import org.hibernate.validator.constraints.NotBlank;

import com.the.harbor.api.pay.IPaymentSV;
import com.the.harbor.base.vo.Response;

public class NotifyPaymentReq extends Response {

	private static final long serialVersionUID = 1L;

	/**
	 * 业务交易订单号
	 */
	@NotBlank(message = "业务交易订单号为空", groups = { IPaymentSV.CreatePaymentOrder.class })
	private String payOrderId;

	/**
	 * 微信侧业务流水
	 */
	private String transactionId;

	/**
	 * 微信侧交易完成时间
	 */
	private String timeEnd;

	/**
	 * 业务交易结果 当returnCode=SUCCESS时候，取值 SUCCESS/FAIL
	 */
	private String resultCode;

	/**
	 * 返回结果 SUCCESS/FAIL
	 */
	private String returnCode;

	/**
	 * 返回消息
	 */
	private String returnMsg;

	/**
	 * 交易总参数包
	 */
	private String notifyParam;

	public String getPayOrderId() {
		return payOrderId;
	}

	public void setPayOrderId(String payOrderId) {
		this.payOrderId = payOrderId;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

	public String getNotifyParam() {
		return notifyParam;
	}

	public void setNotifyParam(String notifyParam) {
		this.notifyParam = notifyParam;
	}

}
