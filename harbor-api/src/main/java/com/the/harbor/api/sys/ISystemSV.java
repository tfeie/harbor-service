package com.the.harbor.api.sys;

import com.the.harbor.api.sys.param.MQRecord;
import com.the.harbor.base.exception.BusinessException;
import com.the.harbor.base.exception.SystemException;
import com.the.harbor.base.vo.Response;

public interface ISystemSV {

	/**
	 * 处理MQ的消息记录
	 * 
	 * @param mqRecord
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	Response processMQ(MQRecord mqRecord) throws BusinessException, SystemException;

}
