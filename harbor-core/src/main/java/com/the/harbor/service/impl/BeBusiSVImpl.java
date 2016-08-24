package com.the.harbor.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.mortbay.log.Log;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.aliyun.opensearch.CloudsearchClient;
import com.aliyun.opensearch.CloudsearchDoc;
import com.the.harbor.api.be.param.Be;
import com.the.harbor.api.be.param.BeComment;
import com.the.harbor.api.be.param.BeCreateReq;
import com.the.harbor.api.be.param.BeDetail;
import com.the.harbor.api.be.param.BeIndexModel;
import com.the.harbor.api.be.param.BeTag;
import com.the.harbor.api.be.param.DoBeComment;
import com.the.harbor.api.be.param.DoBeFavorite;
import com.the.harbor.api.be.param.DoBeIndexRealtimeStat;
import com.the.harbor.api.be.param.DoBeLikes;
import com.the.harbor.api.be.param.DoBeView;
import com.the.harbor.api.be.param.GiveHBReq;
import com.the.harbor.api.user.param.DoUserAssetsTrade;
import com.the.harbor.api.user.param.UserViewInfo;
import com.the.harbor.base.enumeration.common.BusiErrorCode;
import com.the.harbor.base.enumeration.common.Status;
import com.the.harbor.base.enumeration.hybe.BeDetailType;
import com.the.harbor.base.enumeration.hybe.HideFlag;
import com.the.harbor.base.enumeration.hybe.TopFlag;
import com.the.harbor.base.enumeration.hynotify.AccepterType;
import com.the.harbor.base.enumeration.hynotify.NotifyType;
import com.the.harbor.base.enumeration.hynotify.SenderType;
import com.the.harbor.base.enumeration.hypaymentorder.BusiType;
import com.the.harbor.base.enumeration.hytags.TagType;
import com.the.harbor.base.enumeration.hyuserassets.AssetsType;
import com.the.harbor.base.exception.BusinessException;
import com.the.harbor.commons.components.aliyuncs.opensearch.OpenSearchFactory;
import com.the.harbor.commons.components.elasticsearch.ElasticSearchFactory;
import com.the.harbor.commons.indices.def.HarborIndex;
import com.the.harbor.commons.indices.def.HarborIndexType;
import com.the.harbor.commons.redisdata.def.DoNotify;
import com.the.harbor.commons.redisdata.util.HyBeUtil;
import com.the.harbor.commons.util.CollectionUtil;
import com.the.harbor.commons.util.DateUtil;
import com.the.harbor.commons.util.StringUtil;
import com.the.harbor.commons.util.UUIDUtil;
import com.the.harbor.dao.mapper.bo.HyBe;
import com.the.harbor.dao.mapper.bo.HyBeComments;
import com.the.harbor.dao.mapper.bo.HyBeCriteria;
import com.the.harbor.dao.mapper.bo.HyBeDetail;
import com.the.harbor.dao.mapper.bo.HyBeDetailCriteria;
import com.the.harbor.dao.mapper.bo.HyBeFavorite;
import com.the.harbor.dao.mapper.bo.HyBeFavoriteCriteria;
import com.the.harbor.dao.mapper.bo.HyBeGiveHb;
import com.the.harbor.dao.mapper.bo.HyBeLikes;
import com.the.harbor.dao.mapper.bo.HyBeLikesCriteria;
import com.the.harbor.dao.mapper.bo.HyBeTags;
import com.the.harbor.dao.mapper.bo.HyBeTagsCriteria;
import com.the.harbor.dao.mapper.bo.HyBeView;
import com.the.harbor.dao.mapper.bo.HyUserAssets;
import com.the.harbor.dao.mapper.interfaces.HyBeCommentsMapper;
import com.the.harbor.dao.mapper.interfaces.HyBeDetailMapper;
import com.the.harbor.dao.mapper.interfaces.HyBeFavoriteMapper;
import com.the.harbor.dao.mapper.interfaces.HyBeGiveHbMapper;
import com.the.harbor.dao.mapper.interfaces.HyBeLikesMapper;
import com.the.harbor.dao.mapper.interfaces.HyBeMapper;
import com.the.harbor.dao.mapper.interfaces.HyBeTagsMapper;
import com.the.harbor.dao.mapper.interfaces.HyBeViewMapper;
import com.the.harbor.service.interfaces.IBeBusiSV;
import com.the.harbor.service.interfaces.IUserManagerSV;
import com.the.harbor.util.ESIndexBuildMQSend;
import com.the.harbor.util.HarborSeqUtil;
import com.the.harbor.util.IndexRealtimeCountMQSend;
import com.the.harbor.util.NotifyMQSend;
import com.the.harbor.util.UserAssetsTradeMQSend;
import com.the.harbor.util.UserFavorMQSend;
import com.the.harbor.vo.BeIndexOperate;

