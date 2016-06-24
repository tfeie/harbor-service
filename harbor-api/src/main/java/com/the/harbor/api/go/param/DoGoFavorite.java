package com.the.harbor.api.go.param;

import java.io.Serializable;
import java.sql.Timestamp;

import com.the.harbor.base.vo.MNSBody;

/**
 * GO收藏消息
 * 
 * @author zhangchao
 *
 */
public class DoGoFavorite extends MNSBody {

	private static final long serialVersionUID = 1L;

	public enum HandleType implements Serializable {
		DO, CANCEL;
	}

	/**
	 * GO标识
	 */
	private String goId;

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

	public String getGoId() {
		return goId;
	}

	public void setGoId(String goId) {
		this.goId = goId;
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
