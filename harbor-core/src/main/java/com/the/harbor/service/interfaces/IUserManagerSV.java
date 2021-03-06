package com.the.harbor.service.interfaces;

import java.util.List;

import com.the.harbor.api.user.param.CreateUserBuyHBOrderReq;
import com.the.harbor.api.user.param.CreateUserBuyMemberOrderReq;
import com.the.harbor.api.user.param.DoUserAssetsTrade;
import com.the.harbor.api.user.param.DoUserFans;
import com.the.harbor.api.user.param.DoUserFriend;
import com.the.harbor.api.user.param.UserAuthReq;
import com.the.harbor.api.user.param.UserCertificationReq;
import com.the.harbor.api.user.param.UserEditReq;
import com.the.harbor.api.user.param.UserInviteInfo;
import com.the.harbor.api.user.param.UserInviteReq;
import com.the.harbor.api.user.param.UserMemberInfo;
import com.the.harbor.api.user.param.UserRegReq;
import com.the.harbor.api.user.param.UserSystemTagQueryReq;
import com.the.harbor.api.user.param.UserSystemTagQueryResp;
import com.the.harbor.api.user.param.UserTag;
import com.the.harbor.api.user.param.UserTagQueryReq;
import com.the.harbor.api.user.param.UserTagQueryResp;
import com.the.harbor.api.user.param.UserViewInfo;
import com.the.harbor.api.user.param.UserWealthQueryResp;
import com.the.harbor.dao.mapper.bo.HyUserAssets;
import com.the.harbor.dao.mapper.bo.HyUserBuyHb;
import com.the.harbor.dao.mapper.bo.HyUserBuyMember;

public interface IUserManagerSV {

	/**
	 * 用户注册
	 * 
	 * @param userRegReq
	 * @return
	 * @author zhangchao
	 */
	String userRegister(UserRegReq userRegReq);

	/**
	 * 提交认证材料
	 * 
	 * @param userCertificationReq
	 */
	void submitUserCertification(UserCertificationReq userCertificationReq);

	/**
	 * 提交用户系统级别技能标签选择
	 * 
	 * @param userSystemTagReq
	 */
	void submitUserSelectedSystemTags(String userId, List<UserTag> systemTags);

	/**
	 * 获取用户系统级别的标签
	 * 
	 * @param userSystemTagQueryReq
	 * @return
	 */
	UserSystemTagQueryResp queryUserSystemTags(UserSystemTagQueryReq userSystemTagQueryReq);

	/**
	 * 查询会员信息
	 * 
	 * @param userId
	 * @return
	 */
	UserMemberInfo queryUserMemberInfo(String userId);

	UserViewInfo getUserViewInfoByUserId(String userId);

	UserViewInfo getUserViewInfoByOpenId(String openId);

	/**
	 * 会员缴费续期
	 * 
	 * @param buyMember
	 * @return
	 */
	void userMemberRenewal(HyUserBuyMember buyMember);

	/**
	 * 用户资料编辑
	 * 
	 * @param userEditReq
	 */
	void userEdit(UserEditReq userEditReq);

	/**
	 * 获取用户所有选择的标签
	 * 
	 * @param userTagQueryReq
	 * @return
	 */
	UserTagQueryResp queryUserTags(UserTagQueryReq userTagQueryReq);

	/**
	 * 用户互粉处理
	 * 
	 * @param doUserFans
	 */
	void processDoUserFans(DoUserFans doUserFans);

	/**
	 * 加好友处理
	 * 
	 * @param doUserFriend
	 */
	void processDoUserFriend(DoUserFriend doUserFriend);

	/**
	 * 创建虚拟账户
	 * 
	 * @param userId
	 */
	void createDefaultUserAssets(String userId);

	/**
	 * 用户资产交易处理
	 * 
	 * @param notify
	 */
	void process(DoUserAssetsTrade notify);

	UserWealthQueryResp queryUserWealth(String userId);

	HyUserAssets getUserAssets(String userId, String assetsType);

	UserViewInfo getUserViewInfoFromDBByUserId(String userId);

	/**
	 * 查询未认证的用户
	 * 
	 * @param status
	 * @return
	 */
	List<UserViewInfo> getUnAuthUsers();

	String submitUserAuthInfo(UserAuthReq userStatusReq);

	/**
	 * 用户邀请码
	 * 
	 * @param userInviteReq
	 * @return
	 */
	List<UserInviteInfo> getUserInvite(UserInviteReq userInviteReq);

	void updateUserInvite(UserInviteReq userInviteReq);

	String createUserBuyHB(CreateUserBuyHBOrderReq createUserBuyHBOrderReq);

	String createUserBuyMember(CreateUserBuyMemberOrderReq createUserBuyMemberOrderReq);

	HyUserBuyMember getHyUserBuyMember(String buyOrderId);

	HyUserBuyHb getHyUserBuyHb(String buyOrderId);

	void resetUsersInRedis();

	void batchCreateInviteCodeForAllUsers();

	UserViewInfo queryUserViewByMobilePhone(String mobilePhone);

}