@Component
@Transactional
public class BeBusiSVImpl implements IBeBusiSV {

	@Autowired
	private transient HyBeMapper hyBeMapper;

	@Autowired
	private transient HyBeTagsMapper hyBeTagsMapper;

	@Autowired
	private transient HyBeDetailMapper hyBeDetailMapper;

	@Autowired
	private transient HyBeLikesMapper hyBeLikesMapper;

	@Autowired
	private transient HyBeCommentsMapper hyBeCommentsMapper;

	@Autowired
	private transient HyBeGiveHbMapper hyBeGiveHbMapper;

	@Autowired
	private transient IUserManagerSV userManagerSV;

	@Autowired
	private transient HyBeViewMapper hyBeViewMapper;

	@Autowired
	private transient HyBeFavoriteMapper hyBeFavoriteMapper;

	@Override
	public String createBe(BeCreateReq beCreateReq) {
		String beId = HarborSeqUtil.createBeId();
		Timestamp sysdate = DateUtil.getSysDate();

		Be be = new Be();
		// 写入主表
		HyBe record = new HyBe();
		record.setBeId(beId);
		record.setCreateDate(sysdate);
		record.setStatus(Status.VALID.getValue());
		record.setUserId(beCreateReq.getUserId());
		record.setTopFlag(TopFlag.NO.getValue());
		record.setHideFlag(HideFlag.NO.getValue());
		// 复制内容
		BeanUtils.copyProperties(record, be);
		hyBeMapper.insert(record);
		// 写入内容明细表
		if (!CollectionUtil.isEmpty(beCreateReq.getBeDetails())) {
			// 复制内容
			be.setBeDetails(beCreateReq.getBeDetails());
			// 写表
			for (BeDetail d : beCreateReq.getBeDetails()) {
				HyBeDetail bd = new HyBeDetail();
				BeanUtils.copyProperties(d, bd);
				bd.setId(HarborSeqUtil.createBeDetailId());
				bd.setDetail(d.getDetail());
				bd.setBeId(beId);
				bd.setCreateDate(sysdate);
				bd.setStatus(Status.VALID.getValue());
				hyBeDetailMapper.insert(bd);
			}
		}
		// 写入标签表
		if (!CollectionUtil.isEmpty(beCreateReq.getBeTags())) {
			// 复制内容
			be.setBeTags(beCreateReq.getBeTags());
			// 写表
			int sortId = 0;
			for (BeTag d : beCreateReq.getBeTags()) {
				String tagId = StringUtil.isBlank(d.getTagId()) ? HarborSeqUtil.createTagId(TagType.BE.getValue())
						: d.getTagId();
				d.setTagId(tagId);
				HyBeTags bt = new HyBeTags();
				BeanUtils.copyProperties(d, bt);
				bt.setRecordId(HarborSeqUtil.createHyUserTagsRecordId());
				bt.setBeId(beId);
				bt.setSortId(sortId);
				bt.setTagId(tagId);
				bt.setStatus(Status.VALID.getValue());
				sortId++;
				hyBeTagsMapper.insert(bt);
			}
		}
		// 构建索引记录并发送到消息中,实现异步构建
		ESIndexBuildMQSend.sendMQ(be);
		return beId;
	}

