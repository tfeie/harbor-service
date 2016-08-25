package com.the.harbor.api.be.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.opensearch.CloudsearchClient;
import com.aliyun.opensearch.CloudsearchSearch;
import com.the.harbor.api.be.IBeSV;
import com.the.harbor.api.be.param.Be;
import com.the.harbor.api.be.param.BeCreateReq;
import com.the.harbor.api.be.param.BeCreateResp;
import com.the.harbor.api.be.param.BeDetail;
import com.the.harbor.api.be.param.BeQueryReq;
import com.the.harbor.api.be.param.BeQueryResp;
import com.the.harbor.api.be.param.BeTag;
import com.the.harbor.api.be.param.DeleteBeReq;
import com.the.harbor.api.be.param.DoBeDelete;
import com.the.harbor.api.be.param.GiveHBReq;
import com.the.harbor.api.be.param.HideBeReq;
import com.the.harbor.api.be.param.QueryMyBeReq;
import com.the.harbor.api.be.param.QueryMyBeResp;
import com.the.harbor.api.be.param.QueryMyFavorBeReq;
import com.the.harbor.api.be.param.QueryMyFavorBeResp;
import com.the.harbor.api.be.param.QueryOneBeReq;
import com.the.harbor.api.be.param.QueryOneBeResp;
import com.the.harbor.api.be.param.TopBeReq;
import com.the.harbor.base.constants.ExceptCodeConstants;
import com.the.harbor.base.enumeration.common.Status;
import com.the.harbor.base.enumeration.hybe.BeDetailType;
import com.the.harbor.base.enumeration.hybe.HideFlag;
import com.the.harbor.base.enumeration.hybe.TopFlag;
import com.the.harbor.base.enumeration.hygo.GoDetailType;
import com.the.harbor.base.enumeration.hytags.TagCat;
import com.the.harbor.base.enumeration.hytags.TagType;
import com.the.harbor.base.exception.BusinessException;
import com.the.harbor.base.exception.SystemException;
import com.the.harbor.base.util.ResponseBuilder;
import com.the.harbor.base.util.ValidatorUtil;
import com.the.harbor.base.vo.PageInfo;
import com.the.harbor.base.vo.Response;
import com.the.harbor.base.vo.ResponseHeader;
import com.the.harbor.commons.components.aliyuncs.opensearch.OpenSearchFactory;
import com.the.harbor.commons.components.elasticsearch.ElasticSearchFactory;
import com.the.harbor.commons.indices.def.HarborIndex;
import com.the.harbor.commons.indices.def.HarborIndexType;
import com.the.harbor.commons.redisdata.util.HyBeUtil;
import com.the.harbor.commons.util.CollectionUtil;
import com.the.harbor.commons.util.DateUtil;
import com.the.harbor.commons.util.StringUtil;
import com.the.harbor.service.interfaces.IBeBusiSV;
import com.the.harbor.service.interfaces.IUserManagerSV;
import com.the.harbor.util.BeGoDeleteMQSend;

@Service(validation = "true")
public class BeSVImpl implements IBeSV {

	@Autowired
	private transient IBeBusiSV beBusiSV;

	@Autowired
	private transient IUserManagerSV userManagerSV;

	@Override
	public BeCreateResp createBe(BeCreateReq beCreateReq) throws BusinessException, SystemException {
		this.validate(beCreateReq);
		String beId = beBusiSV.createBe(beCreateReq);
		BeCreateResp resp = new BeCreateResp();
		ResponseHeader responseHeader = ResponseBuilder.buildSuccessResponseHeader("活动创建成功");
		resp.setResponseHeader(responseHeader);
		resp.setBeId(beId);
		return resp;
	}

