package com.the.harbor.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.mns.client.CloudQueue;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.common.ClientException;
import com.aliyun.mns.common.ServiceException;
import com.aliyun.mns.model.Message;
import com.the.harbor.api.go.param.CreateGoPaymentOrderReq;
import com.the.harbor.api.go.param.DoGoFavorite;
import com.the.harbor.api.go.param.DoGoView;
import com.the.harbor.api.go.param.Go;
import com.the.harbor.api.go.param.GoCreateReq;
import com.the.harbor.api.go.param.GoDetail;
import com.the.harbor.api.go.param.GoOrderConfirmReq;
import com.the.harbor.api.go.param.GoOrderCreateReq;
import com.the.harbor.api.go.param.GoOrderFinishReq;
import com.the.harbor.api.go.param.GoOrderMeetLocaltionConfirmReq;
import com.the.harbor.api.go.param.GoOrderMeetLocaltionReq;
import com.the.harbor.api.go.param.GoTag;
import com.the.harbor.api.go.param.UpdateGoOrderPayReq;
import com.the.harbor.api.pay.param.CreatePaymentOrderReq;
import com.the.harbor.api.user.param.UserViewInfo;
import com.the.harbor.base.enumeration.hygo.GoType;
import com.the.harbor.base.enumeration.hygo.OrgMode;
import com.the.harbor.base.enumeration.hygo.PayMode;
import com.the.harbor.base.enumeration.hygo.Status;
import com.the.harbor.base.enumeration.hygoorder.OrderStatus;
import com.the.harbor.base.enumeration.hynotify.AccepterType;
import com.the.harbor.base.enumeration.hynotify.NotifyType;
import com.the.harbor.base.enumeration.hynotify.SenderType;
import com.the.harbor.base.enumeration.hytags.TagType;
import com.the.harbor.base.exception.BusinessException;
import com.the.harbor.commons.components.aliyuncs.mns.MNSFactory;
import com.the.harbor.commons.components.globalconfig.GlobalSettings;
import com.the.harbor.commons.redisdata.def.DoNotify;
import com.the.harbor.commons.util.AmountUtils;
import com.the.harbor.commons.util.CollectionUtil;
import com.the.harbor.commons.util.DateUtil;
import com.the.harbor.commons.util.StringUtil;
import com.the.harbor.dao.mapper.bo.HyGo;
import com.the.harbor.dao.mapper.bo.HyGoDetail;
import com.the.harbor.dao.mapper.bo.HyGoFavorite;
import com.the.harbor.dao.mapper.bo.HyGoFavoriteCriteria;
import com.the.harbor.dao.mapper.bo.HyGoOrder;
import com.the.harbor.dao.mapper.bo.HyGoOrderCriteria;
import com.the.harbor.dao.mapper.bo.HyGoTags;
import com.the.harbor.dao.mapper.bo.HyGoView;
import com.the.harbor.dao.mapper.interfaces.HyGoDetailMapper;
import com.the.harbor.dao.mapper.interfaces.HyGoFavoriteMapper;
import com.the.harbor.dao.mapper.interfaces.HyGoMapper;
import com.the.harbor.dao.mapper.interfaces.HyGoOrderMapper;
import com.the.harbor.dao.mapper.interfaces.HyGoTagsMapper;
import com.the.harbor.dao.mapper.interfaces.HyGoViewMapper;
import com.the.harbor.service.interfaces.IGoBusiSV;
import com.the.harbor.service.interfaces.IPaymentBusiSV;
import com.the.harbor.service.interfaces.IUserManagerSV;
import com.the.harbor.util.HarborSeqUtil;

@Component
@Transactional
public class GoBusiSVImpl implements IGoBusiSV {

	private static final Logger LOG = LoggerFactory.getLogger(GoBusiSVImpl.class);

	@Autowired
	private transient HyGoDetailMapper hyGoDetailMapper;

	@Autowired
	private transient HyGoTagsMapper hyGoTagsMapper;

	@Autowired
	private transient HyGoMapper hyGoMapper;

	@Autowired
	private transient HyGoOrderMapper hyGoOrderMapper;

	@Autowired
	private transient HyGoViewMapper hyGoViewMapper;

