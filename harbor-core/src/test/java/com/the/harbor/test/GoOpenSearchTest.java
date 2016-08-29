package com.the.harbor.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.opensearch.CloudsearchClient;
import com.aliyun.opensearch.CloudsearchSearch;
import com.the.harbor.base.exception.SystemException;
import com.the.harbor.commons.components.aliyuncs.opensearch.OpenSearchFactory;
import com.the.harbor.service.interfaces.IGoBusiSV;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class GoOpenSearchTest {

	@Autowired
	IGoBusiSV goSV;

	@Test
	public void rebuildOneGo() {
		goSV.pushGoToOpenSearch("ACE97DD905C9468DA06B8D331373FA95");

	}
	
	@Test
	public void rebuildAllOpenSearch() {
		goSV.pushAllGoToOpenSearch();
	}
	
	@Test
	public void getGoIndex() {
		List<String> indexs =getGoIndexs("0991DDE9CA164162A110E8C51CC848BF");
		System.out.println(indexs.toString());

	}

	
	private List<String> getGoIndexs(String goId) {
		CloudsearchClient client = OpenSearchFactory.getClient();
		CloudsearchSearch search = new CloudsearchSearch(client);
		search.addIndex("harbor_go");
		search.setQueryString(" goid:'" + goId + "'");
		search.setFormat("json");
		String res;
		try {
			res = search.search();
		} catch (Exception e) {
			throw new SystemException("查询错误");
		}
		List<String> indexs = new ArrayList<String>();
		JSONObject d = JSONObject.parseObject(res);
		String status = d.getString("status");
		if ("OK".equals(status)) {
			JSONObject rs = d.getJSONObject("result");
			JSONArray arr = rs.getJSONArray("items");
			for (int i = 0; i < arr.size(); i++) {
				JSONObject data = arr.getJSONObject(i);
				String indexId = data.getString("indexid");
				indexs.add(indexId);
			}

		}
		return indexs;
	}
}
