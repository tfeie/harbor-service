package com.the.harbor.api.be.param;

import java.io.Serializable;

public class BeDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;

	private String type;

	private String detail;

	private String imageUrl;
	
	public String imgThumbnailUrl;

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
		return imageUrl+"@!be_thumbnail";
	}

	public void setImgThumbnailUrl(String imgThumbnailUrl) {
		this.imgThumbnailUrl = imgThumbnailUrl;
	}
	
	

}
