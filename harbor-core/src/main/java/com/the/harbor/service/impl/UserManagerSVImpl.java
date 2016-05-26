package com.the.harbor.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.the.harbor.api.user.param.UserRegReq;
import com.the.harbor.base.exception.BusinessException;
import com.the.harbor.commons.constants.ExceptCodeConstants;
import com.the.harbor.commons.util.CollectionUtil;
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
        
        return null;
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