	@Override
	public void processDoBeLikesMQ(DoBeLikes doBELikes) {
		HyBe be = hyBeMapper.selectByPrimaryKey(doBELikes.getBeId());
		if (be == null) {
			return;
		}
		if (DoBeLikes.HandleType.ZAN.name().equals(doBELikes.getHandleType())) {
			// 如果是点赞，则记录
			HyBeLikes record = new HyBeLikes();
			record.setBeId(doBELikes.getBeId());
			record.setCreateDate(doBELikes.getTime() == null ? DateUtil.getSysDate() : doBELikes.getTime());
			record.setLikesId(HarborSeqUtil.createBeLikesId());
			record.setUserId(be.getUserId());
			record.setDianzanUserId(doBELikes.getUserId());
			hyBeLikesMapper.insert(record);

			// 发送用户自动收藏的消息
			if (!HyBeUtil.checkUserBeFavorite(be.getBeId(), doBELikes.getUserId())) {
				DoBeFavorite body = new DoBeFavorite();
				body.setHandleType(DoBeFavorite.HandleType.DO.name());
				body.setBeId(be.getBeId());
				body.setUserId(doBELikes.getUserId());
				UserFavorMQSend.sendMQ(body);
			}
		} else if (DoBeLikes.HandleType.CANCEL.name().equals(doBELikes.getHandleType())) {
			// 如果是取消赞，则删除
			if (!StringUtil.isBlank(doBELikes.getUserId()) && !StringUtil.isBlank(doBELikes.getBeId())) {
				HyBeLikesCriteria sql = new HyBeLikesCriteria();
				sql.or().andUserIdEqualTo(doBELikes.getUserId()).andBeIdEqualTo(doBELikes.getBeId());
				hyBeLikesMapper.deleteByExample(sql);
			}
		}
	}

	@Override
	public void processDoBeComment(DoBeComment doBeComment) {
		Be be = this.queryBe(doBeComment.getBeId());
		if (be == null) {
			throw new BusinessException("BE[" + doBeComment.getBeId() + "]索引不存在");
		}
		if (DoBeComment.HandleType.PUBLISH.name().equals(doBeComment.getHandleType())) {
			// 如果是点赞，则记录
			HyBeComments record = new HyBeComments();
			record.setBeId(doBeComment.getBeId());
			record.setCreateDate(doBeComment.getSysdate() == null ? DateUtil.getSysDate() : doBeComment.getSysdate());
			record.setCommentId(doBeComment.getCommentId());
			record.setContent(doBeComment.getContent());
			record.setParentCommentId(doBeComment.getParentCommentId());
			record.setParentUserId(doBeComment.getParentUserId());
			record.setUserId(doBeComment.getUserId());
			record.setStatus(com.the.harbor.base.enumeration.hybecomments.Status.NORMAL.getValue());
			hyBeCommentsMapper.insert(record);

			// 给被评论方发个消息
			String accepterId = null;
			String content = null;
			// 发表评论的人
			String enName = userManagerSV.getUserViewInfoByUserId(doBeComment.getUserId()).getEnName();
			if (StringUtil.isBlank(doBeComment.getParentUserId())) {
				// 被评论发为BE的作者
				accepterId = be.getUserId();
				content = enName + "评论了您的动态,点击查看..";
			} else {
				accepterId = doBeComment.getParentUserId();
				content = enName + "在动态中回复了您的评论,点击查看..";
			}
			if (!doBeComment.getUserId().equals(accepterId)) {
				// 只有当评论的发表者和接受者不是一个人的时候，才会给接受者发送系统通知
				DoNotify notify = new DoNotify();
				notify.setHandleType(DoNotify.HandleType.PUBLISH.name());
				notify.setNotifyId(UUIDUtil.genId32());
				notify.setNotifyType(NotifyType.SYSTEM_NOTIFY.getValue());
				notify.setSenderType(SenderType.USER.getValue());
				notify.setSenderId(doBeComment.getUserId());
				notify.setAccepterType(AccepterType.USER.getValue());
				notify.setAccepterId(accepterId);
				notify.setTitle("Be有新评论啦~");
				notify.setContent(content);
				notify.setLink("../be/detail.html?beId=" + be.getBeId() + "&notifyId=" + notify.getNotifyId());
				NotifyMQSend.sendNotifyMQ(notify);
			}
			// 写入REDIS关系
			BeComment b = new BeComment();
			BeanUtils.copyProperties(record, b);
			HyBeUtil.recordBeCommentId(record.getBeId(), record.getCommentId());
			HyBeUtil.recordBeComment(record.getCommentId(), JSON.toJSONString(b));

		} else if (DoBeComment.HandleType.CANCEL.name().equals(doBeComment.getHandleType())) {
			// 如果是删除评论
			if (!StringUtil.isBlank(doBeComment.getCommentId())) {
				HyBeComments record = new HyBeComments();
				record.setCommentId(doBeComment.getCommentId());
				record.setStatus(com.the.harbor.base.enumeration.hybecomments.Status.DELETED.getValue());
				hyBeCommentsMapper.updateByPrimaryKeySelective(record);

				String becomment = HyBeUtil.getBeComment(doBeComment.getCommentId());
				if (!StringUtil.isBlank(becomment)) {
					BeComment b = JSON.parseObject(becomment, BeComment.class);
					b.setStatus(com.the.harbor.base.enumeration.hybecomments.Status.DELETED.getValue());
					HyBeUtil.recordBeComment(doBeComment.getCommentId(), JSON.toJSONString(b));
				}
			}
		}

	}

