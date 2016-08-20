package com.the.harbor.api.user.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.opensearch.CloudsearchClient;
import com.aliyun.opensearch.CloudsearchSearch;
import com.the.harbor.api.user.IUserSV;
import com.the.harbor.api.user.param.CreateUserBuyHBOrderReq;
import com.the.harbor.api.user.param.CreateUserBuyHBOrderResp;
import com.the.harbor.api.user.param.CreateUserBuyMemberOrderReq;
import com.the.harbor.api.user.param.CreateUserBuyMemberOrderResp;
import com.the.harbor.api.user.param.DoIMUserSync;
import com.the.harbor.api.user.param.UserAuthReq;
import com.the.harbor.api.user.param.UserCertificationReq;
import com.the.harbor.api.user.param.UserEditReq;
import com.the.harbor.api.user.param.UserInfo;
import com.the.harbor.api.user.param.UserInviteInfo;
import com.the.harbor.api.user.param.UserInviteReq;
import com.the.harbor.api.user.param.UserMemberInfo;
import com.the.harbor.api.user.param.UserMemberQuery;
import com.the.harbor.api.user.param.UserQueryResp;
import com.the.harbor.api.user.param.UserRegReq;
import com.the.harbor.api.user.param.UserSystemTagQueryReq;
import com.the.harbor.api.user.param.UserSystemTagQueryResp;
import com.the.harbor.api.user.param.UserSystemTagSubmitReq;
import com.the.harbor.api.user.param.UserTag;
import com.the.harbor.api.user.param.UserTagQueryReq;
import com.the.harbor.api.user.param.UserTagQueryResp;
import com.the.harbor.api.user.param.UserTuijianQueryReq;
import com.the.harbor.api.user.param.UserTuijianQueryResp;
import com.the.harbor.api.user.param.UserViewInfo;
import com.the.harbor.api.user.param.UserViewResp;
import com.the.harbor.api.user.param.UserWealthQueryReq;
import com.the.harbor.api.user.param.UserWealthQueryResp;
import com.the.harbor.base.constants.ExceptCodeConstants;
import com.the.harbor.base.exception.BusinessException;
import com.the.harbor.base.exception.SystemException;
import com.the.harbor.base.util.ResponseBuilder;
import com.the.harbor.base.vo.Response;
import com.the.harbor.base.vo.ResponseHeader;
import com.the.harbor.commons.components.aliyuncs.opensearch.OpenSearchFactory;
import com.the.harbor.commons.components.aliyuncs.opensearch.OpenSearchSettings;
import com.the.harbor.commons.components.globalconfig.GlobalSettings;
import com.the.harbor.commons.redisdata.util.HyUserUtil;
import com.the.harbor.commons.util.CollectionUtil;
import com.the.harbor.commons.util.StringUtil;
import com.the.harbor.service.interfaces.IUserManagerSV;
import com.the.harbor.util.IMUserSyncMQSend;

@Service(validation = "true")
public class UserSVImpl implements IUserSV {

	@Autowired
	private transient IUserManagerSV userManagerSV;

	@Override
	public Response userRegister(UserRegReq userRegReq) throws BusinessException, SystemException {
		String userId = userManagerSV.userRegister(userRegReq);
		this.storeUserInfo2Redis(userId);

		// 发送IM账号同步通知
		DoIMUserSync body = new DoIMUserSync();
		body.setHandleType(DoIMUserSync.HandleType.ADD.name());
		body.setUserId(userId);
		IMUserSyncMQSend.sendMQ(body);
		return ResponseBuilder.buildSuccessResponse("用户注册成功");
	}

	@Override
	public Response submitUserCertification(UserCertificationReq userCertificationReq)
			throws BusinessException, SystemException {
		userManagerSV.submitUserCertification(userCertificationReq);
		this.storeUserInfo2Redis(userCertificationReq.getUserId());
		return ResponseBuilder.buildSuccessResponse("认证材料提交成功");
	}

	@Override
	public Response submitUserAuthInfo(UserAuthReq userStatusReq) {
		String userId = userManagerSV.submitUserAuthInfo(userStatusReq);
		this.storeUserInfo2Redis(userId);
		return ResponseBuilder.buildSuccessResponse("用户认证成功");
	}

