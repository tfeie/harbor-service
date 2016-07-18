package com.the.harbor.api.be.param;

import java.io.Serializable;

public class BeTag implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 标签ID
	 */
	private String tagId;

	/**
	 * 标签类型
	 */
	private String tagType;

	/**
	 * 标签名称
	 */
	private String tagName;

	/**
	 * 标签类目
	 */
	private String tagCat;
	
	private String polyTagId;

	public String getTagId() {
		return tagId;
	}

	public void setTagId(String tagId) {
		this.tagId = tagId;
	}

	public String getTagType() {
		return tagType;
	}

	public void setTagType(String tagType) {
		this.tagType = tagType;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getTagCat() {
		return tagCat;
	}

	public void setTagCat(String tagCat) {
		this.tagCat = tagCat;
	}

	public String getPolyTagId() {
		return polyTagId;
	}

	public void setPolyTagId(String polyTagId) {
		this.polyTagId = polyTagId;
	}
	
	

}
