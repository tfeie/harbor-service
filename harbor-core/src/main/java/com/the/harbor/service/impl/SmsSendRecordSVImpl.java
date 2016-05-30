package com.the.harbor.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.the.harbor.dao.mapper.bo.HySmsSend;
import com.the.harbor.dao.mapper.interfaces.HySmsSendMapper;
import com.the.harbor.service.interfaces.ISmsSendRecordSV;

@Component
@Transactional
public class SmsSendRecordSVImpl implements ISmsSendRecordSV {

	@Autowired
	private transient HySmsSendMapper hySmsSendMapper;

	@Override
	public void saveSmsSendRecord(HySmsSend record) {
		if (record == null) {
			return;
		}
		try {
			hySmsSendMapper.insert(record);
		} catch (Exception ex) {
			//
		}

	}

}
