package com.the.harbor.service.interfaces;

import com.the.harbor.api.go.param.CreateGoPaymentOrderReq;
import com.the.harbor.api.go.param.GoCreateReq;
import com.the.harbor.api.go.param.GoOrderConfirmReq;
import com.the.harbor.api.go.param.GoOrderCreateReq;
import com.the.harbor.api.go.param.GoOrderFinishReq;
import com.the.harbor.api.go.param.GoOrderMeetLocaltionConfirmReq;
import com.the.harbor.api.go.param.GoOrderMeetLocaltionReq;
import com.the.harbor.api.go.param.UpdateGoOrderPayReq;
import com.the.harbor.dao.mapper.bo.HyGo;
import com.the.harbor.dao.mapper.bo.HyGoOrder;

public interface IGoBusiSV {

	String createGo(GoCreateReq goCreateReq);

	HyGoOrder getHyGoOrder(String userId, String goId);

	String orderOneOnOne(GoOrderCreateReq goOrderCreateReq);

	String createGoPaymentOrder(CreateGoPaymentOrderReq createGoPaymentOrderReq);

	HyGoOrder getHyGoOrder(String orderId);

	HyGo getHyGo(String goId);

	void updateGoOrderPay(UpdateGoOrderPayReq updateGoOrderPayReq);

	void confirmGoOrder(GoOrderConfirmReq goOrderConfirmReq);

	void setGoOrderMeetLocaltion(GoOrderMeetLocaltionReq goOrderMeetLocaltionReq);

	void confirmGoOrderMeetLocaltion(GoOrderMeetLocaltionConfirmReq goOrderMeetLocaltionConfirmReq);

	void finishGoOrder(GoOrderFinishReq goOrderFinishReq);

}
