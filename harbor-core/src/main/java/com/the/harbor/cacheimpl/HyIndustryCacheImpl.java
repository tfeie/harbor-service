package com.the.harbor.cacheimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.the.harbor.commons.cache.base.AbstractCache;
import com.the.harbor.commons.components.redis.CacheFactory;
import com.the.harbor.commons.components.redis.interfaces.ICacheClient;
import com.the.harbor.commons.redisdata.def.HyIndustryVo;
import com.the.harbor.commons.redisdata.def.RedisDataKey;
import com.the.harbor.commons.util.BeanUtils;
import com.the.harbor.commons.util.CollectionUtil;
import com.the.harbor.dao.mapper.bo.HyIndustry;
import com.the.harbor.service.interfaces.IBasicDataSV;

@Component
public class HyIndustryCacheImpl extends AbstractCache {

	@Autowired
	private transient IBasicDataSV basicDataSV;

	@Override
	public void write() throws Exception {
		List<HyIndustry> list = basicDataSV.getAllHyIndustries();
		if (CollectionUtil.isEmpty(list)) {
			return;
		}
		ICacheClient client = CacheFactory.getClient();
		client.del(RedisDataKey.KEY_SINGLE_INDUSTRY.getKey());
		client.del(RedisDataKey.KEY_ALL_INDUSTRIES.getKey());
		List<HyIndustryVo> l = new ArrayList<HyIndustryVo>();
		for (HyIndustry o : list) {
			HyIndustryVo bo = new HyIndustryVo();
			BeanUtils.copyProperties(bo, o);
			l.add(bo);
			client.hset(RedisDataKey.KEY_SINGLE_INDUSTRY.getKey(), bo.getIndustryCode(), JSONObject.toJSONString(bo));
		}
		client.set(RedisDataKey.KEY_ALL_INDUSTRIES.getKey(), JSON.toJSONString(l));
	}

}
