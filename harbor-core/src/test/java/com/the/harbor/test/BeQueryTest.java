package com.the.harbor.test;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;

import com.the.harbor.commons.components.elasticsearch.ElasticSearchFactory;
import com.the.harbor.commons.indices.def.HarborIndex;
import com.the.harbor.commons.indices.def.HarborIndexType;

public class BeQueryTest {

	public static void main(String[] args) {
		SortBuilder sortBuilder = SortBuilders.fieldSort("createDate").order(SortOrder.DESC);
		BoolQueryBuilder builder = QueryBuilders.boolQuery();
		builder.must(QueryBuilders.termQuery("userId", "hy00000062"));
		builder.must(
				QueryBuilders.termQuery("status", com.the.harbor.base.enumeration.common.Status.VALID.getValue()));
		SearchResponse response = ElasticSearchFactory.getClient().prepareSearch(HarborIndex.HY_BE_DB.getValue())
				.setTypes(HarborIndexType.HY_BE.getValue()).setFrom(0).setSize(10).setQuery(builder)
				.addSort(sortBuilder).execute().actionGet();
		SearchHits hits = response.getHits();
		long total = hits.getTotalHits();
		System.out.println(total);
	}

}
