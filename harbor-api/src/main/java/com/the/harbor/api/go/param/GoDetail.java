package com.the.harbor.api.go.param;

import java.io.Serializable;

public class GoDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 标识
	 */
	private String id;

	/**
	 * 类型
	 */
	private String type;

	private String detail;

	private String imageUrl;
	
	private String imgThumbnailUrl;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getImgThumbnailUrl() {
		return imageUrl+"@!go_thumbnail";
	}

	public void setImgThumbnailUrl(String imgThumbnailUrl) {
		this.imgThumbnailUrl = imgThumbnailUrl;
	}
	
	

}
