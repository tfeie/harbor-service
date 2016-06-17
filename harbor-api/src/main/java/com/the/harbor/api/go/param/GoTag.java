package com.the.harbor.api.go.param;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.the.harbor.api.go.IGoSV;

public class GoTag implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 标签ID
	 */
	private String tagId;

	/**
	 * 标签类型
	 */
	@NotNull(message="标签类型不能为空",groups = {IGoSV.CreateGo.class})
	private String tagType;

	/**
	 * 标签名称
	 */
	private String tagName;

	/**
	 * 标签类目
	 */
	private String tagCat;

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

}
