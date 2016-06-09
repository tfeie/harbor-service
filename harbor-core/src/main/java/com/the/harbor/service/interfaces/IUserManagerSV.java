package com.the.harbor.service.interfaces;

import com.the.harbor.api.user.param.UserCertificationReq;
import com.the.harbor.api.user.param.UserRegReq;
import com.the.harbor.api.user.param.UserSystemTagQueryReq;
import com.the.harbor.api.user.param.UserSystemTagQueryResp;
import com.the.harbor.api.user.param.UserSystemTagSubmitReq;
import com.the.harbor.dao.mapper.bo.HyUser;

public interface IUserManagerSV {

	/**
	 * 用户注册
	 * 
	 * @param userRegReq
	 * @return
	 * @author zhangchao
	 */
	String userRegister(UserRegReq userRegReq);

	/**
	 * 根据微信号获取已经注册用户资料
	 * 
	 * @param weixin
	 * @return
	 * @author zhangchao
	 */
	HyUser getUserByWeixin(String weixin);

	/**
	 * 提交认证材料
	 * 
	 * @param userCertificationReq
	 */
	void submitUserCertification(UserCertificationReq userCertificationReq);

	/**
	 * 提交用户系统级别技能标签选择
	 * 
	 * @param userSystemTagReq
	 */
	void submitUserSelectedSystemTags(UserSystemTagSubmitReq userSystemTagReq);

	/**
	 * 获取用户系统级别的标签
	 * 
	 * @param userSystemTagQueryReq
	 * @return
	 */
	UserSystemTagQueryResp queryUserSystemTags(UserSystemTagQueryReq userSystemTagQueryReq);

}
