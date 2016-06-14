package com.the.harbor.api.user.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.the.harbor.api.user.IUserSV;
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
import com.the.harbor.api.user.param.UserTag;
import com.the.harbor.api.user.param.UserTagQueryReq;
import com.the.harbor.api.user.param.UserTagQueryResp;
import com.the.harbor.base.constants.ExceptCodeConstants;
import com.the.harbor.base.exception.BusinessException;
import com.the.harbor.base.exception.SystemException;
import com.the.harbor.base.util.ResponseBuilder;
import com.the.harbor.base.vo.Response;
import com.the.harbor.base.vo.ResponseHeader;
import com.the.harbor.commons.util.CollectionUtil;
import com.the.harbor.commons.util.StringUtil;
import com.the.harbor.dao.mapper.bo.HyPaymentOrder;
import com.the.harbor.dao.mapper.bo.HyUser;
import com.the.harbor.service.interfaces.IPaymentBusiSV;
import com.the.harbor.service.interfaces.IUserManagerSV;

@Service(validation = "true")
public class UserSVImpl implements IUserSV {

	@Autowired
	private transient IUserManagerSV userManagerSV;

	@Autowired
	private transient IPaymentBusiSV paymentBusiSV;

	@Override
	public Response userRegister(UserRegReq userRegReq) throws BusinessException, SystemException {
		userManagerSV.userRegister(userRegReq);
		return ResponseBuilder.buildSuccessResponse("用户注册成功");
	}

	@Override
	public Response submitUserCertification(UserCertificationReq userCertificationReq)
			throws BusinessException, SystemException {
		userManagerSV.submitUserCertification(userCertificationReq);
		return ResponseBuilder.buildSuccessResponse("认证材料提交成功");
	}

