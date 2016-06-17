package com.the.harbor.api.go;

import com.the.harbor.api.go.param.GoCreateReq;
import com.the.harbor.api.go.param.GoCreateResp;
import com.the.harbor.base.exception.BusinessException;
import com.the.harbor.base.exception.SystemException;

public interface IGoSV {

	/**
	 * 创建一个新的活动
	 * 
	 * @param goCreateReq
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	GoCreateResp createGo(GoCreateReq goCreateReq) throws BusinessException, SystemException;

}
