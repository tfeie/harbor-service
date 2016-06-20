package com.the.harbor.service.interfaces;

import com.the.harbor.api.go.param.CreateGoPaymentOrderReq;
import com.the.harbor.api.go.param.GoCreateReq;
import com.the.harbor.api.go.param.GoOrderCreateReq;
import com.the.harbor.dao.mapper.bo.HyGo;
import com.the.harbor.dao.mapper.bo.HyGoOrder;

public interface IGoBusiSV {

	String createGo(GoCreateReq goCreateReq);

	HyGoOrder getHyGoOrder(String userId, String goId);

	String orderOneOnOne(GoOrderCreateReq goOrderCreateReq);

	String createGoPaymentOrder(CreateGoPaymentOrderReq createGoPaymentOrderReq);

	HyGoOrder getHyGoOrder(String orderId);

	HyGo getHyGo(String goId);

}
