package com.the.harbor.api.user.param;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.the.harbor.api.user.IUserSV;

public class UserCertificationReq implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户标识
	 */
	@NotBlank(message = "用户不能为空", groups = { IUserSV.SubmitUserCertification.class })
	private String userId;

	/**
	 * 身份证照片地址
	 */
	@NotBlank(message = "请上传身份证正面照", groups = { IUserSV.SubmitUserCertification.class })
	private String idcardPhoto;

	/**
	 * 海外证件照片地址
	 */
	@NotBlank(message = "请上传海外学历认证/签证/学生证", groups = { IUserSV.SubmitUserCertification.class })
	private String overseasPhoto;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getIdcardPhoto() {
		return idcardPhoto;
	}

	public void setIdcardPhoto(String idcardPhoto) {
		this.idcardPhoto = idcardPhoto;
	}

	public String getOverseasPhoto() {
		return overseasPhoto;
	}

	public void setOverseasPhoto(String overseasPhoto) {
		this.overseasPhoto = overseasPhoto;
	}

}
