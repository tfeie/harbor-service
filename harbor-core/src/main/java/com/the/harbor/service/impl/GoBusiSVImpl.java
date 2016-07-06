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
import com.aliyun.mns.client.CloudQueue;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.common.ClientException;
import com.aliyun.mns.common.ServiceException;
import com.aliyun.mns.model.Message;
import com.the.harbor.api.go.param.CheckUserOrderGoReq;
import com.the.harbor.api.go.param.CreateGoPaymentOrderReq;
import com.the.harbor.api.go.param.DoGoComment;
import com.the.harbor.api.go.param.DoGoFavorite;
import com.the.harbor.api.go.param.DoGoJoinConfirm;
import com.the.harbor.api.go.param.DoGoView;
import com.the.harbor.api.go.param.Go;
import com.the.harbor.api.go.param.GoComment;
import com.the.harbor.api.go.param.GoCreateReq;
import com.the.harbor.api.go.param.GoDetail;
import com.the.harbor.api.go.param.GoOrderConfirmReq;
import com.the.harbor.api.go.param.GoOrderCreateReq;
import com.the.harbor.api.go.param.GoOrderFinishReq;
import com.the.harbor.api.go.param.GoOrderMeetLocaltionConfirmReq;
import com.the.harbor.api.go.param.GoOrderMeetLocaltionReq;
import com.the.harbor.api.go.param.GoTag;
import com.the.harbor.api.go.param.GroupApplyReq;
import com.the.harbor.api.go.param.GroupApplyResp;
import com.the.harbor.api.go.param.UpdateGoJoinPayReq;
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
import com.the.harbor.base.enumeration.hypaymentorder.BusiType;
import com.the.harbor.base.enumeration.hypaymentorder.PayStatus;
import com.the.harbor.base.enumeration.hypaymentorder.PayType;
import com.the.harbor.base.enumeration.hytags.TagType;
import com.the.harbor.base.exception.BusinessException;
import com.the.harbor.commons.components.aliyuncs.mns.MNSFactory;
import com.the.harbor.commons.components.globalconfig.GlobalSettings;
import com.the.harbor.commons.redisdata.def.DoNotify;
import com.the.harbor.commons.redisdata.util.HyGoUtil;
import com.the.harbor.commons.util.AmountUtils;
import com.the.harbor.commons.util.CollectionUtil;
import com.the.harbor.commons.util.DateUtil;
import com.the.harbor.commons.util.StringUtil;
import com.the.harbor.commons.util.UUIDUtil;
import com.the.harbor.dao.mapper.bo.HyGo;
import com.the.harbor.dao.mapper.bo.HyGoComments;
import com.the.harbor.dao.mapper.bo.HyGoDetail;
import com.the.harbor.dao.mapper.bo.HyGoFavorite;
import com.the.harbor.dao.mapper.bo.HyGoFavoriteCriteria;
import com.the.harbor.dao.mapper.bo.HyGoJoin;
import com.the.harbor.dao.mapper.bo.HyGoJoinCriteria;
import com.the.harbor.dao.mapper.bo.HyGoOrder;
import com.the.harbor.dao.mapper.bo.HyGoOrderCriteria;
import com.the.harbor.dao.mapper.bo.HyGoTags;
import com.the.harbor.dao.mapper.bo.HyGoView;
import com.the.harbor.dao.mapper.bo.HyPaymentOrder;
import com.the.harbor.dao.mapper.interfaces.HyGoCommentsMapper;
import com.the.harbor.dao.mapper.interfaces.HyGoDetailMapper;
import com.the.harbor.dao.mapper.interfaces.HyGoFavoriteMapper;
import com.the.harbor.dao.mapper.interfaces.HyGoJoinMapper;
import com.the.harbor.dao.mapper.interfaces.HyGoMapper;
import com.the.harbor.dao.mapper.interfaces.HyGoOrderMapper;
import com.the.harbor.dao.mapper.interfaces.HyGoTagsMapper;
import com.the.harbor.dao.mapper.interfaces.HyGoViewMapper;
import com.the.harbor.service.interfaces.IGoBusiSV;
import com.the.harbor.service.interfaces.IPaymentBusiSV;
import com.the.harbor.service.interfaces.IUserManagerSV;
import com.the.harbor.util.HarborSeqUtil;
import com.the.harbor.util.NotifyMQSend;

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

	@Autowired
	private transient HyGoCommentsMapper hyGoCommentsMapper;

	@Autowired
	private transient HyGoJoinMapper hyGoJoinMapper;

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
				NotifyMQSend.sendNotifyMQ(body);
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
		UserViewInfo publishUser = userManagerSV.getUserViewInfoByUserId(hyGo.getUserId());
		String title = "";
		String content = "";
		HyGoOrder o = new HyGoOrder();
		o.setOrderId(hyGoOrder.getOrderId());
		if ("confirm".equals(goOrderConfirmReq.getAckFlag())) {
			title = "海牛同意了您的预约";
			content = "[" + publishUser.getEnName() + "]同意了您的预约的活动[" + hyGo.getTopic() + "]";
			o.setOrderStatus(OrderStatus.WAIT_MEET.getValue());
		} else if ("reject".equals(goOrderConfirmReq.getAckFlag())) {
			o.setOrderStatus(OrderStatus.REJECT.getValue());
			title = "海牛拒绝了您的预约";
			content = "[" + publishUser.getEnName() + "]拒绝了您的预约的活动[" + hyGo.getTopic() + "]";
		}
		Timestamp sysdate = DateUtil.getSysDate();
		o.setStsDate(sysdate);
		o.setConfirmDate(sysdate);
		hyGoOrderMapper.updateByPrimaryKeySelective(o);

		/* 海牛确认或拒绝消息发送 */
		DoNotify body = new DoNotify();
		body.setHandleType(DoNotify.HandleType.PUBLISH.name());
		body.setNotifyType(NotifyType.SYSTEM_NOTIFY.getValue());
		body.setSenderType(SenderType.USER.getValue());
		body.setSenderId(hyGo.getUserId());
		body.setAccepterType(AccepterType.USER.getValue());
		body.setAccepterId(hyGoOrder.getUserId());
		body.setTitle(title);
		body.setContent(content);
		NotifyMQSend.sendNotifyMQ(body);

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

		// 将海牛设置的时间与地点信息告知小白
		UserViewInfo publishUser = userManagerSV.getUserViewInfoByUserId(hyGo.getUserId());
		DoNotify body = new DoNotify();
		body.setHandleType(DoNotify.HandleType.PUBLISH.name());
		body.setNotifyType(NotifyType.SYSTEM_NOTIFY.getValue());
		body.setSenderType(SenderType.USER.getValue());
		body.setSenderId(hyGo.getUserId());
		body.setAccepterType(AccepterType.USER.getValue());
		body.setAccepterId(hyGoOrder.getUserId());
		body.setTitle("海牛提交了与您见面的时间地点");
		body.setContent("[" + publishUser.getEnName() + "]提交了您预约的活动[" + hyGo.getTopic() + "]见面的时间与地点，您可以选择确认啦~");
		body.setLink("../go/toConfirm.html?goOrderId=" + hyGoOrder.getOrderId());
		NotifyMQSend.sendNotifyMQ(body);

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
		HyGo hyGo = this.getHyGo(hyGoOrder.getGoId());
		if (hyGo == null) {
			throw new BusinessException("GO_0001", "活动信息不存在");
		}
		HyGoOrder o = new HyGoOrder();
		o.setOrderId(hyGoOrder.getOrderId());
		Timestamp sysdate = DateUtil.getSysDate();
		o.setConfirmTime(goOrderMeetLocaltionConfirmReq.getConfirmTime());
		o.setConfirmLocation(goOrderMeetLocaltionConfirmReq.getConfirmLocation());
		o.setConfirmStsDate(sysdate);
		hyGoOrderMapper.updateByPrimaryKeySelective(o);

		// 将小白设置的时间与地点信息告知海牛
		UserViewInfo orderUser = userManagerSV.getUserViewInfoByUserId(hyGoOrder.getUserId());
		DoNotify body = new DoNotify();
		body.setHandleType(DoNotify.HandleType.PUBLISH.name());
		body.setNotifyType(NotifyType.SYSTEM_NOTIFY.getValue());
		body.setSenderType(SenderType.USER.getValue());
		body.setSenderId(hyGoOrder.getUserId());
		body.setAccepterType(AccepterType.USER.getValue());
		body.setAccepterId(hyGo.getUserId());
		body.setTitle("小白确认了与您见面的时间地点");
		body.setContent("[" + orderUser.getEnName() + "]确认了活动[" + hyGo.getTopic() + "]见面的时间与地点，点击查看~");
		body.setLink("../go/toHainiuAppointment.html?goOrderId=" + hyGoOrder.getOrderId());
		NotifyMQSend.sendNotifyMQ(body);
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
			throw new BusinessException("小白可能没有确认约见，暂时不能结束服务");
		}
		HyGoOrder o = new HyGoOrder();
		o.setOrderId(hyGoOrder.getOrderId());
		Timestamp sysdate = DateUtil.getSysDate();
		o.setOrderStatus(OrderStatus.FINISH.getValue());
		o.setStsDate(sysdate);
		hyGoOrderMapper.updateByPrimaryKeySelective(o);

		// 将海牛确认服务结束后，告知小白
		UserViewInfo publishUser = userManagerSV.getUserViewInfoByUserId(hyGo.getUserId());
		DoNotify body = new DoNotify();
		body.setHandleType(DoNotify.HandleType.PUBLISH.name());
		body.setNotifyType(NotifyType.SYSTEM_NOTIFY.getValue());
		body.setSenderType(SenderType.USER.getValue());
		body.setSenderId(hyGo.getUserId());
		body.setAccepterType(AccepterType.USER.getValue());
		body.setAccepterId(hyGoOrder.getUserId());
		body.setTitle("海牛确认了对您的服务结束");
		body.setContent("[" + publishUser.getEnName() + "]确认了您预约的活动[" + hyGo.getTopic() + "]服务结束，您可以进入互评~");
		body.setLink("../go/toFeedback.html?goOrderId=" + hyGoOrder.getOrderId());
		NotifyMQSend.sendNotifyMQ(body);

		// 海牛确认服务结束后，给自己发送一条消息
		UserViewInfo orderUser = userManagerSV.getUserViewInfoByUserId(hyGoOrder.getUserId());
		body = new DoNotify();
		body.setHandleType(DoNotify.HandleType.PUBLISH.name());
		body.setNotifyType(NotifyType.SYSTEM_NOTIFY.getValue());
		body.setSenderType(SenderType.USER.getValue());
		body.setSenderId(hyGo.getUserId());
		body.setAccepterType(AccepterType.USER.getValue());
		body.setAccepterId(hyGo.getUserId());
		body.setTitle("您确认了服务结束");
		body.setContent("您确认了[" + orderUser.getEnName() + "]预约您活动[" + hyGo.getTopic() + "]服务结束，您可以进入互评~");
		body.setLink("../go/toHainiuFeedback.html?goOrderId=" + hyGoOrder.getOrderId());
		NotifyMQSend.sendNotifyMQ(body);
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

	@Override
	public void processDoGoComment(DoGoComment doGoComment) {
		if (DoGoComment.HandleType.PUBLISH.name().equals(doGoComment.getHandleType())) {
			// 如果是点赞，则记录
			HyGoComments record = new HyGoComments();
			BeanUtils.copyProperties(doGoComment, record);
			record.setCreateDate(doGoComment.getSysdate() == null ? DateUtil.getSysDate() : doGoComment.getSysdate());
			hyGoCommentsMapper.insert(record);

			// 写入REDIS关系
			GoComment b = new GoComment();
			BeanUtils.copyProperties(record, b);
			HyGoUtil.recordGoOrderCommentId(record.getGoId(), record.getOrderId(), record.getCommentId());
			HyGoUtil.recordGoComment(record.getCommentId(), JSON.toJSONString(b));
		} else if (DoGoComment.HandleType.CANCEL.name().equals(doGoComment.getHandleType())) {
			// 如果是取消赞，则删除
			if (!StringUtil.isBlank(doGoComment.getCommentId())) {
				HyGoComments record = hyGoCommentsMapper.selectByPrimaryKey(doGoComment.getCommentId());
				if (record != null) {
					hyGoCommentsMapper.deleteByPrimaryKey(doGoComment.getCommentId());
					// 从REDIS中删除
					HyGoUtil.deleteGoOrderCommentId(record.getGoId(), record.getOrderId(), record.getCommentId());
					HyGoUtil.deleteGoComment(record.getCommentId());
				}
			}
		}

	}

	@Override
	public int getOrderCount(String goId, String goType) {
		int orderCount = 0;
		if (GoType.ONE_ON_ONE.getValue().equals(goType)) {
			HyGoOrderCriteria sql = new HyGoOrderCriteria();
			sql.or().andGoIdEqualTo(goId).andOrderStatusEqualTo(OrderStatus.FINISH.getValue());
			orderCount = hyGoOrderMapper.countByExample(sql);
		} else if (GoType.GROUP.getValue().equals(goType)) {

		}
		return orderCount;
	}

	@Override
	public GroupApplyResp applyGroup(GroupApplyReq groupApplyReq) {
		String userId = groupApplyReq.getUserId();
		String goId = groupApplyReq.getGoId();
		/* 判断用户是否已经报名成功此活动 */
		boolean joint = HyGoUtil.checkUserHadJointGroup(goId, userId);
		if (joint) {
			throw new BusinessException("您已经报名参加此活动了，不能重复报名哦~");
		}
		HyGo hyGo = this.getHyGo(goId);
		if (hyGo == null) {
			throw new BusinessException("活动信息不存在");
		}
		if (!GoType.GROUP.getValue().equals(hyGo.getGoType())) {
			throw new BusinessException("您申请的活动不是Group活动");
		}
		// 判断是否需要支付
		boolean needPay = false;
		String payAmount = "0.00";
		String payOrderId = null;
		String orderId = null;
		if (!PayMode.MY_TREAT.getValue().equals(hyGo.getPayMode())) {
			needPay = true;
			payAmount = AmountUtils.changeF2Y(hyGo.getFixedPrice());
		}
		/* 判断用户是否已经申请了此活动 */
		HyGoJoin applied = this.getApplyHyGoJoin(goId, userId);
		if (applied != null) {
			// 如果已经申请过了，获取申请信息
			HyGoJoin hyGoJoin = this.getApplyHyGoJoin(goId, userId);
			if (hyGoJoin == null) {
				throw new BusinessException("申请参加活动状态异常");
			}
			orderId = hyGoJoin.getOrderId();
			if (needPay) {
				// 如果需要支付,判断是否需要申请新的支付订单信息
				boolean newPayOrderFlag = false;
				payOrderId = hyGoJoin.getPayOrderId();
				if (!StringUtil.isBlank(payOrderId)) {
					// 如果存在支付订单，判断支付订单的状态是否是待支付的
					HyPaymentOrder paymentOrder = paymentBusiSV.getHyPaymentOrder(payOrderId);
					if (paymentOrder == null) {
						newPayOrderFlag = true;
					} else {
						if (!PayStatus.NO_PAY.getValue().equals(paymentOrder.getPayStatus())) {
							newPayOrderFlag = true;
						}
					}
				} else {
					newPayOrderFlag = true;
				}
				if (newPayOrderFlag) {
					// 产生一个新的支付流水
					CreatePaymentOrderReq createPaymentOrderReq = new CreatePaymentOrderReq();
					createPaymentOrderReq.setBusiType(BusiType.PAY_FOR_GO.getValue());
					createPaymentOrderReq.setPayAmount(hyGo.getFixedPrice());
					createPaymentOrderReq.setPayType(PayType.WEIXIN.getValue());
					createPaymentOrderReq.setSummary("GROUP活动[" + hyGo.getGoId() + "]报名缴费");
					createPaymentOrderReq.setUserId(hyGo.getUserId());
					payOrderId = paymentBusiSV.createPaymentOrder(createPaymentOrderReq);

					// 更新到预约订单记录中
					HyGoJoin record = new HyGoJoin();
					record.setPayOrderId(payOrderId);
					record.setOrderId(hyGoJoin.getOrderId());
					hyGoJoinMapper.updateByPrimaryKeySelective(record);
				}
			}
		} else {
			// 如果没有申请过，则提交一个新的申请
			// 产生一个新的支付流水
			CreatePaymentOrderReq createPaymentOrderReq = new CreatePaymentOrderReq();
			createPaymentOrderReq.setBusiType(BusiType.PAY_FOR_GO.getValue());
			createPaymentOrderReq.setPayAmount(hyGo.getFixedPrice());
			createPaymentOrderReq.setPayType(PayType.WEIXIN.getValue());
			createPaymentOrderReq.setSummary("GROUP活动[" + hyGo.getGoId() + "]报名缴费");
			createPaymentOrderReq.setUserId(hyGo.getUserId());
			payOrderId = paymentBusiSV.createPaymentOrder(createPaymentOrderReq);
			// 产生一个申请
			orderId = UUIDUtil.genId32();
			Timestamp sysdate = DateUtil.getSysDate();
			HyGoJoin record = new HyGoJoin();
			record.setGoId(goId);
			record.setCreateDate(sysdate);
			record.setGoType(hyGo.getGoType());
			record.setOrderId(orderId);
			record.setOrderStatus(com.the.harbor.base.enumeration.hygojoin.OrderStatus.APPLIED.getValue());
			record.setPayOrderId(payOrderId);
			record.setStsDate(sysdate);
			record.setUserId(userId);
			hyGoJoinMapper.insert(record);
		}
		GroupApplyResp resp = new GroupApplyResp();
		resp.setNeedPay(needPay);
		resp.setOrderId(orderId);
		resp.setPayAmount(payAmount);
		resp.setPayOrderId(payOrderId);
		return resp;
	}

	private HyGoJoin getApplyHyGoJoin(String goId, String userId) {
		HyGoJoinCriteria sql = new HyGoJoinCriteria();
		sql.or().andGoIdEqualTo(goId).andUserIdEqualTo(userId)
				.andOrderStatusEqualTo(com.the.harbor.base.enumeration.hygojoin.OrderStatus.APPLIED.getValue());
		List<HyGoJoin> list = hyGoJoinMapper.selectByExample(sql);
		return CollectionUtil.isEmpty(list) ? null : list.get(0);
	}

	private HyGoJoin getWaitConfirmHyGoJoin(String goId, String userId) {
		HyGoJoinCriteria sql = new HyGoJoinCriteria();
		sql.or().andGoIdEqualTo(goId).andUserIdEqualTo(userId)
				.andOrderStatusEqualTo(com.the.harbor.base.enumeration.hygojoin.OrderStatus.WAIT_CONFIRM.getValue());
		List<HyGoJoin> list = hyGoJoinMapper.selectByExample(sql);
		return CollectionUtil.isEmpty(list) ? null : list.get(0);
	}

	@Override
	public void updateGoJoinPay(UpdateGoJoinPayReq updateGoJoinPayReq) {
		HyGoJoin goJoin = hyGoJoinMapper.selectByPrimaryKey(updateGoJoinPayReq.getGoOrderId());
		if (goJoin == null) {
			throw new BusinessException("更新活动支付状态失败:活动参加记录不存在");
		}
		if (!updateGoJoinPayReq.getPayOrderId().equals(goJoin.getPayOrderId())) {
			throw new BusinessException("更新活动支付状态失败:支付流水不正确");
		}
		HyGo hyGo = this.getHyGo(goJoin.getGoId());
		if (hyGo == null) {
			throw new BusinessException("更新活动支付状态失败:活动不存在");
		}
		if (com.the.harbor.base.enumeration.hygojoin.OrderStatus.APPLIED.getValue().equals(goJoin.getOrderStatus())) {
			// 如果状态是报名完成，则如果支付成功，则将状态修改等待审核。如果没有支付成功，则状态还是初始化
			if ("SUCCESS".equals(updateGoJoinPayReq.getPayStatus())) {
				// 如果是完成状态，则写入信息
				Timestamp sysdate = DateUtil.getSysDate();
				HyGoJoin o = new HyGoJoin();
				o.setOrderId(goJoin.getOrderId());
				o.setOrderStatus(com.the.harbor.base.enumeration.hygojoin.OrderStatus.WAIT_CONFIRM.getValue());
				o.setStsDate(sysdate);
				hyGoJoinMapper.updateByPrimaryKeySelective(o);

				// 支付成功后记录用户报名申请此活动，交给活动发起者进行审核
				HyGoUtil.userApplyJoinGroup(hyGo.getGoId(), goJoin.getUserId());

				// 给活动发起者通知审核
				UserViewInfo orderUser = userManagerSV.getUserViewInfoByUserId(goJoin.getUserId());
				DoNotify body = new DoNotify();
				body.setHandleType(DoNotify.HandleType.PUBLISH.name());
				body.setNotifyType(NotifyType.SYSTEM_NOTIFY.getValue());
				body.setSenderType(SenderType.USER.getValue());
				body.setSenderId(goJoin.getUserId());
				body.setAccepterType(AccepterType.USER.getValue());
				body.setAccepterId(hyGo.getUserId());
				body.setTitle("有新用户报名参加活动");
				body.setContent("[" + orderUser.getEnName() + "]报名参加并支付了您发起的group活动[" + hyGo.getTopic() + "],请您确认~");
				body.setLink("../go/confirmlist.html?goId=" + hyGo.getGoId());
				NotifyMQSend.sendNotifyMQ(body);
			}
		}
	}

	@Override
	public void processDoGoJoinConfirm(DoGoJoinConfirm doGoJoinConfirm) {
		HyGoJoin goJoin = this.getWaitConfirmHyGoJoin(doGoJoinConfirm.getGoId(), doGoJoinConfirm.getUserId());
		Timestamp sysdate = DateUtil.getSysDate();
		if (DoGoJoinConfirm.HandleType.AGREE.name().equals(doGoJoinConfirm.getHandleType())) {
			// 如果同意，则修改状态为同意
			if (goJoin != null) {
				HyGoJoin o = new HyGoJoin();
				o.setOrderId(goJoin.getOrderId());
				o.setOrderStatus(com.the.harbor.base.enumeration.hygojoin.OrderStatus.AGREE.getValue());
				o.setStsDate(sysdate);
				hyGoJoinMapper.updateByPrimaryKeySelective(o);
			}
			// 更新REDIS状态集合
			HyGoUtil.agreeUserJoinGroupApply(goJoin.getGoId(), goJoin.getUserId());
		} else if (DoGoJoinConfirm.HandleType.REJECT.name().equals(doGoJoinConfirm.getHandleType())) {
			// 如果拒绝，则修改状态为拒绝
			if (goJoin != null) {
				HyGoJoin o = new HyGoJoin();
				o.setOrderId(goJoin.getOrderId());
				o.setOrderStatus(com.the.harbor.base.enumeration.hygojoin.OrderStatus.REJECT.getValue());
				o.setStsDate(sysdate);
				hyGoJoinMapper.updateByPrimaryKeySelective(o);
			}
			// 更新REDIS状态集合
			HyGoUtil.rejectUserJoinGroupApply(goJoin.getGoId(), goJoin.getUserId());
		}

	}

	@Override
	public boolean checkUserOrderGo(CheckUserOrderGoReq checkUserOrderGoReq) {
		HyGo hyGo = this.getHyGo(checkUserOrderGoReq.getGoId());
		if (hyGo == null) {
			throw new BusinessException("活动不存在");
		}
		if (hyGo.getUserId().equals(checkUserOrderGoReq.getUserId())) {
			// 如果是活动发起者
			return true;
		}
		if (GoType.ONE_ON_ONE.getValue().equals(hyGo.getGoType())) {
			HyGoOrder o = hyGoOrderMapper.selectByPrimaryKey(checkUserOrderGoReq.getGoOrderId());
			if (o == null) {
				throw new BusinessException("活动预约不存在");
			}
			return o.getUserId().equals(checkUserOrderGoReq.getUserId());
		} else if (GoType.GROUP.getValue().equals(hyGo.getGoType())) {
			HyGoJoin o = hyGoJoinMapper.selectByPrimaryKey(checkUserOrderGoReq.getGoOrderId());
			if (o == null) {
				throw new BusinessException("活动参加记录不存在");
			}
			return o.getUserId().equals(checkUserOrderGoReq.getUserId());
		}
		return false;
	}

}
