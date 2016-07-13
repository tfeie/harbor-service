package com.the.harbor.api.user;

import java.util.List;

import com.the.harbor.api.user.param.UserCertificationReq;
import com.the.harbor.api.user.param.UserEditReq;
import com.the.harbor.api.user.param.UserInfo;
import com.the.harbor.api.user.param.UserMemberInfo;
import com.the.harbor.api.user.param.UserMemberQuery;
import com.the.harbor.api.user.param.UserMemberRenewalReq;
import com.the.harbor.api.user.param.UserMemberRenewalResp;
import com.the.harbor.api.user.param.UserQueryResp;
import com.the.harbor.api.user.param.UserRegReq;
import com.the.harbor.api.user.param.UserSystemTagQueryReq;
import com.the.harbor.api.user.param.UserSystemTagQueryResp;
import com.the.harbor.api.user.param.UserSystemTagSubmitReq;
import com.the.harbor.api.user.param.UserTagQueryReq;
import com.the.harbor.api.user.param.UserTagQueryResp;
import com.the.harbor.api.user.param.UserViewInfo;
import com.the.harbor.api.user.param.UserViewResp;
import com.the.harbor.api.user.param.UserWealthQueryReq;
import com.the.harbor.api.user.param.UserWealthQueryResp;
import com.the.harbor.base.exception.BusinessException;
import com.the.harbor.base.exception.SystemException;
import com.the.harbor.base.vo.Response;

public interface IUserSV {

	@interface UserRegister {
	}

	@interface UserEdit {

	}

	@interface SubmitUserCertification {
	}

	@interface SumitUserSelectedSystemTags {

	}

	@interface QueryUserSystemTags {

	}

	@interface QueryUserMemberInfo {

	}

	@interface UserMemberRenewal {

	}

	@interface QueryUserTags {

	}

	@interface QueryUserWealth {

	}

	/**
	 * 用户注册
	 * 
	 * @param userRegReq
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author zhangchao
	 */
	Response userRegister(UserRegReq userRegReq) throws BusinessException, SystemException;

	/**
	 * 用户提交认证材料
	 * 
	 * @param userCertificationReq
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	Response submitUserCertification(UserCertificationReq userCertificationReq)
			throws BusinessException, SystemException;

	/**
	 * 用户选择系统预设的标签技能
	 * 
	 * @param userSystemTagReq
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	Response submitUserSelectedSystemTags(UserSystemTagSubmitReq userSystemTagReq)
			throws BusinessException, SystemException;

	/**
	 * 获取用户设定的系统级别标签
	 * 
	 * @param userSystemTagQueryReq
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	UserSystemTagQueryResp queryUserSystemTags(UserSystemTagQueryReq userSystemTagQueryReq)
			throws BusinessException, SystemException;

	/**
	 * 查询会员信息
	 * 
	 * @param query
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	UserMemberInfo queryUserMemberInfo(UserMemberQuery query) throws BusinessException, SystemException;

	/**
	 * 根据OPENID获取用户信息
	 * 
	 * @param openId
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	UserQueryResp queryUserInfoByOpenId(String openId) throws BusinessException, SystemException;

	/**
	 * 会员续期服务
	 * 
	 * @param userMemberRenewalReq
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	UserMemberRenewalResp userMemberRenewal(UserMemberRenewalReq userMemberRenewalReq)
			throws BusinessException, SystemException;

	/**
	 * 根据userId获取用户信息
	 * 
	 * @param openId
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	UserQueryResp queryUserInfoByUserId(String userId) throws BusinessException, SystemException;

	/**
	 * 编辑用户资料
	 * 
	 * @param userEditReq
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	Response userEdit(UserEditReq userEditReq) throws BusinessException, SystemException;

	/**
	 * 获取用户选择的所有标签
	 * 
	 * @param userTagQueryReq
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	UserTagQueryResp queryUserTags(UserTagQueryReq userTagQueryReq) throws BusinessException, SystemException;

	/**
	 * 查询用户信息
	 * 
	 * @param userId
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	UserViewResp queryUserViewByUserId(String userId) throws BusinessException, SystemException;

	/**
	 * 查询用户信息
	 * 
	 * @param userId
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	UserViewResp queryUserViewByOpenId(String openId) throws BusinessException, SystemException;

	/**
	 * 查询用户财富
	 * 
	 * @param req
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	UserWealthQueryResp queryUserWealth(UserWealthQueryReq req) throws BusinessException, SystemException;

	/**
	 * 根据用户状态查询信息
	 * @param status
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	List<UserViewInfo> queryUserViewInfosByStatus(String status) throws BusinessException, SystemException;;
	
	/**
	 * 更新用户资料
	 * @param user
	 * @return
	 */
	Response updateUserInfo(UserInfo user);
}