	private void validate(BeCreateReq beCreateReq) {
		if (beCreateReq == null) {
			throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "参数为空");
		}
		if (CollectionUtil.isEmpty(beCreateReq.getBeDetails())) {
			throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "请填写要发布的文字或图片");
		}

		for (BeDetail detail : beCreateReq.getBeDetails()) {
			if (StringUtil.isBlank(detail.getType())) {
				throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "BE详情类型为空");
			}
			boolean valid = ValidatorUtil.validate(detail.getType(), BeDetailType.class.getEnumConstants());
			if (!valid) {
				throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "BE类型取值不合规");
			}
			if (GoDetailType.TEXT.getValue().equals(detail.getType())) {
				if (StringUtil.isBlank(detail.getDetail())) {
					throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "请填写要发表的内容");
				}
			} else if (GoDetailType.IMAGE.getValue().equals(detail.getType())) {
				if (StringUtil.isBlank(detail.getImageUrl())) {
					throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "请上传图片");
				}
			}
		}

		if (CollectionUtil.isEmpty(beCreateReq.getBeTags())) {
			throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "请至少选择一个标签");
		}
		if (beCreateReq.getBeTags().size() > 5) {
			throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "最多只能选择5个标签");
		}
		for (BeTag tag : beCreateReq.getBeTags()) {
			if (StringUtil.isBlank(tag.getTagCat())) {
				throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "标签类目为空");
			}
			if (StringUtil.isBlank(tag.getTagName())) {
				throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "标签名称为空");
			}
			if (StringUtil.isBlank(tag.getTagType())) {
				throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "标签类型为空");
			}

			boolean valid = ValidatorUtil.validate(tag.getTagType(), TagType.class.getEnumConstants());
			if (!valid) {
				throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "标签类型取值不合规");
			}
			valid = ValidatorUtil.validate(tag.getTagCat(), TagCat.class.getEnumConstants());
			if (!valid) {
				throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "标签类目取值不合规");
			}
		}
	}

	@Override
	public QueryMyBeResp queryMyBe(QueryMyBeReq queryMyBeReq) throws BusinessException, SystemException {
		PageInfo<Be> pageInfo = this.queryMyBeFromOpenSearch(queryMyBeReq);
		ResponseHeader responseHeader = ResponseBuilder.buildSuccessResponseHeader("查询成功");
		QueryMyBeResp resp = new QueryMyBeResp();
		resp.setPagInfo(pageInfo);
		resp.setResponseHeader(responseHeader);
		return resp;
	}

	private PageInfo<Be> queryMyBeFromOpenSearch(QueryMyBeReq queryMyBeReq) {
		int start = (queryMyBeReq.getPageNo() - 1) * queryMyBeReq.getPageSize();
		CloudsearchClient client = OpenSearchFactory.getClient();
		CloudsearchSearch search = new CloudsearchSearch(client);
		search.addIndex("harbor_be");
		search.addCustomConfig("start", start);
		search.addCustomConfig("hit", queryMyBeReq.getPageSize());
		// 状态必须是有效的
		StringBuffer query = new StringBuffer();
		query.append("status:'" + com.the.harbor.base.enumeration.common.Status.VALID.getValue() + "'");
		query.append(" AND userid:'" + queryMyBeReq.getUserId() + "'");
		search.setQueryString(query.toString());
		// 按照BEID进行聚合搜索排重
		search.addDistinct("beid", 1, 1, "false");
		search.setPair("duniqfield:beid");
		// 指定搜索返回的格式。
		search.setFormat("json");
		// 设定排序方式 + 表示正序 - 表示降序
		search.addSort("createdate", "-");
		// 返回搜索结果
		List<Be> result = new ArrayList<Be>();
		int total = 0;
		String res;
		try {
			res = search.search();
		} catch (Exception e) {
			throw new SystemException("查询错误");
		}
		JSONObject d = JSONObject.parseObject(res);
		String status = d.getString("status");
		if ("OK".equals(status)) {
			JSONObject rs = d.getJSONObject("result");
			total = d.getIntValue("total");
			JSONArray arr = rs.getJSONArray("items");
			for (int i = 0; i < arr.size(); i++) {
				JSONObject data = arr.getJSONObject(i);
				String beId = data.getString("beid");
				String bedata = HyBeUtil.getBe(beId);
				Be newBe = JSONObject.parseObject(bedata, Be.class);
				if (newBe != null) {
					beBusiSV.fillBeInfo(newBe);
					result.add(newBe);
				}
			}

		}

		PageInfo<Be> pageInfo = new PageInfo<Be>();
		pageInfo.setCount(Integer.parseInt(total + ""));
		pageInfo.setPageNo(queryMyBeReq.getPageNo());
		pageInfo.setPageSize(queryMyBeReq.getPageSize());
		pageInfo.setResult(result);
		return pageInfo;
	}

	private PageInfo<Be> queryMyBeFromES(QueryMyBeReq queryMyBeReq) {
		int start = (queryMyBeReq.getPageNo() - 1) * queryMyBeReq.getPageSize();
		int end = queryMyBeReq.getPageNo() * queryMyBeReq.getPageSize();
		SortBuilder sortBuilder = SortBuilders.fieldSort("createDate").order(SortOrder.DESC);
		BoolQueryBuilder builder = QueryBuilders.boolQuery();
		builder.must(QueryBuilders.termQuery("userId", queryMyBeReq.getUserId()));
		builder.must(QueryBuilders.termQuery("status",
				com.the.harbor.base.enumeration.common.Status.VALID.getValue().toLowerCase()));
		SearchResponse response = ElasticSearchFactory.getClient().prepareSearch(HarborIndex.HY_BE_DB.getValue())
				.setTypes(HarborIndexType.HY_BE.getValue()).setFrom(start).setSize(end - start).setQuery(builder)
				.addSort(sortBuilder).execute().actionGet();
		SearchHits hits = response.getHits();
		long total = hits.getTotalHits();
		List<Be> result = new ArrayList<Be>();
		for (SearchHit hit : hits) {
			Be be = JSON.parseObject(hit.getSourceAsString(), Be.class);
			// 考虑加载性能
			beBusiSV.fillBeInfo(be);
			result.add(be);
		}
		PageInfo<Be> pageInfo = new PageInfo<Be>();
		pageInfo.setCount(Integer.parseInt(total + ""));
		pageInfo.setPageNo(queryMyBeReq.getPageNo());
		pageInfo.setPageSize(queryMyBeReq.getPageSize());
		pageInfo.setResult(result);
		return pageInfo;
	}

	@Override
	public QueryOneBeResp queryOneBe(QueryOneBeReq queryOneBeReq) throws BusinessException, SystemException {
		Be be = beBusiSV.queryOneBeFromRDS(queryOneBeReq.getBeId());
		ResponseHeader responseHeader = ResponseBuilder.buildSuccessResponseHeader("查询成功");
		QueryOneBeResp resp = new QueryOneBeResp();
		resp.setBe(be);
		resp.setResponseHeader(responseHeader);
		return resp;
	}

	@Override
	public BeQueryResp queryBes(BeQueryReq beQueryReq) throws BusinessException, SystemException {
		PageInfo<Be> pageInfo = this.queryHyBesFromOpenSearch(beQueryReq);
		ResponseHeader responseHeader = ResponseBuilder.buildSuccessResponseHeader("查询成功");
		BeQueryResp resp = new BeQueryResp();
		resp.setPagInfo(pageInfo);
		resp.setResponseHeader(responseHeader);
		return resp;
	}

	private PageInfo<Be> queryHyBesFromES(BeQueryReq beQueryReq) {
		int start = (beQueryReq.getPageNo() - 1) * beQueryReq.getPageSize();
		int end = beQueryReq.getPageNo() * beQueryReq.getPageSize();
		SortBuilder topSortBuilder = SortBuilders.fieldSort("topDate").order(SortOrder.DESC);

		SortBuilder createSortBuilder = SortBuilders.fieldSort("createDate").order(SortOrder.DESC);
		BoolQueryBuilder builder = QueryBuilders.boolQuery();
		builder.must(QueryBuilders.termQuery("status",
				com.the.harbor.base.enumeration.common.Status.VALID.getValue().toLowerCase()));
		if (!StringUtil.isBlank(beQueryReq.getTagId())) {
			builder.must(QueryBuilders.termQuery("beTags.tagId", beQueryReq.getTagId()));
		}
		if (!StringUtil.isBlank(beQueryReq.getPolyTagId())) {
			builder.must(QueryBuilders.termQuery("beTags.polyTagId", beQueryReq.getPolyTagId()));
		}
		if (!StringUtil.isBlank(beQueryReq.getSearchKey())) {
			builder.must(QueryBuilders.queryStringQuery(beQueryReq.getSearchKey()));
		}
		if (!beQueryReq.isQueryhide()) {
			// 不查询隐藏记录
			builder.must(QueryBuilders.termQuery("hideFlag", HideFlag.NO.getValue()));
		}

		SearchResponse response = ElasticSearchFactory.getClient().prepareSearch(HarborIndex.HY_BE_DB.getValue())
				.setTypes(HarborIndexType.HY_BE.getValue()).setFrom(start).setSize(end - start).setQuery(builder)
				.addSort(topSortBuilder).addSort(createSortBuilder).execute().actionGet();
		SearchHits hits = response.getHits();
		long total = hits.getTotalHits();
		List<Be> result = new ArrayList<Be>();
		for (SearchHit hit : hits) {
			Be be = JSON.parseObject(hit.getSourceAsString(), Be.class);
			beBusiSV.fillBeInfo(be);
			result.add(be);
		}
		PageInfo<Be> pageInfo = new PageInfo<Be>();
		pageInfo.setCount(Integer.parseInt(total + ""));
		pageInfo.setPageNo(beQueryReq.getPageNo());
		pageInfo.setPageSize(beQueryReq.getPageSize());
		pageInfo.setResult(result);
		return pageInfo;
	}

	private PageInfo<Be> queryHyBesFromOpenSearch(BeQueryReq beQueryReq) {
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
		List<Be> result = new ArrayList<Be>();
		int total = 0;
		String res;
		try {
			res = search.search();
		} catch (Exception e) {
			throw new SystemException("查询错误");
		}
		JSONObject d = JSONObject.parseObject(res);
		String status = d.getString("status");
		if ("OK".equals(status)) {
			JSONObject rs = d.getJSONObject("result");
			total = d.getIntValue("total");
			JSONArray arr = rs.getJSONArray("items");
			for (int i = 0; i < arr.size(); i++) {
				JSONObject data = arr.getJSONObject(i);
				String beId = data.getString("beid");
				String bedata = HyBeUtil.getBe(beId);
				Be newBe = JSONObject.parseObject(bedata, Be.class);
				if (newBe != null) {
					beBusiSV.fillBeInfo(newBe);
					result.add(newBe);
				}
			}

		}

		PageInfo<Be> pageInfo = new PageInfo<Be>();
		pageInfo.setCount(Integer.parseInt(total + ""));
		pageInfo.setPageNo(beQueryReq.getPageNo());
		pageInfo.setPageSize(beQueryReq.getPageSize());
		pageInfo.setResult(result);
		return pageInfo;
	}

	@Override
	public Response giveHaibei(GiveHBReq giveHBReq) throws BusinessException, SystemException {
		beBusiSV.giveHaibei(giveHBReq);
		return ResponseBuilder.buildSuccessResponse("海贝打赏成功");
	}

	@Override
	public QueryMyFavorBeResp queryMyFavorBe(QueryMyFavorBeReq queryMyFavorBeReq)
			throws BusinessException, SystemException {
		long total = HyBeUtil.getUserFavorBesCount(queryMyFavorBeReq.getUserId());
		Set<String> beIds = HyBeUtil.getUserFavorBesPage(queryMyFavorBeReq.getUserId(), queryMyFavorBeReq.getPageNo(),
				queryMyFavorBeReq.getPageSize(), false);
		List<Be> result = new ArrayList<Be>();
		for (String beId : beIds) {
			Be be = beBusiSV.queryOneBeFromRDS(beId);
			if (be != null) {
				result.add(be);
			}
		}
		PageInfo<Be> pageInfo = new PageInfo<Be>();
		pageInfo.setCount(Integer.parseInt(total + ""));
		pageInfo.setPageNo(queryMyFavorBeReq.getPageNo());
		pageInfo.setPageSize(queryMyFavorBeReq.getPageSize());
		pageInfo.setResult(result);
		ResponseHeader responseHeader = ResponseBuilder.buildSuccessResponseHeader("查询成功");
		QueryMyFavorBeResp resp = new QueryMyFavorBeResp();
		resp.setPagInfo(pageInfo);
		resp.setResponseHeader(responseHeader);
		return resp;
	}

	@Override
	public Response deleteBe(DeleteBeReq deleteBeReq) throws BusinessException, SystemException {
		Be be = beBusiSV.queryOneBeFromRDS(deleteBeReq.getBeId());
		if (be != null) {
			// 判断是否是发布者
			if (!be.getUserId().equals(deleteBeReq.getUserId())) {
				throw new BusinessException("您无权删除");
			}
			be.setStatus(Status.INVALID.getValue());
			/*
			 * ElasticSearchFactory.getClient()
			 * .prepareIndex(HarborIndex.HY_BE_DB.getValue().toLowerCase(),
			 * HarborIndexType.HY_BE.getValue().toLowerCase(), be.getBeId())
			 * .setRefresh(true).setSource(JSON.toJSONString(be)).execute().
			 * actionGet();
			 */
			// 标记RDS状态
			HyBeUtil.recordBe(be.getBeId(), JSON.toJSONString(be));
			// 标记索引状态
			beBusiSV.deleteBeToOpenSearch(be.getBeId());
			// 发送删除消息处理
			DoBeDelete body = new DoBeDelete();
			body.setBeId(be.getBeId());
			BeGoDeleteMQSend.sendMQ(body);
		}
		return ResponseBuilder.buildSuccessResponse("删除成功");
	}

	@Override
	public Response topBe(TopBeReq topBeReq) throws BusinessException, SystemException {
		Be be = beBusiSV.queryOneBeFromRDS(topBeReq.getBeId());
		if (be != null) {
			if (topBeReq.isTop()) {
				be.setTopFlag(TopFlag.YES.getValue());
				be.setTopDate(DateUtil.getSysDate());
			} else {
				be.setTopFlag(TopFlag.NO.getValue());
				be.setTopDate(null);
			}

			/**
			 * ElasticSearchFactory.getClient()
			 * .prepareIndex(HarborIndex.HY_BE_DB.getValue().toLowerCase(),
			 * HarborIndexType.HY_BE.getValue().toLowerCase(), be.getBeId())
			 * .setRefresh(true).setSource(JSON.toJSONString(be)).execute().
			 * actionGet();
			 */
			// 标记RDS状态
			HyBeUtil.recordBe(be.getBeId(), JSON.toJSONString(be));
			beBusiSV.topBe(be.getBeId(), be.getTopFlag(), be.getTopDate());

		}
		return ResponseBuilder.buildSuccessResponse("操作成功");
	}

	@Override
	public Response hideBe(HideBeReq hideBeReq) throws BusinessException, SystemException {
		Be be = beBusiSV.queryOneBeFromRDS(hideBeReq.getBeId());
		if (be != null) {
			be.setHideFlag(hideBeReq.isHide() ? HideFlag.YES.getValue() : HideFlag.NO.getValue());

			/**
			 * ElasticSearchFactory.getClient()
			 * .prepareIndex(HarborIndex.HY_BE_DB.getValue().toLowerCase(),
			 * HarborIndexType.HY_BE.getValue().toLowerCase(), be.getBeId())
			 * .setRefresh(true).setSource(JSON.toJSONString(be)).execute().
			 * actionGet();
			 **/
			// 标记RDS状态
			HyBeUtil.recordBe(be.getBeId(), JSON.toJSONString(be));
			beBusiSV.hideBe(be.getBeId(), be.getHideFlag());

		}
		return ResponseBuilder.buildSuccessResponse("操作成功");
	}

}
