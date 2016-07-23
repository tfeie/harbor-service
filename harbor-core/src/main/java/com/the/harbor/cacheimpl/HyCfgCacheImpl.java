package com.the.harbor.cacheimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.the.harbor.commons.cache.base.AbstractCache;
import com.the.harbor.commons.components.redis.CacheFactory;
import com.the.harbor.commons.components.redis.interfaces.ICacheClient;
import com.the.harbor.commons.redisdata.def.RedisDataKey;
import com.the.harbor.commons.util.CollectionUtil;
import com.the.harbor.dao.mapper.bo.HyCfg;
import com.the.harbor.service.interfaces.IBasicDataSV;

@Component
public class HyCfgCacheImpl extends AbstractCache {

	@Autowired
	private transient IBasicDataSV basicDataSV;

	@Override
	public void write() throws Exception {
		List<HyCfg> cfgs = basicDataSV.getAllCfgs();
		if (CollectionUtil.isEmpty(cfgs)) {
			return;
		}
		ICacheClient client = CacheFactory.getClient();
		client.del(RedisDataKey.KEY_CFG.getKey());
		for (HyCfg o : cfgs) {
			client.hset(RedisDataKey.KEY_CFG.getKey(), o.getCfgKey(), o.getCfgValue());
		}
	}

}
