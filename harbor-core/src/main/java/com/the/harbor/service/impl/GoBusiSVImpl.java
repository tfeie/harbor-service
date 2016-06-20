package com.the.harbor.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.the.harbor.api.go.param.CreateGoPaymentOrderReq;
import com.the.harbor.api.go.param.GoCreateReq;
import com.the.harbor.api.go.param.GoDetail;
import com.the.harbor.api.go.param.GoOrderCreateReq;
import com.the.harbor.api.go.param.GoTag;
import com.the.harbor.api.go.param.UpdateGoOrderPayReq;
import com.the.harbor.api.pay.param.CreatePaymentOrderReq;
import com.the.harbor.base.enumeration.hygo.GoType;
import com.the.harbor.base.enumeration.hygo.OrgMode;
import com.the.harbor.base.enumeration.hygo.PayMode;
import com.the.harbor.base.enumeration.hygo.Status;
import com.the.harbor.base.enumeration.hygoorder.OrderStatus;
import com.the.harbor.base.enumeration.hytags.TagType;
import com.the.harbor.base.exception.BusinessException;
import com.the.harbor.commons.util.AmountUtils;
import com.the.harbor.commons.util.CollectionUtil;
import com.the.harbor.commons.util.DateUtil;
import com.the.harbor.commons.util.StringUtil;
import com.the.harbor.dao.mapper.bo.HyGo;
import com.the.harbor.dao.mapper.bo.HyGoDetail;
import com.the.harbor.dao.mapper.bo.HyGoOrder;
import com.the.harbor.dao.mapper.bo.HyGoOrderCriteria;
import com.the.harbor.dao.mapper.bo.HyGoTags;
import com.the.harbor.dao.mapper.interfaces.HyGoDetailMapper;
import com.the.harbor.dao.mapper.interfaces.HyGoMapper;
import com.the.harbor.dao.mapper.interfaces.HyGoOrderMapper;
import com.the.harbor.dao.mapper.interfaces.HyGoTagsMapper;
import com.the.harbor.service.interfaces.IGoBusiSV;
import com.the.harbor.service.interfaces.IPaymentBusiSV;
import com.the.harbor.util.HarborSeqUtil;

@Component
@Transactional
public class GoBusiSVImpl implements IGoBusiSV {

	@Autowired
	private transient HyGoDetailMapper hyGoDetailMapper;

	@Autowired
	private transient HyGoTagsMapper hyGoTagsMapper;

	@Autowired
	private transient HyGoMapper hyGoMapper;

	@Autowired
	private transient HyGoOrderMapper hyGoOrderMapper;

	@Autowired
	private transient IPaymentBusiSV paymentBusiSV;

	@Override
	public String createGo(GoCreateReq goCreateReq) {
		String goId = HarborSeqUtil.createGoId();
		Timestamp sysdate = DateUtil.getSysDate();
		/* 1.活动主表 */
		HyGo go = new HyGo();
		go.setGoId(goId);
		go.setUserId(goCreateReq.getUserId());
		go.setGoType(goCreateReq.getGoType());
		go.setTopic(goCreateReq.getTopic());
		go.setInviteMembers(
				GoType.GROUP.getValue().equals(goCreateReq.getGoType()) ? goCreateReq.getInviteMembers() : null);
		go.setExpectedStartTime(
				GoType.GROUP.getValue().equals(goCreateReq.getGoType()) ? goCreateReq.getExpectedStartTime() : null);
		go.setExpectedDuration(goCreateReq.getExpectedDuration());
		go.setPayMode(goCreateReq.getPayMode());
		if (PayMode.AA.getValue().equals(goCreateReq.getPayMode())
				|| PayMode.FIXED_FEE.getValue().equals(goCreateReq.getPayMode())) {
			go.setFixedPrice(Long.parseLong(AmountUtils.changeY2F(goCreateReq.getPrice())));
		}
		go.setOrgMode(goCreateReq.getOrgMode());
		go.setLocation(OrgMode.OFFLINE.getValue().equals(goCreateReq.getOrgMode()) ? goCreateReq.getLocation() : null);
		go.setMyStory(GoType.ONE_ON_ONE.getValue().equals(goCreateReq.getGoType()) ? goCreateReq.getMyStory() : null);
		go.setCreateDate(sysdate);
		go.setStatus(Status.ING.getValue());
		hyGoMapper.insert(go);
		/* 2.活动明细 */
		if (!CollectionUtil.isEmpty(goCreateReq.getGoDetails())) {
			for (GoDetail d : goCreateReq.getGoDetails()) {
				HyGoDetail gd = new HyGoDetail();
				BeanUtils.copyProperties(d, gd);
				gd.setCreateDate(sysdate);
				gd.setGoId(goId);
				gd.setId(HarborSeqUtil.createGoDetailId());
				gd.setStatus(com.the.harbor.base.enumeration.common.Status.VALID.getValue());
				hyGoDetailMapper.insert(gd);
			}
		}
		/* 3.活动标签 */
		if (!CollectionUtil.isEmpty(goCreateReq.getGoTags())) {
			int sortId = 0;
			for (GoTag d : goCreateReq.getGoTags()) {
				HyGoTags record = new HyGoTags();
				BeanUtils.copyProperties(d, record);
				record.setGoId(goId);
				record.setRecordId(HarborSeqUtil.createHyUserTagsRecordId());
				record.setSortId(sortId);
				record.setTagId(StringUtil.isBlank(d.getTagId()) ? HarborSeqUtil.createTagId(TagType.GO.getValue())
						: d.getTagId());
				record.setStatus(com.the.harbor.base.enumeration.common.Status.VALID.getValue());
				sortId++;
				hyGoTagsMapper.insert(record);
			}
		}

		return goId;
	}

