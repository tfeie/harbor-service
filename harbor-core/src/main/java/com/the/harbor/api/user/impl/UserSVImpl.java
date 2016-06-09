package com.the.harbor.api.user.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.the.harbor.api.user.IUserSV;
import com.the.harbor.api.user.param.UserCertificationReq;
import com.the.harbor.api.user.param.UserRegReq;
import com.the.harbor.api.user.param.UserSystemTagQueryReq;
import com.the.harbor.api.user.param.UserSystemTagQueryResp;
import com.the.harbor.api.user.param.UserSystemTagSubmitReq;
import com.the.harbor.api.user.param.UserTag;
import com.the.harbor.base.constants.ExceptCodeConstants;
import com.the.harbor.base.exception.BusinessException;
import com.the.harbor.base.exception.SystemException;
import com.the.harbor.base.util.ResponseBuilder;
import com.the.harbor.base.vo.Response;
import com.the.harbor.base.vo.ResponseHeader;
import com.the.harbor.commons.util.CollectionUtil;
import com.the.harbor.service.interfaces.IUserManagerSV;

@Service(validation = "true")
public class UserSVImpl implements IUserSV {

	@Autowired
	private transient IUserManagerSV userManagerSV;

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

}
