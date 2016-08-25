package com.the.harbor.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.opensearch.CloudsearchClient;
import com.aliyun.opensearch.CloudsearchDoc;
import com.taobao.api.ApiException;
import com.taobao.api.domain.Userinfos;
import com.taobao.api.request.OpenimUsersAddRequest;
import com.taobao.api.request.OpenimUsersGetRequest;
import com.taobao.api.request.OpenimUsersUpdateRequest;
import com.taobao.api.response.OpenimUsersAddResponse;
import com.taobao.api.response.OpenimUsersGetResponse;
import com.taobao.api.response.OpenimUsersUpdateResponse;
import com.the.harbor.api.be.param.DoBeComment;
import com.the.harbor.api.be.param.DoBeFavorite;
import com.the.harbor.api.be.param.DoBeLikes;
import com.the.harbor.api.be.param.DoBeView;
import com.the.harbor.api.go.param.DoGoComment;
import com.the.harbor.api.go.param.DoGoFavorite;
import com.the.harbor.api.go.param.DoGoJoinConfirm;
import com.the.harbor.api.go.param.DoGoView;
import com.the.harbor.api.user.param.DoIMUserSync;
import com.the.harbor.api.user.param.DoUserFans;
import com.the.harbor.api.user.param.DoUserFriend;
import com.the.harbor.api.user.param.UserViewInfo;
import com.the.harbor.base.enumeration.mns.MQType;
import com.the.harbor.base.vo.MNSBody;
import com.the.harbor.commons.components.aliyuncs.im.IMFactory;
import com.the.harbor.commons.components.aliyuncs.opensearch.OpenSearchFactory;
import com.the.harbor.commons.components.aliyuncs.opensearch.OpenSearchSettings;
import com.the.harbor.commons.util.CollectionUtil;
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

		} else if (MQType.MQ_HY_BE_FAVORITE.getValue().equals(mqType)) {
			// BE收藏行为
			DoBeFavorite doBeFavorite = JSONObject.parseObject(mnsBody, DoBeFavorite.class);
			beBusiSV.processDoBeFavoriteMQ(doBeFavorite);

		} else if (MQType.MQ_HY_BE_VIEWS.getValue().equals(mqType)) {
			// BE浏览行为
			DoBeView doBeView = JSONObject.parseObject(mnsBody, DoBeView.class);
			beBusiSV.processDoBeView(doBeView);

		} else if (MQType.MQ_HY_BE_COMMENT.getValue().equals(mqType)) {
			// BE评论
			DoBeComment doBeComment = JSONObject.parseObject(mnsBody, DoBeComment.class);
			beBusiSV.processDoBeComment(doBeComment);
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
		}

	}

	@Override
	public void userSync2OpenIM(DoIMUserSync notify) {
		String handleType = notify.getHandleType();
		String userId = notify.getUserId();
		if (DoIMUserSync.HandleType.ADD.name().equals(handleType)) {
			this.syncUser2IM(userId);
		} else if (DoIMUserSync.HandleType.UPDATE.name().equals(handleType)) {
			this.syncUser2IM(userId);
		} else if (DoIMUserSync.HandleType.DELETE.name().equals(handleType)) {

		}
	}

	private boolean checkIMUserExists(String userId) {
		OpenimUsersGetRequest req = new OpenimUsersGetRequest();
		req.setUserids(userId);
		try {
			OpenimUsersGetResponse rsp = IMFactory.getTaobaoClient().execute(req);
			List<Userinfos> users = rsp.getUserinfos();
			return CollectionUtil.isEmpty(users) ? false : true;
		} catch (ApiException e) {
			LOG.error("查询IM账户失败", e);
		}
		return false;
	}

	private void syncUser2IM(String userId) {
		UserViewInfo userInfo = userManagerSV.getUserViewInfoFromDBByUserId(userId);
		if (userInfo == null) {
			return;
		}
		boolean exists = this.checkIMUserExists(userId);
		if (exists) {
			OpenimUsersUpdateRequest req = new OpenimUsersUpdateRequest();
			List<Userinfos> users = new ArrayList<Userinfos>();
			Userinfos user = new Userinfos();
			user.setNick(userInfo.getEnName());
			user.setIconUrl(userInfo.getWxHeadimg());
			user.setEmail(userInfo.getEmail());
			user.setMobile(userInfo.getMobilePhone());
			user.setUserid(userInfo.getUserId());
			user.setPassword(userInfo.getWxOpenid());
			user.setName(userInfo.getChName());
			user.setGender(userInfo.getSex());
			users.add(user);
			req.setUserinfos(users);
			try {
				OpenimUsersUpdateResponse rsp = IMFactory.getTaobaoClient().execute(req);
				LOG.debug("修改IM账户信息:" + JSON.toJSONString(rsp));
			} catch (ApiException e) {
				LOG.error("修改IM账户错误", e);
			}
		} else {
			OpenimUsersAddRequest req = new OpenimUsersAddRequest();
			List<Userinfos> users = new ArrayList<Userinfos>();
			Userinfos user = new Userinfos();
			user.setNick(userInfo.getEnName());
			user.setIconUrl(userInfo.getWxHeadimg());
			user.setEmail(userInfo.getEmail());
			user.setMobile(userInfo.getMobilePhone());
			user.setUserid(userInfo.getUserId());
			user.setPassword(userInfo.getWxOpenid());
			user.setName(userInfo.getChName());
			user.setGender(userInfo.getSex());
			users.add(user);
			req.setUserinfos(users);
			try {
				OpenimUsersAddResponse rsp = IMFactory.getTaobaoClient().execute(req);
				LOG.debug("新增IM账户信息:" + JSON.toJSONString(rsp));
			} catch (ApiException e) {
				LOG.error("新增IM账户错误", e);
			}
		}

	}

	@Override
	public void userSync2OpenSearch(DoIMUserSync notify) {
		String handleType = notify.getHandleType();
		String userId = notify.getUserId();
		if (DoIMUserSync.HandleType.ADD.name().equals(handleType)) {
			syncUser2Opensearch(userId, "add");
		} else if (DoIMUserSync.HandleType.UPDATE.name().equals(handleType)) {
			// 覆盖更新索引
			syncUser2Opensearch(userId, "add");
		} else if (DoIMUserSync.HandleType.DELETE.name().equals(handleType)) {

		}
	}

	private void syncUser2Opensearch(String userId, String cmd) {
		UserViewInfo userInfo = userManagerSV.getUserViewInfoByUserId(userId);
		JSONObject fileds = JSONObject.parseObject(JSON.toJSONString(userInfo));
		JSONObject data = new JSONObject();
		data.put("cmd", cmd);
		data.put("fields", fileds);
		JSONArray arr = new JSONArray();
		arr.add(data);
		CloudsearchClient client = OpenSearchFactory.getClient();
		CloudsearchDoc doc = new CloudsearchDoc(OpenSearchSettings.getAppName(), client);
		try {
			String result = doc.push(arr.toJSONString(), "hy_user");
			LOG.debug(result);
		} catch (Exception e) {
			LOG.error("同步索引失败", e);
		}

	}

}