	@Override
	public long getBesCount(String userId) {
		HyBeLikesCriteria sql = new HyBeLikesCriteria();
		sql.or().andUserIdEqualTo(userId);
		return hyBeLikesMapper.countByExample(sql);
	}

	@Override
	public void giveHaibei(GiveHBReq giveHBReq) {
		HyUserAssets userAssets = userManagerSV.getUserAssets(giveHBReq.getFromUserId(), AssetsType.HAIBEI.getValue());
		if (userAssets == null) {
			throw new BusinessException(BusiErrorCode.HAIBEI_NOT_ENOUGH.getValue(), "您的海贝数量不足，请充值");
		}
		if (userAssets.getBalance() < giveHBReq.getCount()) {
			throw new BusinessException(BusiErrorCode.HAIBEI_NOT_ENOUGH.getValue(),
					"您的海贝数量只有" + userAssets.getBalance() + "个，请充值");
		}
		HyBe be = hyBeMapper.selectByPrimaryKey(giveHBReq.getBeId());
		if (be == null) {
			throw new BusinessException("打赏失败:主题已被删除");
		}
		String giveId = UUIDUtil.genId32();
		HyBeGiveHb record = new HyBeGiveHb();
		record.setGiveId(giveId);
		record.setBeId(giveHBReq.getBeId());
		record.setBusiType(BusiType.REWARD_HB_FOR_BE.getValue());
		record.setPayUserId(giveHBReq.getFromUserId());
		record.setTargetUserId(be.getUserId());
		record.setAmount(giveHBReq.getCount());
		record.setTradeDate(DateUtil.getSysDate());
		hyBeGiveHbMapper.insert(record);
		// 发送消息执行两端用户的海贝的赠送与扣减
		DoUserAssetsTrade t = new DoUserAssetsTrade();
		t.setAssetsType(AssetsType.HAIBEI.getValue());
		t.setBusiType(BusiType.REWARD_HB_FOR_BE.getValue());
		t.setFromUserId(giveHBReq.getFromUserId());
		t.setHandleType(DoUserAssetsTrade.HandleType.TRANSFER.name());
		t.setSourceNo(giveId);
		t.setSummary("用户[" + giveHBReq.getFromUserId() + "]给BE[" + giveHBReq.getBeId() + "]打赏用户[" + be.getUserId()
				+ "]海贝[" + giveHBReq.getCount() + "]");
		t.setToUserId(be.getUserId());
		t.setTradeBalance(giveHBReq.getCount());
		UserAssetsTradeMQSend.sendMQ(t);
		// 记录BE的打赏用户信息
		HyBeUtil.userRewardBe(giveHBReq.getBeId(), giveHBReq.getFromUserId());
		HyBeUtil.recordBeRewardHB(giveHBReq.getBeId(), giveHBReq.getCount());
		// 发送索引更新消息
		IndexRealtimeCountMQSend.sendBeRealtimeIndexUpdateMQ(
				new DoBeIndexRealtimeStat(giveHBReq.getBeId(), DoBeIndexRealtimeStat.StatType.REWARD.name()));
		// 发送用户自动收藏的消息
		if (!HyBeUtil.checkUserBeFavorite(be.getBeId(), giveHBReq.getFromUserId())) {
			DoBeFavorite body = new DoBeFavorite();
			body.setHandleType(DoBeFavorite.HandleType.DO.name());
			body.setBeId(be.getBeId());
			body.setUserId(giveHBReq.getFromUserId());
			UserFavorMQSend.sendMQ(body);
		}
	}

