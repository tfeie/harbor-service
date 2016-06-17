package com.the.harbor.api.go;

import javax.validation.constraints.NotNull;

import com.the.harbor.api.go.param.GoCreateReq;
import com.the.harbor.api.go.param.GoCreateResp;
import com.the.harbor.base.exception.BusinessException;
import com.the.harbor.base.exception.SystemException;

public interface IGoSV {

	@interface CreateGo {

	}

	/**
	 * 创建一个新的活动
	 * 
	 * @param goCreateReq
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	GoCreateResp createGo(@NotNull(message = "参数为空") GoCreateReq goCreateReq) throws BusinessException, SystemException;

}
