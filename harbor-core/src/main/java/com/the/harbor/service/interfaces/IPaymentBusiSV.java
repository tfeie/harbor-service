package com.the.harbor.service.interfaces;

import com.the.harbor.api.pay.param.CreatePaymentOrderReq;
import com.the.harbor.api.pay.param.NotifyPaymentReq;
import com.the.harbor.dao.mapper.bo.HyPaymentOrder;

public interface IPaymentBusiSV {

	String createPaymentOrder(CreatePaymentOrderReq createPaymentOrderReq);

	HyPaymentOrder getHyPaymentOrder(String payOrderId);

	void updateByPrimaryKeySelective(HyPaymentOrder order);
	
	void notifyPayResult(NotifyPaymentReq notifyPaymentReq);

}
