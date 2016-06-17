package com.the.harbor.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.the.harbor.dao.mapper.interfaces.HyGoDetailMapper;
import com.the.harbor.dao.mapper.interfaces.HyGoMapper;
import com.the.harbor.dao.mapper.interfaces.HyGoTagsMapper;
import com.the.harbor.service.interfaces.IGoBusiSV;

@Component
@Transactional
public class GoBusiSVImpl implements IGoBusiSV {

	@Autowired
	private transient HyGoDetailMapper hyGoDetailMapper;

	@Autowired
	private transient HyGoTagsMapper hyGoTagsMapper;

	@Autowired
	private transient HyGoMapper hyGoMapper;

}
