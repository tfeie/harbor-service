package com.the.harbor.cacheimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.the.harbor.commons.cache.base.AbstractCache;
import com.the.harbor.service.interfaces.IBeBusiSV;

@Component
public class HyBeCacheImpl extends AbstractCache {

	@Autowired
	private transient IBeBusiSV beBusiSV;

	@Override
	public void write() throws Exception {
		beBusiSV.resetAllBe2Redis();
	}

}
