package com.the.harbor.api.go;

import javax.validation.constraints.NotNull;

import com.the.harbor.api.go.param.CreateGoPaymentOrderReq;
import com.the.harbor.api.go.param.CreateGoPaymentOrderResp;
import com.the.harbor.api.go.param.GoCreateReq;
import com.the.harbor.api.go.param.GoCreateResp;
import com.the.harbor.api.go.param.GoOrderCheckReq;
import com.the.harbor.api.go.param.GoOrderCheckResp;
import com.the.harbor.api.go.param.GoOrderCreateReq;
import com.the.harbor.api.go.param.GoOrderCreateResp;
import com.the.harbor.api.go.param.GoOrderQueryReq;
import com.the.harbor.api.go.param.GoOrderQueryResp;
import com.the.harbor.api.go.param.UpdateGoOrderPayReq;
import com.the.harbor.base.exception.BusinessException;
import com.the.harbor.base.exception.SystemException;
import com.the.harbor.base.vo.Response;

public interface IGoSV {

	@interface CreateGo {

	}

	@interface CheckUserJoinGo {

	}

	@interface OrderOneOnOne {

	}

	@interface CreateGoPaymentOrder {

	}

	@interface QueryGoOrderDetail {

	}

	@interface UpdateGoOrderPay {

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
	 * 校验用户是否参与了某个活动
	 * 
	 * @param goOrderCheckReq
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	GoOrderCheckResp checkUserJoinGo(@NotNull(message = "参数为空") GoOrderCheckReq goOrderCheckReq)
			throws BusinessException, SystemException;

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
	GoOrderQueryResp queryGoOrderDetail(@NotNull(message = "参数为空") GoOrderQueryReq goOrderQueryReq)
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
