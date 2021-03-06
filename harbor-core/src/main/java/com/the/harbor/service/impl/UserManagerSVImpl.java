package com.the.harbor.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.the.harbor.api.pay.param.CreatePaymentOrderReq;
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
import com.the.harbor.base.constants.ExceptCodeConstants;
import com.the.harbor.base.enumeration.common.Status;
import com.the.harbor.base.enumeration.dict.ParamCode;
import com.the.harbor.base.enumeration.dict.TypeCode;
import com.the.harbor.base.enumeration.hynotify.AccepterType;
import com.the.harbor.base.enumeration.hynotify.NotifyType;
import com.the.harbor.base.enumeration.hynotify.SenderType;
import com.the.harbor.base.enumeration.hypaymentorder.BusiType;
import com.the.harbor.base.enumeration.hytags.TagCat;
import com.the.harbor.base.enumeration.hytags.TagType;
import com.the.harbor.base.enumeration.hyuser.AccessPermission;
import com.the.harbor.base.enumeration.hyuser.AuthIdentity;
import com.the.harbor.base.enumeration.hyuser.AuthSts;
import com.the.harbor.base.enumeration.hyuser.MemberLevel;
import com.the.harbor.base.enumeration.hyuser.SystemUser;
import com.the.harbor.base.enumeration.hyuser.UserInviteStatus;
import com.the.harbor.base.enumeration.hyuser.UserStatus;
import com.the.harbor.base.enumeration.hyuser.UserType;
import com.the.harbor.base.enumeration.hyuserassets.AssetsStatus;
import com.the.harbor.base.enumeration.hyuserassets.AssetsType;
import com.the.harbor.base.enumeration.hyuserassets.AssetsUnit;
import com.the.harbor.base.enumeration.hyuserassets.TradeType;
import com.the.harbor.base.exception.BusinessException;
import com.the.harbor.base.exception.SystemException;
import com.the.harbor.commons.components.globalconfig.GlobalSettings;
import com.the.harbor.commons.redisdata.def.DoNotify;
import com.the.harbor.commons.redisdata.def.HyCountryVo;
import com.the.harbor.commons.redisdata.util.HyAreaUtil;
import com.the.harbor.commons.redisdata.util.HyCountryUtil;
import com.the.harbor.commons.redisdata.util.HyDictUtil;
import com.the.harbor.commons.redisdata.util.HyIndustryUtil;
import com.the.harbor.commons.redisdata.util.HyUserUtil;
import com.the.harbor.commons.util.AmountUtils;
import com.the.harbor.commons.util.CollectionUtil;
import com.the.harbor.commons.util.DateUtil;
import com.the.harbor.commons.util.RandomUtil;
import com.the.harbor.commons.util.StringUtil;
import com.the.harbor.commons.util.UUIDUtil;
import com.the.harbor.constants.HarborErrorCodeConstants;
import com.the.harbor.dao.mapper.bo.HyPaymentOrder;
import com.the.harbor.dao.mapper.bo.HyUser;
import com.the.harbor.dao.mapper.bo.HyUserAssets;
import com.the.harbor.dao.mapper.bo.HyUserAssetsCriteria;
import com.the.harbor.dao.mapper.bo.HyUserAssetsTrade;
import com.the.harbor.dao.mapper.bo.HyUserBuyHb;
import com.the.harbor.dao.mapper.bo.HyUserBuyHbCriteria;
import com.the.harbor.dao.mapper.bo.HyUserBuyMember;
import com.the.harbor.dao.mapper.bo.HyUserBuyMemberCriteria;
import com.the.harbor.dao.mapper.bo.HyUserCriteria;
import com.the.harbor.dao.mapper.bo.HyUserFans;
import com.the.harbor.dao.mapper.bo.HyUserFansCriteria;
import com.the.harbor.dao.mapper.bo.HyUserFriend;
import com.the.harbor.dao.mapper.bo.HyUserFriendCriteria;
import com.the.harbor.dao.mapper.bo.HyUserHbAssets;
import com.the.harbor.dao.mapper.bo.HyUserInvite;
import com.the.harbor.dao.mapper.bo.HyUserInviteCriteria;
import com.the.harbor.dao.mapper.bo.HyUserInviteCriteria.Criteria;
import com.the.harbor.dao.mapper.bo.HyUserTags;
import com.the.harbor.dao.mapper.bo.HyUserTagsCriteria;
import com.the.harbor.dao.mapper.interfaces.HyUserAssetsMapper;
import com.the.harbor.dao.mapper.interfaces.HyUserAssetsTradeMapper;
import com.the.harbor.dao.mapper.interfaces.HyUserBuyHbMapper;
import com.the.harbor.dao.mapper.interfaces.HyUserBuyMemberMapper;
import com.the.harbor.dao.mapper.interfaces.HyUserFansMapper;
import com.the.harbor.dao.mapper.interfaces.HyUserFriendMapper;
import com.the.harbor.dao.mapper.interfaces.HyUserHbAssetsMapper;
import com.the.harbor.dao.mapper.interfaces.HyUserInviteMapper;
import com.the.harbor.dao.mapper.interfaces.HyUserMapper;
import com.the.harbor.dao.mapper.interfaces.HyUserTagsMapper;
import com.the.harbor.service.interfaces.IBeBusiSV;
import com.the.harbor.service.interfaces.IGoBusiSV;
import com.the.harbor.service.interfaces.IPaymentBusiSV;
import com.the.harbor.service.interfaces.IUserManagerSV;
import com.the.harbor.util.HarborSeqUtil;
import com.the.harbor.util.NotifyMQSend;

@Component
@Transactional
public class UserManagerSVImpl implements IUserManagerSV {

	@Autowired
	private transient HyUserMapper hyUserMapper;

	@Autowired
	private transient HyUserTagsMapper hyUserTagsMapper;

	@Autowired
	private transient HyUserFansMapper HyUserFansMapper;

	@Autowired
	private transient HyUserFriendMapper hyUserFriendMapper;

	@Autowired
	private transient HyUserAssetsMapper hyUserAssetsMapper;

	@Autowired
	private transient HyUserAssetsTradeMapper hyUserAssetsTradeMapper;

	@Autowired
	private transient HyUserHbAssetsMapper hyUserHbAssetsMapper;

	@Autowired
	private transient IBeBusiSV beBusiSV;

	@Autowired
	private transient IGoBusiSV goBusiSV;

	@Autowired
	private transient IPaymentBusiSV paymentBusiSV;

	@Autowired
	private transient HyUserInviteMapper hyUserInviteMapper;

	@Autowired
	private transient HyUserBuyHbMapper hyUserBuyHbMapper;

	@Autowired
	private transient HyUserBuyMemberMapper hyUserBuyMemberMapper;

