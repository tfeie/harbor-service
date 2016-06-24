package com.the.harbor.api.go.param;

import java.sql.Timestamp;

import com.the.harbor.base.vo.MNSBody;

/**
 * GO查看消息
 * 
 * @author zhangchao
 *
 */
public class DoGoView extends MNSBody {

	private static final long serialVersionUID = 1L;

	/**
	 * GO标识
	 */
	private String goId;

	/**
	 * 查看用户
	 */
	private String userId;

	/**
	 * 操作时间
	 */
	private Timestamp time;

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

}
