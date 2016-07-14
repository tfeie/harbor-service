package com.the.harbor.api.be;

import javax.validation.constraints.NotNull;

import com.the.harbor.api.be.param.BeCreateReq;
import com.the.harbor.api.be.param.BeCreateResp;
import com.the.harbor.api.be.param.BeQueryReq;
import com.the.harbor.api.be.param.BeQueryResp;
import com.the.harbor.api.be.param.GiveHBReq;
import com.the.harbor.api.be.param.QueryMyBeReq;
import com.the.harbor.api.be.param.QueryMyBeResp;
import com.the.harbor.api.be.param.QueryMyFavorBeReq;
import com.the.harbor.api.be.param.QueryMyFavorBeResp;
import com.the.harbor.api.be.param.QueryOneBeReq;
import com.the.harbor.api.be.param.QueryOneBeResp;
import com.the.harbor.base.exception.BusinessException;
import com.the.harbor.base.exception.SystemException;
import com.the.harbor.base.vo.Response;

public interface IBeSV {

	@interface CreateBe {

	}

	@interface QueryMyBe {

	}

	@interface QueryOneBe {

	}

	@interface GiveHaibei {

	}

	@interface QueryMyFavorBe {

	}

	/**
	 * 发布一个BE
	 * 
	 * @param beCreateReq
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	BeCreateResp createBe(@NotNull(message = "参数为空") BeCreateReq beCreateReq) throws BusinessException, SystemException;

	/**
	 * 查询我的BE信息
	 * 
	 * @param queryMyBeReq
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	QueryMyBeResp queryMyBe(@NotNull(message = "参数为空") QueryMyBeReq queryMyBeReq)
			throws BusinessException, SystemException;

	/**
	 * 查询我收藏的BE
	 * 
	 * @param queryMyFavorBeReq
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	QueryMyFavorBeResp queryMyFavorBe(@NotNull(message = "参数为空") QueryMyFavorBeReq queryMyFavorBeReq)
			throws BusinessException, SystemException;

	/**
	 * 查询一个BE
	 * 
	 * @param queryOneBeReq
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	QueryOneBeResp queryOneBe(@NotNull(message = "参数为空") QueryOneBeReq queryOneBeReq)
			throws BusinessException, SystemException;

	/**
	 * 查询BE列表
	 * 
	 * @param beQueryReq
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	BeQueryResp queryBes(@NotNull(message = "参数为空") BeQueryReq beQueryReq) throws BusinessException, SystemException;

	/**
	 * 打赏海贝
	 * 
	 * @param giveHBReq
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	Response giveHaibei(@NotNull(message = "参数为空") GiveHBReq giveHBReq) throws BusinessException, SystemException;

}
