package com.the.harbor.api.user.param;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.the.harbor.api.user.IUserSV;
import com.the.harbor.base.enumeration.hytags.TagCat;
import com.the.harbor.base.enumeration.hytags.TagType;
import com.the.harbor.base.validator.StringEnum;

public class UserTag implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "标签ID不能为空", groups = { IUserSV.SumitUserSelectedSystemTags.class })
	private String tagId;

	@NotBlank(message = "标签类型不能为空", groups = { IUserSV.SumitUserSelectedSystemTags.class })
	@StringEnum(enumClazz = TagType.class, message = "标签类型取值不正确", groups = {
			IUserSV.SumitUserSelectedSystemTags.class })
	private String tagType;

	@NotBlank(message = "标签名称不能为空", groups = { IUserSV.SumitUserSelectedSystemTags.class })
	private String tagName;

	@NotBlank(message = "标签类目不能为空", groups = { IUserSV.SumitUserSelectedSystemTags.class })
	@StringEnum(enumClazz = TagCat.class, message = "标签类目取值不正确", groups = { IUserSV.SumitUserSelectedSystemTags.class })
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
