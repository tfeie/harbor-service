package com.the.harbor.api.user.param;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.the.harbor.api.user.IUserSV;
import com.the.harbor.base.enumeration.hyuser.Sex;
import com.the.harbor.base.validator.StringEnum;

/**
 * 用户资料编辑服务
 * 
 * @author zhangchao
 *
 */
public class UserEditReq implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户标识
	 */
	@NotBlank(message = "用户标识不能为空", groups = { IUserSV.UserEdit.class })
	private String userId;

	/**
	 * 微信号
	 */
	@NotBlank(message = "微信账号不能为空", groups = { IUserSV.UserEdit.class })
	private String wxOpenid;

	/**
	 * 头像
	 */
	private String wxHeadimg;

	/**
	 * 主页背景图片
	 */
	private String homePageBg;

	/**
	 * 英文名
	 */
	@NotBlank(message = "英文名不能为空", groups = { IUserSV.UserEdit.class })
	@Length(min = 1, max = 30, message = "英文名不能大于30个字符", groups = { IUserSV.UserEdit.class })
	private String enName;

	/**
	 * 性别
	 */
	@NotBlank(message = "性别不能为空", groups = { IUserSV.UserEdit.class })
	@StringEnum(enumClazz = Sex.class, message = "性别不正确", groups = { IUserSV.UserEdit.class })
	private String sex;

	/**
	 * 留学国家
	 */
	@NotBlank(message = "留学国家不能为空", groups = { IUserSV.UserEdit.class })
	private String abroadCountry;

	/**
	 * 留学大学
	 */
	@Length(min = 1, max = 120, message = "留学大学名不能大于120个字符", groups = { IUserSV.UserEdit.class })
	private String abroadUniversity;

	/**
	 * 婚姻状态
	 */
	private String maritalStatus;

	/**
	 * 星座
	 */
	private String constellation;

	/**
	 * 个性签名
	 */
	@Length(min = 0, max = 120, message = "个性签名不能大于120个字符", groups = { IUserSV.UserEdit.class })
	private String signature;

	/**
	 * 手机号码
	 */
	@Length(min = 0, max = 11, message = "手机号码不能大于11个字符", groups = { IUserSV.UserEdit.class })
	private String mobilePhone;

	/**
	 * 所在公司
	 */
	@Length(min = 0, max = 120, message = "所在公司不能大于120个字符", groups = { IUserSV.UserEdit.class })
	private String company;

	/**
	 * 所在行业
	 */
	private String industry;

	/**
	 * 职位
	 */
	@Length(min = 0, max = 40, message = "职位名称不能大于40个字符", groups = { IUserSV.UserEdit.class })
	private String title;

	/**
	 * 兴趣标签
	 */
	private List<UserTag> interestSelectedTags;

	/**
	 * 技能标签
	 */
	private List<UserTag> skillSelectedTags;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getHomePageBg() {
		return homePageBg;
	}

	public void setHomePageBg(String homePageBg) {
		this.homePageBg = homePageBg;
	}

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

	public String getAbroadCountry() {
		return abroadCountry;
	}

	public void setAbroadCountry(String abroadCountry) {
		this.abroadCountry = abroadCountry;
	}

	public String getAbroadUniversity() {
		return abroadUniversity;
	}

	public void setAbroadUniversity(String abroadUniversity) {
		this.abroadUniversity = abroadUniversity;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getConstellation() {
		return constellation;
	}

	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<UserTag> getInterestSelectedTags() {
		return interestSelectedTags;
	}

	public void setInterestSelectedTags(List<UserTag> interestSelectedTags) {
		this.interestSelectedTags = interestSelectedTags;
	}

	public List<UserTag> getSkillSelectedTags() {
		return skillSelectedTags;
	}

	public void setSkillSelectedTags(List<UserTag> skillSelectedTags) {
		this.skillSelectedTags = skillSelectedTags;
	}

}
