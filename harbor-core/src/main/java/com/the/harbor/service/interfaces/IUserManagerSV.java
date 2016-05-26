package com.the.harbor.service.interfaces;

import com.the.harbor.api.user.param.UserRegReq;
import com.the.harbor.dao.mapper.bo.HyUser;

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
     * 根据微信号获取已经注册用户资料
     * 
     * @param weixin
     * @return
     * @author zhangchao
     */
    HyUser getUserByWeixin(String weixin);

}
