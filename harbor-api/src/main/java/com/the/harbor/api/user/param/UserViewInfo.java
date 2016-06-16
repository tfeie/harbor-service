package com.the.harbor.api.user.param;

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

	private String abroadUniversityName;

	private String atCityName;

	private String maritalStatusName;

	private String constellationName;

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

	public String getAbroadUniversityName() {
		return abroadUniversityName;
	}

	public void setAbroadUniversityName(String abroadUniversityName) {
		this.abroadUniversityName = abroadUniversityName;
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

}
