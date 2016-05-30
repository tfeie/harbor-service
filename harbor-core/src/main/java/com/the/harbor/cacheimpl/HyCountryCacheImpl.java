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
import com.the.harbor.commons.redisdata.def.HyCountryVo;
import com.the.harbor.commons.redisdata.def.RedisDataKey;
import com.the.harbor.commons.util.BeanUtils;
import com.the.harbor.commons.util.CollectionUtil;
import com.the.harbor.dao.mapper.bo.HyCountry;
import com.the.harbor.service.interfaces.IBasicDataSV;

@Component
public class HyCountryCacheImpl extends AbstractCache {

	@Autowired
	private transient IBasicDataSV basicDataSV;

	@Override
	public void write() throws Exception {
		List<HyCountry> countries = basicDataSV.getAllHyCountries();
		if (CollectionUtil.isEmpty(countries)) {
			return;
		}
		ICacheClient client = CacheFactory.getClient();
		List<HyCountryVo> l = new ArrayList<HyCountryVo>();
		for (HyCountry o : countries) {
			HyCountryVo bo = new HyCountryVo();
			BeanUtils.copyProperties(bo, o);
			l.add(bo);
			client.hset(RedisDataKey.KEY_SINGLE_COUNTRY.getKey(), bo.getCountryCode(), JSONObject.toJSONString(bo));
		}
		client.set(RedisDataKey.KEY_ALL_COUNTRIES.getKey(), JSON.toJSONString(l));
	}

}
