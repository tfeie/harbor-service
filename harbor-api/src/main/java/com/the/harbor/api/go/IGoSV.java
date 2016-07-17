package com.the.harbor.api.go;

import javax.validation.constraints.NotNull;

import com.the.harbor.api.go.param.CheckUserOrderGoReq;
import com.the.harbor.api.go.param.CheckUserOrderGoResp;
import com.the.harbor.api.go.param.CreateGoPaymentOrderReq;
import com.the.harbor.api.go.param.CreateGoPaymentOrderResp;
import com.the.harbor.api.go.param.GoCreateReq;
import com.the.harbor.api.go.param.GoCreateResp;
import com.the.harbor.api.go.param.GoOrderConfirmReq;
import com.the.harbor.api.go.param.GoOrderCreateReq;
import com.the.harbor.api.go.param.GoOrderCreateResp;
import com.the.harbor.api.go.param.GoOrderFinishReq;
import com.the.harbor.api.go.param.GoOrderMeetLocaltionConfirmReq;
import com.the.harbor.api.go.param.GoOrderMeetLocaltionReq;
import com.the.harbor.api.go.param.GoOrderQueryReq;
import com.the.harbor.api.go.param.GoOrderQueryResp;
import com.the.harbor.api.go.param.GoQueryReq;
import com.the.harbor.api.go.param.GoQueryResp;
import com.the.harbor.api.go.param.GroupApplyReq;
import com.the.harbor.api.go.param.GroupApplyResp;
import com.the.harbor.api.go.param.QueryGoReq;
import com.the.harbor.api.go.param.QueryGoResp;
import com.the.harbor.api.go.param.QueryMyFavorGoReq;
import com.the.harbor.api.go.param.QueryMyFavorGoResp;
import com.the.harbor.api.go.param.QueryMyGoReq;
import com.the.harbor.api.go.param.QueryMyGoResp;
import com.the.harbor.api.go.param.QueryMyJointGoReq;
import com.the.harbor.api.go.param.QueryMyJointGoResp;
import com.the.harbor.api.go.param.UpdateGoJoinPayReq;
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

	@interface UpdateGoJoinPay {

	}

	@interface QueryUserOrderGo {

	}

	@interface ConfirmGoOrder {

	}

	@interface SetGoOrderMeetLocaltion {

	}

	@interface ConfirmGoOrderMeetLocaltion {

	}

	@interface FinishGoOrder {

	}

	@interface QueryMyGoes {

	}

	@interface QueryGoes {

	}

	@interface ApplyGroup {

	}

	@interface CheckUserOrderGo {

	}

	@interface QueryMyFavorGoes {

	}

	@interface QueryMyJointGoes {

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
	 * 
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

	/**
	 * 海牛确认
	 * 
	 * @param goOrderConfirmReq
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	Response confirmGoOrder(@NotNull(message = "参数为空") GoOrderConfirmReq goOrderConfirmReq)
			throws BusinessException, SystemException;

	/**
	 * 海牛设置活动预期地点
	 * 
	 * @param goOrderMeetLocaltionReq
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	Response setGoOrderMeetLocaltion(@NotNull(message = "参数为空") GoOrderMeetLocaltionReq goOrderMeetLocaltionReq)
			throws BusinessException, SystemException;

	/**
	 * 小白选择确认活动时间
	 * 
	 * @param goOrderMeetLocaltionConfirmReq
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	Response confirmGoOrderMeetLocaltion(
			@NotNull(message = "参数为空") GoOrderMeetLocaltionConfirmReq goOrderMeetLocaltionConfirmReq)
			throws BusinessException, SystemException;

	/**
	 * 海牛确认活动结束
	 * 
	 * @param goOrderMeetLocaltionConfirmReq
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	Response finishGoOrder(@NotNull(message = "参数为空") GoOrderFinishReq goOrderFinishReq)
			throws BusinessException, SystemException;

	/**
	 * 查询我发表的GO
	 * 
	 * @param queryMyGoReq
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	QueryMyGoResp queryMyGoes(@NotNull(message = "参数为空") QueryMyGoReq queryMyGoReq)
			throws BusinessException, SystemException;

	QueryMyFavorGoResp queryMyFavorGoes(@NotNull(message = "参数为空") QueryMyFavorGoReq queryMyGoReq)
			throws BusinessException, SystemException;

	QueryMyJointGoResp queryMyFavorGoes(@NotNull(message = "参数为空") QueryMyJointGoReq queryMyGoReq)
			throws BusinessException, SystemException;

	/**
	 * 查询活动信息
	 * 
	 * @param queryGoReq
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	QueryGoResp queryGoes(@NotNull(message = "参数为空") QueryGoReq queryGoReq) throws BusinessException, SystemException;

	/**
	 * 申请参加GROUP活动
	 * 
	 * @param groupApplyReq
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	GroupApplyResp applyGroup(@NotNull(message = "参数为空") GroupApplyReq groupApplyReq)
			throws BusinessException, SystemException;

	/**
	 * group活动参加支付结果写入
	 * 
	 * @param updateGoJoinPayReq
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	Response updateGoJoinPay(@NotNull(message = "参数为空") UpdateGoJoinPayReq updateGoJoinPayReq)
			throws BusinessException, SystemException;

	/**
	 * 判断用户是否订购了此活动
	 * 
	 * @param checkUserOrderGoReq
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	CheckUserOrderGoResp checkUserOrderGo(@NotNull(message = "参数为空") CheckUserOrderGoReq checkUserOrderGoReq)
			throws BusinessException, SystemException;

}
