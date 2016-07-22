package com.the.harbor.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.the.harbor.commons.components.redis.CacheFactory;
import com.the.harbor.commons.components.redis.interfaces.ICacheClient;
import com.the.harbor.commons.redisdata.def.HyTagVo;
import com.the.harbor.commons.redisdata.def.RedisDataKey;
import com.the.harbor.commons.util.StringUtil;
import com.the.harbor.dao.mapper.bo.HyTags;
import com.the.harbor.dao.mapper.interfaces.HyTagsMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class RestoreTags {

	@Autowired
	HyTagsMapper m;

	@Test
	public void build() throws Exception {
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
