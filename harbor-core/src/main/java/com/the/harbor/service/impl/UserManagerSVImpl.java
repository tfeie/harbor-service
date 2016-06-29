package com.the.harbor.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.the.harbor.api.user.param.DoUserFans;
import com.the.harbor.api.user.param.DoUserFriend;
import com.the.harbor.api.user.param.UserCertificationReq;
import com.the.harbor.api.user.param.UserEditReq;
import com.the.harbor.api.user.param.UserMemberInfo;
import com.the.harbor.api.user.param.UserMemberRenewalReq;
import com.the.harbor.api.user.param.UserMemberRenewalResp;
import com.the.harbor.api.user.param.UserRegReq;
import com.the.harbor.api.user.param.UserSystemTagQueryReq;
import com.the.harbor.api.user.param.UserSystemTagQueryResp;
import com.the.harbor.api.user.param.UserTag;
import com.the.harbor.api.user.param.UserTagQueryReq;
import com.the.harbor.api.user.param.UserTagQueryResp;
import com.the.harbor.api.user.param.UserViewInfo;
import com.the.harbor.base.constants.ExceptCodeConstants;
import com.the.harbor.base.enumeration.common.Status;
import com.the.harbor.base.enumeration.dict.ParamCode;
import com.the.harbor.base.enumeration.dict.TypeCode;
import com.the.harbor.base.enumeration.hytags.TagCat;
import com.the.harbor.base.enumeration.hytags.TagType;
import com.the.harbor.base.enumeration.hyuser.AccessPermission;
import com.the.harbor.base.enumeration.hyuser.MemberLevel;
import com.the.harbor.base.enumeration.hyuser.UserStatus;
import com.the.harbor.base.enumeration.hyuser.UserType;
import com.the.harbor.base.exception.BusinessException;
import com.the.harbor.base.exception.SystemException;
import com.the.harbor.commons.components.globalconfig.GlobalSettings;
import com.the.harbor.commons.redisdata.util.HyCountryUtil;
import com.the.harbor.commons.redisdata.util.HyDictUtil;
import com.the.harbor.commons.redisdata.util.HyIndustryUtil;
import com.the.harbor.commons.redisdata.util.HyUserUtil;
import com.the.harbor.commons.util.CollectionUtil;
import com.the.harbor.commons.util.DateUtil;
import com.the.harbor.commons.util.StringUtil;
import com.the.harbor.constants.HarborErrorCodeConstants;
import com.the.harbor.dao.mapper.bo.HyUser;
import com.the.harbor.dao.mapper.bo.HyUserCriteria;
import com.the.harbor.dao.mapper.bo.HyUserFans;
import com.the.harbor.dao.mapper.bo.HyUserFansCriteria;
import com.the.harbor.dao.mapper.bo.HyUserFriend;
import com.the.harbor.dao.mapper.bo.HyUserFriendCriteria;
import com.the.harbor.dao.mapper.bo.HyUserTags;
import com.the.harbor.dao.mapper.bo.HyUserTagsCriteria;
import com.the.harbor.dao.mapper.interfaces.HyUserFansMapper;
import com.the.harbor.dao.mapper.interfaces.HyUserFriendMapper;
import com.the.harbor.dao.mapper.interfaces.HyUserMapper;
import com.the.harbor.dao.mapper.interfaces.HyUserTagsMapper;
import com.the.harbor.service.interfaces.IUserManagerSV;
import com.the.harbor.util.HarborSeqUtil;

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

	@Override
	public String userRegister(UserRegReq userRegReq) {
		/* 根据微信号判断用户是否已经注册 */
		HyUser hyUser = this.getUserByWeixin(userRegReq.getWxOpenid());
		if (hyUser != null) {
			throw new BusinessException(HarborErrorCodeConstants.WEIXIN_BOUND, "您的微信账号已经注册");
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
		int success = hyUserMapper.insertSelective(user);
		if (success == 0) {
			throw new SystemException("注册失败,请稍候重试");
		}
		return user.getUserId();
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
		int n = hyUserMapper.updateByPrimaryKeySelective(u);
		if (n == 0) {
			throw new SystemException("认证材料提交失败,请稍候重试");
		}
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

	@Override
	public UserMemberRenewalResp userMemberRenewal(UserMemberRenewalReq userMemberRenewalReq) {
		// 校验用户信息
		HyUser hyUser = this.getUserInfo(userMemberRenewalReq.getUserId());
		if (hyUser == null) {
			throw new BusinessException("USER_00001", "传入的用户不存在");
		}
		if (!hyUser.getWxOpenid().equals(userMemberRenewalReq.getOpenId())) {
			throw new BusinessException("USER_00002", "传入的微信openId与实际不符合，无法续期");
		}
		// 获取续期月份
		int payMonth = userMemberRenewalReq.getPayMonth();
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
		// 组织返回
		UserMemberRenewalResp resp = new UserMemberRenewalResp();
		resp.setChName(hyUser.getChName());
		resp.setEnName(hyUser.getEnName());
		resp.setExpDate(DateUtil.getDateString(expDate, DateUtil.DATE_FORMAT));
		resp.setPayMonth(payMonth);
		resp.setUserId(hyUser.getUserId());
		return resp;
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

			userInfo.setAtCityName(hyUser.getAtCity());
			userInfo.setIndustryName(HyIndustryUtil.getHyIndustryName(hyUser.getIndustry()));
			userInfo.setUserTypeName(HyDictUtil.getHyDictDesc(TypeCode.HY_USER.getValue(),
					ParamCode.USER_TYPE.getValue(), hyUser.getAbroadCountry()));
			userInfo.setSexName(HyDictUtil.getHyDictDesc(TypeCode.HY_USER.getValue(), ParamCode.SEX.getValue(),
					hyUser.getAbroadCountry()));
			userInfo.setMaritalStatusName(HyDictUtil.getHyDictDesc(TypeCode.HY_USER.getValue(),
					ParamCode.MARITAL_STATUS.getValue(), hyUser.getAbroadCountry()));
			userInfo.setConstellationName(HyDictUtil.getHyDictDesc(TypeCode.HY_USER.getValue(),
					ParamCode.CONSTELLATION.getValue(), hyUser.getAbroadCountry()));
			if (UserStatus.AUTHORIZED_SUCCESS.equals(hyUser.getUserStatus())) {
				String userStatus = HyDictUtil.getHyDictDesc(TypeCode.HY_USER.getValue(),
						ParamCode.USER_STATUS.getValue(), hyUser.getUserStatus());
				userInfo.setUserStatusName(userStatus);
			} else {
				String userStatus = HyDictUtil.getHyDictDesc(TypeCode.HY_USER.getValue(),
						ParamCode.USER_STATUS.getValue(), UserStatus.UNAUTHORIZED.getValue());
				userInfo.setUserStatusName(userStatus);
			}

		}
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
				hyUserFriendMapper.insert(record);
				// 同意好友申请
				HyUserUtil.userAAgreeApplyFriendofUserB(doUserFriend.getUserId(), doUserFriend.getFriendUserId());
			}
		}

	}

}
