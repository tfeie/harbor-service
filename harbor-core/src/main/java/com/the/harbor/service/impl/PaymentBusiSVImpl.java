package com.the.harbor.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.the.harbor.api.go.param.UpdateGoJoinPayReq;
import com.the.harbor.api.go.param.UpdateGoOrderPayReq;
import com.the.harbor.api.pay.param.CreatePaymentOrderReq;
import com.the.harbor.api.pay.param.NotifyPaymentReq;
import com.the.harbor.api.user.param.DoUserAssetsTrade;
import com.the.harbor.base.enumeration.hypaymentorder.BusiType;
import com.the.harbor.base.enumeration.hypaymentorder.PayStatus;
import com.the.harbor.base.enumeration.hyuser.SystemUser;
import com.the.harbor.base.enumeration.hyuserassets.AssetsType;
import com.the.harbor.base.exception.BusinessException;
import com.the.harbor.commons.util.DateUtil;
import com.the.harbor.dao.mapper.bo.HyPaymentOrder;
import com.the.harbor.dao.mapper.bo.HyUserBuyHb;
import com.the.harbor.dao.mapper.bo.HyUserBuyMember;
import com.the.harbor.dao.mapper.interfaces.HyPaymentOrderMapper;
import com.the.harbor.service.interfaces.IGoBusiSV;
import com.the.harbor.service.interfaces.IPaymentBusiSV;
import com.the.harbor.service.interfaces.IUserManagerSV;
import com.the.harbor.util.HarborSeqUtil;
import com.the.harbor.util.UserAssetsTradeMQSend;

@Component
@Transactional
public class PaymentBusiSVImpl implements IPaymentBusiSV {

	@Autowired
	private HyPaymentOrderMapper hyPaymentOrderMapper;

	@Autowired
	private IGoBusiSV goBusiSV;

	@Autowired
	private IUserManagerSV userManagerSV;

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
		p.setSourceNo(createPaymentOrderReq.getSourceNo());
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
		if (BusiType.PAY_FOR_GROUP.getValue().equals(busiType)) {
			// 支付GO的费用
			UpdateGoJoinPayReq updateGoJoinPayReq = new UpdateGoJoinPayReq();
			updateGoJoinPayReq.setGoOrderId(payOrder.getSourceNo());
			updateGoJoinPayReq.setPayOrderId(payOrder.getPayOrderId());
			updateGoJoinPayReq.setPayStatus("SUCCESS");
			goBusiSV.updateGoJoinPay(updateGoJoinPayReq);
		} else if (BusiType.PAY_FOR_HAIBI.getValue().equals(busiType)) {
			// 充值海贝，需要将海贝冲入账户
			String buyOrderId = payOrder.getSourceNo();
			HyUserBuyHb buyHB = userManagerSV.getHyUserBuyHb(buyOrderId);
			if (buyHB != null) {
				DoUserAssetsTrade t = new DoUserAssetsTrade();
				t.setAssetsType(AssetsType.HAIBEI.getValue());
				t.setBusiType(BusiType.PAY_FOR_HAIBI.getValue());
				t.setFromUserId(SystemUser.SYSTEM.getValue());
				t.setHandleType(DoUserAssetsTrade.HandleType.TRANSFER.name());
				t.setSourceNo(buyHB.getBuyOrderId());
				t.setSummary("购买海贝[" + buyHB.getBuyAmount() + "]个");
				t.setToUserId(payOrder.getUserId());
				t.setTradeBalance(buyHB.getBuyAmount());
				UserAssetsTradeMQSend.sendMQ(t);
			}
		} else if (BusiType.PAY_FOR_MEMBER.getValue().equals(busiType)) {
			// 会员续费
			String buyOrderId = payOrder.getSourceNo();
			HyUserBuyMember buyMember = userManagerSV.getHyUserBuyMember(buyOrderId);
			if (buyMember != null) {
				userManagerSV.userMemberRenewal(buyMember);

			}
		} else if (BusiType.PAY_FOR_ONO.getValue().equals(busiType)) {
			//ono活动支付成功处理
			UpdateGoOrderPayReq updateGoOrderPayReq = new UpdateGoOrderPayReq();
			updateGoOrderPayReq.setGoOrderId(payOrder.getSourceNo());
			updateGoOrderPayReq.setPayOrderId(payOrder.getPayOrderId());
			updateGoOrderPayReq.setPayStatus("SUCCESS");
			goBusiSV.updateGoOrderPay(updateGoOrderPayReq);
			
		}
	}

}
