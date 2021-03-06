package com.the.harbor.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.opensearch.CloudsearchClient;
import com.aliyun.opensearch.CloudsearchDoc;
import com.aliyun.opensearch.CloudsearchSearch;
import com.the.harbor.api.go.param.CheckUserOrderGoReq;
import com.the.harbor.api.go.param.CreateGoPaymentOrderReq;
import com.the.harbor.api.go.param.DoGoComment;
import com.the.harbor.api.go.param.DoGoFavorite;
import com.the.harbor.api.go.param.DoGoJoinConfirm;
import com.the.harbor.api.go.param.DoGoView;
import com.the.harbor.api.go.param.GiveHBReq;
import com.the.harbor.api.go.param.Go;
import com.the.harbor.api.go.param.GoComment;
import com.the.harbor.api.go.param.GoCreateReq;
import com.the.harbor.api.go.param.GoDetail;
import com.the.harbor.api.go.param.GoIndexModel;
import com.the.harbor.api.go.param.GoJoin;
import com.the.harbor.api.go.param.GoOrder;
import com.the.harbor.api.go.param.GoOrderConfirmReq;
import com.the.harbor.api.go.param.GoOrderCreateReq;
import com.the.harbor.api.go.param.GoOrderFinishReq;
import com.the.harbor.api.go.param.GoOrderMeetLocaltionConfirmReq;
import com.the.harbor.api.go.param.GoOrderMeetLocaltionReq;
import com.the.harbor.api.go.param.GoStory;
import com.the.harbor.api.go.param.GoTag;
import com.the.harbor.api.go.param.GroupApplyReq;
import com.the.harbor.api.go.param.GroupApplyResp;
import com.the.harbor.api.go.param.QueryMyJointGoReq;
import com.the.harbor.api.go.param.SubmitGoHelpReq;
import com.the.harbor.api.go.param.UpdateGoJoinPayReq;
import com.the.harbor.api.go.param.UpdateGoOrderPayReq;
import com.the.harbor.api.pay.param.CreatePaymentOrderReq;
import com.the.harbor.api.user.param.DoUserAssetsTrade;
import com.the.harbor.api.user.param.UserViewInfo;
import com.the.harbor.base.enumeration.common.BusiErrorCode;
import com.the.harbor.base.enumeration.dict.ParamCode;
import com.the.harbor.base.enumeration.dict.TypeCode;
import com.the.harbor.base.enumeration.hygo.GoDetailType;
import com.the.harbor.base.enumeration.hygo.GoType;
import com.the.harbor.base.enumeration.hygo.HideFlag;
import com.the.harbor.base.enumeration.hygo.OrgMode;
import com.the.harbor.base.enumeration.hygo.PayMode;
import com.the.harbor.base.enumeration.hygo.Status;
import com.the.harbor.base.enumeration.hygo.TopFlag;
import com.the.harbor.base.enumeration.hygojoin.HelpValue;
import com.the.harbor.base.enumeration.hygoorder.OrderStatus;
import com.the.harbor.base.enumeration.hynotify.AccepterType;
import com.the.harbor.base.enumeration.hynotify.NotifyType;
import com.the.harbor.base.enumeration.hynotify.SenderType;
import com.the.harbor.base.enumeration.hypaymentorder.BusiType;
import com.the.harbor.base.enumeration.hypaymentorder.PayStatus;
import com.the.harbor.base.enumeration.hypaymentorder.PayType;
import com.the.harbor.base.enumeration.hyuser.SystemUser;
import com.the.harbor.base.enumeration.hyuserassets.AssetsType;
import com.the.harbor.base.exception.BusinessException;
import com.the.harbor.base.exception.SystemException;
import com.the.harbor.commons.components.aliyuncs.opensearch.OpenSearchFactory;
import com.the.harbor.commons.components.aliyuncs.sms.SMSSender;
import com.the.harbor.commons.components.aliyuncs.sms.param.SMSSendRequest;
import com.the.harbor.commons.components.globalconfig.GlobalSettings;
import com.the.harbor.commons.redisdata.def.DoNotify;
import com.the.harbor.commons.redisdata.util.HyAreaUtil;
import com.the.harbor.commons.redisdata.util.HyBeUtil;
import com.the.harbor.commons.redisdata.util.HyCfgUtil;
import com.the.harbor.commons.redisdata.util.HyDictUtil;
import com.the.harbor.commons.redisdata.util.HyGoUtil;
import com.the.harbor.commons.util.AmountUtils;
import com.the.harbor.commons.util.CollectionUtil;
import com.the.harbor.commons.util.DateUtil;
import com.the.harbor.commons.util.StringUtil;
import com.the.harbor.commons.util.UUIDUtil;
import com.the.harbor.dao.mapper.bo.HyGo;
import com.the.harbor.dao.mapper.bo.HyGoComments;
import com.the.harbor.dao.mapper.bo.HyGoCriteria;
import com.the.harbor.dao.mapper.bo.HyGoDetail;
import com.the.harbor.dao.mapper.bo.HyGoDetailCriteria;
import com.the.harbor.dao.mapper.bo.HyGoFavorite;
import com.the.harbor.dao.mapper.bo.HyGoFavoriteCriteria;
import com.the.harbor.dao.mapper.bo.HyGoJoin;
import com.the.harbor.dao.mapper.bo.HyGoJoinCriteria;
import com.the.harbor.dao.mapper.bo.HyGoOrder;
import com.the.harbor.dao.mapper.bo.HyGoOrderCriteria;
import com.the.harbor.dao.mapper.bo.HyGoStory;
import com.the.harbor.dao.mapper.bo.HyGoStoryCriteria;
import com.the.harbor.dao.mapper.bo.HyGoTags;
import com.the.harbor.dao.mapper.bo.HyGoTagsCriteria;
import com.the.harbor.dao.mapper.bo.HyGoView;
import com.the.harbor.dao.mapper.bo.HyPaymentOrder;
import com.the.harbor.dao.mapper.bo.HyUserAssets;
import com.the.harbor.dao.mapper.interfaces.HyGoCommentsMapper;
import com.the.harbor.dao.mapper.interfaces.HyGoDetailMapper;
import com.the.harbor.dao.mapper.interfaces.HyGoFavoriteMapper;
import com.the.harbor.dao.mapper.interfaces.HyGoJoinMapper;
import com.the.harbor.dao.mapper.interfaces.HyGoMapper;
import com.the.harbor.dao.mapper.interfaces.HyGoOrderMapper;
import com.the.harbor.dao.mapper.interfaces.HyGoStoryMapper;
import com.the.harbor.dao.mapper.interfaces.HyGoTagsMapper;
import com.the.harbor.dao.mapper.interfaces.HyGoViewMapper;
import com.the.harbor.service.interfaces.IGoBusiSV;
import com.the.harbor.service.interfaces.IPaymentBusiSV;
import com.the.harbor.service.interfaces.IUserManagerSV;
import com.the.harbor.util.ESIndexBuildMQSend;
import com.the.harbor.util.HarborSeqUtil;
import com.the.harbor.util.NotifyMQSend;
import com.the.harbor.util.UserAssetsTradeMQSend;
import com.the.harbor.util.UserFavorMQSend;
import com.the.harbor.vo.GoIndexOperate;

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

	@Autowired
	private transient HyGoStoryMapper hyGoStoryMapper;

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
		go.setTopFlag(TopFlag.NO.getValue());
		go.setHideFlag(HideFlag.NO.getValue());
		go.setOnlineNet(goCreateReq.getOnlineNet());
		go.setOnlinePic(goCreateReq.getOnlinePic());
		go.setOfflineCity(goCreateReq.getOfflineCity());
		go.setOfflineProvince(goCreateReq.getOfflineProvince());

		// 复制内容
		BeanUtils.copyProperties(go, bgo);
		// 写表
		hyGoMapper.insert(go);
		/* 2.活动明细 */
		if (!CollectionUtil.isEmpty(goCreateReq.getGoDetails())) {
			// 复制内容
			bgo.setGoDetails(goCreateReq.getGoDetails());
			int sort = 0;
			for (GoDetail d : goCreateReq.getGoDetails()) {
				HyGoDetail gd = new HyGoDetail();
				BeanUtils.copyProperties(d, gd);
				gd.setCreateDate(sysdate);
				gd.setGoId(goId);
				gd.setId(HarborSeqUtil.createGoDetailId());
				gd.setStatus(com.the.harbor.base.enumeration.common.Status.VALID.getValue());
				gd.setSort(sort);
				hyGoDetailMapper.insert(gd);
				sort++;
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
				record.setTagId(
						StringUtil.isBlank(d.getTagId()) ? HarborSeqUtil.createTagId(d.getTagType()) : d.getTagId());
				record.setStatus(com.the.harbor.base.enumeration.common.Status.VALID.getValue());
				sortId++;
				hyGoTagsMapper.insert(record);
			}
		}
		/* 4. 活动发起者的故事会 */
		if (!CollectionUtil.isEmpty(goCreateReq.getGoStories())) {
			// 复制内容
			bgo.setGoStories(goCreateReq.getGoStories());
			int sort = 0;
			for (GoStory d : goCreateReq.getGoStories()) {
				HyGoStory gd = new HyGoStory();
				BeanUtils.copyProperties(d, gd);
				gd.setCreateDate(sysdate);
				gd.setGoId(goId);
				gd.setId(HarborSeqUtil.createGoDetailId());
				gd.setStatus(com.the.harbor.base.enumeration.common.Status.VALID.getValue());
				gd.setSort(sort);
				hyGoStoryMapper.insert(gd);
				sort++;
			}
		}
		// 将GO的数据发送给MNS处理
		// ESIndexBuildMQSend.sendMQ(bgo);

		// 写入REDIS
		HyGoUtil.recordGo(go.getGoId(), JSON.toJSONString(bgo));
		// 写入OPENSEARCH索引
		this.pushGoToOpenSearch(go.getGoId());
		return goId;
	}

	@Override
	public List<HyGoOrder> getHyGoOrders(String userId, String goId) {
		HyGoOrderCriteria sql = new HyGoOrderCriteria();
		sql.or().andUserIdEqualTo(userId).andGoIdEqualTo(goId);
		List<HyGoOrder> list = hyGoOrderMapper.selectByExample(sql);
		return list;
	}

	@Override
	public String orderOneOnOne(GoOrderCreateReq goOrderCreateReq) {
		HyGo hyGo = this.getHyGo(goOrderCreateReq.getGoId());
		if (hyGo == null) {
			throw new BusinessException("GO_0001", "预约的活动不存在");
		}
		// 判断是否重复参加
		/**
		 * HyGoOrder o = this.getHyGoOrder(goOrderCreateReq.getUserId(),
		 * goOrderCreateReq.getGoId()); if (o != null &&
		 * !OrderStatus.CANCEL.getValue().equals(o.getOrderStatus())) { throw
		 * new BusinessException("GO_0001", "您已经预约了此活动"); }
		 **/
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
		record.setSponsorId(hyGo.getUserId());
		hyGoOrderMapper.insert(record);

		// 发送用户自动收藏的消息
		if (!HyGoUtil.checkUserGoFavorite(goOrderCreateReq.getGoId(), goOrderCreateReq.getUserId())) {
			DoGoFavorite body = new DoGoFavorite();
			body.setHandleType(DoGoFavorite.HandleType.DO.name());
			body.setGoId(goOrderCreateReq.getGoId());
			body.setUserId(goOrderCreateReq.getUserId());
			UserFavorMQSend.sendMQ(body);
		}
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
			createPaymentOrderReq.setSourceNo(goOrderId);
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
				body.setNotifyId(UUIDUtil.genId32());
				body.setHandleType(DoNotify.HandleType.PUBLISH.name());
				body.setNotifyType(NotifyType.SYSTEM_NOTIFY.getValue());
				body.setSenderType(SenderType.USER.getValue());
				body.setSenderId(goOrder.getUserId());
				body.setAccepterType(AccepterType.USER.getValue());
				body.setAccepterId(hyGo.getUserId());
				body.setTitle("活动预约确认");
				body.setContent("[" + orderUser.getEnName() + "]预约并支付了您发布的一对一活动[" + hyGo.getTopic() + "],请您确认~");
				body.setLink("../go/toHainiuConfirm.html?goOrderId=" + goOrder.getOrderId() + "&notifyId="
						+ body.getNotifyId());
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
		String link = "";
		HyGoOrder o = new HyGoOrder();
		o.setOrderId(hyGoOrder.getOrderId());
		String notifyId = UUIDUtil.genId32();
		if ("confirm".equals(goOrderConfirmReq.getAckFlag())) {
			title = "海牛同意了您的预约";
			content = "[" + publishUser.getEnName() + "]同意了您的预约的活动[" + hyGo.getTopic() + "]";
			link = "../go/toAppointment.html?goOrderId=" + hyGoOrder.getOrderId() + "&notifyId=" + notifyId;
			o.setOrderStatus(OrderStatus.WAIT_MEET.getValue());
		} else if ("reject".equals(goOrderConfirmReq.getAckFlag())) {
			o.setOrderStatus(OrderStatus.REJECT.getValue());
			title = "海牛拒绝了您的预约";
			content = "[" + publishUser.getEnName() + "]拒绝了您的预约的活动[" + hyGo.getTopic() + "],您可以浏览其它活动~";
			link = "../go/goindex.html?notifyId=" + notifyId + "&notifyId=" + notifyId;
		}
		Timestamp sysdate = DateUtil.getSysDate();
		o.setStsDate(sysdate);
		o.setConfirmDate(sysdate);
		hyGoOrderMapper.updateByPrimaryKeySelective(o);

		/* 海牛确认或拒绝消息发送 */
		DoNotify body = new DoNotify();
		body.setNotifyId(notifyId);
		body.setHandleType(DoNotify.HandleType.PUBLISH.name());
		body.setNotifyType(NotifyType.SYSTEM_NOTIFY.getValue());
		body.setSenderType(SenderType.USER.getValue());
		body.setSenderId(hyGo.getUserId());
		body.setAccepterType(AccepterType.USER.getValue());
		body.setAccepterId(hyGoOrder.getUserId());
		body.setTitle(title);
		body.setContent(content);
		body.setLink(link);
		NotifyMQSend.sendNotifyMQ(body);

		/* 通知小白短信审核结果 */
		UserViewInfo userInfo = userManagerSV.getUserViewInfoByUserId(hyGoOrder.getUserId());
		if (userInfo != null && !StringUtil.isBlank(userInfo.getMobilePhone())) {
			SMSSendRequest req = new SMSSendRequest();
			List<String> recNumbers = new ArrayList<String>();
			recNumbers.add(userInfo.getMobilePhone());
			JSONObject smsParams = new JSONObject();
			smsParams.put("goTopic", hyGo.getTopic());
			smsParams.put("result", "confirm".equals(goOrderConfirmReq.getAckFlag()) ? "通过" : "不通过");
			req.setRecNumbers(recNumbers);
			req.setSmsFreeSignName(GlobalSettings.getSMSFreeSignName());
			req.setSmsParams(smsParams);
			req.setSmsTemplateCode(HyCfgUtil.getSMSCodeOfOnOAuditResult());
			SMSSender.send(req);
		}

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
		body.setNotifyId(UUIDUtil.genId32());
		body.setHandleType(DoNotify.HandleType.PUBLISH.name());
		body.setNotifyType(NotifyType.SYSTEM_NOTIFY.getValue());
		body.setSenderType(SenderType.USER.getValue());
		body.setSenderId(hyGo.getUserId());
		body.setAccepterType(AccepterType.USER.getValue());
		body.setAccepterId(hyGoOrder.getUserId());
		body.setTitle("等待新秀确认");
		body.setContent("[" + publishUser.getEnName() + "]提交了您预约的活动[" + hyGo.getTopic() + "]见面的时间与地点，您可以选择确认啦~");
		body.setLink(
				"../go/toAppointment.html?goOrderId=" + hyGoOrder.getOrderId() + "&notifyId=" + body.getNotifyId());
		NotifyMQSend.sendNotifyMQ(body);

		// 将海牛设置的时间与地点短信告知小白
		UserViewInfo userInfo = userManagerSV.getUserViewInfoByUserId(hyGoOrder.getUserId());
		if (userInfo != null && !StringUtil.isBlank(userInfo.getMobilePhone())) {
			SMSSendRequest req = new SMSSendRequest();
			List<String> recNumbers = new ArrayList<String>();
			recNumbers.add(userInfo.getMobilePhone());
			JSONObject smsParams = new JSONObject();
			smsParams.put("goTopic", hyGo.getTopic());
			req.setRecNumbers(recNumbers);
			req.setSmsFreeSignName(GlobalSettings.getSMSFreeSignName());
			req.setSmsParams(smsParams);
			req.setSmsTemplateCode(HyCfgUtil.getSMSCodeOfOnOHNSetMeetingInfo());
			SMSSender.send(req);
		}
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
		body.setNotifyId(UUIDUtil.genId32());
		body.setHandleType(DoNotify.HandleType.PUBLISH.name());
		body.setNotifyType(NotifyType.SYSTEM_NOTIFY.getValue());
		body.setSenderType(SenderType.USER.getValue());
		body.setSenderId(hyGoOrder.getUserId());
		body.setAccepterType(AccepterType.USER.getValue());
		body.setAccepterId(hyGo.getUserId());
		body.setTitle("小白确认了与您见面的时间地点");
		body.setContent("[" + orderUser.getEnName() + "]确认了活动[" + hyGo.getTopic() + "]见面的时间与地点，点击查看~");
		body.setLink("../go/toHainiuAppointment.html?goOrderId=" + hyGoOrder.getOrderId() + "&notifyId="
				+ body.getNotifyId());
		NotifyMQSend.sendNotifyMQ(body);

		// 小白将选择的时间与地点短信通知海牛
		UserViewInfo userInfo = userManagerSV.getUserViewInfoByUserId(hyGo.getUserId());
		if (userInfo != null && !StringUtil.isBlank(userInfo.getMobilePhone())) {
			SMSSendRequest req = new SMSSendRequest();
			List<String> recNumbers = new ArrayList<String>();
			recNumbers.add(userInfo.getMobilePhone());
			JSONObject smsParams = new JSONObject();
			smsParams.put("goTopic", hyGo.getTopic());
			smsParams.put("userName", orderUser.getEnName());
			req.setRecNumbers(recNumbers);
			req.setSmsFreeSignName(GlobalSettings.getSMSFreeSignName());
			req.setSmsParams(smsParams);
			req.setSmsTemplateCode(HyCfgUtil.getSMSCodeOfOnOXBChooseMeetingInfo());
			SMSSender.send(req);
		}
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
		body.setNotifyId(UUIDUtil.genId32());
		body.setHandleType(DoNotify.HandleType.PUBLISH.name());
		body.setNotifyType(NotifyType.SYSTEM_NOTIFY.getValue());
		body.setSenderType(SenderType.USER.getValue());
		body.setSenderId(hyGo.getUserId());
		body.setAccepterType(AccepterType.USER.getValue());
		body.setAccepterId(hyGoOrder.getUserId());
		body.setTitle("海牛确认了对您的服务结束");
		body.setContent("[" + publishUser.getEnName() + "]确认了您预约的活动[" + hyGo.getTopic() + "]服务结束，您可以进入互评~");
		body.setLink("../go/toFeedback.html?goOrderId=" + hyGoOrder.getOrderId() + "&notifyId=" + body.getNotifyId());
		NotifyMQSend.sendNotifyMQ(body);

		// 海牛确认服务结束后，给自己发送一条消息
		UserViewInfo orderUser = userManagerSV.getUserViewInfoByUserId(hyGoOrder.getUserId());
		body = new DoNotify();
		body.setNotifyId(UUIDUtil.genId32());
		body.setHandleType(DoNotify.HandleType.PUBLISH.name());
		body.setNotifyType(NotifyType.SYSTEM_NOTIFY.getValue());
		body.setSenderType(SenderType.USER.getValue());
		body.setSenderId(hyGo.getUserId());
		body.setAccepterType(AccepterType.USER.getValue());
		body.setAccepterId(hyGo.getUserId());
		body.setTitle("您确认了服务结束");
		body.setContent("您确认了[" + orderUser.getEnName() + "]预约您活动[" + hyGo.getTopic() + "]服务结束，您可以进入互评~");
		body.setLink(
				"../go/toHainiuFeedback.html?goOrderId=" + hyGoOrder.getOrderId() + "&notifyId=" + body.getNotifyId());
		NotifyMQSend.sendNotifyMQ(body);

		// 给小白发送一条短信提醒活动已经结束
		if (orderUser != null && !StringUtil.isBlank(orderUser.getMobilePhone())) {
			SMSSendRequest req = new SMSSendRequest();
			List<String> recNumbers = new ArrayList<String>();
			recNumbers.add(orderUser.getMobilePhone());
			JSONObject smsParams = new JSONObject();
			smsParams.put("goTopic", hyGo.getTopic());
			req.setRecNumbers(recNumbers);
			req.setSmsFreeSignName(GlobalSettings.getSMSFreeSignName());
			req.setSmsParams(smsParams);
			req.setSmsTemplateCode(HyCfgUtil.getSMSCodeOfOnOHNEnd());
			SMSSender.send(req);
		}
	}

	@Override
	public void processDoGoFavoriteMQ(DoGoFavorite doGoFavorite) {
		Go go = this.getGoInfo(doGoFavorite.getGoId());
		if (go == null) {
			return;
		}
		if (DoGoFavorite.HandleType.DO.name().equals(doGoFavorite.getHandleType())) {
			// 如果是收藏，则记录
			HyGoFavorite record = new HyGoFavorite();
			record.setGoId(doGoFavorite.getGoId());
			record.setCreateDate(doGoFavorite.getTime() == null ? DateUtil.getSysDate() : doGoFavorite.getTime());
			record.setFavoriteId(HarborSeqUtil.createGoFavoriteId());
			record.setUserId(doGoFavorite.getUserId());
			hyGoFavoriteMapper.insert(record);

			// 记录用户收藏行为
			HyGoUtil.userFavorGo(doGoFavorite.getUserId(), go.getGoType(), doGoFavorite.getGoId());
		} else if (DoGoFavorite.HandleType.CANCEL.name().equals(doGoFavorite.getHandleType())) {
			// 如果是取消收藏，则删除
			if (!StringUtil.isBlank(doGoFavorite.getUserId()) && !StringUtil.isBlank(doGoFavorite.getGoId())) {
				HyGoFavoriteCriteria sql = new HyGoFavoriteCriteria();
				sql.or().andUserIdEqualTo(doGoFavorite.getUserId()).andGoIdEqualTo(doGoFavorite.getGoId());
				hyGoFavoriteMapper.deleteByExample(sql);

				// 记录用户取消收藏
				HyGoUtil.userCancelFavorGo(doGoFavorite.getUserId(), go.getGoType(), doGoFavorite.getGoId());
			}
		}
	}

	@Override
	public void processDoGoView(DoGoView doGoView) {
		HyGoUtil.userViewGo(doGoView.getGoId());
		if (StringUtil.isBlank(doGoView.getUserId())) {
			return;
		}
		HyGoView record = new HyGoView();
		record.setCreateDate(doGoView.getTime() == null ? DateUtil.getSysDate() : doGoView.getTime());
		record.setViewId(HarborSeqUtil.createGoViewId());
		record.setUserId(doGoView.getUserId());
		record.setGoId(doGoView.getGoId());
		hyGoViewMapper.insert(record);

	}

	@Override
	public void processDoGoComment(DoGoComment doGoComment) {
		Go go = this.getGoInfo(doGoComment.getGoId());
		if (go == null) {
			throw new BusinessException("GO[" + doGoComment.getGoId() + "]索引不存在");
		}
		if (DoGoComment.HandleType.PUBLISH.name().equals(doGoComment.getHandleType())) {
			// 如果是点赞，则记录
			HyGoComments record = new HyGoComments();
			BeanUtils.copyProperties(doGoComment, record);
			record.setCreateDate(doGoComment.getSysdate() == null ? DateUtil.getSysDate() : doGoComment.getSysdate());
			record.setStatus(com.the.harbor.base.enumeration.hygocomments.Status.NORMAL.getValue());
			hyGoCommentsMapper.insert(record);

			// 给被评论方发个消息
			String accepterId = null;
			String content = null;
			// 发表评论的人
			String enName = userManagerSV.getUserViewInfoByUserId(doGoComment.getPublishUserId()).getEnName();
			if (StringUtil.isBlank(doGoComment.getParentUserId())) {
				// 被评论发为GO的作者
				accepterId = go.getUserId();
				content = enName + "评论了您发起的活动,点击查看..";
			} else {
				accepterId = doGoComment.getParentUserId();
				content = enName + "在活动中回复了您的评论,点击查看..";
			}
			if (!doGoComment.getPublishUserId().equals(accepterId)) {
				// 只有当评论的发表者和接受者不是一个人的时候，才会给接受者发送系统通知
				DoNotify notify = new DoNotify();
				notify.setHandleType(DoNotify.HandleType.PUBLISH.name());
				notify.setNotifyId(UUIDUtil.genId32());
				notify.setNotifyType(NotifyType.SYSTEM_NOTIFY.getValue());
				notify.setSenderType(SenderType.USER.getValue());
				notify.setSenderId(doGoComment.getPublishUserId());
				notify.setAccepterType(AccepterType.USER.getValue());
				notify.setAccepterId(accepterId);
				notify.setTitle("Go有新评论啦~");
				notify.setContent(content);
				if (GoType.GROUP.getValue().equals(go.getGoType())) {
					if (go.getUserId().equals(doGoComment.getPublishUserId())) {
						// 有疑问？ 发送给小白端
						notify.setLink("../go/comments.html?goOrderId=" + doGoComment.getOrderId()
								+ "&backURL=../user/messagecenter.html?notifyId=" + notify.getNotifyId());
					} else {
						// 有疑问，顺序颠倒？发送给海牛端
						notify.setLink("../go/hainiugroupcomments.html?goOrderId=" + doGoComment.getOrderId()
								+ "&notifyId=" + notify.getNotifyId() + "&backURL=../user/messagecenter.html");
					}
				} else {
					if (go.getUserId().equals(doGoComment.getPublishUserId())) {
						// 有疑问？ 发送给小白端
						notify.setLink("../go/toFeedback.html?goOrderId=" + doGoComment.getOrderId() + "&notifyId="
								+ notify.getNotifyId() + "&backURL=../user/messagecenter.html");
					} else {
						// 有疑问，顺序颠倒？发送给海牛端
						notify.setLink("../go/toHainiuFeedback.html?goOrderId=" + doGoComment.getOrderId()
								+ "&notifyId=" + notify.getNotifyId() + "&backURL=../user/messagecenter.html");
					}

				}

				NotifyMQSend.sendNotifyMQ(notify);
			}

			// 写入REDIS关系
			GoComment b = new GoComment();
			BeanUtils.copyProperties(record, b);
			HyGoUtil.recordGoOrderCommentId(record.getGoId(), record.getOrderId(), record.getCommentId());
			HyGoUtil.recordGoComment(record.getCommentId(), JSON.toJSONString(b));

			// 给活动发起者发送短信提醒
			if (GoType.GROUP.getValue().equals(go.getGoType())) {
				// 只有当是别人发起的评论时候，才发短信通知活动发起者
				if (!doGoComment.getPublishUserId().equals(go.getUserId())) {
					UserViewInfo userInfo = userManagerSV.getUserViewInfoByUserId(go.getUserId());
					if (userInfo != null && !StringUtil.isBlank(userInfo.getMobilePhone())) {
						SMSSendRequest req = new SMSSendRequest();
						List<String> recNumbers = new ArrayList<String>();
						recNumbers.add(userInfo.getMobilePhone());
						JSONObject smsParams = new JSONObject();
						smsParams.put("goTopic", go.getTopic());
						req.setRecNumbers(recNumbers);
						req.setSmsFreeSignName(GlobalSettings.getSMSFreeSignName());
						req.setSmsParams(smsParams);
						req.setSmsTemplateCode(HyCfgUtil.getSMSCodeOfGroupUserComments());
						SMSSender.send(req);
					}
				}

			} else if (GoType.ONE_ON_ONE.getValue().equals(go.getGoType())) {
				// 只有当是别人发起的评论时候，才发短信通知活动发起者
				if (!doGoComment.getPublishUserId().equals(go.getUserId())) {
					UserViewInfo userInfo = userManagerSV.getUserViewInfoByUserId(go.getUserId());
					if (userInfo != null && !StringUtil.isBlank(userInfo.getMobilePhone())) {
						SMSSendRequest req = new SMSSendRequest();
						List<String> recNumbers = new ArrayList<String>();
						recNumbers.add(userInfo.getMobilePhone());
						JSONObject smsParams = new JSONObject();
						smsParams.put("goTopic", go.getTopic());
						req.setRecNumbers(recNumbers);
						req.setSmsFreeSignName(GlobalSettings.getSMSFreeSignName());
						req.setSmsParams(smsParams);
						req.setSmsTemplateCode(HyCfgUtil.getSMSCodeOfOnOXBComment());
						SMSSender.send(req);
					}
				}
			}
		} else if (DoGoComment.HandleType.CANCEL.name().equals(doGoComment.getHandleType())) {
			// 如果是删除评论
			if (!StringUtil.isBlank(doGoComment.getCommentId())) {
				HyGoComments record = new HyGoComments();
				record.setCommentId(doGoComment.getCommentId());
				record.setStatus(com.the.harbor.base.enumeration.hygocomments.Status.DELETED.getValue());
				hyGoCommentsMapper.updateByPrimaryKeySelective(record);

				String gocomment = HyGoUtil.getGoComment(doGoComment.getCommentId());
				if (!StringUtil.isBlank(gocomment)) {
					GoComment b = JSON.parseObject(gocomment, GoComment.class);
					b.setStatus(com.the.harbor.base.enumeration.hygocomments.Status.DELETED.getValue());
					HyBeUtil.recordBeComment(doGoComment.getCommentId(), JSON.toJSONString(b));
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
			HyGoJoinCriteria sql = new HyGoJoinCriteria();
			sql.or().andGoIdEqualTo(goId);
			orderCount = hyGoJoinMapper.countByExample(sql);
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
					createPaymentOrderReq.setBusiType(BusiType.PAY_FOR_GROUP.getValue());
					createPaymentOrderReq.setPayAmount(hyGo.getFixedPrice());
					createPaymentOrderReq.setPayType(PayType.WEIXIN.getValue());
					createPaymentOrderReq.setSummary("GROUP活动[" + hyGo.getGoId() + "]报名缴费");
					createPaymentOrderReq.setUserId(hyGo.getUserId());
					createPaymentOrderReq.setSourceNo(orderId);
					payOrderId = paymentBusiSV.createPaymentOrder(createPaymentOrderReq);

					// 更新到预约订单记录中
					HyGoJoin record = new HyGoJoin();
					record.setPayOrderId(payOrderId);
					record.setOrderId(hyGoJoin.getOrderId());
					hyGoJoinMapper.updateByPrimaryKeySelective(record);
				}
			}
		} else {
			orderId = UUIDUtil.genId32();
			// 如果没有申请过，则提交一个新的申请
			if (needPay) {
				/* 如果是需要支付，则产生一笔支付流水 */
				CreatePaymentOrderReq createPaymentOrderReq = new CreatePaymentOrderReq();
				createPaymentOrderReq.setBusiType(BusiType.PAY_FOR_GROUP.getValue());
				createPaymentOrderReq.setPayAmount(hyGo.getFixedPrice());
				createPaymentOrderReq.setPayType(PayType.WEIXIN.getValue());
				createPaymentOrderReq.setSummary("GROUP活动[" + hyGo.getGoId() + "]报名缴费");
				createPaymentOrderReq.setUserId(hyGo.getUserId());
				createPaymentOrderReq.setSourceNo(orderId);
				payOrderId = paymentBusiSV.createPaymentOrder(createPaymentOrderReq);
			}
			// 产生一个申请
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
			record.setSponsorId(hyGo.getUserId());
			hyGoJoinMapper.insert(record);
		}
		GroupApplyResp resp = new GroupApplyResp();
		resp.setNeedPay(needPay);
		resp.setOrderId(orderId);
		resp.setPayAmount(payAmount);
		resp.setPayOrderId(payOrderId);

		// 报名成功，需要将获得先收藏
		// 发送用户自动收藏的消息
		if (!HyGoUtil.checkUserGoFavorite(goId, userId)) {
			DoGoFavorite body = new DoGoFavorite();
			body.setHandleType(DoGoFavorite.HandleType.DO.name());
			body.setGoId(goId);
			body.setUserId(userId);
			UserFavorMQSend.sendMQ(body);
		}
		if (!needPay) {
			// 如果不需要支付，则将订单更新成待确认状态
			Timestamp sysdate = DateUtil.getSysDate();
			HyGoJoin o = new HyGoJoin();
			o.setOrderId(orderId);
			o.setOrderStatus(com.the.harbor.base.enumeration.hygojoin.OrderStatus.WAIT_CONFIRM.getValue());
			o.setStsDate(sysdate);
			hyGoJoinMapper.updateByPrimaryKeySelective(o);

			// 支付成功后记录用户报名申请此活动，交给活动发起者进行审核
			HyGoUtil.userApplyJoinGroup(hyGo.getGoId(), userId);

			// 给活动发起者通知审核
			UserViewInfo orderUser = userManagerSV.getUserViewInfoByUserId(userId);
			DoNotify body = new DoNotify();
			body.setNotifyId(UUIDUtil.genId32());
			body.setHandleType(DoNotify.HandleType.PUBLISH.name());
			body.setNotifyType(NotifyType.SYSTEM_NOTIFY.getValue());
			body.setSenderType(SenderType.USER.getValue());
			body.setSenderId(userId);
			body.setAccepterType(AccepterType.USER.getValue());
			body.setAccepterId(hyGo.getUserId());
			body.setTitle("有新用户报名参加活动");
			body.setContent("[" + orderUser.getEnName() + "]报名参加您发起的group活动[" + hyGo.getTopic() + "],请您确认~");
			body.setLink("../go/confirmlist.html?goId=" + hyGo.getGoId() + "&notifyId=" + body.getNotifyId());
			NotifyMQSend.sendNotifyMQ(body);
		}
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
				body.setNotifyId(UUIDUtil.genId32());
				body.setHandleType(DoNotify.HandleType.PUBLISH.name());
				body.setNotifyType(NotifyType.SYSTEM_NOTIFY.getValue());
				body.setSenderType(SenderType.USER.getValue());
				body.setSenderId(goJoin.getUserId());
				body.setAccepterType(AccepterType.USER.getValue());
				body.setAccepterId(hyGo.getUserId());
				body.setTitle("有新用户报名参加活动");
				body.setContent("[" + orderUser.getEnName() + "]报名参加并支付了您发起的group活动[" + hyGo.getTopic() + "],请您确认~");
				body.setLink("../go/confirmlist.html?goId=" + hyGo.getGoId() + "&notifyId=" + body.getNotifyId());
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

				// 将参与者支付的信息转移到个人账户下
				HyGo go = this.getHyGo(goJoin.getGoId());
				if (!PayMode.MY_TREAT.getValue().equals(go.getPayMode())) {
					// 如果不是我请客，则说明用户已经支付过了
					DoUserAssetsTrade t = new DoUserAssetsTrade();
					t.setAssetsType(AssetsType.CASH.getValue());
					t.setBusiType(BusiType.PAY_FOR_GROUP.getValue());
					// 因为用户支付现金给系统，这里由系统支付给活动发起方
					t.setFromUserId(SystemUser.SYSTEM.getValue());
					t.setHandleType(DoUserAssetsTrade.HandleType.TRANSFER.name());
					t.setSourceNo(o.getOrderId());
					t.setSummary("系统将用户[" + doGoJoinConfirm.getUserId() + "]支付的现金[" + go.getFixedPrice() + "]转给活动发起方");
					t.setToUserId(go.getUserId());
					t.setTradeBalance(go.getFixedPrice());
					UserAssetsTradeMQSend.sendMQ(t);
				}

				// 更新REDIS状态集合
				HyGoUtil.agreeUserJoinGroupApply(goJoin.getGoId(), goJoin.getUserId());
				// 发送通知消息
				DoNotify body = new DoNotify();
				body.setNotifyId(UUIDUtil.genId32());
				body.setHandleType(DoNotify.HandleType.PUBLISH.name());
				body.setNotifyType(NotifyType.SYSTEM_NOTIFY.getValue());
				body.setSenderType(SenderType.USER.getValue());
				body.setSenderId(doGoJoinConfirm.getPublishUserId());
				body.setAccepterType(AccepterType.USER.getValue());
				body.setAccepterId(doGoJoinConfirm.getUserId());
				body.setTitle("同意您的参加活动");
				body.setContent(
						doGoJoinConfirm.getPublishUserName() + "同意您参加group活动[" + doGoJoinConfirm.getTopic() + "]，查看详情");
				body.setLink("../go/invite.html?goId=" + doGoJoinConfirm.getGoId() + "&notifyId=" + body.getNotifyId());
				NotifyMQSend.sendNotifyMQ(body);

				// GROUP活动发起方确认同意，短信通知给参与方
				UserViewInfo userInfo = userManagerSV.getUserViewInfoByUserId(goJoin.getUserId());
				if (userInfo != null && !StringUtil.isBlank(userInfo.getMobilePhone())) {
					SMSSendRequest req = new SMSSendRequest();
					List<String> recNumbers = new ArrayList<String>();
					recNumbers.add(userInfo.getMobilePhone());
					JSONObject smsParams = new JSONObject();
					smsParams.put("goTopic", go.getTopic());
					smsParams.put("result", "通过");
					req.setRecNumbers(recNumbers);
					req.setSmsFreeSignName(GlobalSettings.getSMSFreeSignName());
					req.setSmsParams(smsParams);
					req.setSmsTemplateCode(HyCfgUtil.getSMSCodeOfGroupAuditResult());
					SMSSender.send(req);
				}
			}

		} else if (DoGoJoinConfirm.HandleType.REJECT.name().equals(doGoJoinConfirm.getHandleType())) {
			// 如果拒绝，则修改状态为拒绝
			if (goJoin != null) {
				HyGoJoin o = new HyGoJoin();
				o.setOrderId(goJoin.getOrderId());
				o.setOrderStatus(com.the.harbor.base.enumeration.hygojoin.OrderStatus.REJECT.getValue());
				o.setStsDate(sysdate);
				hyGoJoinMapper.updateByPrimaryKeySelective(o);

				// 更新REDIS状态集合
				HyGoUtil.rejectUserJoinGroupApply(goJoin.getGoId(), goJoin.getUserId());
				// 发送消息
				DoNotify body = new DoNotify();
				body.setNotifyId(UUIDUtil.genId32());
				body.setHandleType(DoNotify.HandleType.PUBLISH.name());
				body.setNotifyType(NotifyType.SYSTEM_NOTIFY.getValue());
				body.setSenderType(SenderType.USER.getValue());
				body.setSenderId(doGoJoinConfirm.getPublishUserId());
				body.setAccepterType(AccepterType.USER.getValue());
				body.setAccepterId(doGoJoinConfirm.getUserId());
				body.setTitle("拒绝您的参加活动");
				body.setContent(doGoJoinConfirm.getPublishUserName() + "拒绝您参加group活动[" + doGoJoinConfirm.getTopic()
						+ "],您支付的费用将于3天内退回您的账户。查看详情");
				body.setLink("../go/invite.html?goId=" + doGoJoinConfirm.getGoId() + "&notifyId=" + body.getNotifyId());
				NotifyMQSend.sendNotifyMQ(body);

				HyGo go = this.getHyGo(goJoin.getGoId());
				// GROUP活动发起方不同意，短信通知给参与方
				UserViewInfo userInfo = userManagerSV.getUserViewInfoByUserId(goJoin.getUserId());
				if (userInfo != null && !StringUtil.isBlank(userInfo.getMobilePhone())) {
					SMSSendRequest req = new SMSSendRequest();
					List<String> recNumbers = new ArrayList<String>();
					recNumbers.add(userInfo.getMobilePhone());
					JSONObject smsParams = new JSONObject();
					smsParams.put("goTopic", go.getTopic());
					smsParams.put("result", "不通过");
					req.setRecNumbers(recNumbers);
					req.setSmsFreeSignName(GlobalSettings.getSMSFreeSignName());
					req.setSmsParams(smsParams);
					req.setSmsTemplateCode(HyCfgUtil.getSMSCodeOfGroupAuditResult());
					SMSSender.send(req);
				}
			}

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

	@Override
	public int getMyJointGoCount(String userId, String goType) {
		int orderCount = 0;
		if (GoType.ONE_ON_ONE.getValue().equals(goType)) {
			HyGoOrderCriteria sql = new HyGoOrderCriteria();
			sql.or().andUserIdEqualTo(userId).andGoTypeEqualTo(goType);
			orderCount = hyGoOrderMapper.countByExample(sql);
		} else if (GoType.GROUP.getValue().equals(goType)) {
			HyGoJoinCriteria sql = new HyGoJoinCriteria();
			List<String> statusList = new ArrayList<String>();
			statusList.add(com.the.harbor.base.enumeration.hygojoin.OrderStatus.AGREE.getValue());
			statusList.add(com.the.harbor.base.enumeration.hygojoin.OrderStatus.FINISH.getValue());
			sql.or().andUserIdEqualTo(userId).andGoTypeEqualTo(goType).andOrderStatusIn(statusList);
			orderCount = hyGoJoinMapper.countByExample(sql);
		}
		return orderCount;
	}

	@Override
	public List<HyGoOrder> getMyJointGroupGoes(QueryMyJointGoReq req) {
		String goType = req.getGoType();
		String userId = req.getUserId();
		int start = (req.getPageNo() - 1) * req.getPageSize();
		int end = req.getPageNo() * req.getPageSize();
		HyGoOrderCriteria sql = new HyGoOrderCriteria();
		sql.or().andUserIdEqualTo(userId).andGoTypeEqualTo(goType);
		sql.setLimitStart(start);
		sql.setLimitEnd(end);
		return hyGoOrderMapper.selectByExample(sql);
	}

	public List<HyGoJoin> getMyJointOnOGoes(QueryMyJointGoReq req) {
		String goType = req.getGoType();
		String userId = req.getUserId();
		int start = (req.getPageNo() - 1) * req.getPageSize();
		int end = req.getPageNo() * req.getPageSize();
		HyGoJoinCriteria sql = new HyGoJoinCriteria();
		List<String> statusList = new ArrayList<String>();
		statusList.add(com.the.harbor.base.enumeration.hygojoin.OrderStatus.AGREE.getValue());
		statusList.add(com.the.harbor.base.enumeration.hygojoin.OrderStatus.FINISH.getValue());
		sql.or().andUserIdEqualTo(userId).andGoTypeEqualTo(goType).andOrderStatusIn(statusList);
		sql.setLimitStart(start);
		sql.setLimitEnd(end);
		return hyGoJoinMapper.selectByExample(sql);
	}

	@Override
	public void submitGoHelp(SubmitGoHelpReq submitGoHelpReq) {
		if (GoType.GROUP.getValue().equals(submitGoHelpReq.getGoType())) {
			HyGoJoin o = hyGoJoinMapper.selectByPrimaryKey(submitGoHelpReq.getGoOrderId());
			if (o == null) {
				throw new BusinessException("活动参加申请不存在，无法评价哦");
			}
			if (!o.getUserId().equals(submitGoHelpReq.getUserId())) {
				throw new BusinessException("您没有参加活动，无法评价哦");
			}
			o.setHelpValue(submitGoHelpReq.getHelpValue());
			hyGoJoinMapper.updateByPrimaryKeySelective(o);
		} else if (GoType.ONE_ON_ONE.getValue().equals(submitGoHelpReq.getGoType())) {
			HyGoOrder o = hyGoOrderMapper.selectByPrimaryKey(submitGoHelpReq.getGoOrderId());
			if (o == null) {
				throw new BusinessException("活动预约申请不存在，无法评价哦");
			}
			if (!o.getUserId().equals(submitGoHelpReq.getUserId())) {
				throw new BusinessException("您不是当前活动申请者，无法评价哦");
			}
			o.setHelpValue(submitGoHelpReq.getHelpValue());
			hyGoOrderMapper.updateByPrimaryKeySelective(o);
		}
	}

	@Override
	public void giveHaibei(GiveHBReq giveHBReq) {
		HyUserAssets userAssets = userManagerSV.getUserAssets(giveHBReq.getUserId(), AssetsType.HAIBEI.getValue());
		if (userAssets == null) {
			throw new BusinessException(BusiErrorCode.HAIBEI_NOT_ENOUGH.getValue(), "您的海贝数量不足，请充值");
		}
		if (userAssets.getBalance() < giveHBReq.getCount()) {
			throw new BusinessException(BusiErrorCode.HAIBEI_NOT_ENOUGH.getValue(),
					"您的海贝数量只有" + userAssets.getBalance() + "个,不足以打赏，请充值");
		}
		if (GoType.GROUP.getValue().equals(giveHBReq.getGoType())) {
			HyGoJoin o = hyGoJoinMapper.selectByPrimaryKey(giveHBReq.getGoOrderId());
			if (o == null) {
				throw new BusinessException("活动参加申请不存在，不能打赏哦");
			}
			if (!o.getUserId().equals(giveHBReq.getUserId())) {
				throw new BusinessException("您没有参加活动，不能打赏哦");
			}
			Go go = this.getGoInfo(o.getGoId());
			if (go == null) {
				throw new BusinessException("活动信息不存在，不能打赏哦");
			}
			o.setGiveHb(o.getGiveHb() + giveHBReq.getCount());
			hyGoJoinMapper.updateByPrimaryKeySelective(o);
			// 发送海贝赠送交易消息
			DoUserAssetsTrade t = new DoUserAssetsTrade();
			t.setAssetsType(AssetsType.HAIBEI.getValue());
			t.setBusiType(BusiType.REWARD_HB_FOR_GROUP.getValue());
			t.setFromUserId(giveHBReq.getUserId());
			t.setHandleType(DoUserAssetsTrade.HandleType.TRANSFER.name());
			t.setSourceNo(o.getOrderId());
			t.setSummary("用户[" + giveHBReq.getUserId() + "]点评GROUP活动并打赏活动发起者[" + go.getUserId() + "]共["
					+ giveHBReq.getCount() + "]个海贝,源单号为活动参加流水");
			t.setToUserId(go.getUserId());
			t.setTradeBalance(giveHBReq.getCount());
			UserAssetsTradeMQSend.sendMQ(t);
			// 给活动发起者消息中心发送通知
			DoNotify notify = new DoNotify();
			notify.setNotifyId(UUIDUtil.genId32());
			notify.setHandleType(DoNotify.HandleType.PUBLISH.name());
			notify.setNotifyId(UUIDUtil.genId32());
			notify.setNotifyType(NotifyType.SYSTEM_NOTIFY.getValue());
			notify.setSenderType(SenderType.USER.getValue());
			notify.setSenderId(o.getUserId());
			notify.setAccepterType(AccepterType.USER.getValue());
			notify.setAccepterId(go.getUserId());
			notify.setTitle("收到海贝通知~");
			notify.setContent("给您发起的group活动[" + go.getTopic() + "]捐献了[" + giveHBReq.getCount() + "]个海贝，速速查看~");
			notify.setLink(
					"../go/hainiugroupcomments.html?goOrderId=" + o.getOrderId() + "&notifyId=" + notify.getNotifyId());
			NotifyMQSend.sendNotifyMQ(notify);

		} else if (GoType.ONE_ON_ONE.getValue().equals(giveHBReq.getGoType())) {
			HyGoOrder o = hyGoOrderMapper.selectByPrimaryKey(giveHBReq.getGoOrderId());
			if (o == null) {
				throw new BusinessException("活动预约申请不存在，不能打赏哦");
			}
			if (!o.getUserId().equals(giveHBReq.getUserId())) {
				throw new BusinessException("您不是当前活动申请者，不能打赏哦");
			}
			Go go = this.getGoInfo(o.getGoId());
			if (go == null) {
				throw new BusinessException("活动信息不存在，不能打赏哦");
			}
			o.setGiveHb(o.getGiveHb() + giveHBReq.getCount());
			hyGoOrderMapper.updateByPrimaryKeySelective(o);

			// 发送海贝赠送交易消息
			DoUserAssetsTrade t = new DoUserAssetsTrade();
			t.setAssetsType(AssetsType.HAIBEI.getValue());
			t.setBusiType(BusiType.REWARD_HB_FOR_ONO.getValue());
			t.setFromUserId(giveHBReq.getUserId());
			t.setHandleType(DoUserAssetsTrade.HandleType.TRANSFER.name());
			t.setSourceNo(o.getOrderId());
			t.setSummary("用户[" + giveHBReq.getUserId() + "]点评ono活动并打赏活动发起者[" + go.getUserId() + "]共["
					+ giveHBReq.getCount() + "]个海贝,源单号为活动预约流水");
			t.setToUserId(go.getUserId());
			t.setTradeBalance(giveHBReq.getCount());
			UserAssetsTradeMQSend.sendMQ(t);
			// 给活动发起者消息中心发送通知
			DoNotify notify = new DoNotify();
			notify.setHandleType(DoNotify.HandleType.PUBLISH.name());
			notify.setNotifyId(UUIDUtil.genId32());
			notify.setNotifyType(NotifyType.SYSTEM_NOTIFY.getValue());
			notify.setSenderType(SenderType.USER.getValue());
			notify.setSenderId(o.getUserId());
			notify.setAccepterType(AccepterType.USER.getValue());
			notify.setAccepterId(go.getUserId());
			notify.setTitle("收到海贝通知~");
			notify.setContent("给您发起的one on one活动[" + go.getTopic() + "]捐献了[" + giveHBReq.getCount() + "]个海贝，速速查看~");
			notify.setLink(
					"../go/toHainiuFeedback.html?goOrderId=" + o.getOrderId() + "&notifyId=" + notify.getNotifyId());
			NotifyMQSend.sendNotifyMQ(notify);
		}

	}

	@Override
	public List<GoJoin> getGoJoins(String goId) {
		HyGoJoinCriteria sql = new HyGoJoinCriteria();
		sql.or().andGoIdEqualTo(goId);
		List<HyGoJoin> list = hyGoJoinMapper.selectByExample(sql);
		if (CollectionUtil.isEmpty(list)) {
			return null;
		}
		List<GoJoin> l = new ArrayList<GoJoin>();
		for (HyGoJoin b : list) {
			GoJoin o = new GoJoin();
			BeanUtils.copyProperties(b, o);
			UserViewInfo joinUser = userManagerSV.getUserViewInfoByUserId(b.getUserId());
			if (joinUser != null) {
				BeanUtils.copyProperties(joinUser, o);
			}
			o.setOrderStatusName(HyDictUtil.getHyDictDesc(TypeCode.HY_GO_JOIN.getValue(),
					ParamCode.ORDER_STATUS.getValue(), b.getOrderStatus()));
			l.add(o);
		}
		return l;
	}

	@Override
	public List<GoOrder> getGoOrders(String goId) {
		HyGoOrderCriteria sql = new HyGoOrderCriteria();
		sql.or().andGoIdEqualTo(goId);
		List<HyGoOrder> list = hyGoOrderMapper.selectByExample(sql);
		if (CollectionUtil.isEmpty(list)) {
			return null;
		}
		List<GoOrder> l = new ArrayList<GoOrder>();
		for (HyGoOrder b : list) {
			GoOrder o = new GoOrder();
			BeanUtils.copyProperties(b, o);
			UserViewInfo joinUser = userManagerSV.getUserViewInfoByUserId(b.getUserId());
			if (joinUser != null) {
				BeanUtils.copyProperties(joinUser, o);
			}
			o.setOrderStatusName(HyDictUtil.getHyDictDesc(TypeCode.HY_GO_ORDER.getValue(),
					ParamCode.ORDER_STATUS.getValue(), b.getOrderStatus()));
			l.add(o);
		}
		return l;
	}

	@Override
	public HyGoJoin getHyGoJoin(String orderId) {
		return hyGoJoinMapper.selectByPrimaryKey(orderId);
	}

	@Override
	public List<HyGoJoin> getHyGoJoins(String userId, String goId) {
		HyGoJoinCriteria sql = new HyGoJoinCriteria();
		sql.or().andGoIdEqualTo(goId).andUserIdEqualTo(userId);
		List<HyGoJoin> list = hyGoJoinMapper.selectByExample(sql);
		return list;
	}

	@Override
	public void doGoFavorite(GroupApplyReq groupApplyReq) {
		String userId = groupApplyReq.getUserId();
		String goId = groupApplyReq.getGoId();
		/* 判断用户是否已经收藏此活动 */
		boolean joint = HyGoUtil.checkUserGoFavorite(goId, userId);
		if (joint) {
			throw new BusinessException("您已经收藏此活动，不能重复收藏哦~");
		}
		DoGoFavorite body = new DoGoFavorite();
		body.setHandleType(DoGoFavorite.HandleType.DO.name());
		body.setGoId(goId);
		body.setUserId(userId);
		UserFavorMQSend.sendMQ(body);
	}

	@Override
	public void processGoDelete(String goId, String goType) {
		HyGo record = new HyGo();
		record.setStatus(com.the.harbor.base.enumeration.hygo.Status.CANCEL.getValue());
		record.setGoId(goId);
		hyGoMapper.updateByPrimaryKeySelective(record);

		this.deleteGoToOpenSearch(goId);

		this.processGoFavorDelete(goId, goType);
	}

	private void processGoFavorDelete(String goId, String goType) {
		HyGoFavoriteCriteria sql = new HyGoFavoriteCriteria();
		sql.or().andGoIdEqualTo(goId);
		List<HyGoFavorite> list = hyGoFavoriteMapper.selectByExample(sql);
		if (CollectionUtil.isEmpty(list)) {
			return;
		}
		for (HyGoFavorite go : list) {
			// hyGoFavoriteMapper.deleteByPrimaryKey(go.getFavoriteId());
			HyGoUtil.userCancelFavorGo(go.getUserId(), goType, goId);
		}
	}

	@Override
	public void topGo(String goId, String topFlag, Timestamp topDate) {
		HyGo record = new HyGo();
		record.setTopFlag(topFlag);
		record.setTopDate(topDate);
		record.setGoId(goId);
		hyGoMapper.updateByPrimaryKeySelective(record);

		// 更新索引
		this.topGoToOpenSearch(goId, topFlag, topDate);
	}

	@Override
	public void hideGo(String goId, String hideFlag) {
		HyGo record = new HyGo();
		record.setHideFlag(hideFlag);
		record.setGoId(goId);
		hyGoMapper.updateByPrimaryKeySelective(record);

		this.hideGoToOpenSearch(goId, hideFlag);
	}

	@Override
	public int getGoYiYouCount(String userId) {
		HyGoJoinCriteria sql = new HyGoJoinCriteria();
		sql.or().andHelpValueEqualTo(HelpValue.YIYOU.getValue()).andSponsorIdEqualTo(userId);
		return hyGoJoinMapper.countByExample(sql);
	}

	@Override
	public int getZhuRenCount(String userId) {
		HyGoOrderCriteria sql = new HyGoOrderCriteria();
		sql.or().andHelpValueEqualTo(com.the.harbor.base.enumeration.hygoorder.HelpValue.BE_HELPFULL.getValue())
				.andSponsorIdEqualTo(userId);
		return hyGoOrderMapper.countByExample(sql);
	}

	@Override
	public int getGoHelpCount(String goId, String goType) {
		if (GoType.GROUP.getValue().equals(goType)) {
			HyGoJoinCriteria sql = new HyGoJoinCriteria();
			sql.or().andHelpValueEqualTo(HelpValue.YIYOU.getValue()).andGoIdEqualTo(goId);
			return hyGoJoinMapper.countByExample(sql);
		} else if (GoType.ONE_ON_ONE.getValue().equals(goType)) {
			HyGoOrderCriteria sql = new HyGoOrderCriteria();
			sql.or().andHelpValueEqualTo(com.the.harbor.base.enumeration.hygoorder.HelpValue.BE_HELPFULL.getValue())
					.andGoIdEqualTo(goId);
			return hyGoOrderMapper.countByExample(sql);
		}
		return 0;
	}

	@Override
	public void pushGoToOpenSearch(String goId) {
		HyGo hyGo = hyGoMapper.selectByPrimaryKey(goId);
		if (hyGo == null) {
			return;
		}
		// 索引以GO的明细记录*GO的故事记录
		List<GoIndexOperate> indexs = new ArrayList<GoIndexOperate>();
		List<String> goTagIds = new ArrayList<String>();
		List<String> goTagNames = new ArrayList<String>();
		List<String> polyTagIds = new ArrayList<String>();
		Map<String, String> contentMap = new HashMap<String, String>();
		HyGoTagsCriteria sql = new HyGoTagsCriteria();
		sql.or().andGoIdEqualTo(goId).andStatusEqualTo(com.the.harbor.base.enumeration.common.Status.VALID.getValue());
		List<HyGoTags> goTags = hyGoTagsMapper.selectByExample(sql);
		if (!CollectionUtil.isEmpty(goTags)) {
			for (HyGoTags hyGoTags : goTags) {
				goTagIds.add(hyGoTags.getTagId());
				goTagNames.add(hyGoTags.getTagName());
				if (!StringUtil.isBlank(hyGoTags.getPolyTagId())) {
					polyTagIds.add(hyGoTags.getPolyTagId());
				}

			}
		}
		// 获取GO明细
		HyGoDetailCriteria sql1 = new HyGoDetailCriteria();
		sql1.or().andGoIdEqualTo(goId).andTypeEqualTo(GoDetailType.TEXT.getValue())
				.andStatusEqualTo(com.the.harbor.base.enumeration.common.Status.VALID.getValue());
		List<HyGoDetail> godetails = hyGoDetailMapper.selectByExample(sql1);
		if (!CollectionUtil.isEmpty(godetails)) {
			for (HyGoDetail goDetail : godetails) {
				contentMap.put("godetail_" + goDetail.getId(), goDetail.getDetail());
			}
		}
		// 获取STORY明细
		HyGoStoryCriteria sql2 = new HyGoStoryCriteria();
		sql2.or().andGoIdEqualTo(goId).andTypeEqualTo(GoDetailType.TEXT.getValue())
				.andStatusEqualTo(com.the.harbor.base.enumeration.common.Status.VALID.getValue());
		List<HyGoStory> goStories = hyGoStoryMapper.selectByExample(sql2);
		if (!CollectionUtil.isEmpty(goStories)) {
			for (HyGoStory goStory : goStories) {
				contentMap.put("gostory_" + goStory.getId(), goStory.getDetail());
			}
		}
		// 组织索引数据，交叉循环计算
		for (String key : contentMap.keySet()) {
			String type = key.split("_")[0];
			String story = null;
			String content = null;
			if ("godetail".equals(type)) {
				content = contentMap.get(key);
			} else {
				story = contentMap.get(key);
			}

			GoIndexModel m = new GoIndexModel();
			BeanUtils.copyProperties(hyGo, m);
			m.setIndexId(UUIDUtil.genId32());
			m.setContent(content);
			m.setStory(story);
			m.setGoTagIds(goTagIds.toArray(new String[goTagIds.size()]));
			m.setGoTagNames(goTagNames.toArray(new String[goTagNames.size()]));
			m.setPolyTagIds(polyTagIds.toArray(new String[polyTagIds.size()]));

			GoIndexOperate op = new GoIndexOperate();
			op.setCmd("add");
			op.setFields(m);
			indexs.add(op);
		}

		System.out.println(JSON.toJSONString(indexs));
		CloudsearchClient client = OpenSearchFactory.getClient();
		CloudsearchDoc doc = new CloudsearchDoc("harbor_go", client);
		try {
			String result = doc.push(JSON.toJSONString(indexs), "hy_go");
			LOG.debug("同步GO到openSearch:" + result);
			JSONObject d = JSON.parseObject(result);
			String status = d.getString("status");
			if ("FAIL".equals(status)) {
				JSONArray errors = d.getJSONArray("errors");
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < errors.size(); i++) {
					JSONObject e = errors.getJSONObject(i);
					sb.append(e.getString("message"));
				}
				String errormsg = StringUtil.isBlank(sb.toString()) ? "搜索引擎返回异常" : sb.toString();
				throw new SystemException(errormsg);
			}
		} catch (Exception e) {
			LOG.error("同步GO到openSearch失败", e);
			throw new SystemException(e.getMessage());
		}
	}

	@Override
	public void pushAllGoToOpenSearch() {
		List<GoIndexOperate> indexs = new ArrayList<GoIndexOperate>();
		HyGoCriteria sql0 = new HyGoCriteria();
		List<HyGo> goes = hyGoMapper.selectByExample(sql0);
		if (!CollectionUtil.isEmpty(goes)) {
			for (HyGo hyGo : goes) {
				String goId = hyGo.getGoId();
				List<String> goTagIds = new ArrayList<String>();
				List<String> goTagNames = new ArrayList<String>();
				List<String> polyTagIds = new ArrayList<String>();
				Map<String, String> contentMap = new HashMap<String, String>();
				HyGoTagsCriteria sql = new HyGoTagsCriteria();
				sql.or().andGoIdEqualTo(goId)
						.andStatusEqualTo(com.the.harbor.base.enumeration.common.Status.VALID.getValue());
				List<HyGoTags> goTags = hyGoTagsMapper.selectByExample(sql);
				if (!CollectionUtil.isEmpty(goTags)) {
					for (HyGoTags hyGoTags : goTags) {
						goTagIds.add(hyGoTags.getTagId());
						goTagNames.add(hyGoTags.getTagName());
						if (!StringUtil.isBlank(hyGoTags.getPolyTagId())) {
							polyTagIds.add(hyGoTags.getPolyTagId());
						}

					}
				}
				// 获取GO明细
				HyGoDetailCriteria sql1 = new HyGoDetailCriteria();
				sql1.or().andGoIdEqualTo(goId).andTypeEqualTo(GoDetailType.TEXT.getValue())
						.andStatusEqualTo(com.the.harbor.base.enumeration.common.Status.VALID.getValue());
				List<HyGoDetail> godetails = hyGoDetailMapper.selectByExample(sql1);
				if (!CollectionUtil.isEmpty(godetails)) {
					for (HyGoDetail goDetail : godetails) {
						contentMap.put("godetail_" + goDetail.getId(), goDetail.getDetail());
					}
				}
				// 获取STORY明细
				HyGoStoryCriteria sql2 = new HyGoStoryCriteria();
				sql2.or().andGoIdEqualTo(goId).andTypeEqualTo(GoDetailType.TEXT.getValue())
						.andStatusEqualTo(com.the.harbor.base.enumeration.common.Status.VALID.getValue());
				List<HyGoStory> goStories = hyGoStoryMapper.selectByExample(sql2);
				if (!CollectionUtil.isEmpty(goStories)) {
					for (HyGoStory goStory : goStories) {
						contentMap.put("gostory_" + goStory.getId(), goStory.getDetail());
					}
				}
				// 组织索引数据，交叉循环计算
				for (String key : contentMap.keySet()) {
					String type = key.split("_")[0];
					String story = null;
					String content = null;
					if ("godetail".equals(type)) {
						content = contentMap.get(key);
					} else {
						story = contentMap.get(key);
					}

					GoIndexModel m = new GoIndexModel();
					BeanUtils.copyProperties(hyGo, m);
					m.setIndexId(UUIDUtil.genId32());
					m.setContent(content);
					m.setStory(story);
					m.setGoTagIds(goTagIds.toArray(new String[goTagIds.size()]));
					m.setGoTagNames(goTagNames.toArray(new String[goTagNames.size()]));
					m.setPolyTagIds(polyTagIds.toArray(new String[polyTagIds.size()]));

					GoIndexOperate op = new GoIndexOperate();
					op.setCmd("add");
					op.setFields(m);
					indexs.add(op);
				}

			}
		}

		CloudsearchClient client = OpenSearchFactory.getClient();
		CloudsearchDoc doc = new CloudsearchDoc("harbor_go", client);
		try {
			String result = doc.push(JSON.toJSONString(indexs), "hy_go");
			LOG.debug("同步GO到openSearch:" + result);
			JSONObject d = JSON.parseObject(result);
			String status = d.getString("status");
			if ("FAIL".equals(status)) {
				JSONArray errors = d.getJSONArray("errors");
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < errors.size(); i++) {
					JSONObject e = errors.getJSONObject(i);
					sb.append(e.getString("message"));
				}
				String errormsg = StringUtil.isBlank(sb.toString()) ? "搜索引擎返回异常" : sb.toString();
				throw new SystemException(errormsg);
			}
		} catch (Exception e) {
			LOG.error("同步GO到openSearch失败", e);
			throw new SystemException(e.getMessage());
		}

	}

	@Override
	public Go getGoInfo(String goId) {
		String data = HyGoUtil.getGo(goId);
		Go go = JSON.parseObject(data, Go.class);
		if (go == null) {
			this.resetGo2Redis(goId);
		}
		this.fillGoInfo(go);
		return go;
	}

	@Override
	public void fillGoInfo(Go go) {
		if (go == null) {
			return;
		}
		String contentSummary = null;
		if (!CollectionUtil.isEmpty(go.getGoDetails())) {
			for (GoDetail detail : go.getGoDetails()) {
				if (GoDetailType.TEXT.getValue().equals(detail.getType())) {
					contentSummary = detail.getDetail();
					break;
				}
			}
		}
		go.setContentSummary(contentSummary);
		go.setFixPriceYuan(String.valueOf(go.getFixedPrice()/100));
		go.setGoTypeName(
				HyDictUtil.getHyDictDesc(TypeCode.HY_GO.getValue(), ParamCode.GO_TYPE.getValue(), go.getGoType()));
		go.setPayModeName(
				HyDictUtil.getHyDictDesc(TypeCode.HY_GO.getValue(), ParamCode.PAY_MODE.getValue(), go.getPayMode()));
		go.setOrgModeName(
				HyDictUtil.getHyDictDesc(TypeCode.HY_GO.getValue(), ParamCode.ORG_MODE.getValue(), go.getOrgMode()));
		if (!StringUtil.isBlank(go.getOfflineCity())) {
			go.setOfflineCityName(HyAreaUtil.getAreaName(go.getOfflineCity()));
		}

		if (!StringUtil.isBlank(go.getOfflineProvince())) {
			go.setOfflineProvinceName(HyAreaUtil.getAreaName(go.getOfflineProvince()));
		}

		// 发布用户信息
		UserViewInfo createUserInfo = userManagerSV.getUserViewInfoByUserId(go.getUserId());
		if (createUserInfo != null) {
			go.setAtCityName(createUserInfo.getAtCityName());
			go.setEnName(createUserInfo.getEnName());
			go.setIndustryName(createUserInfo.getIndustryName());
			go.setTitle(createUserInfo.getTitle());
			go.setWxHeadimg(createUserInfo.getWxHeadimg());
			go.setUserStatus(createUserInfo.getUserStatus());
			go.setUserStatusName(createUserInfo.getUserStatusName());
			go.setAbroadCountryName(createUserInfo.getAbroadCountryName());
			go.setHomePageBg(createUserInfo.getHomePageBg());
			go.setAbroadCountryRGB(createUserInfo.getAbroadCountryRGB());
		}

		go.setCreateTimeStr(DateUtil.getDateString(go.getCreateDate(), "MM月dd"));
		go.setCreateTimeInterval(DateUtil.getInterval(go.getCreateDate()));

		go.setHelpCount(this.getGoHelpCount(go.getGoId(), go.getGoType()));

		long count = HyGoUtil.getGoFavoredUserCount(go.getGoId());
		go.setFavorCount(count);

		long joinCount = HyGoUtil.getGroupConfirmedJoinUsersCount(go.getGoId());
		go.setJoinCount(joinCount);

		long viewCount = HyGoUtil.getGoViewCount(go.getGoId());
		go.setViewCount(viewCount);

	}

	private List<String> getGoIndexs(String goId) {
		CloudsearchClient client = OpenSearchFactory.getClient();
		CloudsearchSearch search = new CloudsearchSearch(client);
		search.addIndex("harbor_go");
		search.setQueryString(" goid:'" + goId + "'");
		search.setFormat("json");
		String res;
		try {
			res = search.search();
		} catch (Exception e) {
			throw new SystemException("查询错误");
		}
		List<String> indexs = new ArrayList<String>();
		JSONObject d = JSONObject.parseObject(res);
		String status = d.getString("status");
		if ("OK".equals(status)) {
			JSONObject rs = d.getJSONObject("result");
			JSONArray arr = rs.getJSONArray("items");
			for (int i = 0; i < arr.size(); i++) {
				JSONObject data = arr.getJSONObject(i);
				String indexId = data.getString("indexid");
				indexs.add(indexId);
			}

		}
		return indexs;
	}

	public void deleteGoToOpenSearch(String goId) {
		List<String> indexIds = this.getGoIndexs(goId);
		if (CollectionUtil.isEmpty(indexIds)) {
			return;
		}
		List<GoIndexOperate> indexs = new ArrayList<GoIndexOperate>();
		for (String indexId : indexIds) {
			GoIndexModel m = new GoIndexModel();
			m.setIndexId(indexId);
			m.setStatus(Status.CANCEL.getValue());

			GoIndexOperate op = new GoIndexOperate();
			op.setCmd("update");
			op.setFields(m);
			indexs.add(op);
		}
		CloudsearchClient client = OpenSearchFactory.getClient();
		CloudsearchDoc doc = new CloudsearchDoc("harbor_go", client);
		try {
			String result = doc.push(JSON.toJSONString(indexs), "hy_go");
			LOG.debug("删除GO的结果到openSearch:" + result);
		} catch (Exception e) {
			LOG.error("删除GO操作同步到openSearch失败", e);
			throw new SystemException(e);
		}
	}

	public void hideGoToOpenSearch(String goId, String hideFlag) {
		List<String> indexIds = this.getGoIndexs(goId);
		if (CollectionUtil.isEmpty(indexIds)) {
			return;
		}
		List<GoIndexOperate> indexs = new ArrayList<GoIndexOperate>();
		for (String indexId : indexIds) {
			GoIndexModel m = new GoIndexModel();
			m.setIndexId(indexId);
			m.setHideFlag(hideFlag);
			GoIndexOperate op = new GoIndexOperate();
			op.setCmd("update");
			op.setFields(m);
			indexs.add(op);
		}
		CloudsearchClient client = OpenSearchFactory.getClient();
		CloudsearchDoc doc = new CloudsearchDoc("harbor_go", client);
		try {
			String result = doc.push(JSON.toJSONString(indexs), "hy_go");
			LOG.debug("隐藏GO的结果到openSearch:" + result);
		} catch (Exception e) {
			LOG.error("隐藏GO操作同步到openSearch失败", e);
			throw new SystemException(e);
		}
	}

	public void topGoToOpenSearch(String goId, String topFlag, Timestamp topDate) {
		List<String> indexIds = this.getGoIndexs(goId);
		if (CollectionUtil.isEmpty(indexIds)) {
			return;
		}
		List<GoIndexOperate> indexs = new ArrayList<GoIndexOperate>();
		for (String indexId : indexIds) {
			GoIndexModel m = new GoIndexModel();
			m.setIndexId(indexId);
			m.setTopDate(TopFlag.YES.getValue().equals(topFlag) ? topDate : DateUtil.getTimestamp(0));
			m.setTopFlag(topFlag);
			GoIndexOperate op = new GoIndexOperate();
			op.setCmd("update");
			op.setFields(m);
			indexs.add(op);
		}
		CloudsearchClient client = OpenSearchFactory.getClient();
		CloudsearchDoc doc = new CloudsearchDoc("harbor_go", client);
		try {
			String result = doc.push(JSON.toJSONString(indexs), "hy_go");
			LOG.debug("置顶GO的结果到openSearch:" + result);
		} catch (Exception e) {
			LOG.error("置顶GO操作同步到openSearch失败", e);
			throw new SystemException(e);
		}
	}

	@Override
	public void resetAllGo2Redis() {
		HyGoCriteria sql = new HyGoCriteria();
		List<HyGo> goes = hyGoMapper.selectByExample(sql);
		if (CollectionUtil.isEmpty(goes)) {
			return;
		}
		for (HyGo hyGo : goes) {
			String goId = hyGo.getGoId();
			Go bgo = new Go();
			List<GoTag> goTags = new ArrayList<GoTag>();
			List<GoDetail> goDetails = new ArrayList<GoDetail>();
			List<GoStory> goStories = new ArrayList<GoStory>();
			BeanUtils.copyProperties(hyGo, bgo);

			// 获取标签
			HyGoTagsCriteria tagSql = new HyGoTagsCriteria();
			tagSql.or().andGoIdEqualTo(goId)
					.andStatusEqualTo(com.the.harbor.base.enumeration.common.Status.VALID.getValue());
			List<HyGoTags> hyGoTags = hyGoTagsMapper.selectByExample(tagSql);
			if (!CollectionUtil.isEmpty(hyGoTags)) {
				for (HyGoTags hyGoTag : hyGoTags) {
					GoTag t = new GoTag();
					BeanUtils.copyProperties(hyGoTag, t);
					goTags.add(t);
				}
			}
			// 获取GO明细
			HyGoDetailCriteria sql1 = new HyGoDetailCriteria();
			sql1.or().andGoIdEqualTo(goId)
					.andStatusEqualTo(com.the.harbor.base.enumeration.common.Status.VALID.getValue());
			sql1.setOrderByClause(" sort asc");
			List<HyGoDetail> godetails = hyGoDetailMapper.selectByExample(sql1);
			if (!CollectionUtil.isEmpty(godetails)) {
				for (HyGoDetail hyGoDetail : godetails) {
					GoDetail t = new GoDetail();
					BeanUtils.copyProperties(hyGoDetail, t);
					goDetails.add(t);
				}
			}
			// 获取STORY明细
			HyGoStoryCriteria sql2 = new HyGoStoryCriteria();
			sql2.or().andGoIdEqualTo(goId)
					.andStatusEqualTo(com.the.harbor.base.enumeration.common.Status.VALID.getValue());
			sql2.setOrderByClause(" sort asc");
			List<HyGoStory> hyGoStories = hyGoStoryMapper.selectByExample(sql2);
			if (!CollectionUtil.isEmpty(hyGoStories)) {
				for (HyGoStory goStory : hyGoStories) {
					GoStory t = new GoStory();
					BeanUtils.copyProperties(goStory, t);
					goStories.add(t);
				}
			}

			bgo.setGoDetails(goDetails);
			bgo.setGoTags(goTags);
			bgo.setGoStories(goStories);
			// 填充其它信息
			this.fillGoInfo(bgo);

			// 写入REDIS
			HyGoUtil.recordGo(goId, JSON.toJSONString(bgo));

		}

	}

	private void resetGo2Redis(String goId) {
		HyGo hyGo = hyGoMapper.selectByPrimaryKey(goId);
		if (hyGo == null) {
			return;
		}
		Go bgo = new Go();
		List<GoTag> goTags = new ArrayList<GoTag>();
		List<GoDetail> goDetails = new ArrayList<GoDetail>();
		List<GoStory> goStories = new ArrayList<GoStory>();
		BeanUtils.copyProperties(hyGo, bgo);

		// 获取标签
		HyGoTagsCriteria tagSql = new HyGoTagsCriteria();
		tagSql.or().andGoIdEqualTo(goId)
				.andStatusEqualTo(com.the.harbor.base.enumeration.common.Status.VALID.getValue());
		List<HyGoTags> hyGoTags = hyGoTagsMapper.selectByExample(tagSql);
		if (!CollectionUtil.isEmpty(hyGoTags)) {
			for (HyGoTags hyGoTag : hyGoTags) {
				GoTag t = new GoTag();
				BeanUtils.copyProperties(hyGoTag, t);
				goTags.add(t);
			}
		}
		// 获取GO明细
		HyGoDetailCriteria sql1 = new HyGoDetailCriteria();
		sql1.or().andGoIdEqualTo(goId).andStatusEqualTo(com.the.harbor.base.enumeration.common.Status.VALID.getValue());
		sql1.setOrderByClause(" sort asc");
		List<HyGoDetail> godetails = hyGoDetailMapper.selectByExample(sql1);
		if (!CollectionUtil.isEmpty(godetails)) {
			for (HyGoDetail hyGoDetail : godetails) {
				GoDetail t = new GoDetail();
				BeanUtils.copyProperties(hyGoDetail, t);
				goDetails.add(t);
			}
		}
		// 获取STORY明细
		HyGoStoryCriteria sql2 = new HyGoStoryCriteria();
		sql2.or().andGoIdEqualTo(goId).andStatusEqualTo(com.the.harbor.base.enumeration.common.Status.VALID.getValue());
		sql2.setOrderByClause(" sort asc");
		List<HyGoStory> hyGoStories = hyGoStoryMapper.selectByExample(sql2);
		if (!CollectionUtil.isEmpty(hyGoStories)) {
			for (HyGoStory goStory : hyGoStories) {
				GoStory t = new GoStory();
				BeanUtils.copyProperties(goStory, t);
				goStories.add(t);
			}
		}

		bgo.setGoDetails(goDetails);
		bgo.setGoTags(goTags);
		bgo.setGoStories(goStories);
		// 填充其它信息
		this.fillGoInfo(bgo);

		// 写入REDIS
		HyGoUtil.recordGo(goId, JSON.toJSONString(bgo));

	}

}
