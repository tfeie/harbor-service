package com.the.harbor.test;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

import com.alibaba.fastjson.JSON;
import com.the.harbor.api.be.param.Be;
import com.the.harbor.api.go.param.Go;
import com.the.harbor.commons.components.elasticsearch.ElasticSearchFactory;
import com.the.harbor.commons.indices.def.HarborIndex;
import com.the.harbor.commons.indices.def.HarborIndexType;
import com.the.harbor.commons.indices.mq.MNSRecord;

public class QueryBeTest {

	public static void main(String[] args) {
		deleteGoIndex();
		deleteBeIndex();
		deleteMNSIndex();

	}

	public static void deleteBeIndex() {
		BoolQueryBuilder builder = QueryBuilders.boolQuery();
		int start = 0;
		int end = 1000;
		SearchResponse response = ElasticSearchFactory.getClient().prepareSearch(HarborIndex.HY_BE_DB.getValue())
				.setTypes(HarborIndexType.HY_BE.getValue()).setFrom(start).setSize(end - start).setQuery(builder)
				.execute().actionGet();
		SearchHits hits = response.getHits();
		for (SearchHit hit : hits) {
			Be be = JSON.parseObject(hit.getSourceAsString(), Be.class);
			ElasticSearchFactory.getClient()
					.prepareDelete(HarborIndex.HY_BE_DB.getValue(), HarborIndexType.HY_BE.getValue(), be.getBeId())
					.execute().actionGet();
		}

	}

	public static void deleteGoIndex() {
		BoolQueryBuilder builder = QueryBuilders.boolQuery();
		int start = 0;
		int end = 1000;
		SearchResponse response = ElasticSearchFactory.getClient().prepareSearch(HarborIndex.HY_GO_DB.getValue())
				.setTypes(HarborIndexType.HY_GO.getValue()).setFrom(start).setSize(end - start).setQuery(builder)
				.execute().actionGet();
		SearchHits hits = response.getHits();
		for (SearchHit hit : hits) {
			Go go = JSON.parseObject(hit.getSourceAsString(), Go.class);
			if("673A35F6591E4179AA4CA0D17E7F4553".equals(go.getGoId())){
				continue;
			}
			ElasticSearchFactory.getClient()
					.prepareDelete(HarborIndex.HY_GO_DB.getValue(), HarborIndexType.HY_GO.getValue(), go.getGoId())
					.execute().actionGet();
		}

	}

	public static void deleteMNSIndex() {
		BoolQueryBuilder builder = QueryBuilders.boolQuery();
		int start = 0;
		int end = 1000;
		SearchResponse response = ElasticSearchFactory.getClient().prepareSearch(HarborIndex.HY_MNS_DB.getValue())
				.setTypes(HarborIndexType.HY_MNS_DATA.getValue()).setFrom(start).setSize(end - start).setQuery(builder)
				.execute().actionGet();
		SearchHits hits = response.getHits();
		for (SearchHit hit : hits) {
			MNSRecord go = JSON.parseObject(hit.getSourceAsString(), MNSRecord.class);
			ElasticSearchFactory.getClient().prepareDelete(HarborIndex.HY_MNS_DB.getValue(),
					HarborIndexType.HY_MNS_DATA.getValue(), go.getMqType() + "." + go.getMqId()).execute().actionGet();
		}

	}

}
