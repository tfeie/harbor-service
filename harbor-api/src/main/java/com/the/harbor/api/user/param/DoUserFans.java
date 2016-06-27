package com.the.harbor.api.user.param;

import java.io.Serializable;
import java.sql.Timestamp;

import com.the.harbor.base.vo.MNSBody;

public class DoUserFans extends MNSBody {

	private static final long serialVersionUID = 1L;

	public enum HandleType implements Serializable {
		GUANZHU, CANCEL;
	}

	private String handleType;

	/**
	 * 主用户
	 */
	private String userId;

	/**
	 * 粉丝用户
	 */
	private String fansUserId;

	/**
	 * 操作时间
	 */
	private Timestamp time;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getHandleType() {
		return handleType;
	}

	public void setHandleType(String handleType) {
		this.handleType = handleType;
	}

	public String getFansUserId() {
		return fansUserId;
	}

	public void setFansUserId(String fansUserId) {
		this.fansUserId = fansUserId;
	}

}
