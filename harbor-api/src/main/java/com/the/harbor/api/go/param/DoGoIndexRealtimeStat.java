package com.the.harbor.api.go.param;

import java.io.Serializable;

/**
 * GO索引实时统计更新
 * 
 * @author zhangchao
 *
 */
public class DoGoIndexRealtimeStat implements Serializable {

	private static final long serialVersionUID = 1L;

	public enum StatType implements Serializable {
		VIEW/* 查看数量 */, FAVOR/* 收藏数量 */, GROUPJOIN/* GROUP参加人数量 */;
	}

	private String goId;

	private String statType;

	public DoGoIndexRealtimeStat() {
		// 序列化时候默认构造函数必须声明
	}

	public DoGoIndexRealtimeStat(String goId, String statType) {
		this.goId = goId;
		this.statType = statType;
	}

	public String getGoId() {
		return goId;
	}

	public void setGoId(String goId) {
		this.goId = goId;
	}

	public String getStatType() {
		return statType;
	}

	public void setStatType(String statType) {
		this.statType = statType;
	}

}
