package com.the.harbor.api.be;

import javax.validation.constraints.NotNull;

import com.the.harbor.api.be.param.BeCreateReq;
import com.the.harbor.api.be.param.BeCreateResp;
import com.the.harbor.api.be.param.QueryMyBeReq;
import com.the.harbor.api.be.param.QueryMyBeResp;
import com.the.harbor.base.exception.BusinessException;
import com.the.harbor.base.exception.SystemException;

public interface IBeSV {

	@interface CreateBe {

	}

	@interface QueryMyBe {

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

	/**
	 * 查询我的BE信息
	 * 
	 * @param queryMyBeReq
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	QueryMyBeResp queryMyBe(@NotNull(message = "参数为空") QueryMyBeReq queryMyBeReq)
			throws BusinessException, SystemException;

}
