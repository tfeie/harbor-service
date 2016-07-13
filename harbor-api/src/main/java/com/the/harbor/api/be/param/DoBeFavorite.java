package com.the.harbor.api.be.param;

import java.io.Serializable;
import java.sql.Timestamp;

import com.the.harbor.base.vo.MNSBody;

/**
 * BE收藏消息
 * 
 * @author zhangchao
 *
 */
public class DoBeFavorite extends MNSBody {

	private static final long serialVersionUID = 1L;

	public enum HandleType implements Serializable {
		DO, CANCEL;
	}

	/**
	 * BE标识
	 */
	private String beId;

	/**
	 * 点赞或取消赞用户
	 */
	private String userId;

	/**
	 * 操作时间
	 */
	private Timestamp time;

	/**
	 * 操作类型
	 */
	private String handleType;

	public String getBeId() {
		return beId;
	}

	public void setBeId(String beId) {
		this.beId = beId;
	}

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

}
