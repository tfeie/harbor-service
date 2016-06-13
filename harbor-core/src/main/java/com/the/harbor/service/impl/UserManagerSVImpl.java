package com.the.harbor.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.the.harbor.api.user.param.UserCertificationReq;
import com.the.harbor.api.user.param.UserMemberInfo;
import com.the.harbor.api.user.param.UserRegReq;
import com.the.harbor.api.user.param.UserSystemTagQueryReq;
import com.the.harbor.api.user.param.UserSystemTagQueryResp;
import com.the.harbor.api.user.param.UserTag;
import com.the.harbor.base.constants.ExceptCodeConstants;
import com.the.harbor.base.enumeration.hytags.Status;
import com.the.harbor.base.enumeration.hytags.TagType;
import com.the.harbor.base.enumeration.hyuser.AccessPermission;
import com.the.harbor.base.enumeration.hyuser.MemberLevel;
import com.the.harbor.base.enumeration.hyuser.UserStatus;
import com.the.harbor.base.enumeration.hyuser.UserType;
import com.the.harbor.base.exception.BusinessException;
import com.the.harbor.base.exception.SystemException;
import com.the.harbor.commons.util.CollectionUtil;
import com.the.harbor.commons.util.DateUtil;
import com.the.harbor.commons.util.StringUtil;
import com.the.harbor.constants.HarborErrorCodeConstants;
import com.the.harbor.dao.mapper.bo.HyUser;
import com.the.harbor.dao.mapper.bo.HyUserCriteria;
import com.the.harbor.dao.mapper.bo.HyUserTags;
import com.the.harbor.dao.mapper.bo.HyUserTagsCriteria;
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
		user.setWxHeadimg(userRegReq.getWxHeadimg());
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

	@Override
	public HyUser getUserByWeixin(String wxOpenId) {
		if (StringUtil.isBlank(wxOpenId)) {
			throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "微信OPEN_ID不能为空");
		}
		HyUserCriteria sql = new HyUserCriteria();
		sql.or().andWxOpenidEqualTo(wxOpenId);
		List<HyUser> users = hyUserMapper.selectByExample(sql);
		return CollectionUtil.isEmpty(users) ? null : users.get(0);
	}

	public HyUser getUserInfo(String userId) {
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
		for (UserTag userTag : systemTags) {
			// 判断标签ID是否存在
			HyUserTags hyUserTag = this.getHyUserTag(userTag.getTagId(), userId);
			if (hyUserTag != null) {
				hyUserTag.setStatus(Status.VALID.getValue());
				hyUserTagsMapper.updateByPrimaryKey(hyUserTag);
			} else {
				hyUserTag = new HyUserTags();
				hyUserTag.setRecordId(HarborSeqUtil.createHyUserTagsRecordId());
				hyUserTag.setSortId(99);
				hyUserTag.setTagId(userTag.getTagId());
				hyUserTag.setStatus(Status.VALID.getValue());
				hyUserTag.setTagCat(userTag.getTagCat());
				hyUserTag.setTagName(userTag.getTagName());
				hyUserTag.setTagType(userTag.getTagType());
				hyUserTag.setUserId(userId);
				hyUserTagsMapper.insert(hyUserTag);
			}
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

}
