package com.the.harbor.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.the.harbor.api.be.param.DoBeComment;
import com.the.harbor.api.be.param.DoBeLikes;
import com.the.harbor.api.go.param.DoGoFavorite;
import com.the.harbor.api.go.param.DoGoView;
import com.the.harbor.api.user.param.DoUserFans;
import com.the.harbor.api.user.param.DoUserFriend;
import com.the.harbor.base.enumeration.mns.MQType;
import com.the.harbor.base.vo.MNSBody;
import com.the.harbor.service.interfaces.IBeBusiSV;
import com.the.harbor.service.interfaces.IGoBusiSV;
import com.the.harbor.service.interfaces.IUserInterfactionSV;
import com.the.harbor.service.interfaces.IUserManagerSV;

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
		} else if (MQType.MQ_HY_GO_FAVORITE.getValue().equals(mqType)) {
			// GO收藏行为
			DoGoFavorite doGoFavorite = JSONObject.parseObject(mnsBody, DoGoFavorite.class);
			goBusiSV.processDoGoFavoriteMQ(doGoFavorite);
		} else if (MQType.MQ_HY_GO_VIEWS.getValue().equals(mqType)) {
			// GO浏览行为
			DoGoView doGoView = JSONObject.parseObject(mnsBody, DoGoView.class);
			goBusiSV.processDoGoView(doGoView);

		} else if (MQType.MQ_HY_BE_COMMENT.getValue().equals(mqType)) {
			// BE评论
			DoBeComment doBeComment = JSONObject.parseObject(mnsBody, DoBeComment.class);
			beBusiSV.processDoBeComment(doBeComment);
		} else if (MQType.MQ_HY_GO_COMMENT.getValue().equals(mqType)) {
			// BE评论
		} else if (MQType.MQ_HY_USER_FANS.getValue().equals(mqType)) {
			// 粉丝互动
			DoUserFans doUserFans = JSONObject.parseObject(mnsBody, DoUserFans.class);
			userManagerSV.processDoUserFans(doUserFans);
		} else if (MQType.MQ_HY_USER_FRIEND.getValue().equals(mqType)) {
			// 加好友
			DoUserFriend doUserFriend = JSONObject.parseObject(mnsBody, DoUserFriend.class);
			userManagerSV.processDoUserFriend(doUserFriend);
		}

	}

}
