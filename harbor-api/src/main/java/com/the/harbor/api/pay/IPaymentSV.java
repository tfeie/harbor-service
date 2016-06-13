package com.the.harbor.api.pay;

import com.the.harbor.api.pay.param.CreatePaymentOrderReq;
import com.the.harbor.api.pay.param.CreatePaymentOrderResp;
import com.the.harbor.api.pay.param.NotifyPaymentReq;
import com.the.harbor.base.exception.BusinessException;
import com.the.harbor.base.exception.SystemException;
import com.the.harbor.base.vo.Response;

public interface IPaymentSV {

	@interface CreatePaymentOrder {

	}

	/**
	 * 产生一个支付订单
	 * 
	 * @param createPaymentOrderReq
	 * @return
	 */
	CreatePaymentOrderResp createPaymentOrder(CreatePaymentOrderReq createPaymentOrderReq)
			throws BusinessException, SystemException;

	/**
	 * 接收支付系统的通知结果
	 * 
	 * @param notifyPaymentReq
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	Response notifyPayResult(NotifyPaymentReq notifyPaymentReq) throws BusinessException, SystemException;

}
