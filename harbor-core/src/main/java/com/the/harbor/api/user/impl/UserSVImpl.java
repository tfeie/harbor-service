package com.the.harbor.api.user.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.the.harbor.api.user.IUserSV;
import com.the.harbor.api.user.param.UserCertificationReq;
import com.the.harbor.api.user.param.UserRegReq;
import com.the.harbor.base.exception.BusinessException;
import com.the.harbor.base.exception.SystemException;
import com.the.harbor.base.util.ResponseBuilder;
import com.the.harbor.base.vo.Response;
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

}
