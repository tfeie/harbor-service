package com.the.harbor.api.pay.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.the.harbor.api.pay.IPaymentSV;
import com.the.harbor.api.pay.param.CreatePaymentOrderReq;
import com.the.harbor.api.pay.param.CreatePaymentOrderResp;
import com.the.harbor.api.pay.param.NotifyPaymentReq;
import com.the.harbor.base.constants.ExceptCodeConstants;
import com.the.harbor.base.enumeration.hypaymentorder.PayStatus;
import com.the.harbor.base.exception.BusinessException;
import com.the.harbor.base.exception.SystemException;
import com.the.harbor.base.util.ResponseBuilder;
import com.the.harbor.base.vo.Response;
import com.the.harbor.base.vo.ResponseHeader;
import com.the.harbor.commons.util.DateUtil;
import com.the.harbor.dao.mapper.bo.HyPaymentOrder;
import com.the.harbor.dao.mapper.bo.HyUser;
import com.the.harbor.service.interfaces.IPaymentBusiSV;
import com.the.harbor.service.interfaces.IUserManagerSV;

@Service(validation = "true")
public class PaymentSVImpl implements IPaymentSV {

	@Autowired
	private transient IPaymentBusiSV paymentBusiSV;

	@Autowired
	private transient IUserManagerSV userManagerSV;

	@Override
	public CreatePaymentOrderResp createPaymentOrder(CreatePaymentOrderReq createPaymentOrderReq)
			throws BusinessException, SystemException {
		if (createPaymentOrderReq == null) {
			throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "参数为空");
		}
		// 校验用户编码是否正确
		HyUser user = userManagerSV.getUserInfo(createPaymentOrderReq.getUserId());
		if (user == null) {
			throw new BusinessException("USER_0001", "支付订单创建失败,用户没有注册绑定");
		}
		String payOrderId = paymentBusiSV.createPaymentOrder(createPaymentOrderReq);
		CreatePaymentOrderResp resp = new CreatePaymentOrderResp();
		ResponseHeader responseHeader = ResponseBuilder.buildSuccessResponseHeader("支付交易订单创建成功");
		resp.setResponseHeader(responseHeader);
		resp.setPayOrderId(payOrderId);
		return resp;

	}

	@Override
	public Response notifyPayResult(NotifyPaymentReq notifyPaymentReq) throws BusinessException, SystemException {
		if (notifyPaymentReq == null) {
			throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "参数为空");
		}
		HyPaymentOrder payOrder = paymentBusiSV.getHyPaymentOrder(notifyPaymentReq.getPayOrderId());
		if (payOrder == null) {
			throw new BusinessException("PAY_0001", "接收支付结果通知失败，业务支付流水不存在");
		}
		if (!("SUCCESS".equals(payOrder.getReturnCode()) || "FAIL".equals(payOrder.getReturnCode()))) {
			// 如果不是这两个取值，说明没有接收过微信的支付回调通知,微信侧会二次推送
			payOrder.setNotifyDate(DateUtil.getSysDate());
			payOrder.setTransactionId(notifyPaymentReq.getTransactionId());
			payOrder.setTimeEnd(notifyPaymentReq.getTimeEnd());
			payOrder.setResultCode(notifyPaymentReq.getResultCode());
			payOrder.setReturnCode(notifyPaymentReq.getReturnCode());
			payOrder.setReturnMsg(notifyPaymentReq.getReturnMsg());
			payOrder.setNotifyParam(notifyPaymentReq.getNotifyParam());
			if ("SUCCESS".equals(notifyPaymentReq.getReturnCode())) {
				if ("SUCCESS".equals(notifyPaymentReq.getResultCode())) {
					payOrder.setPayStatus(PayStatus.SUCCESS_PAY.getValue());
				} else if ("FAIL".equals(payOrder.getReturnCode())) {
					payOrder.setPayStatus(PayStatus.FAILURE_PAY.getValue());
				}
			} else if ("FAIL".equals(payOrder.getReturnCode())) {
				payOrder.setPayStatus(PayStatus.FAILURE_PAY.getValue());
			}
		}
		return ResponseBuilder.buildSuccessResponse("支付通知记录成功");
	}

}
