package com.the.harbor.api.go.param;

import java.sql.Timestamp;

import com.the.harbor.base.vo.MNSBody;

/**
 * GO删除消息
 * 
 * @author zhangchao
 *
 */
public class DoGoDelete extends MNSBody {

	private static final long serialVersionUID = 1L;

	/**
	 * GO标识
	 */
	private String goId;

	/**
	 * GO类型
	 */
	private String goType;

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

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getGoType() {
		return goType;
	}

	public void setGoType(String goType) {
		this.goType = goType;
	}

}
