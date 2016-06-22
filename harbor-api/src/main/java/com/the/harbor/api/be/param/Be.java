package com.the.harbor.api.be.param;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class Be implements Serializable {

	private static final long serialVersionUID = 1L;

	private String beId;

	private String userId;

	private String status;

	private Timestamp createDate;

	private Timestamp invalidDate;

	private List<BeDetail> beDetails;

	private List<BeTag> beTags;

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

	public List<BeDetail> getBeDetails() {
		return beDetails;
	}

	public void setBeDetails(List<BeDetail> beDetails) {
		this.beDetails = beDetails;
	}

	public List<BeTag> getBeTags() {
		return beTags;
	}

	public void setBeTags(List<BeTag> beTags) {
		this.beTags = beTags;
	}

}
