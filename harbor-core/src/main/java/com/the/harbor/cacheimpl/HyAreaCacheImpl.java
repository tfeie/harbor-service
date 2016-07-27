package com.the.harbor.cacheimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.the.harbor.base.enumeration.hyarea.AreaLevel;
import com.the.harbor.commons.cache.base.AbstractCache;
import com.the.harbor.commons.components.redis.CacheFactory;
import com.the.harbor.commons.components.redis.interfaces.ICacheClient;
import com.the.harbor.commons.redisdata.def.HyAreaVo;
import com.the.harbor.commons.redisdata.def.RedisDataKey;
import com.the.harbor.commons.util.BeanUtils;
import com.the.harbor.commons.util.CollectionUtil;
import com.the.harbor.dao.mapper.bo.HyArea;
import com.the.harbor.service.interfaces.IBasicDataSV;

@Component
public class HyAreaCacheImpl extends AbstractCache {

	@Autowired
	private transient IBasicDataSV basicDataSV;

	@Override
	public void write() throws Exception {
		List<HyArea> areas = basicDataSV.getAllAreas();
		if (CollectionUtil.isEmpty(areas)) {
			return;
		}
		ICacheClient client = CacheFactory.getClient();
		client.del(RedisDataKey.KEY_ALL_AREA.getKey());
		client.del(RedisDataKey.KEY_ALL_PROVINCE.getKey());
		client.del(RedisDataKey.KEY_CITY.getKey());
		List<HyAreaVo> provices = new ArrayList<HyAreaVo>();
		Map<String, List<HyAreaVo>> cityMap = new HashMap<String, List<HyAreaVo>>();
		for (HyArea o : areas) {
			HyAreaVo bo = new HyAreaVo();
			BeanUtils.copyProperties(bo, o);

			client.hset(RedisDataKey.KEY_ALL_AREA.getKey(), o.getAreaCode(), JSON.toJSONString(bo));
			if (AreaLevel.PROVICE.getValue().equals(o.getAreaLevel())) {
				provices.add(bo);
			} else if (AreaLevel.CITY.getValue().equals(o.getAreaLevel())) {
				String parentAreaCode = o.getParentAreaCode();
				if (cityMap.containsKey(parentAreaCode)) {
					List<HyAreaVo> cities = cityMap.get(parentAreaCode);
					cities.add(bo);
				} else {
					List<HyAreaVo> cities = new ArrayList<HyAreaVo>();
					cities.add(bo);
					cityMap.put(parentAreaCode, cities);
				}
			}
		}
		client.set(RedisDataKey.KEY_ALL_PROVINCE.getKey(), JSON.toJSONString(provices));
		for (String proviceCode : cityMap.keySet()) {
			client.hset(RedisDataKey.KEY_CITY.getKey(), proviceCode, JSON.toJSONString(cityMap.get(proviceCode)));
		}
	}

}