	@Override
	public String userRegister(UserRegReq userRegReq) {
		/* 根据微信号判断用户是否已经注册 */
		HyUser hyUser = this.getUserByWeixin(userRegReq.getWxOpenid());
		if (hyUser != null) {
			throw new BusinessException(HarborErrorCodeConstants.WEIXIN_BOUND, "您的微信账号已经注册");
		}
		if (!StringUtil.isBlank(userRegReq.getInviteCode())) {
			HyUserInvite userInvite = hyUserInviteMapper.selectByPrimaryKey(userRegReq.getInviteCode());
			if (userInvite == null) {
				throw new BusinessException("您的邀请码不存在哦，速速找好友邀请吧~");
			}
			if (UserInviteStatus.USED.getValue().equals(userInvite.getStatus())) {
				throw new BusinessException("您的邀请码已经被使用哦，速速找好友邀请吧~");
			}
		}

		HyUser user = new HyUser();
		String hyId = HarborSeqUtil.createHyUserHyId(userRegReq.getAbroadCountry());
		user.setUserId(HarborSeqUtil.createHyUserId());
		user.setUserType(UserType.ORDINARY_USER.getValue());
		user.setHyId(hyId);
		user.setEnName(userRegReq.getEnName());
		user.setSex(userRegReq.getSex());
		user.setWxHeadimg(StringUtil.isBlank(userRegReq.getWxHeadimg())
				? GlobalSettings.getHarborUserDefaultHeadICONURL() : userRegReq.getWxHeadimg());
		user.setHomePageBg(GlobalSettings.getHarborUserDefaultHomePageBGURL());
		user.setWxNickname(userRegReq.getWxNickname());
		user.setWxOpenid(userRegReq.getWxOpenid());
		user.setAbroadCountry(userRegReq.getAbroadCountry());
		user.setIndustry(userRegReq.getIndustry());
		user.setAtCity(userRegReq.getAtCity());
		user.setMobilePhone(userRegReq.getMobilePhone());
		user.setEmail(userRegReq.getEmail());
		user.setRegDate(DateUtil.getSysDate());
		user.setUserStatus(UserStatus.UNAUTHORIZED.getValue());
		user.setAccessPermission(AccessPermission.ALL_ALLOWED.getValue());
		user.setMemberLevel(MemberLevel.NOT.getValue());
		user.setAuthSts(AuthSts.NOT_APPLY.getValue());
		int success = hyUserMapper.insertSelective(user);
		if (success == 0) {
			throw new SystemException("注册失败,请稍候重试");
		}
		if (!StringUtil.isBlank(userRegReq.getInviteCode())) {
			this.resetUserInvite(userRegReq.getInviteCode(), user.getUserId());
		}

		// 初始化资产信息
		this.createDefaultUserAssets(user.getUserId());
		// 生成邀请码
		this.createInviteCodes(user.getUserId());
		return user.getUserId();
	}

	public void createDefaultUserAssets(String userId) {
		this.createDefaultUserCashAssets(userId);
		this.createDefaultUserHaibeiAssets(userId);
	}

	private void createDefaultUserCashAssets(String userId) {
		HyUserAssets record = new HyUserAssets();
		record.setAssetsId(UUIDUtil.genId32());
		record.setUserId(userId);
		record.setAssetsType(AssetsType.CASH.getValue());
		record.setAssetsUnit(AssetsUnit.FEN.getValue());
		record.setBalance(0l);
		record.setCreateDate(DateUtil.getSysDate());
		record.setTotalExpenditure(0);
		record.setTotalIncome(0);
		record.setAssetsStatus(AssetsStatus.NORMAL.getValue());
		hyUserAssetsMapper.insert(record);
	}

	private void createDefaultUserHaibeiAssets(String userId) {
		HyUserAssets record = new HyUserAssets();
		record.setAssetsId(UUIDUtil.genId32());
		record.setUserId(userId);
		record.setAssetsType(AssetsType.HAIBEI.getValue());
		record.setAssetsUnit(AssetsUnit.GE.getValue());
		record.setBalance(0l);
		record.setCreateDate(DateUtil.getSysDate());
		record.setTotalExpenditure(0);
		record.setTotalIncome(0);
		record.setAssetsStatus(AssetsStatus.NORMAL.getValue());
		hyUserAssetsMapper.insert(record);

		HyUserHbAssets hb = new HyUserHbAssets();
		hb.setAssetsId(record.getAssetsId());
		hb.setUserId(record.getUserId());
		hb.setTotalDashang(0);
		hb.setTotalBeishang(0);
		hb.setTotalGongyi(0);
		hb.setTotalJiangli(0);
		hyUserHbAssetsMapper.insertSelective(hb);
	}

