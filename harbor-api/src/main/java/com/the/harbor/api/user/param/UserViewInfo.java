package com.the.harbor.api.user.param;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户浏览信息
 * 
 * @author zhangchao
 *
 */
public class UserViewInfo extends UserInfo {

	private static final long serialVersionUID = 1L;

	private String userTypeName;

	private String sexName;

	private String abroadCountryName;

	private String atCityName;

	private String maritalStatusName;

	private String constellationName;

	private String industryName;

	private String userStatusName;

	private String authStsName;

	public transient String employmentInfo;

	public String getEmploymentInfo() {
		List<String> list = new ArrayList<String>();
		if (this.getIndustryName() != null && !"".equals(this.getIndustryName().trim())) {
			list.add(this.getIndustryName());
		}
		if (this.getTitle() != null && !"".equals(this.getTitle().trim())) {
			list.add(this.getTitle());
		}
		if (this.getAtCityName() != null && !"".equals(this.getAtCityName().trim())) {
			list.add(this.getAtCityName());
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i)).append("/");
		}
		if(sb.toString().length()==0){
			employmentInfo = "";
		}else{
			employmentInfo=sb.toString().substring(0, sb.toString().length() - 1);
		}
		return employmentInfo;
	}

	public void setEmploymentInfo(String employmentInfo) {
		this.employmentInfo = employmentInfo;
	}

	public String getUserTypeName() {
		return userTypeName;
	}

	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}

	public String getSexName() {
		return sexName;
	}

	public void setSexName(String sexName) {
		this.sexName = sexName;
	}

	public String getAbroadCountryName() {
		return abroadCountryName;
	}

	public void setAbroadCountryName(String abroadCountryName) {
		this.abroadCountryName = abroadCountryName;
	}

	public String getAtCityName() {
		return atCityName;
	}

	public void setAtCityName(String atCityName) {
		this.atCityName = atCityName;
	}

	public String getMaritalStatusName() {
		return maritalStatusName;
	}

	public void setMaritalStatusName(String maritalStatusName) {
		this.maritalStatusName = maritalStatusName;
	}

	public String getConstellationName() {
		return constellationName;
	}

	public void setConstellationName(String constellationName) {
		this.constellationName = constellationName;
	}

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	public String getUserStatusName() {
		return userStatusName;
	}

	public void setUserStatusName(String userStatusName) {
		this.userStatusName = userStatusName;
	}

	public String getAuthStsName() {
		return authStsName;
	}

	public void setAuthStsName(String authStsName) {
		this.authStsName = authStsName;
	}

}
