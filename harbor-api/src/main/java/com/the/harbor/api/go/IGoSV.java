package com.the.harbor.api.go;

import javax.validation.constraints.NotNull;

import com.the.harbor.api.go.param.CreateGoPaymentOrderReq;
import com.the.harbor.api.go.param.CreateGoPaymentOrderResp;
import com.the.harbor.api.go.param.GoCreateReq;
import com.the.harbor.api.go.param.GoCreateResp;
import com.the.harbor.api.go.param.GoOrderCreateReq;
import com.the.harbor.api.go.param.GoOrderCreateResp;
import com.the.harbor.api.go.param.GoOrderQueryReq;
import com.the.harbor.api.go.param.GoOrderQueryResp;
import com.the.harbor.api.go.param.GoQueryReq;
import com.the.harbor.api.go.param.GoQueryResp;
import com.the.harbor.api.go.param.UpdateGoOrderPayReq;
import com.the.harbor.base.exception.BusinessException;
import com.the.harbor.base.exception.SystemException;
import com.the.harbor.base.vo.Response;

public interface IGoSV {

	@interface CreateGo {

	}

	@interface QueryGo {

	}

	@interface OrderOneOnOne {

	}

	@interface CreateGoPaymentOrder {

	}

	@interface QueryGoOrder {

	}

	@interface UpdateGoOrderPay {

	}
	
	@interface QueryUserOrderGo {
		
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

	/**
	 * 查询GO信息
	 * 
	 * @param goQueryReq
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	GoQueryResp queryGo(@NotNull(message = "参数为空") GoQueryReq goQueryReq) throws BusinessException, SystemException;

	/**
	 * 预约一个OneOnOne的活动
	 * 
	 * @param goOrderCreateReq
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	GoOrderCreateResp orderOneOnOne(@NotNull(message = "参数为空") GoOrderCreateReq goOrderCreateReq)
			throws BusinessException, SystemException;

	/**
	 * 产生一个OneOnOne活动的支付流水
	 * 
	 * @param createGoPaymentOrderReq
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	CreateGoPaymentOrderResp createGoPaymentOrder(
			@NotNull(message = "参数为空") CreateGoPaymentOrderReq createGoPaymentOrderReq)
			throws BusinessException, SystemException;

	/**
	 * 查询One on One活动预约信息
	 * 
	 * @param goOrderQueryReq
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	GoOrderQueryResp queryGoOrder(@NotNull(message = "参数为空") GoOrderQueryReq goOrderQueryReq)
			throws BusinessException, SystemException;
	
	/**
	 * 查询用户订购的某个活动
	 * @param goOrderQueryReq
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	GoOrderQueryResp queryUserOrderGo(@NotNull(message = "参数为空") GoOrderQueryReq goOrderQueryReq)
			throws BusinessException, SystemException;

	/**
	 * 更新活动支付状态
	 * 
	 * @param updateGoOrderPayReq
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	Response updateGoOrderPay(@NotNull(message = "参数为空") UpdateGoOrderPayReq updateGoOrderPayReq)
			throws BusinessException, SystemException;

}
