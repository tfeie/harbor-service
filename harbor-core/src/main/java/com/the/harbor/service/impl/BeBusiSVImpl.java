package com.the.harbor.service.impl;

import java.sql.Timestamp;

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
import com.the.harbor.api.be.param.Be;
import com.the.harbor.api.be.param.BeComment;
import com.the.harbor.api.be.param.BeCreateReq;
import com.the.harbor.api.be.param.BeDetail;
import com.the.harbor.api.be.param.BeTag;
import com.the.harbor.api.be.param.DoBeComment;
import com.the.harbor.api.be.param.DoBeLikes;
import com.the.harbor.base.enumeration.common.Status;
import com.the.harbor.base.enumeration.hytags.TagType;
import com.the.harbor.commons.components.aliyuncs.mns.MNSFactory;
import com.the.harbor.commons.components.globalconfig.GlobalSettings;
import com.the.harbor.commons.redisdata.util.HyBeUtil;
import com.the.harbor.commons.util.CollectionUtil;
import com.the.harbor.commons.util.DateUtil;
import com.the.harbor.commons.util.StringUtil;
import com.the.harbor.dao.mapper.bo.HyBe;
import com.the.harbor.dao.mapper.bo.HyBeComments;
import com.the.harbor.dao.mapper.bo.HyBeDetail;
import com.the.harbor.dao.mapper.bo.HyBeLikes;
import com.the.harbor.dao.mapper.bo.HyBeLikesCriteria;
import com.the.harbor.dao.mapper.bo.HyBeTags;
import com.the.harbor.dao.mapper.interfaces.HyBeCommentsMapper;
import com.the.harbor.dao.mapper.interfaces.HyBeDetailMapper;
import com.the.harbor.dao.mapper.interfaces.HyBeLikesMapper;
import com.the.harbor.dao.mapper.interfaces.HyBeMapper;
import com.the.harbor.dao.mapper.interfaces.HyBeTagsMapper;
import com.the.harbor.service.interfaces.IBeBusiSV;
import com.the.harbor.util.HarborSeqUtil;

@Component
@Transactional
public class BeBusiSVImpl implements IBeBusiSV {

	private static final Logger LOG = LoggerFactory.getLogger(BeBusiSVImpl.class);

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
		this.buildBeIndexBuildMQ(be);
		return beId;
	}

	/**
	 * 产生一个BE索引构建消息
	 * 
	 * @param be
	 */
	private void buildBeIndexBuildMQ(Be be) {
		MNSClient client = MNSFactory.getMNSClient();
		try {
			CloudQueue queue = client.getQueueRef(GlobalSettings.getBeIndexBuildQueueName());
			Message message = new Message();
			message.setMessageBody(JSON.toJSONString(be));
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
			LOG.error("BE index build  message put in Queue error", se);
		} catch (Exception e) {
			LOG.error("Unknown exception happened!", e);
		}
		client.close();

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
			hyBeCommentsMapper.insert(record);

			// 写入REDIS关系
			BeComment b = new BeComment();
			BeanUtils.copyProperties(record, b);
			HyBeUtil.recordBeCommentId(record.getBeId(), record.getCommentId());
			HyBeUtil.recordBeComment(record.getCommentId(), JSON.toJSONString(b));
		} else if (DoBeComment.HandleType.CANCEL.name().equals(doBeComment.getHandleType())) {
			// 如果是取消赞，则删除
			if (!StringUtil.isBlank(doBeComment.getCommentId())) {
				HyBeComments record = hyBeCommentsMapper.selectByPrimaryKey(doBeComment.getCommentId());
				if (record != null) {
					hyBeCommentsMapper.deleteByPrimaryKey(doBeComment.getCommentId());
					// 从REDIS中删除
					HyBeUtil.deleteBeCommentId(record.getBeId(), record.getCommentId());
					HyBeUtil.deleteBeComment(record.getCommentId());
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

}
