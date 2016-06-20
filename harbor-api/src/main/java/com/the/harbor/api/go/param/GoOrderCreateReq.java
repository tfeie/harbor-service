package com.the.harbor.api.go.param;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.the.harbor.api.go.IGoSV;

/**
 * OneOnOne活动预约
 * 
 * @author zhangchao
 *
 */
public class GoOrderCreateReq implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "用户ID为空", groups = { IGoSV.OrderOneOnOne.class })
	private String userId;

	@NotNull(message = "参与的活动ID为空", groups = { IGoSV.OrderOneOnOne.class })
	private String goId;

	@NotNull(message = "想请教的问题为空", groups = { IGoSV.OrderOneOnOne.class })
	private String questions;

	@NotNull(message = "自我介绍为空", groups = { IGoSV.OrderOneOnOne.class })
	private String selfIntro;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getGoId() {
		return goId;
	}

	public void setGoId(String goId) {
		this.goId = goId;
	}

	public String getQuestions() {
		return questions;
	}

	public void setQuestions(String questions) {
		this.questions = questions;
	}

	public String getSelfIntro() {
		return selfIntro;
	}

	public void setSelfIntro(String selfIntro) {
		this.selfIntro = selfIntro;
	}

}
