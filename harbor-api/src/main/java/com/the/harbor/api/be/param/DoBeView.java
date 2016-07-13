package com.the.harbor.api.be.param;

import java.sql.Timestamp;

import com.the.harbor.base.vo.MNSBody;

/**
 * BE浏览消息
 * 
 * @author zhangchao
 *
 */
public class DoBeView extends MNSBody {

	private static final long serialVersionUID = 1L;

	/**
	 * BE标识
	 */
	private String beId;

	/**
	 * 查看用户
	 */
	private String userId;

	/**
	 * 操作时间
	 */
	private Timestamp time;

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

}