	@Override
	public Response submitUserSelectedSystemTags(UserSystemTagSubmitReq userSystemTagReq)
			throws BusinessException, SystemException {
		if (userSystemTagReq == null) {
			throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "参数为空");
		}
		if (CollectionUtil.isEmpty(userSystemTagReq.getInterestSelectedTags())) {
			throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "您没有选择任何兴趣标签");
		}
		if (CollectionUtil.isEmpty(userSystemTagReq.getSkillSelectedTags())) {
			throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "您没有选择技能标签");
		}
		int interestTagCount = userSystemTagReq.getInterestSelectedTags().size();
		int skillTagCount = userSystemTagReq.getSkillSelectedTags().size();
		if (interestTagCount == 0) {
			throw new BusinessException("USER-0003", "您至少需要选择1个兴趣标签");
		}
		if (interestTagCount > 5) {
			throw new BusinessException("USER-0003", "您最多只能选择5个兴趣标签");
		}
		if (skillTagCount == 0) {
			throw new BusinessException("USER-0003", "您至少需要选择1个技能标签");
		}
		if (skillTagCount > 5) {
			throw new BusinessException("USER-0003", "您最多只能选择5个技能标签");
		}
		List<UserTag> systemTags = new ArrayList<UserTag>();
		systemTags.addAll(userSystemTagReq.getInterestSelectedTags());
		systemTags.addAll(userSystemTagReq.getSkillSelectedTags());

		userManagerSV.submitUserSelectedSystemTags(userSystemTagReq.getUserId(), systemTags);
		return ResponseBuilder.buildSuccessResponse("用户系统级兴趣技能标签提交成功");
	}

	@Override
	public UserSystemTagQueryResp queryUserSystemTags(UserSystemTagQueryReq userSystemTagQueryReq)
			throws BusinessException, SystemException {
		if (userSystemTagQueryReq == null) {
			throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "参数为空");
		}
		UserSystemTagQueryResp resp = userManagerSV.queryUserSystemTags(userSystemTagQueryReq);
		ResponseHeader responseHeader = ResponseBuilder.buildSuccessResponseHeader("查询成功");
		resp.setResponseHeader(responseHeader);
		return resp;
	}

	@Override
	public UserMemberInfo queryUserMemberInfo(UserMemberQuery query) throws BusinessException, SystemException {
		if (query == null) {
			throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "参数为空");
		}
		UserMemberInfo member = userManagerSV.queryUserMemberInfo(query.getUserId());
		ResponseHeader responseHeader = ResponseBuilder.buildSuccessResponseHeader("查询成功");
		member.setResponseHeader(responseHeader);
		return member;
	}

	@Override
	public UserQueryResp queryUserInfoByOpenId(String openId) throws BusinessException, SystemException {
		if (StringUtil.isBlank(openId)) {
			throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "用户微信openId为空");
		}
		UserInfo userInfo = null;
		HyUser hyUser = userManagerSV.getUserByWeixin(openId);
		if (hyUser != null) {
			userInfo = new UserInfo();
			BeanUtils.copyProperties(hyUser, userInfo);
		}
		UserQueryResp resp = new UserQueryResp();
		resp.setUserInfo(userInfo);
		resp.setResponseHeader(ResponseBuilder.buildSuccessResponseHeader("查询成功"));
		return resp;
	}

	@Override
	public UserMemberRenewalResp userMemberRenewal(UserMemberRenewalReq userMemberRenewalReq)
			throws BusinessException, SystemException {
		if (userMemberRenewalReq == null) {
			throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "参数为空");
		}
		if (userMemberRenewalReq.getPayMonth() == 0) {
			throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "您的购买时长为0个月，请重选");
		}
		// 判断的支付的交易流水是否正确
		HyPaymentOrder payOrder = paymentBusiSV.getHyPaymentOrder(userMemberRenewalReq.getPayOrderId());
		if (payOrder == null) {
			throw new BusinessException("PAY_00001", "您输入的会员支付交易流水不正确，无法续期");
		}
		// 执行续期动作
		UserMemberRenewalResp resp = userManagerSV.userMemberRenewal(userMemberRenewalReq);
		resp.setResponseHeader(ResponseBuilder.buildSuccessResponseHeader("会员续期成功"));
		return resp;
	}

	@Override
	public UserQueryResp queryUserInfoByUserId(String userId) throws BusinessException, SystemException {
		if (StringUtil.isBlank(userId)) {
			throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "用户标识为空");
		}
		UserInfo userInfo = null;
		HyUser hyUser = userManagerSV.getUserInfo(userId);
		if (hyUser != null) {
			userInfo = new UserInfo();
			BeanUtils.copyProperties(hyUser, userInfo);
		}
		UserQueryResp resp = new UserQueryResp();
		resp.setUserInfo(userInfo);
		resp.setResponseHeader(ResponseBuilder.buildSuccessResponseHeader("查询成功"));
		return resp;
	}

	@Override
	public Response userEdit(UserEditReq userEditReq) throws BusinessException, SystemException {
		if (userEditReq == null) {
			throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "参数为空");
		}
		if (CollectionUtil.isEmpty(userEditReq.getInterestSelectedTags())) {
			throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "您没有选择任何兴趣标签");
		}
		if (CollectionUtil.isEmpty(userEditReq.getSkillSelectedTags())) {
			throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "您没有选择技能标签");
		}
		int interestTagCount = userEditReq.getInterestSelectedTags().size();
		int skillTagCount = userEditReq.getSkillSelectedTags().size();
		if (interestTagCount == 0) {
			throw new BusinessException("USER-0003", "您至少需要选择1个兴趣标签");
		}
		if (interestTagCount > 5) {
			throw new BusinessException("USER-0003", "您最多只能选择5个兴趣标签");
		}
		if (skillTagCount == 0) {
			throw new BusinessException("USER-0003", "您至少需要选择1个技能标签");
		}
		if (skillTagCount > 5) {
			throw new BusinessException("USER-0003", "您最多只能选择5个技能标签");
		}
		userManagerSV.userEdit(userEditReq);
		return ResponseBuilder.buildSuccessResponse("用户资料编辑成功");
	}

	@Override
	public UserTagQueryResp queryUserTags(UserTagQueryReq userTagQueryReq) throws BusinessException, SystemException {
		if (userTagQueryReq == null) {
			throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "参数为空");
		}
		UserTagQueryResp resp = userManagerSV.queryUserTags(userTagQueryReq);
		ResponseHeader responseHeader = ResponseBuilder.buildSuccessResponseHeader("查询成功");
		resp.setResponseHeader(responseHeader);
		return resp;
	}

}
