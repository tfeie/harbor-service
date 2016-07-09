package com.the.harbor.api.be.impl;

import java.util.ArrayList;
import java.util.List;

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
import com.the.harbor.api.be.IBeSV;
import com.the.harbor.api.be.param.Be;
import com.the.harbor.api.be.param.BeCreateReq;
import com.the.harbor.api.be.param.BeCreateResp;
import com.the.harbor.api.be.param.BeDetail;
import com.the.harbor.api.be.param.BeQueryReq;
import com.the.harbor.api.be.param.BeQueryResp;
import com.the.harbor.api.be.param.BeTag;
import com.the.harbor.api.be.param.GiveHBReq;
import com.the.harbor.api.be.param.QueryMyBeReq;
import com.the.harbor.api.be.param.QueryMyBeResp;
import com.the.harbor.api.be.param.QueryOneBeReq;
import com.the.harbor.api.be.param.QueryOneBeResp;
import com.the.harbor.api.user.param.UserViewInfo;
import com.the.harbor.base.constants.ExceptCodeConstants;
import com.the.harbor.base.enumeration.hybe.BeDetailType;
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
import com.the.harbor.commons.components.elasticsearch.ElasticSearchFactory;
import com.the.harbor.commons.indices.def.HarborIndex;
import com.the.harbor.commons.indices.def.HarborIndexType;
import com.the.harbor.commons.redisdata.util.HyBeUtil;
import com.the.harbor.commons.util.CollectionUtil;
import com.the.harbor.commons.util.DateUtil;
import com.the.harbor.commons.util.StringUtil;
import com.the.harbor.service.interfaces.IBeBusiSV;
import com.the.harbor.service.interfaces.IUserManagerSV;

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
		int start = (queryMyBeReq.getPageNo() - 1) * queryMyBeReq.getPageSize();
		int end = queryMyBeReq.getPageNo() * queryMyBeReq.getPageSize();
		SortBuilder sortBuilder = SortBuilders.fieldSort("createDate").order(SortOrder.DESC);
		BoolQueryBuilder builder = QueryBuilders.boolQuery();
		builder.must(QueryBuilders.termQuery("userId", queryMyBeReq.getUserId()));
		SearchResponse response = ElasticSearchFactory.getClient().prepareSearch(HarborIndex.HY_BE_DB.getValue())
				.setTypes(HarborIndexType.HY_BE.getValue()).setFrom(start).setSize(end - start).setQuery(builder)
				.addSort(sortBuilder).execute().actionGet();
		SearchHits hits = response.getHits();
		long total = hits.getTotalHits();
		List<Be> result = new ArrayList<Be>();
		for (SearchHit hit : hits) {
			Be be = JSON.parseObject(hit.getSourceAsString(), Be.class);
			this.fillBeInfo(be);
			result.add(be);
		}
		PageInfo<Be> pageInfo = new PageInfo<Be>();
		pageInfo.setCount(Integer.parseInt(total + ""));
		pageInfo.setPageNo(queryMyBeReq.getPageNo());
		pageInfo.setPageSize(queryMyBeReq.getPageSize());
		pageInfo.setResult(result);
		ResponseHeader responseHeader = ResponseBuilder.buildSuccessResponseHeader("查询成功");
		QueryMyBeResp resp = new QueryMyBeResp();
		resp.setPagInfo(pageInfo);
		resp.setResponseHeader(responseHeader);
		return resp;
	}

	@Override
	public QueryOneBeResp queryOneBe(QueryOneBeReq queryOneBeReq) throws BusinessException, SystemException {
		SearchResponse response = ElasticSearchFactory.getClient().prepareSearch(HarborIndex.HY_BE_DB.getValue())
				.setTypes(HarborIndexType.HY_BE.getValue())
				.setQuery(QueryBuilders.termQuery("_id", queryOneBeReq.getBeId())).execute().actionGet();
		if (response.getHits().totalHits() == 0) {
			return null;
		}
		Be be = JSON.parseObject(response.getHits().getHits()[0].getSourceAsString(), Be.class);
		this.fillBeInfo(be);
		ResponseHeader responseHeader = ResponseBuilder.buildSuccessResponseHeader("查询成功");
		QueryOneBeResp resp = new QueryOneBeResp();
		resp.setBe(be);
		resp.setResponseHeader(responseHeader);
		return resp;
	}

	/**
	 * 完善BE信息
	 * 
	 * @param be
	 */
	private void fillBeInfo(Be be) {
		boolean hastext = false;
		boolean hasimg = false;
		String contentSummary = null;
		String imageURL = null;
		if (!CollectionUtil.isEmpty(be.getBeDetails())) {
			for (BeDetail detail : be.getBeDetails()) {
				if (BeDetailType.TEXT.getValue().equals(detail.getType())) {
					if (!hastext) {
						contentSummary = detail.getDetail();
						hastext = true;
					}
				} else if (BeDetailType.IMAGE.getValue().equals(detail.getType())) {
					if (!hasimg) {
						imageURL = detail.getImageUrl();
						hasimg = true;
					}
				}
			}
		}
		be.setContentSummary(contentSummary);
		be.setImageURL(imageURL);
		be.setHasimg(hasimg);
		be.setHastext(hastext);
		be.setCreateTimeInterval(DateUtil.getInterval(be.getCreateDate()));
		// 发布用户信息
		UserViewInfo createUserInfo = userManagerSV.getUserViewInfoByUserId(be.getUserId());
		be.setAtCityName(createUserInfo.getAtCityName());
		be.setEnName(createUserInfo.getEnName());
		be.setIndustryName(createUserInfo.getIndustryName());
		be.setTitle(createUserInfo.getTitle());
		be.setWxHeadimg(createUserInfo.getWxHeadimg());
		be.setUserStatusName(createUserInfo.getUserStatusName());
		be.setAbroadCountryName(createUserInfo.getAbroadCountryName());

		be.setCommentCount(HyBeUtil.getBeCommentsCount(be.getBeId()));
		be.setDianzanCount(HyBeUtil.getBeDianzanCount(be.getBeId()));
		be.setGiveHaibeiCount(HyBeUtil.getRewardUserCount(be.getBeId()));
		be.setGiveHaibeiCount(0);
	}

	@Override
	public BeQueryResp queryBes(BeQueryReq beQueryReq) throws BusinessException, SystemException {
		int start = (beQueryReq.getPageNo() - 1) * beQueryReq.getPageSize();
		int end = beQueryReq.getPageNo() * beQueryReq.getPageSize();
		SortBuilder sortBuilder = SortBuilders.fieldSort("createDate").order(SortOrder.DESC);
		BoolQueryBuilder builder = QueryBuilders.boolQuery();
		if (!StringUtil.isBlank(beQueryReq.getBeTag())) {
			builder.must(QueryBuilders.termQuery("beTags.tagId", beQueryReq.getBeTag()));
		}
		if (!StringUtil.isBlank(beQueryReq.getSearchKey())) {
			builder.must(QueryBuilders.queryStringQuery(beQueryReq.getSearchKey()));
		}
		SearchResponse response = ElasticSearchFactory.getClient().prepareSearch(HarborIndex.HY_BE_DB.getValue())
				.setTypes(HarborIndexType.HY_BE.getValue()).setFrom(start).setSize(end - start).setQuery(builder)
				.addSort(sortBuilder).execute().actionGet();
		SearchHits hits = response.getHits();
		long total = hits.getTotalHits();
		List<Be> result = new ArrayList<Be>();
		for (SearchHit hit : hits) {
			Be be = JSON.parseObject(hit.getSourceAsString(), Be.class);
			this.fillBeInfo(be);
			result.add(be);
		}
		PageInfo<Be> pageInfo = new PageInfo<Be>();
		pageInfo.setCount(Integer.parseInt(total + ""));
		pageInfo.setPageNo(beQueryReq.getPageNo());
		pageInfo.setPageSize(beQueryReq.getPageSize());
		pageInfo.setResult(result);
		ResponseHeader responseHeader = ResponseBuilder.buildSuccessResponseHeader("查询成功");
		BeQueryResp resp = new BeQueryResp();
		resp.setPagInfo(pageInfo);
		resp.setResponseHeader(responseHeader);
		return resp;
	}

	@Override
	public Response giveHaibei(GiveHBReq giveHBReq) throws BusinessException, SystemException {
		beBusiSV.giveHaibei(giveHBReq);
		return ResponseBuilder.buildSuccessResponse("海贝打赏成功");
	}

}
