package com.the.harbor.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.the.harbor.api.pay.param.CreatePaymentOrderReq;
import com.the.harbor.api.pay.param.NotifyPaymentReq;
import com.the.harbor.base.enumeration.hypaymentorder.BusiType;
import com.the.harbor.base.enumeration.hypaymentorder.PayStatus;
import com.the.harbor.base.exception.BusinessException;
import com.the.harbor.commons.util.DateUtil;
import com.the.harbor.dao.mapper.bo.HyPaymentOrder;
import com.the.harbor.dao.mapper.interfaces.HyPaymentOrderMapper;
import com.the.harbor.service.interfaces.IPaymentBusiSV;
import com.the.harbor.util.HarborSeqUtil;

@Component
@Transactional
public class PaymentBusiSVImpl implements IPaymentBusiSV {

	@Autowired
	private HyPaymentOrderMapper hyPaymentOrderMapper;

	@Override
	public String createPaymentOrder(CreatePaymentOrderReq createPaymentOrderReq) {
		String payOrderId = HarborSeqUtil.createPayOrderId();
		HyPaymentOrder p = new HyPaymentOrder();
		p.setPayOrderId(payOrderId);
		p.setBusiType(createPaymentOrderReq.getBusiType());
		p.setPayAmount(createPaymentOrderReq.getPayAmount());
		p.setPayDate(DateUtil.getSysDate());
		p.setPayStatus(PayStatus.NO_PAY.getValue());
		p.setPayType(createPaymentOrderReq.getPayType());
		p.setSummary(createPaymentOrderReq.getSummary());
		p.setUserId(createPaymentOrderReq.getUserId());
		hyPaymentOrderMapper.insertSelective(p);
		return payOrderId;
	}

	@Override
	public HyPaymentOrder getHyPaymentOrder(String payOrderId) {
		return hyPaymentOrderMapper.selectByPrimaryKey(payOrderId);
	}

	@Override
	public void updateByPrimaryKeySelective(HyPaymentOrder order) {
		hyPaymentOrderMapper.updateByPrimaryKeySelective(order);
	}

	@Override
	public void notifyPayResult(NotifyPaymentReq notifyPaymentReq) {
		HyPaymentOrder payOrder = this.getHyPaymentOrder(notifyPaymentReq.getPayOrderId());
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
					// 如果收到支付成功的消息，则进行业务处理
					this.callbackSuccessPay(payOrder);
					payOrder.setPayStatus(PayStatus.SUCCESS_PAY.getValue());
				} else if ("FAIL".equals(payOrder.getReturnCode())) {
					payOrder.setPayStatus(PayStatus.FAILURE_PAY.getValue());
				}
			} else if ("FAIL".equals(payOrder.getReturnCode())) {
				payOrder.setPayStatus(PayStatus.FAILURE_PAY.getValue());
			}
			this.updateByPrimaryKeySelective(payOrder);
		}
	}

	private void callbackSuccessPay(HyPaymentOrder payOrder) {
		String busiType = payOrder.getBusiType();
		if(BusiType.PAY_FOR_GROUP.getValue().equals(busiType)){
			//支付GO的费用
			
		}
	}

}
