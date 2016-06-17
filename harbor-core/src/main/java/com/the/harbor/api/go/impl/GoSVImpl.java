package com.the.harbor.api.go.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.the.harbor.api.go.IGoSV;
import com.the.harbor.api.go.param.GoCreateReq;
import com.the.harbor.api.go.param.GoCreateResp;
import com.the.harbor.base.exception.BusinessException;
import com.the.harbor.base.exception.SystemException;
import com.the.harbor.service.interfaces.IGoBusiSV;

@Service(validation = "true")
public class GoSVImpl implements IGoSV {

	@Autowired
	private transient IGoBusiSV goBusiSV;

	@Override
	public GoCreateResp createGo(GoCreateReq goCreateReq) throws BusinessException, SystemException {
		
		return null;
	}
	
	

}
