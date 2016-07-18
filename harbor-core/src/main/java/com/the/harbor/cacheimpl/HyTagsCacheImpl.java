package com.the.harbor.cacheimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
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
		client.del(RedisDataKey.KEY_ONO_TAGS.getKey());
		client.del(RedisDataKey.KEY_GROUP_TAGS.getKey());
		client.del(RedisDataKey.KEY_BE_TAGS.getKey());
		client.del(RedisDataKey.KEY_BE_INDEX_PAGE_TAGS.getKey());
		client.del(RedisDataKey.KEY_GROUP_INDEX_PAGE_TAGS.getKey());
		client.del(RedisDataKey.KEY_ONO_INDEX_PAGE_TAGS.getKey());
		List<HyTagVo> skillTags = new ArrayList<HyTagVo>();
		List<HyTagVo> interestTags = new ArrayList<HyTagVo>();
		List<HyTagVo> goTags = new ArrayList<HyTagVo>();
		List<HyTagVo> groupTags = new ArrayList<HyTagVo>();
		List<HyTagVo> onoTags = new ArrayList<HyTagVo>();
		List<HyTagVo> beTags = new ArrayList<HyTagVo>();
		List<HyTagVo> beIndexTags = new ArrayList<HyTagVo>();
		List<HyTagVo> groupIndexTags = new ArrayList<HyTagVo>();
		List<HyTagVo> onoIndexTags = new ArrayList<HyTagVo>();
		for (HyTags o : tags) {
			HyTagVo bo = new HyTagVo();
			BeanUtils.copyProperties(bo, o);
			if (TagType.INTEREST.getValue().equals(o.getTagType())
					&& ScopeType.USER.getValue().equals(o.getScopeType())) {
				interestTags.add(bo);
			} else if (TagType.SKILL.getValue().equals(o.getTagType())
					&& ScopeType.USER.getValue().equals(o.getScopeType())) {
				skillTags.add(bo);
			} else if (TagType.ONO.getValue().equals(o.getTagType())
					&& ScopeType.ONO.getValue().equals(o.getScopeType())) {
				if (IsPoly.YES.getValue().equals(o.getIsPoly())) {
					onoIndexTags.add(bo);
				} else {
					onoTags.add(bo);
				}
				goTags.add(bo);
			} else if (TagType.GROUP.getValue().equals(o.getTagType())
					&& ScopeType.GROUP.getValue().equals(o.getScopeType())) {
				if (IsPoly.YES.getValue().equals(o.getIsPoly())) {
					groupIndexTags.add(bo);
				} else {
					groupTags.add(bo);
				}
				goTags.add(bo);
			} else if (TagType.BE.getValue().equals(o.getTagType())
					&& ScopeType.BE.getValue().equals(o.getScopeType())) {
				if (IsPoly.YES.getValue().equals(o.getIsPoly())) {
					beIndexTags.add(bo);
				} else {
					beTags.add(bo);
				}
				
			}
		}
		client.set(RedisDataKey.KEY_BASE_INTEREST_TAGS.getKey(), JSON.toJSONString(interestTags));
		client.set(RedisDataKey.KEY_BASE_SKILL_TAGS.getKey(), JSON.toJSONString(skillTags));
		client.set(RedisDataKey.KEY_GO_TAGS.getKey(), JSON.toJSONString(goTags));
		client.set(RedisDataKey.KEY_BE_TAGS.getKey(), JSON.toJSONString(beTags));
		client.set(RedisDataKey.KEY_BE_INDEX_PAGE_TAGS.getKey(), JSON.toJSONString(beIndexTags));
		client.set(RedisDataKey.KEY_GROUP_TAGS.getKey(), JSON.toJSONString(groupTags));
		client.set(RedisDataKey.KEY_ONO_TAGS.getKey(), JSON.toJSONString(onoTags));
		client.set(RedisDataKey.KEY_GROUP_INDEX_PAGE_TAGS.getKey(), JSON.toJSONString(groupIndexTags));
		client.set(RedisDataKey.KEY_ONO_INDEX_PAGE_TAGS.getKey(), JSON.toJSONString(onoIndexTags));

	}

}