	@Autowired
	private transient HyGoFavoriteMapper hyGoFavoriteMapper;

	@Autowired
	private transient IPaymentBusiSV paymentBusiSV;

	@Autowired
	private transient IUserManagerSV userManagerSV;

	@Override
	public String createGo(GoCreateReq goCreateReq) {
		String goId = HarborSeqUtil.createGoId();
		Timestamp sysdate = DateUtil.getSysDate();
		Go bgo = new Go();
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
		// 复制内容
		BeanUtils.copyProperties(go, bgo);
		// 写表
		hyGoMapper.insert(go);
		/* 2.活动明细 */
		if (!CollectionUtil.isEmpty(goCreateReq.getGoDetails())) {
			// 复制内容
			bgo.setGoDetails(goCreateReq.getGoDetails());
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
			// 复制内容
			bgo.setGoTags(goCreateReq.getGoTags());
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
		// 将GO的数据发送给MNS处理
		this.buildGoIndexBuildMQ(bgo);
		return goId;
	}

	/**
	 * 产生一个GO索引构建消息
	 * 
	 * @param go
	 */
	private void buildGoIndexBuildMQ(Go go) {
		MNSClient client = MNSFactory.getMNSClient();
		try {
			CloudQueue queue = client.getQueueRef(GlobalSettings.getGoIndexBuildQueueName());
			Message message = new Message();
			message.setMessageBody(JSON.toJSONString(go));
			queue.putMessage(message);
		} catch (ClientException ce) {
			LOG.error("Something wrong with the network connection between client and MNS service."
					+ "Please check your network and DNS availablity.", ce);
		} catch (ServiceException se) {
			if (se.getErrorCode().equals("QueueNotExist")) {
				LOG.error("Queue is not exist.Please create before use", se);
			} else if (se.getErrorCode().equals("TimeExpired")) {
				LOG.error("The request is time expired. Please check your local machine timeclock", se);
			}
			LOG.error("Go index build message put in Queue error", se);
		} catch (Exception e) {
			LOG.error("Unknown exception happened!", e);
		}
		client.close();

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
		String payOrderId = null;
		// 判断此业务流水是否已经产生了交易流水
		HyGoOrder goOrder = this.getHyGoOrder(goOrderId);
		if (goOrder == null) {
			throw new BusinessException("GO_0001", "产生支付交易流水失败:预约记录不存在");
		}
		if (!StringUtil.isBlank(goOrder.getPayOrderId())) {
			if (!(OrderStatus.WAIT_PAY.getValue().equals(goOrder.getOrderStatus())
					|| OrderStatus.PAY_FAILURE.getValue().equals(goOrder.getOrderStatus()))) {
				throw new BusinessException("GO_0001", "此活动预约记录已经发起一笔支付交易");
			}
			payOrderId = goOrder.getPayOrderId();
		} else {
			// 产生一笔支付交易流水
			CreatePaymentOrderReq createPaymentOrderReq = new CreatePaymentOrderReq();
			BeanUtils.copyProperties(createGoPaymentOrderReq, createPaymentOrderReq);
			payOrderId = paymentBusiSV.createPaymentOrder(createPaymentOrderReq);
			// 关联上活动预约业务流水
			Timestamp sysdate = DateUtil.getSysDate();
			HyGoOrder record = new HyGoOrder();
			record.setOrderId(goOrderId);
			record.setPayOrderId(payOrderId);
			record.setStsDate(sysdate);
			record.setPayStsDate(sysdate);
			hyGoOrderMapper.updateByPrimaryKeySelective(record);
		}
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
		HyGo hyGo = this.getHyGo(goOrder.getGoId());
		if (hyGo == null) {
			throw new BusinessException("GO_0001", "更新活动支付状态失败:活动不存在");
		}
		if (OrderStatus.WAIT_PAY.getValue().equals(goOrder.getOrderStatus())) {
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

			// 如果支付成功，则发送确认信息给海牛
			if ("SUCCESS".equals(updateGoOrderPayReq.getPayStatus())) {
				// 给海牛通知
				UserViewInfo orderUser = userManagerSV.getUserViewInfoByUserId(goOrder.getUserId());
				DoNotify body = new DoNotify();
				body.setHandleType(DoNotify.HandleType.PUBLISH.name());
				body.setNotifyType(NotifyType.SYSTEM_NOTIFY.getValue());
				body.setSenderType(SenderType.USER.getValue());
				body.setSenderId(goOrder.getUserId());
				body.setAccepterType(AccepterType.USER.getValue());
				body.setAccepterId(hyGo.getUserId());
				body.setTitle("活动预约确认");
				body.setContent("[" + orderUser.getEnName() + "]预约并支付了您发布的一对一活动[" + hyGo.getTopic() + "],请您确认~");
				body.setLink("../go/toHainiuConfirm.html?goOrderId=" + goOrder.getOrderId());
				this.sendNotifyMQ(body);
			}
		}
	}

	@Override
	public void confirmGoOrder(GoOrderConfirmReq goOrderConfirmReq) {
		HyGoOrder hyGoOrder = this.getHyGoOrder(goOrderConfirmReq.getGoOrderId());
		if (hyGoOrder == null) {
			throw new BusinessException("GO_0001", "活动预约信息不存在");
		}
		HyGo hyGo = this.getHyGo(hyGoOrder.getGoId());
		if (hyGo == null) {
			throw new BusinessException("GO_0001", "活动信息不存在");
		}
		if (!hyGo.getUserId().equals(goOrderConfirmReq.getPublishUserId())) {
			throw new BusinessException("操作无效:您不是活动发起方");
		}
		HyGoOrder o = new HyGoOrder();
		o.setOrderId(hyGoOrder.getOrderId());
		if ("confirm".equals(goOrderConfirmReq.getAckFlag())) {
			o.setOrderStatus(OrderStatus.WAIT_MEET.getValue());
		} else if ("reject".equals(goOrderConfirmReq.getAckFlag())) {
			o.setOrderStatus(OrderStatus.REJECT.getValue());
		}
		Timestamp sysdate = DateUtil.getSysDate();
		o.setStsDate(sysdate);
		o.setConfirmDate(sysdate);
		hyGoOrderMapper.updateByPrimaryKeySelective(o);

	}

	@Override
	public void setGoOrderMeetLocaltion(GoOrderMeetLocaltionReq goOrderMeetLocaltionReq) {
		HyGoOrder hyGoOrder = this.getHyGoOrder(goOrderMeetLocaltionReq.getGoOrderId());
		if (hyGoOrder == null) {
			throw new BusinessException("GO_0001", "活动预约信息不存在");
		}
		HyGo hyGo = this.getHyGo(hyGoOrder.getGoId());
		if (hyGo == null) {
			throw new BusinessException("GO_0001", "活动信息不存在");
		}
		if (!hyGo.getUserId().equals(goOrderMeetLocaltionReq.getPublishUserId())) {
			throw new BusinessException("操作无效:您不是活动发起方");
		}
		HyGoOrder o = new HyGoOrder();
		o.setOrderId(hyGoOrder.getOrderId());
		Timestamp sysdate = DateUtil.getSysDate();
		o.setExpectedLocation1(goOrderMeetLocaltionReq.getExpectedLocation1());
		o.setExpectedLocation2(goOrderMeetLocaltionReq.getExpectedLocation2());
		o.setExpectedTime1(goOrderMeetLocaltionReq.getExpectedTime1());
		o.setExpectedTime2(goOrderMeetLocaltionReq.getExpectedTime2());
		o.setConfirmStsDate(sysdate);
		hyGoOrderMapper.updateByPrimaryKeySelective(o);

	}

	@Override
	public void confirmGoOrderMeetLocaltion(GoOrderMeetLocaltionConfirmReq goOrderMeetLocaltionConfirmReq) {
		HyGoOrder hyGoOrder = this.getHyGoOrder(goOrderMeetLocaltionConfirmReq.getGoOrderId());
		if (hyGoOrder == null) {
			throw new BusinessException("GO_0001", "活动预约信息不存在");
		}
		if (!hyGoOrder.getUserId().equals(goOrderMeetLocaltionConfirmReq.getUserId())) {
			throw new BusinessException("操作无效:您不是活动预约者");
		}
		HyGoOrder o = new HyGoOrder();
		o.setOrderId(hyGoOrder.getOrderId());
		Timestamp sysdate = DateUtil.getSysDate();
		o.setConfirmTime(goOrderMeetLocaltionConfirmReq.getConfirmTime());
		o.setConfirmLocation(goOrderMeetLocaltionConfirmReq.getConfirmLocation());
		o.setConfirmStsDate(sysdate);
		hyGoOrderMapper.updateByPrimaryKeySelective(o);
	}

	@Override
	public void finishGoOrder(GoOrderFinishReq goOrderFinishReq) {
		HyGoOrder hyGoOrder = this.getHyGoOrder(goOrderFinishReq.getGoOrderId());
		if (hyGoOrder == null) {
			throw new BusinessException("活动预约信息不存在");
		}
		HyGo hyGo = this.getHyGo(hyGoOrder.getGoId());
		if (hyGo == null) {
			throw new BusinessException("活动信息不存在");
		}
		if (!hyGo.getUserId().equals(goOrderFinishReq.getUserId())) {
			throw new BusinessException("您不是活动发起方，无法结束服务");
		}
		if (StringUtil.isBlank(hyGoOrder.getConfirmLocation())) {
			throw new BusinessException("小白可能没有确认约见地点，暂时不能结束服务");
		}
		HyGoOrder o = new HyGoOrder();
		o.setOrderId(hyGoOrder.getOrderId());
		Timestamp sysdate = DateUtil.getSysDate();
		o.setOrderStatus(OrderStatus.FINISH.getValue());
		o.setStsDate(sysdate);
		hyGoOrderMapper.updateByPrimaryKeySelective(o);
	}

	@Override
	public void processDoGoFavoriteMQ(DoGoFavorite doGoFavorite) {
		if (DoGoFavorite.HandleType.DO.name().equals(doGoFavorite.getHandleType())) {
			// 如果是收藏，则记录
			HyGoFavorite record = new HyGoFavorite();
			record.setGoId(doGoFavorite.getGoId());
			record.setCreateDate(doGoFavorite.getTime() == null ? DateUtil.getSysDate() : doGoFavorite.getTime());
			record.setFavoriteId(HarborSeqUtil.createGoFavoriteId());
			record.setUserId(doGoFavorite.getUserId());
			hyGoFavoriteMapper.insert(record);
		} else if (DoGoFavorite.HandleType.CANCEL.name().equals(doGoFavorite.getHandleType())) {
			// 如果是取消收藏，则删除
			if (!StringUtil.isBlank(doGoFavorite.getUserId()) && !StringUtil.isBlank(doGoFavorite.getGoId())) {
				HyGoFavoriteCriteria sql = new HyGoFavoriteCriteria();
				sql.or().andUserIdEqualTo(doGoFavorite.getUserId()).andGoIdEqualTo(doGoFavorite.getGoId());
				hyGoFavoriteMapper.deleteByExample(sql);
			}
		}
	}

	@Override
	public void processDoGoView(DoGoView doGoView) {
		HyGoView record = new HyGoView();
		record.setCreateDate(doGoView.getTime() == null ? DateUtil.getSysDate() : doGoView.getTime());
		record.setViewId(HarborSeqUtil.createGoViewId());
		record.setUserId(doGoView.getUserId());
		record.setGoId(doGoView.getGoId());
		hyGoViewMapper.insert(record);

	}

	private void sendNotifyMQ(DoNotify body) {
		MNSClient client = MNSFactory.getMNSClient();
		try {
			CloudQueue queue = client.getQueueRef(GlobalSettings.getNotifyQueueName());
			Message message = new Message();
			message.setMessageBody(JSONObject.toJSONString(body));
			queue.putMessage(message);
		} catch (ClientException ce) {
			LOG.error("Something wrong with the network connection between client and MNS service."
					+ "Please check your network and DNS availablity.", ce);
		} catch (ServiceException se) {
			if (se.getErrorCode().equals("QueueNotExist")) {
				LOG.error("Queue is not exist.Please create before use", se);
			} else if (se.getErrorCode().equals("TimeExpired")) {
				LOG.error("The request is time expired. Please check your local machine timeclock", se);
			}
			LOG.error("notify message put in Queue error", se);
		} catch (Exception e) {
			LOG.error("Unknown exception happened!", e);
		}
		client.close();
	}

}
