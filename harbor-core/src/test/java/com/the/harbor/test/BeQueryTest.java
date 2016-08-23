package com.the.harbor.test;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.the.harbor.commons.components.elasticsearch.ElasticSearchFactory;
import com.the.harbor.commons.indices.def.HarborIndex;
import com.the.harbor.commons.indices.def.HarborIndexType;

public class BeQueryTest {

	public static void main(String[] args) {
		BoolQueryBuilder builder = QueryBuilders.boolQuery();
		int start = 0;
		int end = 1;
		SearchResponse response = ElasticSearchFactory.getClient().prepareSearch(HarborIndex.HY_BE_DB.getValue())
				.setTypes(HarborIndexType.HY_BE.getValue()).setFrom(start).setSize(end - start).setQuery(builder)
				.execute().actionGet();
		SearchHits hits = response.getHits();
		JSONArray a = new JSONArray();
		for (SearchHit hit : hits) {
			JSONObject d = JSONObject.parseObject(hit.getSourceAsString());
			a.add(d);
		}
		
		System.out.println(a.toJSONString());
	}

}
