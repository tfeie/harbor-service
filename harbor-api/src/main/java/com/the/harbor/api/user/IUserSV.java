package com.the.harbor.api.user;

import com.the.harbor.api.user.param.UserRegReq;
import com.the.harbor.base.exception.BusinessException;
import com.the.harbor.base.exception.SystemException;
import com.the.harbor.base.vo.Response;

public interface IUserSV {

    @interface UserRegister {
    }

    /**
     * 用户注册
     * 
     * @param userRegReq
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author zhangchao
     */
    Response userRegister(UserRegReq userRegReq) throws BusinessException, SystemException;

}