	private Be queryBe(String beId) {
		SearchResponse response = ElasticSearchFactory.getClient().prepareSearch(HarborIndex.HY_BE_DB.getValue())
				.setTypes(HarborIndexType.HY_BE.getValue()).setQuery(QueryBuilders.termQuery("_id", beId)).execute()
				.actionGet();
		if (response.getHits().totalHits() == 0) {
			return null;
		}
		Be be = JSON.parseObject(response.getHits().getHits()[0].getSourceAsString(), Be.class);
		return be;
	}

	@Override
	public void processDoBeFavoriteMQ(DoBeFavorite doBeFavorite) {
		if (DoBeFavorite.HandleType.DO.name().equals(doBeFavorite.getHandleType())) {
			// 如果是收藏，则记录
			HyBeFavorite record = new HyBeFavorite();
			record.setBeId(doBeFavorite.getBeId());
			record.setCreateDate(doBeFavorite.getTime() == null ? DateUtil.getSysDate() : doBeFavorite.getTime());
			record.setFavoriteId(UUIDUtil.genId32());
			record.setUserId(doBeFavorite.getUserId());
			hyBeFavoriteMapper.insert(record);

			// 记录用户收藏行为
			HyBeUtil.userFavorBe(doBeFavorite.getUserId(), doBeFavorite.getBeId());
		} else if (DoBeFavorite.HandleType.CANCEL.name().equals(doBeFavorite.getHandleType())) {
			// 如果是取消收藏，则删除
			if (!StringUtil.isBlank(doBeFavorite.getUserId()) && !StringUtil.isBlank(doBeFavorite.getBeId())) {
				HyBeFavoriteCriteria sql = new HyBeFavoriteCriteria();
				sql.or().andUserIdEqualTo(doBeFavorite.getUserId()).andBeIdEqualTo(doBeFavorite.getBeId());
				hyBeFavoriteMapper.deleteByExample(sql);

				// 记录用户取消收藏
				HyBeUtil.userCancelFavorBe(doBeFavorite.getUserId(), doBeFavorite.getBeId());
			}
		}
	}

	@Override
	public void processDoBeView(DoBeView doBeView) {
		HyBeView record = new HyBeView();
		record.setCreateDate(doBeView.getTime() == null ? DateUtil.getSysDate() : doBeView.getTime());
		record.setViewId(UUIDUtil.genId32());
		record.setUserId(doBeView.getUserId());
		record.setBeId(doBeView.getBeId());
		hyBeViewMapper.insert(record);
	}

