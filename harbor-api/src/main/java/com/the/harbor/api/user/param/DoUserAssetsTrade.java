package com.the.harbor.api.user.param;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 用户资产交易。
 * 
 * @author zhangchao
 *
 */
public class DoUserAssetsTrade implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	public enum HandleType implements Serializable {
		TRANSFER// 转账;
	}

	/**
	 * 发起用户
	 */
	private String fromUserId;

	/**
	 * 目标用户
	 */
	private String toUserId;

	/**
	 * 业务类型
	 */
	private String busiType;

	/**
	 * 交易资产
	 */
	private String assetsType;

	/**
	 * 交易金额 分OR个
	 */
	private long tradeBalance;

	/**
	 * 交易备注
	 */
	private String summary;

	/**
	 * 交易时间
	 */
	private Timestamp tradeDate;

	/**
	 * 原业务订单号
	 */
	private String sourceNo;

	/**
	 * 操作类型
	 */
	private String handleType;

	public String getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(String fromUserId) {
		this.fromUserId = fromUserId;
	}

	public String getToUserId() {
		return toUserId;
	}

	public void setToUserId(String toUserId) {
		this.toUserId = toUserId;
	}

	public String getBusiType() {
		return busiType;
	}

	public void setBusiType(String busiType) {
		this.busiType = busiType;
	}

	public String getAssetsType() {
		return assetsType;
	}

	public void setAssetsType(String assetsType) {
		this.assetsType = assetsType;
	}

	public long getTradeBalance() {
		return tradeBalance;
	}

	public void setTradeBalance(long tradeBalance) {
		this.tradeBalance = tradeBalance;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Timestamp getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(Timestamp tradeDate) {
		this.tradeDate = tradeDate;
	}

	public String getSourceNo() {
		return sourceNo;
	}

	public void setSourceNo(String sourceNo) {
		this.sourceNo = sourceNo;
	}

	public String getHandleType() {
		return handleType;
	}

	public void setHandleType(String handleType) {
		this.handleType = handleType;
	}

}
