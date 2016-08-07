package com.the.harbor.api.be.param;

import java.sql.Timestamp;

import com.the.harbor.base.vo.MNSBody;

/**
 * BE删除消息
 * 
 * @author zhangchao
 *
 */
public class DoBeDelete extends MNSBody {

	private static final long serialVersionUID = 1L;

	/**
	 * BE标识
	 */
	private String beId;

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

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

}
