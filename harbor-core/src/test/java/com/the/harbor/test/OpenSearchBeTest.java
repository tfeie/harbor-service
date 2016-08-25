package com.the.harbor.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.opensearch.CloudsearchClient;
import com.aliyun.opensearch.CloudsearchSearch;
import com.the.harbor.api.be.param.Be;
import com.the.harbor.api.be.param.BeQueryReq;
import com.the.harbor.base.enumeration.hybe.HideFlag;
import com.the.harbor.base.vo.PageInfo;
import com.the.harbor.commons.components.aliyuncs.opensearch.OpenSearchFactory;
import com.the.harbor.commons.util.StringUtil;

public class OpenSearchBeTest {

	public static void main(String[] args) throws Exception {
		BeQueryReq beQueryReq = new BeQueryReq();
		beQueryReq.setPageNo(1);
		beQueryReq.setPageSize(20);
		beQueryReq.setSearchKey("今天天气");
		queryHyBes(beQueryReq);
	}

	private static PageInfo<Be> queryHyBes(BeQueryReq beQueryReq) throws Exception {
		int start = (beQueryReq.getPageNo() - 1) * beQueryReq.getPageSize();
		CloudsearchClient client = OpenSearchFactory.getClient();
		CloudsearchSearch search = new CloudsearchSearch(client);
		search.addIndex("harbor_be");
		search.addCustomConfig("start", start);
		search.addCustomConfig("hit", beQueryReq.getPageSize());
		// 状态必须是有效的
		StringBuffer query = new StringBuffer();
		query.append("status:'" + com.the.harbor.base.enumeration.common.Status.VALID.getValue() + "'");
		if (!StringUtil.isBlank(beQueryReq.getTagId())) {
			query.append(" AND betagids:'" + beQueryReq.getTagId() + "'");
		}
		if (!StringUtil.isBlank(beQueryReq.getPolyTagId())) {
			query.append(" AND polytagids:'" + beQueryReq.getPolyTagId() + "'");
		}
		if (!StringUtil.isBlank(beQueryReq.getSearchKey())) {
			query.append(" AND fullsearch:'" + beQueryReq.getSearchKey() + "'");
		}
		if (!beQueryReq.isQueryhide()) {
			// 不查询隐藏记录
			query.append(" AND hideflag:'" + HideFlag.NO.getValue() + "'");
		}
		search.setQueryString(query.toString());
		// 按照BEID进行聚合搜索排重
		search.addDistinct("beid", 1, 1, "false");
		search.setPair("duniqfield:beid");
		// 指定搜索返回的格式。
		search.setFormat("json");
		// 设定排序方式 + 表示正序 - 表示降序
		search.addSort("topdate", "-");
		search.addSort("createdate", "-");
		// 返回搜索结果
		String result = search.search();
		JSONObject d = JSONObject.parseObject(result);
		String status = d.getString("status");
		if ("OK".equals(status)) {
			JSONObject rs = d.getJSONObject("result");
			int total = d.getIntValue("total");
			JSONArray arr = rs.getJSONArray("items");
			for (int i = 0; i < arr.size(); i++) {
				JSONObject data = arr.getJSONObject(i);
				String beId = data.getString("beid");
				System.out.println(beId);
			}

		}
		return null;
	}

}