	private HyUser getUserByWeixin(String wxOpenId) {
		if (StringUtil.isBlank(wxOpenId)) {
			throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "微信OPEN_ID不能为空");
		}
		HyUserCriteria sql = new HyUserCriteria();
		sql.or().andWxOpenidEqualTo(wxOpenId);
		List<HyUser> users = hyUserMapper.selectByExample(sql);
		return CollectionUtil.isEmpty(users) ? null : users.get(0);
	}

	private HyUser getUserInfo(String userId) {
		HyUser user = hyUserMapper.selectByPrimaryKey(userId);
		return user;
	}

	public void submitUserCertification(UserCertificationReq userCertificationReq) {
		HyUser user = this.getUserInfo(userCertificationReq.getUserId());
		if (user == null) {
			throw new BusinessException("USER_00001", "请先注册后再提交认证");
		}
		if (UserStatus.AUTHORIZED_SUCCESS.getValue().equals(user.getUserStatus())) {
			throw new BusinessException("USER_00002", "您的资料已经认证通过");
		}
		HyUser u = new HyUser();
		u.setUserId(user.getUserId());
		u.setIdcardPhoto(userCertificationReq.getIdcardPhoto());
		u.setOverseasPhoto(userCertificationReq.getOverseasPhoto());
		u.setCertificationDate(null);
		u.setSubmitCertDate(DateUtil.getSysDate());
		u.setUserStatus(UserStatus.UNAUTHORIZED.getValue());
		u.setAuthSts(AuthSts.SUBMITTED_APPLY.getValue());
		u.setAuthIdentity(userCertificationReq.getAuthIdentity());
		int n = hyUserMapper.updateByPrimaryKeySelective(u);
		if (n == 0) {
			throw new SystemException("认证材料提交失败,请稍候重试");
		}
	}

	@Override
	public String submitUserAuthInfo(UserAuthReq userStatusReq) {
		HyUser user = this.getUserInfo(userStatusReq.getUserId());
		if (user == null) {
			throw new BusinessException("审核的用户不存在");
		}
		if (UserStatus.AUTHORIZED_SUCCESS.getValue().equals(user.getUserStatus())) {
			throw new BusinessException("已经认证通过");
		}
		HyUser u = new HyUser();
		u.setUserId(user.getUserId());
		u.setCertificationDate(DateUtil.getSysDate());
		u.setCertRemark(userStatusReq.getRemark());

		String content = "";
		String link = null;
		String notifyId = UUIDUtil.genId32();
		if (AuthSts.AUTH_PASS.getValue().equals(userStatusReq.getStatus())) {
			u.setUserStatus(UserStatus.AUTHORIZED_SUCCESS.getValue());
			content = "您提交的认证材料审核通过";
		} else if (AuthSts.AUTH_FAILURE.getValue().equals(userStatusReq.getStatus())) {
			u.setUserStatus(UserStatus.AUTHORIZED_FAILURE.getValue());
			content = "您提交的认证材料审核未通过:" + userStatusReq.getRemark();
			link = "../user/toApplyCertficate.html?notifyId=" + notifyId;
		}
		u.setAuthSts(userStatusReq.getStatus());
		hyUserMapper.updateByPrimaryKeySelective(u);

		// 审核通过与否得要发送消息
		DoNotify notify = new DoNotify();
		notify.setHandleType(DoNotify.HandleType.PUBLISH.name());
		notify.setNotifyId(notifyId);
		notify.setNotifyType(NotifyType.SYSTEM_NOTIFY.getValue());
		notify.setSenderType(SenderType.SYSTEM.getValue());
		notify.setSenderId(userStatusReq.getUserId());
		notify.setAccepterType(AccepterType.USER.getValue());
		notify.setAccepterId(userStatusReq.getUserId());
		notify.setTitle("认证审核结果");
		notify.setContent(content);
		notify.setLink(link);

		NotifyMQSend.sendNotifyMQ(notify);

		return userStatusReq.getUserId();
	}

	@Override
	public void submitUserSelectedSystemTags(String userId, List<UserTag> systemTags) {
		if (StringUtil.isBlank(userId)) {
			throw new BusinessException("USER_00001", "用户信息不存在");
		}
		HyUser user = this.getUserInfo(userId);
		if (user == null) {
			throw new BusinessException("USER_00001", "请先注册后再提交");
		}
		// 失效所有的系统级别的标签
		this.invalidAllSystemTags(userId);
		// 获取新提交上来的标签
		if (CollectionUtil.isEmpty(systemTags)) {
			return;
		}
		int index = 1;
		for (UserTag userTag : systemTags) {
			// 判断标签ID是否存在
			HyUserTags hyUserTag = this.getHyUserTag(userTag.getTagId(), userId);
			if (hyUserTag != null) {
				hyUserTag.setStatus(Status.VALID.getValue());
				hyUserTagsMapper.updateByPrimaryKey(hyUserTag);
			} else {
				hyUserTag = new HyUserTags();
				hyUserTag.setRecordId(HarborSeqUtil.createHyUserTagsRecordId());
				hyUserTag.setSortId(index);
				hyUserTag.setTagId(userTag.getTagId());
				hyUserTag.setStatus(Status.VALID.getValue());
				hyUserTag.setTagCat(userTag.getTagCat());
				hyUserTag.setTagName(userTag.getTagName());
				hyUserTag.setTagType(userTag.getTagType());
				hyUserTag.setUserId(userId);
				hyUserTagsMapper.insert(hyUserTag);
			}
			index++;
		}
	}

	private HyUserTags getHyUserTag(String tagId, String userId) {
		HyUserTagsCriteria sql = new HyUserTagsCriteria();
		sql.or().andUserIdEqualTo(userId).andTagIdEqualTo(tagId);
		List<HyUserTags> list = hyUserTagsMapper.selectByExample(sql);
		return CollectionUtil.isEmpty(list) ? null : list.get(0);
	}

	private void invalidAllSystemTags(String userId) {
		List<HyUserTags> l = this.getHyUserSystemTag(userId);
		if (CollectionUtil.isEmpty(l)) {
			return;
		}
		for (HyUserTags record : l) {
			record.setStatus(Status.INVALID.getValue());
			hyUserTagsMapper.updateByPrimaryKey(record);
		}

	}

	/**
	 * 获取有效的系统标签
	 * 
	 * @param userId
	 * @return
	 */
	private List<HyUserTags> getHyUserSystemTag(String userId) {
		HyUserTagsCriteria sql = new HyUserTagsCriteria();
		sql.or().andUserIdEqualTo(userId).andTagCatEqualTo(TagCat.SYSTEM.getValue())
				.andStatusEqualTo(Status.VALID.getValue());
		List<HyUserTags> list = hyUserTagsMapper.selectByExample(sql);
		return list;
	}

	/**
	 * 失效用户所有的标签
	 * 
	 * @param userId
	 */
	private void invalidAllTags(String userId) {
		List<HyUserTags> l = this.getHyUserTags(userId);
		if (CollectionUtil.isEmpty(l)) {
			return;
		}
		for (HyUserTags record : l) {
			record.setStatus(Status.INVALID.getValue());
			hyUserTagsMapper.updateByPrimaryKey(record);
		}

	}

	/**
	 * 获取用户所有有效的标签：系统+自定义
	 * 
	 * @param userId
	 * @return
	 */
	private List<HyUserTags> getHyUserTags(String userId) {
		HyUserTagsCriteria sql = new HyUserTagsCriteria();
		sql.or().andUserIdEqualTo(userId).andStatusEqualTo(Status.VALID.getValue());
		List<HyUserTags> list = hyUserTagsMapper.selectByExample(sql);
		return list;
	}

	@Override
	public UserSystemTagQueryResp queryUserSystemTags(UserSystemTagQueryReq userSystemTagQueryReq) {
		List<HyUserTags> list = this.getHyUserSystemTag(userSystemTagQueryReq.getUserId());
		List<UserTag> systemInterestTags = new ArrayList<UserTag>();
		List<UserTag> systemSkillTags = new ArrayList<UserTag>();
		if (!CollectionUtil.isEmpty(list)) {
			for (HyUserTags t : list) {
				UserTag o = new UserTag();
				BeanUtils.copyProperties(t, o);
				if (TagType.INTEREST.getValue().equals(t.getTagType())) {
					systemInterestTags.add(o);
				} else if (TagType.SKILL.getValue().equals(t.getTagType())) {
					systemSkillTags.add(o);
				}
			}
		}
		UserSystemTagQueryResp resp = new UserSystemTagQueryResp();
		resp.setUserId(userSystemTagQueryReq.getUserId());
		resp.setSystemInterestTags(systemInterestTags);
		resp.setSystemSkillTags(systemSkillTags);
		return resp;
	}

	@Override
	public UserMemberInfo queryUserMemberInfo(String userId) {
		HyUser hyUser = this.getUserInfo(userId);
		if (hyUser == null) {
			throw new BusinessException("USER_00001", "您的信息不存在");
		}
		UserMemberInfo m = new UserMemberInfo();
		m.setUserId(userId);
		m.setOpenId(hyUser.getWxOpenid());
		m.setMemberLevel(hyUser.getMemberLevel());
		if (hyUser.getEffDate() != null) {
			m.setEffDate(hyUser.getEffDate());
			m.setEffDateStr(DateUtil.getDateString(hyUser.getEffDate(), DateUtil.DATE_FORMAT));
		}

		if (hyUser.getExpDate() != null) {
			m.setExpDate(hyUser.getExpDate());
			m.setExpDateStr(DateUtil.getDateString(hyUser.getExpDate(), DateUtil.DATE_FORMAT));

			if (DateUtil.getSysDate().after(hyUser.getExpDate())) {
				m.setExpflag(true);
				m.setDesc(m.getExpDateStr());
			} else {
				m.setExpflag(false);
				m.setDesc(m.getExpDateStr());
			}
		} else {
			m.setExpflag(true);
			if (MemberLevel.NOT.getValue().equals(m.getMemberLevel())) {
				m.setDesc("您还不是会员");
			}
		}

		return m;
	}

	/**
	 * 会员缴费续期
	 * 
	 * @param buyMember
	 * @return
	 */
	public void userMemberRenewal(HyUserBuyMember buyMember) {
		// 校验用户信息
		HyUser hyUser = this.getUserInfo(buyMember.getUserId());
		if (hyUser == null) {
			throw new BusinessException("USER_00001", "传入的用户不存在");
		}
		// 获取续期月份
		int payMonth = buyMember.getBuyMonths();
		// 获取当前系统时间
		Timestamp sysdate = DateUtil.getSysDate();
		Timestamp effDate = null;
		Timestamp expDate = null;
		String memberLevel = hyUser.getMemberLevel();
		// 会员续期的生失效时间计算
		if (MemberLevel.NOT.getValue().equals(hyUser.getMemberLevel())) {
			/* 1.如果当前用户还不是会员，购买成功后为普通会员。会员的生效日期为当前时间，失效日期为当前日期+购买月份 */
			effDate = sysdate;
			expDate = DateUtil.getOffsetMonthsTime(sysdate, payMonth);
			memberLevel = MemberLevel.COMMON.getValue();
		} else {
			/* 2.如果已经是会员了，生效时间不变，失效时间需要判断是否已经过期 */
			effDate = hyUser.getEffDate();
			if (hyUser.getExpDate() == null) {
				// 如果没有失效时间，理论上不存在。则默认设计为当前时间+购买月份
				expDate = DateUtil.getOffsetMonthsTime(sysdate, payMonth);
			} else {
				if (sysdate.after(hyUser.getExpDate())) {
					// 如果失效时间已经过期，则表示用户早前是会员，中间没有续费。此时续费后，失效时间为当前时间+购买月份
					expDate = DateUtil.getOffsetMonthsTime(sysdate, payMonth);
				} else {
					// 如果失效时间还没有到期，则表示当前用户是会员。此时续费后，失效时间为用户当前会员失效时间+购买月份
					expDate = DateUtil.getOffsetMonthsTime(hyUser.getExpDate(), payMonth);
				}
			}
		}
		// 执行会员续期
		hyUser.setEffDate(effDate);
		hyUser.setExpDate(expDate);
		hyUser.setMemberLevel(memberLevel);
		hyUserMapper.updateByPrimaryKeySelective(hyUser);

		// 更新缓存
		this.storeUserInfo2Redis(hyUser.getUserId());

	}

	private void storeUserInfo2Redis(String userId) {
		try {
			UserViewInfo userInfo = this.getUserViewInfoFromDBByUserId(userId);
			if (userInfo != null) {
				HyUserUtil.storeUserInfo2Redis(userId, JSON.toJSONString(userInfo));
				HyUserUtil.buildOpenIdAndUserIdMapped(userInfo.getWxOpenid(), userId);
			}
		} catch (Exception ex) {

		}
	}

	@Override
	public void userEdit(UserEditReq userEditReq) {
		// 校验用户信息
		HyUser hyUser = this.getUserInfo(userEditReq.getUserId());
		if (hyUser == null) {
			throw new BusinessException("USER_00001", "用户不存在");
		}
		if (!hyUser.getWxOpenid().equals(userEditReq.getWxOpenid())) {
			throw new BusinessException("USER_00002", "传入的微信openId与实际不符合");
		}
		// 判断是否有自定义的标签
		List<UserTag> tags = new ArrayList<UserTag>();
		if (!CollectionUtil.isEmpty(userEditReq.getInterestSelectedTags())) {
			tags.addAll(userEditReq.getInterestSelectedTags());
		}
		if (!CollectionUtil.isEmpty(userEditReq.getSkillSelectedTags())) {
			tags.addAll(userEditReq.getSkillSelectedTags());
		}
		// 更新用户基本资料
		HyUser user = new HyUser();
		BeanUtils.copyProperties(userEditReq, user);
		user.setUserId(userEditReq.getUserId());
		user.setWxHeadimg(StringUtil.isBlank(userEditReq.getWxHeadimg())
				? GlobalSettings.getHarborUserDefaultHeadICONURL() : userEditReq.getWxHeadimg());
		user.setHomePageBg(StringUtil.isBlank(userEditReq.getHomePageBg())
				? GlobalSettings.getHarborUserDefaultHomePageBGURL() : userEditReq.getHomePageBg());
		hyUserMapper.updateByPrimaryKeySelective(user);
		// 先失效所有标签
		this.invalidAllTags(hyUser.getUserId());

		// 获取新提交上来的标签
		if (CollectionUtil.isEmpty(tags)) {
			return;
		}
		int index = 1;
		for (UserTag userTag : tags) {
			if (!StringUtil.isBlank(userTag.getTagId())) {
				// 标签ID存在，说明是系统内置标签，判断是否有记录
				HyUserTags hyUserTag = this.getHyUserTag(userTag.getTagId(), hyUser.getUserId());
				if (hyUserTag != null) {
					hyUserTag.setSortId(index);
					hyUserTag.setStatus(Status.VALID.getValue());
					hyUserTagsMapper.updateByPrimaryKey(hyUserTag);
				} else {
					// 没有记录，则写入记录
					hyUserTag = new HyUserTags();
					hyUserTag.setRecordId(HarborSeqUtil.createHyUserTagsRecordId());
					hyUserTag.setSortId(index);
					hyUserTag.setTagId(userTag.getTagId());
					hyUserTag.setStatus(Status.VALID.getValue());
					hyUserTag.setTagCat(userTag.getTagCat());
					hyUserTag.setTagName(userTag.getTagName());
					hyUserTag.setTagType(userTag.getTagType());
					hyUserTag.setUserId(hyUser.getUserId());
					hyUserTagsMapper.insert(hyUserTag);
				}
			} else {
				HyUserTags hyUserTag = new HyUserTags();
				hyUserTag.setRecordId(HarborSeqUtil.createHyUserTagsRecordId());
				hyUserTag.setSortId(index);
				hyUserTag.setTagId(HarborSeqUtil.createTagId(userTag.getTagType()));
				hyUserTag.setStatus(Status.VALID.getValue());
				hyUserTag.setTagCat(userTag.getTagCat());
				hyUserTag.setTagName(userTag.getTagName());
				hyUserTag.setTagType(userTag.getTagType());
				hyUserTag.setUserId(hyUser.getUserId());
				hyUserTagsMapper.insert(hyUserTag);
			}
			index++;
		}
	}

	@Override
	public UserTagQueryResp queryUserTags(UserTagQueryReq userTagQueryReq) {
		List<HyUserTags> list = this.getHyUserTags(userTagQueryReq.getUserId());
		List<UserTag> interestTags = new ArrayList<UserTag>();
		List<UserTag> skillTags = new ArrayList<UserTag>();
		if (!CollectionUtil.isEmpty(list)) {
			for (HyUserTags t : list) {
				UserTag o = new UserTag();
				BeanUtils.copyProperties(t, o);
				if (TagType.INTEREST.getValue().equals(t.getTagType())) {
					interestTags.add(o);
				} else if (TagType.SKILL.getValue().equals(t.getTagType())) {
					skillTags.add(o);
				}
			}
		}
		UserTagQueryResp resp = new UserTagQueryResp();
		resp.setUserId(userTagQueryReq.getUserId());
		resp.setInterestTags(interestTags);
		resp.setSkillTags(skillTags);
		return resp;
	}

	@Override
	public UserViewInfo getUserViewInfoByUserId(String userId) {
		return this.getUserViewInfoFromCacheByUserId(userId);
	}

	@Override
	public UserViewInfo getUserViewInfoByOpenId(String openId) {
		return this.getUserViewInfoFromCacheByOpenId(openId);
	}

	@Override
	public List<UserViewInfo> getUnAuthUsers() {
		HyUserCriteria sql = new HyUserCriteria();
		sql.or().andAuthStsEqualTo(AuthSts.SUBMITTED_APPLY.getValue()).andUserStatusIn(Arrays
				.asList(new String[] { UserStatus.AUTHORIZED_FAILURE.getValue(), UserStatus.UNAUTHORIZED.getValue() }));
		List<HyUser> users = hyUserMapper.selectByExample(sql);
		if (CollectionUtil.isEmpty(users)) {
			return null;
		}
		List<UserViewInfo> userViewList = new ArrayList<UserViewInfo>();
		for (HyUser user : users) {
			UserViewInfo userView = convert(user);
			userViewList.add(userView);
		}
		return userViewList;
	}

	private UserViewInfo convert(HyUser hyUser) {
		UserViewInfo userInfo = null;
		if (hyUser != null) {
			userInfo = new UserViewInfo();
			BeanUtils.copyProperties(hyUser, userInfo);
			userInfo.setHomePageBg(StringUtil.isBlank(hyUser.getHomePageBg())
					? GlobalSettings.getHarborUserDefaultHomePageBGURL() : hyUser.getHomePageBg());
			userInfo.setWxHeadimg(StringUtil.isBlank(hyUser.getWxHeadimg())
					? GlobalSettings.getHarborUserDefaultHeadICONURL() : hyUser.getWxHeadimg());

			userInfo.setAbroadCountryName(HyCountryUtil.getHyCountryName(hyUser.getAbroadCountry()));
			HyCountryVo country = HyCountryUtil.getHyCountry(hyUser.getAbroadCountry());
			userInfo.setAbroadCountryRGB(country == null ? null : country.getCountryRgb());
			if (!StringUtil.isBlank(hyUser.getAtCity())) {
				userInfo.setAtCityName(HyAreaUtil.getAreaName(hyUser.getAtCity()));
			}
			userInfo.setIndustryName(HyIndustryUtil.getHyIndustryName(hyUser.getIndustry()));
			userInfo.setUserTypeName(HyDictUtil.getHyDictDesc(TypeCode.HY_USER.getValue(),
					ParamCode.USER_TYPE.getValue(), hyUser.getAbroadCountry()));
			userInfo.setSexName(HyDictUtil.getHyDictDesc(TypeCode.HY_USER.getValue(), ParamCode.SEX.getValue(),
					hyUser.getAbroadCountry()));
			userInfo.setMaritalStatusName(HyDictUtil.getHyDictDesc(TypeCode.HY_USER.getValue(),
					ParamCode.MARITAL_STATUS.getValue(), hyUser.getAbroadCountry()));
			userInfo.setConstellationName(HyDictUtil.getHyDictDesc(TypeCode.HY_USER.getValue(),
					ParamCode.CONSTELLATION.getValue(), hyUser.getAbroadCountry()));
			userInfo.setAuthIdentityName(HyDictUtil.getHyDictDesc(TypeCode.HY_USER.getValue(),
					ParamCode.AUTH_IDENTITY.getValue(), hyUser.getAuthIdentity()));
			if (UserStatus.AUTHORIZED_SUCCESS.getValue().equals(hyUser.getUserStatus())) {
				String userStatus = "";
				if (StringUtil.isBlank(userInfo.getAuthIdentity())) {
					userStatus = HyDictUtil.getHyDictDesc(TypeCode.HY_USER.getValue(), ParamCode.USER_STATUS.getValue(),
							hyUser.getUserStatus());
				} else {
					if (AuthIdentity.ENTREPRENEUR.getValue().equals(userInfo.getAuthIdentity())) {
						userStatus = HyDictUtil.getHyDictDesc(TypeCode.HY_USER.getValue(),
								ParamCode.USER_STATUS.getValue(), hyUser.getUserStatus());
					} else {
						userStatus = HyDictUtil.getHyDictDesc(TypeCode.HY_USER.getValue(),
								ParamCode.AUTH_IDENTITY.getValue(), hyUser.getAuthIdentity());
					}
				}

				userInfo.setUserStatusName(userStatus);
			} else {
				String userStatus = HyDictUtil.getHyDictDesc(TypeCode.HY_USER.getValue(),
						ParamCode.USER_STATUS.getValue(), UserStatus.UNAUTHORIZED.getValue());
				userInfo.setUserStatusName(userStatus);
			}
			String authStsName = HyDictUtil.getHyDictDesc(TypeCode.HY_USER.getValue(), ParamCode.AUTH_STS.getValue(),
					hyUser.getAuthSts());
			userInfo.setAuthStsName(authStsName);

		}
		return userInfo;
	}

	public UserViewInfo getUserViewInfoFromDBByUserId(String userId) {
		HyUser hyUser = this.getUserInfo(userId);
		UserViewInfo userInfo = this.convert(hyUser);
		return userInfo;
	}

	private UserViewInfo getUserViewInfoFromCacheByUserId(String userId) {
		UserViewInfo userInfo = null;
		// 从REDIS中读取用户信息
		String userData = HyUserUtil.getUserInfoFromRedis(userId);
		if (StringUtil.isBlank(userData)) {
			// 如果换成没有用户信息，则查询库
			HyUser hyUser = this.getUserInfo(userId);
			userInfo = this.convert(hyUser);
			if (userInfo == null) {
				HyUserUtil.storeUserInfo2Redis(userId, JSON.toJSONString(userInfo));
			}
		} else {
			userInfo = JSONObject.parseObject(userData, UserViewInfo.class);
		}
		return userInfo;
	}

	private UserViewInfo getUserViewInfoFromCacheByOpenId(String openId) {
		HyUser hyUser = getUserByWeixin(openId);
		if (hyUser == null) {
			return null;
		}
		UserViewInfo userInfo = null;
		String userData = HyUserUtil.getUserInfoFromRedis(hyUser.getUserId());
		if (StringUtil.isBlank(userData)) {
			userInfo = this.convert(hyUser);
			if (userInfo == null) {
				HyUserUtil.storeUserInfo2Redis(hyUser.getUserId(), JSON.toJSONString(userInfo));
			}
		} else {
			userInfo = JSONObject.parseObject(userData, UserViewInfo.class);
		}
		return userInfo;
	}

	@Override
	public void processDoUserFans(DoUserFans doUserFans) {
		if (DoUserFans.HandleType.GUANZHU.name().equals(doUserFans.getHandleType())) {
			Set<String> set = HyUserUtil.getUserGuanzhuUsers(doUserFans.getUserId());
			if (set.contains(doUserFans.getFansUserId())) {
				return;
			}
			// 记录用户关注行为
			HyUserFans record = new HyUserFans();
			record.setFansId(HarborSeqUtil.createUserFansId());
			record.setFansUserId(doUserFans.getFansUserId());
			record.setStatus(com.the.harbor.base.enumeration.hyuserfans.Status.FANS.getValue());
			record.setStsChgDate(DateUtil.getSysDate());
			record.setUserId(doUserFans.getUserId());
			record.setCreateDate(doUserFans.getTime() == null ? DateUtil.getSysDate() : doUserFans.getTime());
			HyUserFansMapper.insert(record);
			// 写入REDIS记录
			HyUserUtil.userAGuanzhuUserB(doUserFans.getUserId(), doUserFans.getFansUserId());
		} else if (DoUserFans.HandleType.CANCEL.name().equals(doUserFans.getHandleType())) {
			// 记录用户取消关注行为
			if (!StringUtil.isBlank(doUserFans.getUserId()) && !StringUtil.isBlank(doUserFans.getFansUserId())) {
				HyUserFansCriteria sql = new HyUserFansCriteria();
				sql.or().andUserIdEqualTo(doUserFans.getUserId()).andFansUserIdEqualTo(doUserFans.getFansUserId())
						.andStatusEqualTo(com.the.harbor.base.enumeration.hyuserfans.Status.FANS.getValue());
				HyUserFans record = new HyUserFans();
				record.setStatus(com.the.harbor.base.enumeration.hyuserfans.Status.CANCEL.getValue());
				record.setStsChgDate(DateUtil.getSysDate());
				HyUserFansMapper.updateByExampleSelective(record, sql);
				// 写入REDIS记录
				HyUserUtil.userACancelGuanzhuUserB(doUserFans.getUserId(), doUserFans.getFansUserId());
			}
		}
	}

	@Override
	public void processDoUserFriend(DoUserFriend doUserFriend) {
		if (DoUserFriend.HandleType.APPLY.name().equals(doUserFriend.getHandleType())) {
			// 申请信息是记录在对方的，判断对方申请列表中是否有发起者
			Set<String> appSet = HyUserUtil.getUserFriendApplies(doUserFriend.getFriendUserId());
			if (appSet.contains(doUserFriend.getUserId())) {
				return;
			}
			// 判断我的好友中是否有对方
			Set<String> friSet = HyUserUtil.getUserFriends(doUserFriend.getUserId());
			if (friSet.contains(doUserFriend.getFriendUserId())) {
				return;
			}
			// 写入好友申请记录到REDIS
			HyUserUtil.userAApplyFriendToUserB(doUserFriend.getUserId(), doUserFriend.getFriendUserId());
		} else if (DoUserFriend.HandleType.CANCEL.name().equals(doUserFriend.getHandleType())) {
			// 取消成为好友,删除双向好友关系
			if (!StringUtil.isBlank(doUserFriend.getUserId()) && !StringUtil.isBlank(doUserFriend.getFriendUserId())) {
				HyUserFriendCriteria sql = new HyUserFriendCriteria();
				sql.or().andUserIdEqualTo(doUserFriend.getUserId()).andFriendIdEqualTo(doUserFriend.getFriendUserId())
						.andStatusEqualTo(com.the.harbor.base.enumeration.hyuserfriend.Status.AGREE.getValue());
				HyUserFriend record = new HyUserFriend();
				record.setStatus(com.the.harbor.base.enumeration.hyuserfriend.Status.CANCEL.getValue());
				record.setStsDate(DateUtil.getSysDate());
				hyUserFriendMapper.updateByExampleSelective(record, sql);

				sql = new HyUserFriendCriteria();
				sql.or().andUserIdEqualTo(doUserFriend.getFriendUserId()).andFriendIdEqualTo(doUserFriend.getUserId())
						.andStatusEqualTo(com.the.harbor.base.enumeration.hyuserfriend.Status.AGREE.getValue());
				record = new HyUserFriend();
				record.setStatus(com.the.harbor.base.enumeration.hyuserfriend.Status.CANCEL.getValue());
				record.setStsDate(DateUtil.getSysDate());
				hyUserFriendMapper.updateByExampleSelective(record, sql);
				// 从好友列表中删除
				HyUserUtil.userARemoveFriendUserB(doUserFriend.getUserId(), doUserFriend.getFriendUserId());
			}
		} else if (DoUserFriend.HandleType.REJECT.name().equals(doUserFriend.getHandleType())) {
			// 拒绝好友申请,从发起方好友申请列表中移除对方
			if (!StringUtil.isBlank(doUserFriend.getUserId()) && !StringUtil.isBlank(doUserFriend.getFriendUserId())) {
				HyUserUtil.userARejectApplyFriendofUserB(doUserFriend.getUserId(), doUserFriend.getFriendUserId());
			}
		} else if (DoUserFriend.HandleType.AGREE.name().equals(doUserFriend.getHandleType())) {
			// 同意好友申请.互为好友
			if (!StringUtil.isBlank(doUserFriend.getUserId()) && !StringUtil.isBlank(doUserFriend.getFriendUserId())) {
				HyUserFriend record = new HyUserFriend();
				record.setRecordId(HarborSeqUtil.createUserFriendId());
				record.setUserId(doUserFriend.getUserId());
				record.setFriendId(doUserFriend.getFriendUserId());
				record.setStatus(com.the.harbor.base.enumeration.hyuserfriend.Status.AGREE.getValue());
				record.setStsDate(DateUtil.getSysDate());
				record.setCreateDate(DateUtil.getSysDate());
				hyUserFriendMapper.insert(record);
				// 同意好友申请
				HyUserUtil.userAAgreeApplyFriendofUserB(doUserFriend.getUserId(), doUserFriend.getFriendUserId());
			}
		}

	}

	@Override
	public void process(DoUserAssetsTrade notify) {
		if (notify == null) {
			return;
		}
		if (notify.getTradeBalance() == 0) {
			return;
		}
		if (StringUtil.isBlank(notify.getAssetsType())) {
			throw new BusinessException("资产类型为空");
		}
		if (StringUtil.isBlank(notify.getFromUserId())) {
			throw new BusinessException("发起用户为空");
		}
		if (StringUtil.isBlank(notify.getBusiType())) {
			throw new BusinessException("业务类型为空");
		}
		if (StringUtil.isBlank(notify.getSourceNo())) {
			throw new BusinessException("业务订单号为空");
		}
		if (StringUtil.isBlank(notify.getToUserId())) {
			throw new BusinessException("目标用户为空");
		}
		if (StringUtil.isBlank(notify.getHandleType())) {
			throw new BusinessException("交易类型为空");
		}
		Timestamp sysdate = DateUtil.getSysDate();
		if (DoUserAssetsTrade.HandleType.TRANSFER.name().equals(notify.getHandleType())) {
			String fromLogId = UUIDUtil.genId32();
			String toLogId = UUIDUtil.genId32();
			// 如果是转账, 发起用户需要进行扣款操作
			if (!SystemUser.SYSTEM.getValue().equals(notify.getFromUserId())) {
				// 如果发起用户不是系统用户，则进行对应科目的资金扣减
				HyUserAssets assets = this.getUserAssets(notify.getFromUserId(), notify.getAssetsType());
				if (assets == null) {
					throw new BusinessException(
							"用户[" + notify.getFromUserId() + "]缺少资金账户[" + notify.getAssetsType() + "]");
				}
				HyUserAssets record = new HyUserAssets();
				record.setAssetsId(assets.getAssetsId());
				record.setBalance(assets.getBalance() - notify.getTradeBalance());
				record.setChgDate(sysdate);
				record.setTotalExpenditure(assets.getBalance() - notify.getTradeBalance());
				record.setChgDesc("业务交易[" + notify.getSourceNo() + "]给用户[" + notify.getToUserId() + "]转账额度["
						+ notify.getTradeBalance() + "]。账户变动日志[" + fromLogId + "]");
				hyUserAssetsMapper.updateByPrimaryKeySelective(record);

				// 发起用户记录资金变动日志
				HyUserAssetsTrade flog = new HyUserAssetsTrade();
				flog.setLogId(fromLogId);
				flog.setAssetsId(assets.getAssetsId());
				flog.setUserId(assets.getUserId());
				flog.setTradeType(TradeType.DEDUCTION.getValue());
				flog.setBusiType(notify.getBusiType());
				flog.setAssetsType(assets.getAssetsType());
				flog.setAssetsUnit(assets.getAssetsUnit());
				flog.setLastBalance(assets.getBalance());
				flog.setCurrentBalance(notify.getTradeBalance());
				flog.setSummary(notify.getSummary());
				flog.setTradeDate(sysdate);
				flog.setSourceNo(notify.getSourceNo());
				flog.setRelUserId(notify.getToUserId());
				hyUserAssetsTradeMapper.insert(flog);

				HyUserHbAssets hb = this.getUserHBAssets(assets.getAssetsId());
				if (hb == null) {
					hb = new HyUserHbAssets();
					hb.setAssetsId(assets.getAssetsId());
					hb.setUserId(assets.getUserId());
					hb.setTotalDashang(0);
					hb.setTotalBeishang(0);
					hb.setTotalGongyi(0);
					hb.setTotalJiangli(0);
					hyUserHbAssetsMapper.insertSelective(hb);
				}

				// 如果业务类型涉及到海贝交易，且是支出方
				if (BusiType.REWARD_HB_FOR_BE.getValue().equals(notify.getBusiType())) {
					// 我打赏别人海贝
					hb.setAssetsId(assets.getAssetsId());
					hb.setTotalDashang(hb.getTotalDashang() + notify.getTradeBalance());
					hyUserHbAssetsMapper.updateByPrimaryKeySelective(hb);
				}
			}

			// 目标用户资金存入
			HyUserAssets assets = this.getUserAssets(notify.getToUserId(), notify.getAssetsType());
			if (assets == null) {
				throw new BusinessException("用户[" + notify.getToUserId() + "]缺少资金账户[" + notify.getAssetsType() + "]");
			}
			HyUserAssets record = new HyUserAssets();
			record.setAssetsId(assets.getAssetsId());
			record.setBalance(assets.getBalance() + notify.getTradeBalance());
			record.setChgDate(sysdate);
			record.setTotalIncome(assets.getBalance() + notify.getTradeBalance());
			record.setChgDesc("业务交易[" + notify.getSourceNo() + "]用户[" + notify.getFromUserId() + "]给我转账额度["
					+ notify.getTradeBalance() + "]。账户变动日志[" + toLogId + "]");
			hyUserAssetsMapper.updateByPrimaryKeySelective(record);
			// 记录目标用户资金存入变动日志
			HyUserAssetsTrade tlog = new HyUserAssetsTrade();
			tlog.setLogId(toLogId);
			tlog.setAssetsId(assets.getAssetsId());
			tlog.setUserId(assets.getUserId());
			tlog.setTradeType(TradeType.DEPOSIT.getValue());
			tlog.setBusiType(notify.getBusiType());
			tlog.setAssetsType(assets.getAssetsType());
			tlog.setAssetsUnit(assets.getAssetsUnit());
			tlog.setLastBalance(assets.getBalance());
			tlog.setCurrentBalance(notify.getTradeBalance());
			tlog.setSummary(notify.getSummary());
			tlog.setTradeDate(sysdate);
			tlog.setSourceNo(notify.getSourceNo());
			tlog.setRelUserId(notify.getFromUserId());
			hyUserAssetsTradeMapper.insert(tlog);

			HyUserHbAssets hb = this.getUserHBAssets(assets.getAssetsId());
			if (hb == null) {
				hb = new HyUserHbAssets();
				hb.setAssetsId(assets.getAssetsId());
				hb.setUserId(assets.getUserId());
				hb.setTotalDashang(0);
				hb.setTotalBeishang(0);
				hb.setTotalGongyi(0);
				hb.setTotalJiangli(0);
				hyUserHbAssetsMapper.insertSelective(hb);
			}

			// 如果业务类型涉及到海贝交易，且被被打赏的，则进行加入
			if (BusiType.REWARD_HB_FOR_BE.getValue().equals(notify.getBusiType())) {
				// 我获得别人打赏的海贝
				hb.setAssetsId(assets.getAssetsId());
				hb.setTotalBeishang(hb.getTotalBeishang() + notify.getTradeBalance());
				hyUserHbAssetsMapper.updateByPrimaryKeySelective(hb);
			}

		}

	}

	public HyUserAssets getUserAssets(String userId, String assetsType) {
		HyUserAssetsCriteria sql = new HyUserAssetsCriteria();
		sql.or().andUserIdEqualTo(userId).andAssetsTypeEqualTo(assetsType);
		List<HyUserAssets> list = hyUserAssetsMapper.selectByExample(sql);
		return CollectionUtil.isEmpty(list) ? null : list.get(0);
	}

	private HyUserHbAssets getUserHBAssets(String assetsId) {
		HyUserHbAssets b = hyUserHbAssetsMapper.selectByPrimaryKey(assetsId);
		return b;
	}

	@Override
	public UserWealthQueryResp queryUserWealth(String userId) {
		UserWealthQueryResp resp = new UserWealthQueryResp();
		// 查询现金账户余额
		HyUserAssets cash = this.getUserAssets(userId, AssetsType.CASH.getValue());
		if (cash != null) {
			resp.setCashBlance(cash.getBalance());
			resp.setCashBlanceYuan(AmountUtils.changeF2Y(cash.getBalance()));
		}
		// 查询海贝余额
		HyUserAssets haibei = this.getUserAssets(userId, AssetsType.HAIBEI.getValue());
		if (cash != null) {
			resp.setHbBalance(haibei.getBalance());
			// 查询海贝交易明细
			HyUserHbAssets hbast = this.getUserHBAssets(haibei.getAssetsId());
			if (hbast != null) {
				resp.setTotalBeishang(hbast.getTotalBeishang());
				resp.setTotalDashang(hbast.getTotalDashang());
				resp.setTotalGongyi(hbast.getTotalGongyi());
				resp.setTotalJiangli(hbast.getTotalJiangli());
			}
		}
		// 获取用户所有BE被赞
		resp.setTotalDianzan(beBusiSV.getBesCount(userId));
		// 查询益友总数
		resp.setYiyou(goBusiSV.getGoYiYouCount(userId));
		// 查询助人总数
		resp.setZhuren(goBusiSV.getZhuRenCount(userId));
		resp.setUserId(userId);
		return resp;
	}

	@Override
	public List<UserInviteInfo> getUserInvite(UserInviteReq userInviteReq) {
		if (userInviteReq == null) {
			throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "查询邀请码信息入参为空");
		}
		UserInviteInfo userInviteInfo = userInviteReq.getUserInviteInfo();
		if (userInviteInfo == null) {
			throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "查询邀请码信息入参为空");
		}
		HyUserInviteCriteria sql = new HyUserInviteCriteria();
		Criteria criteria = sql.or();
		if (!StringUtil.isBlank(userInviteInfo.getUserId())) {
			criteria.andUserIdEqualTo(userInviteInfo.getUserId());
		}
		if (!StringUtil.isBlank(userInviteInfo.getInviteCode())) {
			criteria.andInviteCodeEqualTo(userInviteInfo.getInviteCode());
		}
		if (!StringUtil.isBlank(userInviteInfo.getStatus())) {
			criteria.andStatusEqualTo(userInviteInfo.getStatus());
		}
		List<HyUserInvite> users = hyUserInviteMapper.selectByExample(sql);
		if (CollectionUtil.isEmpty(users)) {
			return null;
		}
		List<UserInviteInfo> userInviteList = new ArrayList<UserInviteInfo>();
		for (HyUserInvite user : users) {
			UserInviteInfo userInfo = new UserInviteInfo();
			BeanUtils.copyProperties(user, userInfo);
			userInviteList.add(userInfo);
		}
		return userInviteList;
	}

	@Override
	public void updateUserInvite(UserInviteReq userInviteReq) {
		if (userInviteReq == null) {
			throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "查询邀请码信息入参为空");
		}
		UserInviteInfo userInviteInfo = userInviteReq.getUserInviteInfo();
		if (userInviteInfo == null) {
			throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "查询邀请码信息入参为空");
		}

		HyUserInvite hInvite = new HyUserInvite();
		hInvite.setInviteCode(userInviteInfo.getInviteCode());
		if (!StringUtil.isBlank(userInviteInfo.getInviteUserId())) {
			hInvite.setInviteUserId(userInviteInfo.getInviteUserId());
		}
		if (!StringUtil.isBlank(userInviteInfo.getStatus())) {
			hInvite.setStatus(userInviteInfo.getStatus());
		}
		int n = hyUserInviteMapper.updateByPrimaryKeySelective(hInvite);
		if (n == 0) {
			throw new SystemException("提交应邀失败");
		}

	}

	private void resetUserInvite(String inviteCode, String inviteUserId) {
		HyUserInvite hInvite = new HyUserInvite();
		hInvite.setInviteCode(inviteCode);
		hInvite.setInviteUserId(inviteUserId);
		hInvite.setStatus(UserInviteStatus.USED.getValue());
		hyUserInviteMapper.updateByPrimaryKeySelective(hInvite);
	}

	private void createInviteCodes(String userId) {
		for (int i = 0; i < 10; i++) {
			String inviteCode = RandomUtil.generateNumber(4);
			HyUserInvite record = new HyUserInvite();
			record.setInviteCode(inviteCode);
			record.setUserId(userId);
			record.setStatus(UserInviteStatus.NOT_USE.getValue());
			try {
				hyUserInviteMapper.insert(record);
			} catch (Exception ex) {

			}
		}

	}

	@Override
	public String createUserBuyHB(CreateUserBuyHBOrderReq createUserBuyHBOrderReq) {
		String payOrderId = null;
		/* 判断签名流水是否生成了支付订单，放置重复提交 */
		HyUserBuyHbCriteria sql = new HyUserBuyHbCriteria();
		sql.or().andFromSignEqualTo(createUserBuyHBOrderReq.getFromSign());
		List<HyUserBuyHb> list = hyUserBuyHbMapper.selectByExample(sql);
		if (CollectionUtil.isEmpty(list)) {
			/* 如果没有产生，则产生一笔 */
			String buyOrderId = UUIDUtil.genId32();
			CreatePaymentOrderReq createPaymentOrderReq = new CreatePaymentOrderReq();
			BeanUtils.copyProperties(createUserBuyHBOrderReq, createPaymentOrderReq);
			createPaymentOrderReq.setSourceNo(buyOrderId);
			createPaymentOrderReq.setBusiType(BusiType.PAY_FOR_HAIBI.getValue());
			payOrderId = paymentBusiSV.createPaymentOrder(createPaymentOrderReq);
			HyUserBuyHb record = new HyUserBuyHb();
			record.setBuyAmount(createUserBuyHBOrderReq.getBuyAmount());
			record.setBuyDate(DateUtil.getSysDate());
			record.setBuyOrderId(buyOrderId);
			record.setFromSign(createUserBuyHBOrderReq.getFromSign());
			record.setPayAmount(createUserBuyHBOrderReq.getPayAmount());
			record.setPayOrderId(payOrderId);
			record.setStatus("10");
			record.setUserId(createUserBuyHBOrderReq.getUserId());
			hyUserBuyHbMapper.insert(record);
		} else {
			/* 如果已经提交了申请，则更新支付金额等信息 */
			HyUserBuyHb record = list.get(0);
			record.setBuyAmount(createUserBuyHBOrderReq.getBuyAmount());
			record.setBuyDate(DateUtil.getSysDate());
			record.setPayAmount(createUserBuyHBOrderReq.getPayAmount());
			hyUserBuyHbMapper.updateByPrimaryKeySelective(record);

			HyPaymentOrder payOrder = paymentBusiSV.getHyPaymentOrder(record.getPayOrderId());
			if (payOrder != null) {
				payOrder.setPayAmount(createUserBuyHBOrderReq.getPayAmount());
				payOrder.setPayDate(DateUtil.getSysDate());
				paymentBusiSV.updateByPrimaryKeySelective(payOrder);
			}
			payOrderId = record.getPayOrderId();
		}
		return payOrderId;
	}

	@Override
	public String createUserBuyMember(CreateUserBuyMemberOrderReq createUserBuyMemberOrderReq) {
		String payOrderId = null;
		/* 判断签名流水是否生成了支付订单，放置重复提交 */
		HyUserBuyMemberCriteria sql = new HyUserBuyMemberCriteria();
		sql.or().andFromSignEqualTo(createUserBuyMemberOrderReq.getFromSign());
		List<HyUserBuyMember> list = hyUserBuyMemberMapper.selectByExample(sql);
		if (CollectionUtil.isEmpty(list)) {
			/* 如果没有产生，则产生一笔 */
			String buyOrderId = UUIDUtil.genId32();
			CreatePaymentOrderReq createPaymentOrderReq = new CreatePaymentOrderReq();
			BeanUtils.copyProperties(createUserBuyMemberOrderReq, createPaymentOrderReq);
			createPaymentOrderReq.setSourceNo(buyOrderId);
			createPaymentOrderReq.setBusiType(BusiType.PAY_FOR_MEMBER.getValue());
			payOrderId = paymentBusiSV.createPaymentOrder(createPaymentOrderReq);
			HyUserBuyMember record = new HyUserBuyMember();
			record.setBuyMonths(createUserBuyMemberOrderReq.getBuyMonths());
			record.setBuyDate(DateUtil.getSysDate());
			record.setBuyOrderId(buyOrderId);
			record.setFromSign(createUserBuyMemberOrderReq.getFromSign());
			record.setPayAmount(createUserBuyMemberOrderReq.getPayAmount());
			record.setPayOrderId(payOrderId);
			record.setStatus("10");
			record.setUserId(createUserBuyMemberOrderReq.getUserId());
			hyUserBuyMemberMapper.insert(record);
		} else {
			/* 如果已经提交了申请，则更新支付金额等信息 */
			HyUserBuyMember record = list.get(0);
			record.setBuyMonths(createUserBuyMemberOrderReq.getBuyMonths());
			record.setBuyDate(DateUtil.getSysDate());
			record.setPayAmount(createUserBuyMemberOrderReq.getPayAmount());
			hyUserBuyMemberMapper.updateByPrimaryKeySelective(record);

			HyPaymentOrder payOrder = paymentBusiSV.getHyPaymentOrder(record.getPayOrderId());
			if (payOrder != null) {
				payOrder.setPayAmount(createUserBuyMemberOrderReq.getPayAmount());
				payOrder.setPayDate(DateUtil.getSysDate());
				paymentBusiSV.updateByPrimaryKeySelective(payOrder);
			}
			payOrderId = record.getPayOrderId();
		}
		return payOrderId;
	}

	@Override
	public HyUserBuyMember getHyUserBuyMember(String buyOrderId) {
		return hyUserBuyMemberMapper.selectByPrimaryKey(buyOrderId);
	}

	@Override
	public HyUserBuyHb getHyUserBuyHb(String buyOrderId) {
		return hyUserBuyHbMapper.selectByPrimaryKey(buyOrderId);
	}

	@Override
	public void resetUsersInRedis() {
		HyUserCriteria sql = new HyUserCriteria();
		List<HyUser> list = hyUserMapper.selectByExample(sql);
		if (CollectionUtil.isEmpty(list)) {
			return;
		}
		for (HyUser u : list) {
			this.storeUserInfo2Redis(u.getUserId());
		}

	}

	@Override
	public void batchCreateInviteCodeForAllUsers() {
		HyUserCriteria sql = new HyUserCriteria();
		List<HyUser> list = hyUserMapper.selectByExample(sql);
		if (CollectionUtil.isEmpty(list)) {
			return;
		}
		for (HyUser u : list) {
			this.createInviteCodes(u.getUserId());
		}

	}

	@Override
	public UserViewInfo queryUserViewByMobilePhone(String mobilePhone) {
		HyUserCriteria sql = new HyUserCriteria();
		sql.or().andMobilePhoneEqualTo(mobilePhone);
		List<HyUser> list = hyUserMapper.selectByExample(sql);
		if (CollectionUtil.isEmpty(list)) {
			return null;
		}
		HyUser hyUser = list.get(0);
		UserViewInfo userInfo = this.convert(hyUser);
		return userInfo;
	}

}
