package com.the.harbor.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.the.harbor.api.be.param.DoBeComment;
import com.the.harbor.api.be.param.DoBeIndexRealtimeStat;
import com.the.harbor.api.be.param.DoBeLikes;
import com.the.harbor.api.go.param.DoGoComment;
import com.the.harbor.api.go.param.DoGoFavorite;
import com.the.harbor.api.go.param.DoGoIndexRealtimeStat;
import com.the.harbor.api.go.param.DoGoJoinConfirm;
import com.the.harbor.api.go.param.DoGoView;
import com.the.harbor.api.user.param.DoUserFans;
import com.the.harbor.api.user.param.DoUserFriend;
import com.the.harbor.base.enumeration.mns.MQType;
import com.the.harbor.base.vo.MNSBody;
import com.the.harbor.service.interfaces.IBeBusiSV;
import com.the.harbor.service.interfaces.IGoBusiSV;
import com.the.harbor.service.interfaces.IUserInterfactionSV;
import com.the.harbor.service.interfaces.IUserManagerSV;
import com.the.harbor.util.IndexRealtimeCountMQSend;

@Component
@Transactional
public class UserInterfactionSVImpl implements IUserInterfactionSV {

	private static final Logger LOG = LoggerFactory.getLogger(UserInterfactionSVImpl.class);

	@Autowired
	private transient IBeBusiSV beBusiSV;

	@Autowired
	private transient IGoBusiSV goBusiSV;

	@Autowired
	private transient IUserManagerSV userManagerSV;

	@Override
	public void process(String mnsBody) {
		LOG.debug("开始处理用户交互的消息");
		MNSBody body = JSONObject.parseObject(mnsBody, MNSBody.class);
		String mqType = body.getMqType();
		if (MQType.MQ_HY_BE_LIKES.getValue().equals(mqType)) {
			// BE点赞行为
			DoBeLikes doBELikes = JSONObject.parseObject(mnsBody, DoBeLikes.class);
			beBusiSV.processDoBeLikesMQ(doBELikes);
			// 发送索引更新消息
			IndexRealtimeCountMQSend.sendBeRealtimeIndexUpdateMQ(
					new DoBeIndexRealtimeStat(doBELikes.getBeId(), DoBeIndexRealtimeStat.StatType.DIANZAN.name()));
		} else if (MQType.MQ_HY_GO_FAVORITE.getValue().equals(mqType)) {
			// GO收藏行为
			DoGoFavorite doGoFavorite = JSONObject.parseObject(mnsBody, DoGoFavorite.class);
			goBusiSV.processDoGoFavoriteMQ(doGoFavorite);

			// 发送索引更新消息
			IndexRealtimeCountMQSend.sendGoRealtimeIndexUpdateMQ(
					new DoGoIndexRealtimeStat(doGoFavorite.getGoId(), DoGoIndexRealtimeStat.StatType.FAVOR.name()));
		} else if (MQType.MQ_HY_GO_VIEWS.getValue().equals(mqType)) {
			// GO浏览行为
			DoGoView doGoView = JSONObject.parseObject(mnsBody, DoGoView.class);
			goBusiSV.processDoGoView(doGoView);

			// 发送索引更新消息
			IndexRealtimeCountMQSend.sendGoRealtimeIndexUpdateMQ(
					new DoGoIndexRealtimeStat(doGoView.getGoId(), DoGoIndexRealtimeStat.StatType.VIEW.name()));

		} else if (MQType.MQ_HY_BE_COMMENT.getValue().equals(mqType)) {
			// BE评论
			DoBeComment doBeComment = JSONObject.parseObject(mnsBody, DoBeComment.class);
			beBusiSV.processDoBeComment(doBeComment);

			// 发送索引更新消息
			IndexRealtimeCountMQSend.sendBeRealtimeIndexUpdateMQ(
					new DoBeIndexRealtimeStat(doBeComment.getBeId(), DoBeIndexRealtimeStat.StatType.COMMENT.name()));
		} else if (MQType.MQ_HY_GO_COMMENT.getValue().equals(mqType)) {
			// GO评论
			DoGoComment doGoComment = JSONObject.parseObject(mnsBody, DoGoComment.class);
			goBusiSV.processDoGoComment(doGoComment);
		} else if (MQType.MQ_HY_USER_FANS.getValue().equals(mqType)) {
			// 粉丝互动
			DoUserFans doUserFans = JSONObject.parseObject(mnsBody, DoUserFans.class);
			userManagerSV.processDoUserFans(doUserFans);
		} else if (MQType.MQ_HY_USER_FRIEND.getValue().equals(mqType)) {
			// 加好友
			DoUserFriend doUserFriend = JSONObject.parseObject(mnsBody, DoUserFriend.class);
			userManagerSV.processDoUserFriend(doUserFriend);
		} else if (MQType.MQ_HY_GO_JOIN_CONFIRM.getValue().equals(mqType)) {
			// GROUP活动报名审核信息
			DoGoJoinConfirm doGoJoinConfirm = JSONObject.parseObject(mnsBody, DoGoJoinConfirm.class);
			goBusiSV.processDoGoJoinConfirm(doGoJoinConfirm);

			// 发送索引更新消息
			IndexRealtimeCountMQSend.sendGoRealtimeIndexUpdateMQ(new DoGoIndexRealtimeStat(doGoJoinConfirm.getGoId(),
					DoGoIndexRealtimeStat.StatType.GROUPJOIN.name()));
		}

	}

}
