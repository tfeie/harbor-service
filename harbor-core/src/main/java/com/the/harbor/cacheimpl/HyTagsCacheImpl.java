package com.the.harbor.cacheimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.the.harbor.base.enumeration.hytags.IsPoly;
import com.the.harbor.base.enumeration.hytags.ScopeType;
import com.the.harbor.base.enumeration.hytags.TagType;
import com.the.harbor.commons.cache.base.AbstractCache;
import com.the.harbor.commons.components.redis.CacheFactory;
import com.the.harbor.commons.components.redis.interfaces.ICacheClient;
import com.the.harbor.commons.redisdata.def.HyTagVo;
import com.the.harbor.commons.redisdata.def.RedisDataKey;
import com.the.harbor.commons.util.BeanUtils;
import com.the.harbor.commons.util.CollectionUtil;
import com.the.harbor.commons.util.StringUtil;
import com.the.harbor.dao.mapper.bo.HyTags;
import com.the.harbor.dao.mapper.interfaces.HyTagsMapper;
import com.the.harbor.service.interfaces.IBasicDataSV;

@Component
public class HyTagsCacheImpl extends AbstractCache {

	@Autowired
	HyTagsMapper m;

	@Override
	public void write() throws Exception {
		List<HyTagVo> list = new ArrayList<HyTagVo>();
		ICacheClient client = CacheFactory.getClient();
		String data = client.get(RedisDataKey.KEY_BASE_INTEREST_TAGS.getKey());
		if (!StringUtil.isBlank(data)) {
			List<HyTagVo> l = JSONObject.parseArray(data, HyTagVo.class);
			list.addAll(l);
		}
		data = client.get(RedisDataKey.KEY_BASE_SKILL_TAGS.getKey());
		if (!StringUtil.isBlank(data)) {
			List<HyTagVo> l = JSONObject.parseArray(data, HyTagVo.class);
			list.addAll(l);
		}
		data = client.get(RedisDataKey.KEY_GO_TAGS.getKey());
		if (!StringUtil.isBlank(data)) {
			List<HyTagVo> l = JSONObject.parseArray(data, HyTagVo.class);
			list.addAll(l);
		}
		data = client.get(RedisDataKey.KEY_BE_TAGS.getKey());
		if (!StringUtil.isBlank(data)) {
			List<HyTagVo> l = JSONObject.parseArray(data, HyTagVo.class);
			list.addAll(l);
		}
		data = client.get(RedisDataKey.KEY_BE_INDEX_PAGE_TAGS.getKey());
		if (!StringUtil.isBlank(data)) {
			List<HyTagVo> l = JSONObject.parseArray(data, HyTagVo.class);
			list.addAll(l);
		}
		for (HyTagVo bo : list) {
			HyTags record = new HyTags();
			BeanUtils.copyProperties(bo, record);
			m.insert(record);
		}
	}

}
