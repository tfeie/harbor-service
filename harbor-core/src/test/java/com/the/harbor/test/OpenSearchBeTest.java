package com.the.harbor.test;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.opensearch.CloudsearchClient;
import com.aliyun.opensearch.CloudsearchSearch;
import com.the.harbor.commons.components.aliyuncs.opensearch.OpenSearchFactory;

public class OpenSearchBeTest {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		CloudsearchClient client = OpenSearchFactory.getClient();
		CloudsearchSearch search = new CloudsearchSearch(client);
		// 添加指定搜索的应用：
		search.addIndex("harbor_be");
		search.addCustomConfig("start", 1);
		// 指定搜索的关键词，这里要指定在哪个索引上搜索，如果不指定的话默认在使用“default”索引（索引字段名称是您在您的数据结构中的“索引到”字段。）
		search.setQueryString("fullsearch:'一百万'");
		search.addDistinct("beid", 1, 1, "false");
		// 指定搜索返回的格式。
		search.setFormat("json");
		search.setPair("duniqfield:beid");
		// 设定排序方式 + 表示正序 - 表示降序
		// search.addSort("price", "+");
		// 返回搜索结果
		String result = search.search();
		JSONObject d = JSONObject.parseObject(result);
		String status = d.getString("status");
		if ("OK".equals(status)) {
			JSONObject rs = d.getJSONObject("result");
			JSONArray arr = rs.getJSONArray("items");
			System.out.println(arr);
		}

	}

}
