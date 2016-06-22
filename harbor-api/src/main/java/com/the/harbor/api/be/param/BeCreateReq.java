package com.the.harbor.api.be.param;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.the.harbor.api.be.IBeSV;

public class BeCreateReq implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "发起用户为空", groups = { IBeSV.CreateBe.class })
	private String userId;

	@NotNull(message = "请填写BE明细", groups = { IBeSV.CreateBe.class })
	private List<BeDetail> beDetails;

	@NotNull(message = "请选择BE标签", groups = { IBeSV.CreateBe.class })
	private List<BeTag> beTags;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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