	@Override
	public HyGoOrder getHyGoOrder(String userId, String goId) {
		HyGoOrderCriteria sql = new HyGoOrderCriteria();
		sql.or().andUserIdEqualTo(userId).andGoIdEqualTo(goId);
		List<HyGoOrder> list = hyGoOrderMapper.selectByExample(sql);
		return CollectionUtil.isEmpty(list) ? null : list.get(0);
	}

	@Override
	public String orderOneOnOne(GoOrderCreateReq goOrderCreateReq) {
		HyGo hyGo = this.getHyGo(goOrderCreateReq.getGoId());
		if (hyGo == null) {
			throw new BusinessException("GO_0001", "预约的活动不存在");
		}
		// 判断是否重复参加
		HyGoOrder o = this.getHyGoOrder(goOrderCreateReq.getUserId(), goOrderCreateReq.getGoId());
		if (o != null && !OrderStatus.CANCEL.getValue().equals(o.getOrderStatus())) {
			throw new BusinessException("GO_0001", "您已经预约了此活动");
		}
		String orderId = HarborSeqUtil.createGoOrderId();
		Timestamp sysdate = DateUtil.getSysDate();
		HyGoOrder record = new HyGoOrder();
		record.setOrderId(orderId);
		record.setUserId(goOrderCreateReq.getUserId());
		record.setGoId(goOrderCreateReq.getGoId());
		record.setGoType(hyGo.getGoType());
		record.setOrderStatus(OrderStatus.WAIT_PAY.getValue());// 默认待支付
		record.setQuestions(goOrderCreateReq.getQuestions());
		record.setSelfIntro(goOrderCreateReq.getSelfIntro());
		record.setCreateDate(sysdate);
		record.setStsDate(sysdate);
		hyGoOrderMapper.insert(record);
		return orderId;
	}

	@Override
	public String createGoPaymentOrder(CreateGoPaymentOrderReq createGoPaymentOrderReq) {
		String goOrderId = createGoPaymentOrderReq.getGoOrderId();
		// 判断此业务流水是否已经产生了交易流水
		HyGoOrder goOrder = this.getHyGoOrder(goOrderId);
		if (goOrder == null) {
			throw new BusinessException("GO_0001", "产生支付交易流水失败:预约记录不存在");
		}
		if (!StringUtil.isBlank(goOrder.getPayOrderId())) {
			throw new BusinessException("GO_0001", "此活动预约记录已经发起一笔支付交易[" + goOrder.getPayOrderId() + "]");
		}
		// 产生一笔支付交易流水
		CreatePaymentOrderReq createPaymentOrderReq = new CreatePaymentOrderReq();
		BeanUtils.copyProperties(createGoPaymentOrderReq, createPaymentOrderReq);
		String payOrderId = paymentBusiSV.createPaymentOrder(createPaymentOrderReq);
		// 关联上活动预约业务流水
		Timestamp sysdate = DateUtil.getSysDate();
		HyGoOrder record = new HyGoOrder();
		record.setOrderId(goOrderId);
		record.setPayOrderId(payOrderId);
		record.setStsDate(sysdate);
		record.setPayStsDate(sysdate);
		hyGoOrderMapper.updateByPrimaryKey(record);
		return payOrderId;
	}

	@Override
	public HyGoOrder getHyGoOrder(String orderId) {
		return hyGoOrderMapper.selectByPrimaryKey(orderId);
	}

	@Override
	public HyGo getHyGo(String goId) {
		return hyGoMapper.selectByPrimaryKey(goId);
	}

	@Override
	public void updateGoOrderPay(UpdateGoOrderPayReq updateGoOrderPayReq) {
		HyGoOrder goOrder = this.getHyGoOrder(updateGoOrderPayReq.getGoOrderId());
		if (goOrder == null) {
			throw new BusinessException("GO_0001", "更新活动支付状态失败:预约记录不存在");
		}
		if (!updateGoOrderPayReq.getPayOrderId().equals(goOrder.getPayOrderId())) {
			throw new BusinessException("GO_0001", "更新活动支付状态失败:支付流水不正确");
		}
		if (OrderStatus.WAIT_PAY.equals(goOrder.getOrderStatus())) {
			// 如果原来是待支付状态，支付成功则更改成 待海牛确认；支付失败 更改成支付失败
			String orderStatus = null;
			if ("SUCCESS".equals(updateGoOrderPayReq.getPayStatus())) {
				orderStatus = OrderStatus.WAIT_CONFIRM.getValue();
			} else {
				orderStatus = OrderStatus.PAY_FAILURE.getValue();
			}
			Timestamp sysdate = DateUtil.getSysDate();
			HyGoOrder o = new HyGoOrder();
			o.setOrderId(goOrder.getOrderId());
			o.setOrderStatus(orderStatus);
			o.setStsDate(sysdate);
			o.setPayStsDate(sysdate);
			hyGoOrderMapper.updateByPrimaryKeySelective(o);
		}
	}

}
