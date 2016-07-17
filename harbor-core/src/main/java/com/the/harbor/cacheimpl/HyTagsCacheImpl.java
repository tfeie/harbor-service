package com.the.harbor.cacheimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.the.harbor.base.enumeration.hytags.ScopeType;
import com.the.harbor.base.enumeration.hytags.ShowFlag;
import com.the.harbor.base.enumeration.hytags.TagType;
import com.the.harbor.commons.cache.base.AbstractCache;
import com.the.harbor.commons.components.redis.CacheFactory;
import com.the.harbor.commons.components.redis.interfaces.ICacheClient;
import com.the.harbor.commons.redisdata.def.HyTagVo;
import com.the.harbor.commons.redisdata.def.RedisDataKey;
import com.the.harbor.commons.util.BeanUtils;
import com.the.harbor.commons.util.CollectionUtil;
import com.the.harbor.dao.mapper.bo.HyTags;
import com.the.harbor.service.interfaces.IBasicDataSV;

@Component
public class HyTagsCacheImpl extends AbstractCache {

	@Autowired
	private transient IBasicDataSV basicDataSV;

	@Override
	public void write() throws Exception {
		List<HyTags> tags = basicDataSV.getAllHyTags();
		if (CollectionUtil.isEmpty(tags)) {
			return;
		}
		ICacheClient client = CacheFactory.getClient();
		client.del(RedisDataKey.KEY_BASE_INTEREST_TAGS.getKey());
		client.del(RedisDataKey.KEY_BASE_SKILL_TAGS.getKey());
		client.del(RedisDataKey.KEY_GO_TAGS.getKey());
		client.del(RedisDataKey.KEY_BE_TAGS.getKey());
		client.del(RedisDataKey.KEY_BE_INDEX_PAGE_TAGS.getKey());
		client.del(RedisDataKey.KEY_GO_INDEX_PAGE_TAGS.getKey());
		List<HyTagVo> skillTags = new ArrayList<HyTagVo>();
		List<HyTagVo> interestTags = new ArrayList<HyTagVo>();
		List<HyTagVo> goTags = new ArrayList<HyTagVo>();
		List<HyTagVo> beTags = new ArrayList<HyTagVo>();
		List<HyTagVo> beIndexTags = new ArrayList<HyTagVo>();
		List<HyTagVo> goIndexTags = new ArrayList<HyTagVo>();
		for (HyTags o : tags) {
			HyTagVo bo = new HyTagVo();
			BeanUtils.copyProperties(bo, o);
			if (TagType.INTEREST.getValue().equals(o.getTagType())
					&& ScopeType.USER.getValue().equals(o.getScopeType())) {
				interestTags.add(bo);
			} else if (TagType.SKILL.getValue().equals(o.getTagType())
					&& ScopeType.USER.getValue().equals(o.getScopeType())) {
				skillTags.add(bo);
			} else if (TagType.GO.getValue().equals(o.getTagType())
					&& ScopeType.GO.getValue().equals(o.getScopeType())) {
				if (ShowFlag.ACTUAL_PAGE.getValue().equals(o.getShowFlag())) {
					goTags.add(bo);
				} else if (ShowFlag.INDEX_PAGE.getValue().equals(o.getShowFlag())) {
					goIndexTags.add(bo);
				}
			} else if (TagType.BE.getValue().equals(o.getTagType())
					&& ScopeType.BE.getValue().equals(o.getScopeType())) {
				if (ShowFlag.ACTUAL_PAGE.getValue().equals(o.getShowFlag())) {
					beTags.add(bo);
				} else if (ShowFlag.INDEX_PAGE.getValue().equals(o.getShowFlag())) {
					beIndexTags.add(bo);
				}

			}
		}
		client.set(RedisDataKey.KEY_BASE_INTEREST_TAGS.getKey(), JSON.toJSONString(interestTags));
		client.set(RedisDataKey.KEY_BASE_SKILL_TAGS.getKey(), JSON.toJSONString(skillTags));
		client.set(RedisDataKey.KEY_GO_TAGS.getKey(), JSON.toJSONString(goTags));
		client.set(RedisDataKey.KEY_BE_TAGS.getKey(), JSON.toJSONString(beTags));
		client.set(RedisDataKey.KEY_BE_INDEX_PAGE_TAGS.getKey(), JSON.toJSONString(beIndexTags));
		client.set(RedisDataKey.KEY_GO_INDEX_PAGE_TAGS.getKey(), JSON.toJSONString(goIndexTags));
	}

}
