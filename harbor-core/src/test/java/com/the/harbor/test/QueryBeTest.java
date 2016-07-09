package com.the.harbor.test;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;

import com.alibaba.fastjson.JSON;
import com.the.harbor.api.be.impl.BeSVImpl;
import com.the.harbor.api.be.param.QueryOneBeReq;
import com.the.harbor.api.be.param.QueryOneBeResp;
import com.the.harbor.commons.components.elasticsearch.ElasticSearchFactory;
import com.the.harbor.commons.indices.def.HarborIndex;
import com.the.harbor.commons.indices.def.HarborIndexType;

public class QueryBeTest {

	public static void main(String[] args) {
		SortBuilder sortBuilder = SortBuilders.fieldSort("createDate").order(SortOrder.DESC);
		BoolQueryBuilder builder = QueryBuilders.boolQuery(); 
		SearchResponse response = ElasticSearchFactory.getClient().prepareSearch(HarborIndex.HY_BE_DB.getValue())
				.setTypes(HarborIndexType.HY_BE.getValue()).setFrom(1).setSize(4).setQuery(builder)
				.addSort(sortBuilder).execute().actionGet();
		SearchHits hits = response.getHits();
		for (SearchHit hit : hits) {
			System.out.println(hit.getSourceAsString());
		}

	}

}
