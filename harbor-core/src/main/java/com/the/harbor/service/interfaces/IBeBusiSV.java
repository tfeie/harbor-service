package com.the.harbor.service.interfaces;

import com.the.harbor.api.be.param.Be;
import com.the.harbor.api.be.param.BeCreateReq;
import com.the.harbor.api.be.param.QueryMyBeReq;
import com.the.harbor.base.vo.PageInfo;

public interface IBeBusiSV {

	String createBe(BeCreateReq beCreateReq); 
}
