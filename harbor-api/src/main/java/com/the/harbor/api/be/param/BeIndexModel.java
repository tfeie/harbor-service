package com.the.harbor.api.be.param;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * BE在openSearch中的模型定义,以BE_DETAIL为索引记录单元,只对明细中的文本进行索引
 * 
 * @author zhangchao
 *
 */
public class BeIndexModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * BE明细ID
	 */
	private String id;

	/**
	 * BE明细文本内容
	 */
	private String detail;

	/**
	 * BE标识
	 */
	private String beId;

	/**
	 * BE标签ID数组
	 */
	private String[] beTagIds;

	/**
	 * BE标签ID数组
	 */
	private String[] beTagNames;

	/**
	 * BE聚类标签ID数组
	 */
	private String[] polyTagIds;

	/**
	 * 发布用户
	 */
	private String userId;

	/**
	 * 发布用户名称
	 */
	private String enName;

	/**
	 * BE状态
	 */
	private String status;

	/**
	 * 创建时间
	 */
	private Timestamp createDate;

	/**
	 * BE失效时间
	 */
	private Timestamp invalidDate;

	/**
	 * 置顶标识
	 */
	private String topFlag;

	/**
	 * 置顶时间
	 */
	private Timestamp topDate;

	/**
	 * 隐藏标记
	 */
	private String hideFlag;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

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

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Timestamp getInvalidDate() {
		return invalidDate;
	}

	public void setInvalidDate(Timestamp invalidDate) {
		this.invalidDate = invalidDate;
	}

	public String getTopFlag() {
		return topFlag;
	}

	public void setTopFlag(String topFlag) {
		this.topFlag = topFlag;
	}

	public Timestamp getTopDate() {
		return topDate;
	}

	public void setTopDate(Timestamp topDate) {
		this.topDate = topDate;
	}

	public String getHideFlag() {
		return hideFlag;
	}

	public void setHideFlag(String hideFlag) {
		this.hideFlag = hideFlag;
	}

	public String[] getBeTagIds() {
		return beTagIds;
	}

	public void setBeTagIds(String[] beTagIds) {
		this.beTagIds = beTagIds;
	}

	public String[] getBeTagNames() {
		return beTagNames;
	}

	public void setBeTagNames(String[] beTagNames) {
		this.beTagNames = beTagNames;
	}

	public String[] getPolyTagIds() {
		return polyTagIds;
	}

	public void setPolyTagIds(String[] polyTagIds) {
		this.polyTagIds = polyTagIds;
	}

}
