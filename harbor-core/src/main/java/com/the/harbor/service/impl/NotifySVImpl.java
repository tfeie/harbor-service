package com.the.harbor.service.impl;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.the.harbor.base.enumeration.hynotify.AccepterType;
import com.the.harbor.base.enumeration.hynotify.SenderType;
import com.the.harbor.base.enumeration.hynotify.Status;
import com.the.harbor.base.exception.BusinessException;
import com.the.harbor.commons.redisdata.def.DoNotify;
import com.the.harbor.commons.redisdata.def.HyNotifyVo;
import com.the.harbor.commons.redisdata.util.HyNotifyUtil;
import com.the.harbor.commons.util.DateUtil;
import com.the.harbor.commons.util.StringUtil;
import com.the.harbor.commons.util.UUIDUtil;
import com.the.harbor.dao.mapper.bo.HyNotify;
import com.the.harbor.dao.mapper.interfaces.HyNotifyMapper;
import com.the.harbor.service.interfaces.INotifySV;

@Component
@Transactional
public class NotifySVImpl implements INotifySV {

	private static final Logger LOG = LoggerFactory.getLogger(NotifySVImpl.class);

	@Autowired
	private transient HyNotifyMapper hyNotifyMapper;

	@Override
	public void process(DoNotify notify) {
		Timestamp sysdate = DateUtil.getSysDate();
		if (DoNotify.HandleType.PUBLISH.name().equals(notify.getHandleType())) {
			// 如果是发布一条通知
			if (StringUtil.isBlank(notify.getNotifyType())) {
				throw new BusinessException("通知类型为空");
			}
			if (StringUtil.isBlank(notify.getSenderType())) {
				throw new BusinessException("发送者类型为空");
			}
			if (SenderType.USER.getValue().equals(notify.getSenderType())) {
				if (StringUtil.isBlank(notify.getSenderId())) {
					throw new BusinessException("发送者用户为空");
				}
			}
			if (StringUtil.isBlank(notify.getAccepterType())) {
				throw new BusinessException("接受者类型为空");
			}
			if (AccepterType.USER.getValue().equals(notify.getAccepterType())) {
				if (StringUtil.isBlank(notify.getAccepterId())) {
					throw new BusinessException("接受者用户为空");
				}
			}
			if (StringUtil.isBlank(notify.getTitle())) {
				throw new BusinessException("消息主题为空");
			}
			if (StringUtil.isBlank(notify.getContent())) {
				throw new BusinessException("消息内容为空");
			}
			String notifyId = UUIDUtil.genId32();
			HyNotify record = new HyNotify();
			BeanUtils.copyProperties(notify, record);
			record.setNotifyId(notifyId);
			record.setCreateDate(sysdate);
			record.setStatus(Status.UNREAD.getValue());
			hyNotifyMapper.insert(record);
			// 记录消息内容
			HyNotifyUtil.recordNotify(notifyId, this.convert(record));
			// 根据消息接受者类型来通知各个用户
			if (AccepterType.USER.getValue().equals(notify.getAccepterType())) {
				// 通知用户接收消息
				HyNotifyUtil.recordSingleUserNotify(notify.getAccepterId(), notifyId);
			}
		} else if (DoNotify.HandleType.READ.name().equals(notify.getHandleType())
				|| DoNotify.HandleType.CANCEL.name().equals(notify.getHandleType())) {
			// 读消息或者删除消息是用户端发起的，接受者类型必须是用户，
			if (StringUtil.isBlank(notify.getNotifyId())) {
				throw new BusinessException("消息ID为空");
			}
			if (StringUtil.isBlank(notify.getAccepterType())) {
				throw new BusinessException("接受者类型为空");
			}
			if (!AccepterType.USER.getValue().equals(notify.getAccepterType())) {
				throw new BusinessException("接受者类型不是用户级");
			}
			if (StringUtil.isBlank(notify.getAccepterId())) {
				throw new BusinessException("接受者用户为空");
			}
			// 获取消息
			HyNotifyVo notifyVo = HyNotifyUtil.getNotify(notify.getNotifyId());
			if (notifyVo != null && AccepterType.USER.getValue().equals(notifyVo.getAccepterType())) {
				// 消息本身的接受者 如果是单个用户，则这条消息的状态要更新为已读

			}
			HyNotifyUtil.deleteSingleUserNotify(notify.getAccepterId(), notify.getNotifyId());
		}

	}

	private HyNotifyVo convert(HyNotify record) {
		HyNotifyVo notify = new HyNotifyVo();
		BeanUtils.copyProperties(record, notify);
		return notify;
	}

}
