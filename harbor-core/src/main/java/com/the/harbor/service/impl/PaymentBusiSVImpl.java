package com.the.harbor.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.the.harbor.api.pay.param.CreatePaymentOrderReq;
import com.the.harbor.base.enumeration.hypaymentorder.PayStatus;
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

}
