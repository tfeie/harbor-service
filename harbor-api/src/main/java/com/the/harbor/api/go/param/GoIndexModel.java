package com.the.harbor.api.go.param;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * GO在openSearch中的模型定义
 * 
 * @author zhangchao
 *
 */
public class GoIndexModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String indexId;

	private String goId;

	private String userId;

	private String goType;

	private String topic;

	private String orgMode;

	private String location;

	private String status;

	private Timestamp createDate;

	private String topFlag;

	private Timestamp topDate;

	private String hideFlag;

	private String story;

	private String content;

	private String[] goTagIds;

	private String[] goTagNames;

	private String[] polyTagIds;

	public String getIndexId() {
		return indexId;
	}

	public void setIndexId(String indexId) {
		this.indexId = indexId;
	}

	public String getGoId() {
		return goId;
	}

	public void setGoId(String goId) {
		this.goId = goId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getGoType() {
		return goType;
	}

	public void setGoType(String goType) {
		this.goType = goType;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getOrgMode() {
		return orgMode;
	}

	public void setOrgMode(String orgMode) {
		this.orgMode = orgMode;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	public String getTopFlag() {
		return topFlag;
	}

	public void setTopFlag(String topFlag) {
		this.topFlag = topFlag;
	}

	public Timestamp getTopDate() {
		return topDate;
	}

	public void setTopDate(Timestamp topDate) {
		this.topDate = topDate;
	}

	public String getHideFlag() {
		return hideFlag;
	}

	public void setHideFlag(String hideFlag) {
		this.hideFlag = hideFlag;
	}

	public String getStory() {
		return story;
	}

	public void setStory(String story) {
		this.story = story;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String[] getGoTagIds() {
		return goTagIds;
	}

	public void setGoTagIds(String[] goTagIds) {
		this.goTagIds = goTagIds;
	}

	public String[] getGoTagNames() {
		return goTagNames;
	}

	public void setGoTagNames(String[] goTagNames) {
		this.goTagNames = goTagNames;
	}

	public String[] getPolyTagIds() {
		return polyTagIds;
	}

	public void setPolyTagIds(String[] polyTagIds) {
		this.polyTagIds = polyTagIds;
	}

}
