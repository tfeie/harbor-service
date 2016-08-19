package com.the.harbor.cacheimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.the.harbor.commons.cache.base.AbstractCache;
import com.the.harbor.service.interfaces.IUserManagerSV;

@Component
public class HyUserCacheImpl extends AbstractCache {

	@Autowired
	private transient IUserManagerSV userManagerSV;

	@Override
	public void write() throws Exception {
		userManagerSV.resetUsersInRedis();
	}

}