	private void deleteBe(String beId) {
		HyBe record = new HyBe();
		record.setBeId(beId);
		record.setInvalidDate(DateUtil.getSysDate());
		record.setStatus(Status.INVALID.getValue());
		hyBeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public void processBeDelete(String beId) {
		// BE实现
		this.deleteBe(beId);
		// 取消BE搜藏
		this.processDeleteBeFavor(beId);
	}

	private void processDeleteBeFavor(String beId) {
		HyBeFavoriteCriteria sql = new HyBeFavoriteCriteria();
		sql.or().andBeIdEqualTo(beId);
		List<HyBeFavorite> list = hyBeFavoriteMapper.selectByExample(sql);
		if (CollectionUtil.isEmpty(list)) {
			return;
		}
		for (HyBeFavorite be : list) {
			// hyBeFavoriteMapper.deleteByPrimaryKey(be.getFavoriteId());
			HyBeUtil.userCancelFavorBe(be.getUserId(), beId);
		}
	}

	@Override
	public void topBe(String beId, String topFlag, Timestamp topDate) {
		HyBe record = new HyBe();
		record.setBeId(beId);
		record.setTopDate(topDate);
		record.setTopFlag(topFlag);
		hyBeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public void hideBe(String beId, String hideFlag) {
		HyBe record = new HyBe();
		record.setBeId(beId);
		record.setHideFlag(hideFlag);
		hyBeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public void rebuildAllBesOpenSearchIndex() {
		/* 1.获取所有BE数据 */
		HyBeCriteria sql = new HyBeCriteria();
		List<HyBe> bes = hyBeMapper.selectByExample(sql);
		if (CollectionUtil.isEmpty(bes)) {
			return;
		}
		List<BeIndexOperate> indexs = new ArrayList<BeIndexOperate>();
		/* 2.循环所有BE */
		for (HyBe hyBe : bes) {
			/* 2.1 获取所有BE的标签 */
			List<HyBeTags> beTags = this.getBeTags(hyBe.getBeId());
			List<String> beTagIds = new ArrayList<String>();
			List<String> beTagNames = new ArrayList<String>();
			List<String> polyTagIds = new ArrayList<String>();
			for (HyBeTags beTag : beTags) {
				beTagIds.add(beTag.getTagId());
				beTagNames.add(beTag.getTagName());
				polyTagIds.add(beTag.getPolyTagId());
			}

			/* 2.2 获取BE的明细 */
			List<HyBeDetail> beDetails = this.getBeDetails(hyBe.getBeId());
			/* 2.3 获取BE发布用户信息 */
			UserViewInfo userInfo = userManagerSV.getUserViewInfoByUserId(hyBe.getBeId());
			/* 2.3 组织数据 */
			if (CollectionUtil.isEmpty(beDetails)) {
				continue;
			}
			for (HyBeDetail hyBeDetail : beDetails) {
				if (BeDetailType.IMAGE.getValue().equals(hyBeDetail.getType())) {
					continue;
				}
				BeIndexModel m = new BeIndexModel();
				BeanUtils.copyProperties(hyBeDetail, m);
				BeanUtils.copyProperties(hyBe, m);
				m.setEnName(userInfo == null ? null : userInfo.getEnName());
				m.setBeTagIds(beTagIds.toArray(new String[beTagIds.size()]));
				m.setBeTagNames(beTagNames.toArray(new String[beTagNames.size()]));
				m.setPolyTagIds(polyTagIds.toArray(new String[polyTagIds.size()]));
				BeIndexOperate op = new BeIndexOperate();
				op.setCmd("add");
				op.setFields(m);
				indexs.add(op);
			}
		}
		CloudsearchClient client = OpenSearchFactory.getClient();
		CloudsearchDoc doc = new CloudsearchDoc("harbor_be", client);
		try {
			String result = doc.push(JSON.toJSONString(indexs), "hy_be");
			Log.debug(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 发布BE到搜索引擎
	 * 
	 * @param beId
	 */
	public void pushBeToOpenSearch(String beId) {
		/* 1.获取所有BE数据 */
		HyBe hyBe = hyBeMapper.selectByPrimaryKey(beId);
		if (hyBe == null) {
			return;
		}
		List<BeIndexOperate> indexs = new ArrayList<BeIndexOperate>();
		/* 2 获取所有BE的标签 */
		List<HyBeTags> beTags = this.getBeTags(hyBe.getBeId());
		List<String> beTagIds = new ArrayList<String>();
		List<String> beTagNames = new ArrayList<String>();
		List<String> polyTagIds = new ArrayList<String>();
		for (HyBeTags beTag : beTags) {
			beTagIds.add(beTag.getTagId());
			beTagNames.add(beTag.getTagName());
			polyTagIds.add(beTag.getPolyTagId());
		}

		/* 2.2 获取BE的明细 */
		List<HyBeDetail> beDetails = this.getBeDetails(hyBe.getBeId());
		/* 2.3 获取BE发布用户信息 */
		UserViewInfo userInfo = userManagerSV.getUserViewInfoByUserId(hyBe.getBeId());
		/* 2.3 组织数据 */
		if (CollectionUtil.isEmpty(beDetails)) {
			return;
		}
		for (HyBeDetail hyBeDetail : beDetails) {
			if (BeDetailType.IMAGE.getValue().equals(hyBeDetail.getType())) {
				continue;
			}
			BeIndexModel m = new BeIndexModel();
			BeanUtils.copyProperties(hyBeDetail, m);
			BeanUtils.copyProperties(hyBe, m);
			m.setEnName(userInfo == null ? null : userInfo.getEnName());
			m.setBeTagIds(beTagIds.toArray(new String[beTagIds.size()]));
			m.setBeTagNames(beTagNames.toArray(new String[beTagNames.size()]));
			m.setPolyTagIds(polyTagIds.toArray(new String[polyTagIds.size()]));
			BeIndexOperate op = new BeIndexOperate();
			op.setCmd("add");
			op.setFields(m);
			indexs.add(op);
		}

		CloudsearchClient client = OpenSearchFactory.getClient();
		CloudsearchDoc doc = new CloudsearchDoc("harbor_be", client);
		try {
			String result = doc.push(JSON.toJSONString(indexs), "hy_be");
			Log.debug(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void hideBeToOpenSearch(String beId, String hideFlag) {
		List<HyBeDetail> beDetails = this.getBeDetails(beId);
		if (CollectionUtil.isEmpty(beDetails)) {
			return;
		}
		List<BeIndexOperate> indexs = new ArrayList<BeIndexOperate>();
		Map<String, Object> fields = new HashMap<String, Object>();
		for (HyBeDetail hyBeDetail : beDetails) {
			if (BeDetailType.IMAGE.getValue().equals(hyBeDetail.getType())) {
				continue;
			}
			BeIndexModel m = new BeIndexModel();
			m.setId(hyBeDetail.getId());
			m.setHideFlag(hideFlag);
			m.setEnName("aaaaa");

			BeIndexOperate op = new BeIndexOperate();
			op.setCmd("update");
			op.setFields(m);
			indexs.add(op);
		}
		CloudsearchClient client = OpenSearchFactory.getClient();
		CloudsearchDoc doc = new CloudsearchDoc("harbor_be", client);
		try {
			doc.update(fields);
			String result = doc.push(JSON.toJSONString(indexs), "hy_be");
			Log.debug(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void topBeToOpenSearch(String beId, String topFlag) {
		List<HyBeDetail> beDetails = this.getBeDetails(beId);
		if (CollectionUtil.isEmpty(beDetails)) {
			return;
		}
		List<BeIndexOperate> indexs = new ArrayList<BeIndexOperate>();
		for (HyBeDetail hyBeDetail : beDetails) {
			if (BeDetailType.IMAGE.getValue().equals(hyBeDetail.getType())) {
				continue;
			}
			BeIndexModel m = new BeIndexModel();
			m.setId(hyBeDetail.getId());
			m.setTopFlag(topFlag);
			m.setTopDate(DateUtil.getSysDate());

			BeIndexOperate op = new BeIndexOperate();
			op.setCmd("update");
			op.setFields(m);
			indexs.add(op);
		}
		CloudsearchClient client = OpenSearchFactory.getClient();
		CloudsearchDoc doc = new CloudsearchDoc("harbor_be", client);
		try {
			String result = doc.push(JSON.toJSONString(indexs), "hy_be");
			Log.debug(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteBeToOpenSearch(String beId) {
		List<HyBeDetail> beDetails = this.getBeDetails(beId);
		if (CollectionUtil.isEmpty(beDetails)) {
			return;
		}
		List<BeIndexOperate> indexs = new ArrayList<BeIndexOperate>();
		for (HyBeDetail hyBeDetail : beDetails) {
			if (BeDetailType.IMAGE.getValue().equals(hyBeDetail.getType())) {
				continue;
			}
			BeIndexModel m = new BeIndexModel();
			m.setId(hyBeDetail.getId());
			m.setStatus(Status.INVALID.getValue());
			m.setInvalidDate(DateUtil.getSysDate());

			BeIndexOperate op = new BeIndexOperate();
			op.setCmd("update");
			op.setFields(m);
			indexs.add(op);
		}
		CloudsearchClient client = OpenSearchFactory.getClient();
		CloudsearchDoc doc = new CloudsearchDoc("harbor_be", client);
		try {
			String result = doc.push(JSON.toJSONString(indexs), "hy_be");
			Log.debug(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<HyBeDetail> getBeDetails(String beId) {
		HyBeDetailCriteria sql = new HyBeDetailCriteria();
		sql.or().andBeIdEqualTo(beId);
		List<HyBeDetail> list = hyBeDetailMapper.selectByExample(sql);
		return list;
	}

	/**
	 * 获取所有标签数组
	 * 
	 * @param beId
	 * @return
	 */
	private List<HyBeTags> getBeTags(String beId) {
		HyBeTagsCriteria sql = new HyBeTagsCriteria();
		sql.or().andStatusEqualTo(Status.VALID.getValue()).andBeIdEqualTo(beId);
		List<HyBeTags> beTags = hyBeTagsMapper.selectByExample(sql);
		return beTags;
	}

}