	@Override
	public Response submitUserSelectedSystemTags(UserSystemTagSubmitReq userSystemTagReq)
			throws BusinessException, SystemException {
		if (userSystemTagReq == null) {
			throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "参数为空");
		}
		if (CollectionUtil.isEmpty(userSystemTagReq.getInterestSelectedTags())) {
			throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "您没有选择任何兴趣标签");
		}
		if (CollectionUtil.isEmpty(userSystemTagReq.getSkillSelectedTags())) {
			throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "您没有选择技能标签");
		}
		int interestTagCount = userSystemTagReq.getInterestSelectedTags().size();
		int skillTagCount = userSystemTagReq.getSkillSelectedTags().size();
		if (interestTagCount == 0) {
			throw new BusinessException("USER-0003", "您至少需要选择1个兴趣标签");
		}
		if (interestTagCount > 5) {
			throw new BusinessException("USER-0003", "您最多只能选择5个兴趣标签");
		}
		if (skillTagCount == 0) {
			throw new BusinessException("USER-0003", "您至少需要选择1个技能标签");
		}
		if (skillTagCount > 5) {
			throw new BusinessException("USER-0003", "您最多只能选择5个技能标签");
		}
		List<UserTag> systemTags = new ArrayList<UserTag>();
		systemTags.addAll(userSystemTagReq.getInterestSelectedTags());
		systemTags.addAll(userSystemTagReq.getSkillSelectedTags());

		userManagerSV.submitUserSelectedSystemTags(userSystemTagReq.getUserId(), systemTags);
		return ResponseBuilder.buildSuccessResponse("用户系统级兴趣技能标签提交成功");
	}

	@Override
	public UserSystemTagQueryResp queryUserSystemTags(UserSystemTagQueryReq userSystemTagQueryReq)
			throws BusinessException, SystemException {
		if (userSystemTagQueryReq == null) {
			throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "参数为空");
		}
		UserSystemTagQueryResp resp = userManagerSV.queryUserSystemTags(userSystemTagQueryReq);
		ResponseHeader responseHeader = ResponseBuilder.buildSuccessResponseHeader("查询成功");
		resp.setResponseHeader(responseHeader);
		return resp;
	}

	@Override
	public UserMemberInfo queryUserMemberInfo(UserMemberQuery query) throws BusinessException, SystemException {
		if (query == null) {
			throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "参数为空");
		}
		UserMemberInfo member = userManagerSV.queryUserMemberInfo(query.getUserId());
		ResponseHeader responseHeader = ResponseBuilder.buildSuccessResponseHeader("查询成功");
		member.setResponseHeader(responseHeader);
		return member;
	}

	@Override
	public UserQueryResp queryUserInfoByOpenId(String openId) throws BusinessException, SystemException {
		if (StringUtil.isBlank(openId)) {
			throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "用户微信openId为空");
		}
		UserInfo userInfo = null;
		UserViewInfo hyUser = userManagerSV.getUserViewInfoByOpenId(openId);
		if (hyUser != null) {
			userInfo = new UserInfo();
			BeanUtils.copyProperties(hyUser, userInfo);
			userInfo.setHomePageBg(StringUtil.isBlank(hyUser.getHomePageBg())
					? GlobalSettings.getHarborUserDefaultHomePageBGURL() : hyUser.getHomePageBg());
			userInfo.setWxHeadimg(StringUtil.isBlank(hyUser.getWxHeadimg())
					? GlobalSettings.getHarborUserDefaultHeadICONURL() : hyUser.getWxHeadimg());
		}
		UserQueryResp resp = new UserQueryResp();
		resp.setUserInfo(userInfo);
		resp.setResponseHeader(ResponseBuilder.buildSuccessResponseHeader("查询成功"));
		return resp;
	}

	@Override
	public UserQueryResp queryUserInfoByUserId(String userId) throws BusinessException, SystemException {
		if (StringUtil.isBlank(userId)) {
			throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "用户标识为空");
		}
		UserInfo userInfo = null;
		UserViewInfo hyUser = userManagerSV.getUserViewInfoByUserId(userId);
		if (hyUser != null) {
			userInfo = new UserInfo();
			BeanUtils.copyProperties(hyUser, userInfo);
			userInfo.setHomePageBg(StringUtil.isBlank(hyUser.getHomePageBg())
					? GlobalSettings.getHarborUserDefaultHomePageBGURL() : hyUser.getHomePageBg());
			userInfo.setWxHeadimg(StringUtil.isBlank(hyUser.getWxHeadimg())
					? GlobalSettings.getHarborUserDefaultHeadICONURL() : hyUser.getWxHeadimg());
		}
		UserQueryResp resp = new UserQueryResp();
		resp.setUserInfo(userInfo);
		resp.setResponseHeader(ResponseBuilder.buildSuccessResponseHeader("查询成功"));
		return resp;
	}

	@Override
	public Response userEdit(UserEditReq userEditReq) throws BusinessException, SystemException {
		if (userEditReq == null) {
			throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "参数为空");
		}
		if (CollectionUtil.isEmpty(userEditReq.getInterestSelectedTags())) {
			throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "您没有选择任何兴趣标签");
		}
		if (CollectionUtil.isEmpty(userEditReq.getSkillSelectedTags())) {
			throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "您没有选择技能标签");
		}
		int interestTagCount = userEditReq.getInterestSelectedTags().size();
		int skillTagCount = userEditReq.getSkillSelectedTags().size();
		if (interestTagCount == 0) {
			throw new BusinessException("USER-0003", "您至少需要选择1个兴趣标签");
		}
		if (interestTagCount > 5) {
			throw new BusinessException("USER-0003", "您最多只能选择5个兴趣标签");
		}
		if (skillTagCount == 0) {
			throw new BusinessException("USER-0003", "您至少需要选择1个技能标签");
		}
		if (skillTagCount > 5) {
			throw new BusinessException("USER-0003", "您最多只能选择5个技能标签");
		}
		userManagerSV.userEdit(userEditReq);

		// 存储数据
		this.storeUserInfo2Redis(userEditReq.getUserId());

		// 发送IM账号同步通知
		DoIMUserSync body = new DoIMUserSync();
		body.setHandleType(DoIMUserSync.HandleType.UPDATE.name());
		body.setUserId(userEditReq.getUserId());
		IMUserSyncMQSend.sendMQ(body);
		return ResponseBuilder.buildSuccessResponse("用户资料编辑成功");
	}

	@Override
	public UserTagQueryResp queryUserTags(UserTagQueryReq userTagQueryReq) throws BusinessException, SystemException {
		if (userTagQueryReq == null) {
			throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "参数为空");
		}
		UserTagQueryResp resp = userManagerSV.queryUserTags(userTagQueryReq);
		ResponseHeader responseHeader = ResponseBuilder.buildSuccessResponseHeader("查询成功");
		resp.setResponseHeader(responseHeader);
		return resp;
	}

	@Override
	public UserViewResp queryUserViewByUserId(String userId) throws BusinessException, SystemException {
		if (StringUtil.isBlank(userId)) {
			throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "用户为空");
		}
		UserViewInfo userInfo = userManagerSV.getUserViewInfoByUserId(userId);
		UserViewResp resp = new UserViewResp();
		resp.setUserInfo(userInfo);
		resp.setResponseHeader(ResponseBuilder.buildSuccessResponseHeader("查询成功"));
		return resp;
	}

	@Override
	public UserViewResp queryUserViewByOpenId(String openId) throws BusinessException, SystemException {
		if (StringUtil.isBlank(openId)) {
			throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "微信认证标识为空");
		}
		UserViewInfo userInfo = userManagerSV.getUserViewInfoByOpenId(openId);
		UserViewResp resp = new UserViewResp();
		resp.setUserInfo(userInfo);
		resp.setResponseHeader(ResponseBuilder.buildSuccessResponseHeader("查询成功"));
		return resp;
	}

	@Override
	public List<UserViewInfo> queryUnAuthUsers() {
		return userManagerSV.getUnAuthUsers();
	}

	private void storeUserInfo2Redis(String userId) {
		try {
			UserViewInfo userInfo = userManagerSV.getUserViewInfoFromDBByUserId(userId);
			if (userInfo != null) {
				HyUserUtil.storeUserInfo2Redis(userId, JSON.toJSONString(userInfo));
				HyUserUtil.buildOpenIdAndUserIdMapped(userInfo.getWxOpenid(), userId);
			}
		} catch (Exception ex) {

		}
	}

	@Override
	public UserWealthQueryResp queryUserWealth(UserWealthQueryReq req) throws BusinessException, SystemException {
		UserWealthQueryResp resp = userManagerSV.queryUserWealth(req.getUserId());
		resp.setResponseHeader(ResponseBuilder.buildSuccessResponseHeader("查询成功"));
		return resp;
	}

	/**
	 * 查询邀请码信息
	 * 
	 * @param userInviteReq
	 * @return
	 */
	@Override
	public List<UserInviteInfo> queryUserInvite(UserInviteReq userInviteReq) {
		return userManagerSV.getUserInvite(userInviteReq);
	}

	/**
	 * 更新邀请码表中的使用者id,状态
	 * 
	 * @param userInviteReq
	 * @return
	 */
	@Override
	public Response updateUserInvite(UserInviteReq userInviteReq) throws BusinessException, SystemException {
		userManagerSV.updateUserInvite(userInviteReq);
		return ResponseBuilder.buildSuccessResponse("用户邀请码使用情况更新成功");
	}

	@Override
	public CreateUserBuyHBOrderResp createUserBuyHB(CreateUserBuyHBOrderReq createUserBuyHBOrderReq)
			throws BusinessException, SystemException {
		String payOrderId = userManagerSV.createUserBuyHB(createUserBuyHBOrderReq);
		CreateUserBuyHBOrderResp resp = new CreateUserBuyHBOrderResp();
		resp.setPayOrderId(payOrderId);
		resp.setResponseHeader(ResponseBuilder.buildSuccessResponseHeader("处理成功"));
		return resp;
	}

	@Override
	public CreateUserBuyMemberOrderResp createUserBuyMember(CreateUserBuyMemberOrderReq createUserBuyMemberOrderReq)
			throws BusinessException, SystemException {
		String payOrderId = userManagerSV.createUserBuyMember(createUserBuyMemberOrderReq);
		CreateUserBuyMemberOrderResp resp = new CreateUserBuyMemberOrderResp();
		resp.setPayOrderId(payOrderId);
		resp.setResponseHeader(ResponseBuilder.buildSuccessResponseHeader("处理成功"));
		return resp;
	}

	@Override
	public UserTuijianQueryResp queryTuijianUsers(UserTuijianQueryReq userTuijianQueryReq)
			throws BusinessException, SystemException {
		String userId = userTuijianQueryReq.getUserId();
		UserViewInfo userViewInfo = userManagerSV.getUserViewInfoByUserId(userId);
		// 智能匹配当前用户信息
		CloudsearchClient client = OpenSearchFactory.getClient();
		CloudsearchSearch search = new CloudsearchSearch(client);
		// 添加指定搜索的应用：
		search.addIndex(OpenSearchSettings.getAppName());
		search.setQueryString("industryname:'" + userViewInfo.getIndustryName() + "' RANK atcity:'"
				+ userViewInfo.getAtCity() + "' ");
		search.setFormat("json");
		// 返回搜索结果
		String result;
		try {
			result = search.search();
		} catch (Exception e) {
			throw new SystemException(e);
		}
		List<UserViewInfo> users = new ArrayList<UserViewInfo>();
		JSONObject d = JSONObject.parseObject(result);
		String status = d.getString("status");
		if ("OK".equals(status)) {
			JSONObject rs = d.getJSONObject("result");
			JSONArray arr = rs.getJSONArray("items");
			int count=0;
			for (int i = 0; i < arr.size(); i++) {
				if(count>3){
					break;
				}
				JSONObject json = arr.getJSONObject(i);
				String uid = json.getString("userid");
				// 判断是否已经是好友
				boolean frd = HyUserUtil.getUserFriends(userId).contains(uid);
				if (!frd && !uid.equals(userId)) {
					UserViewInfo ud = userManagerSV.getUserViewInfoByUserId(uid);
					users.add(ud);
					count++;
				}
			}
		}
		UserTuijianQueryResp resp = new UserTuijianQueryResp();
		resp.setResponseHeader(ResponseBuilder.buildSuccessResponseHeader("处理成功"));
		resp.setUserInfos(users);
		return resp;
	}
}
