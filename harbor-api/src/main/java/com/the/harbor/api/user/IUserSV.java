package com.the.harbor.api.user;

import com.the.harbor.api.user.param.UserCertificationReq;
import com.the.harbor.api.user.param.UserRegReq;
import com.the.harbor.base.exception.BusinessException;
import com.the.harbor.base.exception.SystemException;
import com.the.harbor.base.vo.Response;

public interface IUserSV {

	@interface UserRegister {
	}

	@interface SubmitUserCertification {
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

}
