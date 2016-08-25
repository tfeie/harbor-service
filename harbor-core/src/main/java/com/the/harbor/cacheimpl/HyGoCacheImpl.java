package com.the.harbor.cacheimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.the.harbor.commons.cache.base.AbstractCache;
import com.the.harbor.service.interfaces.IGoBusiSV;

@Component
public class HyGoCacheImpl extends AbstractCache {

	@Autowired
	private transient IGoBusiSV goBusiSV;

	@Override
	public void write() throws Exception {
		goBusiSV.resetAllGo2Redis();
	}

}
