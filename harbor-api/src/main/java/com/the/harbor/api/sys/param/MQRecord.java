package com.the.harbor.api.sys.param;

import java.io.Serializable;

public class MQRecord implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	public enum HandleType implements Serializable {
		SEND, PROCESS;
	}

	/**
	 * 消息ID
	 */
	private String mqId;

	/**
	 * 消息类型
	 */
	private String mqType;

	/**
	 * 处理是否成功
	 */
	private boolean result;

	/**
	 * 失败原因
	 */
	private String error;

	/**
	 * 状态
	 */
	private String handleType;

	/**
	 * 消息体内容
	 */
	private String mqBody;

	public String getMqId() {
		return mqId;
	}

	public void setMqId(String mqId) {
		this.mqId = mqId;
	}

	public String getMqType() {
		return mqType;
	}

	public void setMqType(String mqType) {
		this.mqType = mqType;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getHandleType() {
		return handleType;
	}

	public void setHandleType(String handleType) {
		this.handleType = handleType;
	}

	public String getMqBody() {
		return mqBody;
	}

	public void setMqBody(String mqBody) {
		this.mqBody = mqBody;
	}

}
