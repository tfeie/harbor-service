package com.the.harbor.cacheimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.the.harbor.commons.cache.base.AbstractCache;
import com.the.harbor.commons.components.redis.CacheFactory;
import com.the.harbor.commons.components.redis.interfaces.ICacheClient;
import com.the.harbor.commons.redisdata.def.HyDictsVo;
import com.the.harbor.commons.redisdata.def.RedisDataKey;
import com.the.harbor.commons.redisdata.util.HyDictUtil;
import com.the.harbor.commons.util.BeanUtils;
import com.the.harbor.commons.util.CollectionUtil;
import com.the.harbor.dao.mapper.bo.HyDicts;
import com.the.harbor.service.interfaces.IBasicDataSV;

@Component
public class HyDictsCacheImpl extends AbstractCache {

	@Autowired
	private transient IBasicDataSV basicDataSV;

	@Override
	public void write() throws Exception {
		List<HyDicts> tags = basicDataSV.getAllHyDicts();
		if (CollectionUtil.isEmpty(tags)) {
			return;
		}
		ICacheClient client = CacheFactory.getClient();

		Map<String, List<HyDictsVo>> m = new HashMap<String, List<HyDictsVo>>();
		for (HyDicts o : tags) {
			HyDictsVo bo = new HyDictsVo();
			BeanUtils.copyProperties(bo, o);
			String key = o.getTypeCode() + HyDictUtil.SPLIT + o.getParamCode();
			if (!m.containsKey(key)) {
				List<HyDictsVo> list = new ArrayList<HyDictsVo>();
				m.put(key, list);
			}
			m.get(key).add(bo);

			String key2 = o.getTypeCode() + HyDictUtil.SPLIT + o.getParamCode() + HyDictUtil.SPLIT + o.getParamValue();
			client.hset(RedisDataKey.KEY_SINGLE_DICT.getKey(), key2, JSON.toJSONString(bo));
		}
		for (String key : m.keySet()) {
			List<HyDictsVo> dicts = m.get(key);
			client.hset(RedisDataKey.KEY_ALL_DICTS.getKey(), key, JSON.toJSONString(dicts));
		}
	}

}
