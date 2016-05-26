package com.the.harbor.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
        HyUser hyUser = this.getUserByWeixin(userRegReq.getWeixin());
        if (hyUser != null) {
            throw new BusinessException(HarborErrorCodeConstants.WEIXIN_BOUND, "您的微信账号已经注册");
        }
        HyUser user = new HyUser();
        user.setUserId(HarborSeqUtil.createHyUserId());
        user.setUserType(UserType.ORDINARY_USER.getValue());
        user.setHyId(user.getHyId());
        user.setEnName(userRegReq.getEnName());
        user.setSex(userRegReq.getSex());
        user.setHeadIcon(userRegReq.getHeadIcon());
        user.setAbroadCountry(userRegReq.getAbroadCountry());
        user.setAbroadUniversity(userRegReq.getAbroadUniversity());
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
    public HyUser getUserByWeixin(String weixin) {
        if (StringUtil.isBlank(weixin)) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "微信号不能为空");
        }
        HyUserCriteria sql = new HyUserCriteria();
        sql.or().andWeixinEqualTo(weixin);
        List<HyUser> users = hyUserMapper.selectByExample(sql);
        return CollectionUtil.isEmpty(users) ? null : users.get(0);
    }

}
