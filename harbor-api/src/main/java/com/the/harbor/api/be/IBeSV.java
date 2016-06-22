package com.the.harbor.api.be;

import javax.validation.constraints.NotNull;

import com.the.harbor.api.be.param.BeCreateReq;
import com.the.harbor.api.be.param.BeCreateResp;
import com.the.harbor.base.exception.BusinessException;
import com.the.harbor.base.exception.SystemException;

public interface IBeSV {

	@interface CreateBe {

	}

	/**
	 * 发布一个BE
	 * 
	 * @param beCreateReq
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	BeCreateResp createBe(@NotNull(message = "参数为空") BeCreateReq beCreateReq) throws BusinessException, SystemException;

}
