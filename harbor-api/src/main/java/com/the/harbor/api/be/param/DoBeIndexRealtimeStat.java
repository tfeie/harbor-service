package com.the.harbor.api.be.param;

import java.io.Serializable;

import com.the.harbor.base.vo.MNSBody;

/**
 * be索引实时统计更新
 * 
 * @author zhangchao
 *
 */
public class DoBeIndexRealtimeStat extends MNSBody {

	private static final long serialVersionUID = 1L;

	public enum StatType implements Serializable {
		DIANZAN/* 点赞数量 */, COMMENT/* 评论数量 */, REWARD/* 打赏海贝数量 */;
	}

	private String beId;

	private String statType;

	public DoBeIndexRealtimeStat() {
		// 序列化时候默认构造函数必须声明
	}

	public DoBeIndexRealtimeStat(String beId, String statType) {
		this.beId = beId;
		this.statType = statType;
	}

	public String getBeId() {
		return beId;
	}

	public void setBeId(String beId) {
		this.beId = beId;
	}

	public String getStatType() {
		return statType;
	}

	public void setStatType(String statType) {
		this.statType = statType;
	}

}
