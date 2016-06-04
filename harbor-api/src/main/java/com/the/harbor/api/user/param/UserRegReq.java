package com.the.harbor.api.user.param;

import java.io.Serializable;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.the.harbor.api.user.IUserSV;
import com.the.harbor.base.enumeration.hyuser.Sex;
import com.the.harbor.base.validator.MobilePhone;
import com.the.harbor.base.validator.StringEnum;

public class UserRegReq implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 英文名
	 */
	@NotBlank(message = "英文名不能为空", groups = { IUserSV.UserRegister.class })
	private String enName;

	/**
	 * 性别
	 */
	@NotBlank(message = "性别不能为空", groups = { IUserSV.UserRegister.class })
	@StringEnum(enumClazz = Sex.class, message = "性别不正确", groups = { IUserSV.UserRegister.class })
	private String sex;

	/**
	 * 微信号
	 */
	@NotBlank(message = "微信OPEN_ID为空，请认证", groups = { IUserSV.UserRegister.class })
	private String wxOpenid;

	/**
	 * 微信头像
	 */
	private String wxHeadimg;

	/**
	 * 微信昵称
	 */
	private String wxNickname;

	/**
	 * 头像
	 */
	private String headIcon;

	/**
	 * 留学国家
	 */
	@NotBlank(message = "留学国家不能为空", groups = { IUserSV.UserRegister.class })
	private String abroadCountry;

	/**
	 * 行业
	 */
	@NotBlank(message = "行业不能为空", groups = { IUserSV.UserRegister.class })
	private String industry;

	/**
	 * 当前所在城市
	 */
	private String atCity;

	/**
	 * 手机号码
	 */
	@MobilePhone(message = "手机号码格式不正确", groups = { IUserSV.UserRegister.class })
	private String mobilePhone;

	/**
	 * 电子邮件
	 */
	@Email(message = "邮箱格式不正确", groups = { IUserSV.UserRegister.class })
	private String email;

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getWxOpenid() {
		return wxOpenid;
	}

	public void setWxOpenid(String wxOpenid) {
		this.wxOpenid = wxOpenid;
	}

	public String getWxHeadimg() {
		return wxHeadimg;
	}

	public void setWxHeadimg(String wxHeadimg) {
		this.wxHeadimg = wxHeadimg;
	}

	public String getWxNickname() {
		return wxNickname;
	}

	public void setWxNickname(String wxNickname) {
		this.wxNickname = wxNickname;
	}

	public String getHeadIcon() {
		return headIcon;
	}

	public void setHeadIcon(String headIcon) {
		this.headIcon = headIcon;
	}

	public String getAbroadCountry() {
		return abroadCountry;
	}

	public void setAbroadCountry(String abroadCountry) {
		this.abroadCountry = abroadCountry;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getAtCity() {
		return atCity;
	}

	public void setAtCity(String atCity) {
		this.atCity = atCity;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
