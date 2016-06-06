package com.the.harbor.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.the.harbor.api.user.param.UserCertificationReq;
import com.the.harbor.api.user.param.UserRegReq;
import com.the.harbor.base.constants.ExceptCodeConstants;
import com.the.harbor.base.enumeration.hyuser.AccessPermission;
import com.the.harbor.base.enumeration.hyuser.IsMember;
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
import com.the.harbor.dao.mapper.interfaces.HyUserMapper;
import com.the.harbor.service.interfaces.IUserManagerSV;
import com.the.harbor.util.HarborSeqUtil;

@Component
@Transactional
public class UserManagerSVImpl implements IUserManagerSV {

	@Autowired
	private transient HyUserMapper hyUserMapper;

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
		user.setIsMember(IsMember.NO.getValue());
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

}
