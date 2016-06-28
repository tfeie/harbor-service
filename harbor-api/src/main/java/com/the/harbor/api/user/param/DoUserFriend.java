package com.the.harbor.api.user.param;

import java.io.Serializable;
import java.sql.Timestamp;

import com.the.harbor.base.vo.MNSBody;

public class DoUserFriend extends MNSBody {

	private static final long serialVersionUID = 1L;

	public enum HandleType implements Serializable {
		APPLY, REJECT, AGREE, CANCEL;
	}

	private String handleType;

	/**
	 * 主用户
	 */
	private String userId;

	/**
	 * 被动用户
	 */
	private String friendUserId;

	/**
	 * 操作时间
	 */
	private Timestamp time;

	/**
	 * 申请消息
	 */
	private String applyMq;

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

	public String getFriendUserId() {
		return friendUserId;
	}

	public void setFriendUserId(String friendUserId) {
		this.friendUserId = friendUserId;
	}

	public String getApplyMq() {
		return applyMq;
	}

	public void setApplyMq(String applyMq) {
		this.applyMq = applyMq;
	}

}
