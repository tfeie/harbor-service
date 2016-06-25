package com.the.harbor.api.go.impl;

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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.the.harbor.api.go.IGoSV;
import com.the.harbor.api.go.param.CreateGoPaymentOrderReq;
import com.the.harbor.api.go.param.CreateGoPaymentOrderResp;
import com.the.harbor.api.go.param.Go;
import com.the.harbor.api.go.param.GoCreateReq;
import com.the.harbor.api.go.param.GoCreateResp;
import com.the.harbor.api.go.param.GoDetail;
import com.the.harbor.api.go.param.GoOrder;
import com.the.harbor.api.go.param.GoOrderConfirmReq;
import com.the.harbor.api.go.param.GoOrderCreateReq;
import com.the.harbor.api.go.param.GoOrderCreateResp;
import com.the.harbor.api.go.param.GoOrderFinishReq;
import com.the.harbor.api.go.param.GoOrderMeetLocaltionConfirmReq;
import com.the.harbor.api.go.param.GoOrderMeetLocaltionReq;
import com.the.harbor.api.go.param.GoOrderQueryReq;
import com.the.harbor.api.go.param.GoOrderQueryResp;
import com.the.harbor.api.go.param.GoQueryReq;
import com.the.harbor.api.go.param.GoQueryResp;
import com.the.harbor.api.go.param.GoTag;
import com.the.harbor.api.go.param.QueryGoReq;
import com.the.harbor.api.go.param.QueryGoResp;
import com.the.harbor.api.go.param.QueryMyGoReq;
import com.the.harbor.api.go.param.QueryMyGoResp;
import com.the.harbor.api.go.param.UpdateGoOrderPayReq;
import com.the.harbor.api.user.param.UserViewInfo;
import com.the.harbor.base.constants.ExceptCodeConstants;
import com.the.harbor.base.enumeration.dict.ParamCode;
import com.the.harbor.base.enumeration.dict.TypeCode;
import com.the.harbor.base.enumeration.hygo.GoDetailType;
import com.the.harbor.base.enumeration.hygo.GoType;
import com.the.harbor.base.enumeration.hygo.OrgMode;
import com.the.harbor.base.enumeration.hygo.PayMode;
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
import com.the.harbor.commons.redisdata.util.HyDictUtil;
import com.the.harbor.commons.redisdata.util.HyUserUtil;
import com.the.harbor.commons.util.CollectionUtil;
import com.the.harbor.commons.util.DateUtil;
import com.the.harbor.commons.util.StringUtil;
import com.the.harbor.dao.mapper.bo.HyGo;
import com.the.harbor.dao.mapper.bo.HyGoOrder;
import com.the.harbor.dao.mapper.bo.HyUser;
import com.the.harbor.service.interfaces.IGoBusiSV;
import com.the.harbor.service.interfaces.IUserManagerSV;

@Service(validation = "true")
public class GoSVImpl implements IGoSV {

	@Autowired
	private transient IGoBusiSV goBusiSV;

	@Autowired
	private transient IUserManagerSV userManagerSV;

	@Override
	public GoCreateResp createGo(GoCreateReq goCreateReq) throws BusinessException, SystemException {
		this.validateCreateGo(goCreateReq);
		String goId = goBusiSV.createGo(goCreateReq);
		GoCreateResp resp = new GoCreateResp();
		ResponseHeader responseHeader = ResponseBuilder.buildSuccessResponseHeader("活动创建成功");
		resp.setResponseHeader(responseHeader);
		resp.setGoId(goId);
		return resp;
	}

	private void validateCreateGo(GoCreateReq goCreateReq) {
		if (goCreateReq == null) {
			throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "参数为空");
		}
		// 业务规则校验
		if (GoType.GROUP.getValue().equals(goCreateReq.getGoType())) {
			// 活动邀请人数不能为空
			if (StringUtil.isBlank(goCreateReq.getInviteMembers())) {
				throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "请输入活动邀请人数");
			}
			// 预期开始时间不能为空
			if (StringUtil.isBlank(goCreateReq.getExpectedStartTime())) {
				throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "请输入预期开始时间");
			}
		} else if (GoType.ONE_ON_ONE.getValue().equals(goCreateReq.getGoType())) {
			// 我的故事不能为空
			if (StringUtil.isBlank(goCreateReq.getMyStory())) {
				throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "请输入我的故事");
			}
			if (!PayMode.FIXED_FEE.getValue().equals(goCreateReq.getPayMode())) {
				throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "One On One的活动付费方式只能选择固定费用");
			}
		}
		if (PayMode.FIXED_FEE.getValue().equals(goCreateReq.getPayMode())
				|| PayMode.AA.getValue().equals(goCreateReq.getPayMode())) {
			// 如果活动付费为固定费用或AA，则费用不能为空
			if (StringUtil.isBlank(goCreateReq.getPrice())) {
				throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "请输入活动费用");
			}
		}
		if (OrgMode.OFFLINE.getValue().equals(goCreateReq.getOrgMode())) {
			if (StringUtil.isBlank(goCreateReq.getLocation())) {
				throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "活动组织为线下，请输入活动地点");
			}
		}
		if (CollectionUtil.isEmpty(goCreateReq.getGoDetails())) {
			throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "请填写活动详情");
		}
		int detailTextCount = 0;
		for (GoDetail detail : goCreateReq.getGoDetails()) {
			if (StringUtil.isBlank(detail.getType())) {
				throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "活动详情类型为空");
			}
			boolean valid = ValidatorUtil.validate(detail.getType(), GoDetailType.class.getEnumConstants());
			if (!valid) {
				throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "活动详情类型取值不合规");
			}
			if (GoDetailType.TEXT.getValue().equals(detail.getType())) {
				detailTextCount++;
				if (StringUtil.isBlank(detail.getDetail())) {
					throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "活动详情为空");
				}
			} else if (GoDetailType.IMAGE.getValue().equals(detail.getType())) {
				if (StringUtil.isBlank(detail.getImageUrl())) {
					throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "请上传活动详情图片");
				}
			}
		}
		if (detailTextCount == 0) {
			throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "请至少输入一条活动详情介绍");
		}

		if (CollectionUtil.isEmpty(goCreateReq.getGoTags())) {
			throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "请至少选择一个活动标签");
		}
		if (goCreateReq.getGoTags().size() > 5) {
			throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "最多只能选择5个活动标签");
		}
		for (GoTag tag : goCreateReq.getGoTags()) {
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
	public GoOrderCreateResp orderOneOnOne(GoOrderCreateReq goOrderCreateReq)
			throws BusinessException, SystemException {
		String orderId = goBusiSV.orderOneOnOne(goOrderCreateReq);
		GoOrderCreateResp resp = new GoOrderCreateResp();
		ResponseHeader responseHeader = ResponseBuilder.buildSuccessResponseHeader("ok");
		resp.setOrderId(orderId);
		resp.setResponseHeader(responseHeader);
		return resp;
	}

	@Override
	public CreateGoPaymentOrderResp createGoPaymentOrder(CreateGoPaymentOrderReq createGoPaymentOrderReq)
			throws BusinessException, SystemException {
		// 校验用户编码是否正确
		HyUser user = userManagerSV.getUserInfo(createGoPaymentOrderReq.getUserId());
		if (user == null) {
			throw new BusinessException("USER_0001", "支付订单创建失败,用户没有注册绑定");
		}
		String payOrderId = goBusiSV.createGoPaymentOrder(createGoPaymentOrderReq);
		CreateGoPaymentOrderResp resp = new CreateGoPaymentOrderResp();
		ResponseHeader responseHeader = ResponseBuilder.buildSuccessResponseHeader("支付交易订单创建成功");
		resp.setResponseHeader(responseHeader);
		resp.setPayOrderId(payOrderId);
		return resp;
	}

	@Override
	public GoOrderQueryResp queryGoOrder(GoOrderQueryReq goOrderQueryReq) throws BusinessException, SystemException {
		// 获取预约流水记录
		HyGoOrder hyGoOrder = goBusiSV.getHyGoOrder(goOrderQueryReq.getGoOrderId());
		if (hyGoOrder == null) {
			throw new BusinessException("GO_0001", "预约记录不存在");
		}
		// 获取活动信息
		HyGo hyGo = goBusiSV.getHyGo(hyGoOrder.getGoId());
		if (hyGo == null) {
			throw new BusinessException("GO_0001", "活动信息不存在");
		}
		GoOrder goOrder = new GoOrder();
		BeanUtils.copyProperties(hyGoOrder, goOrder);
		goOrder.setPublishUserId(hyGo.getUserId());
		goOrder.setTopic(hyGo.getTopic());
		goOrder.setFixedPrice(hyGo.getFixedPrice());
		goOrder.setOrderStatusName(HyDictUtil.getHyDictDesc(TypeCode.HY_GO_ORDER.getValue(),
				ParamCode.ORDER_STATUS.getValue(), hyGoOrder.getOrderStatus()));
		ResponseHeader responseHeader = ResponseBuilder.buildSuccessResponseHeader("查询成功");
		GoOrderQueryResp resp = new GoOrderQueryResp();
		resp.setGoOrder(goOrder);
		resp.setResponseHeader(responseHeader);
		return resp;
	}

	@Override
	public Response updateGoOrderPay(UpdateGoOrderPayReq updateGoOrderPayReq)
			throws BusinessException, SystemException {
		if (!"SUCCESS".equals(updateGoOrderPayReq.getPayStatus())
				&& !"FAIL".equals(updateGoOrderPayReq.getPayStatus())) {
			throw new BusinessException("GO_0001", "支付结果状态不合格");
		}
		goBusiSV.updateGoOrderPay(updateGoOrderPayReq);
		return ResponseBuilder.buildSuccessResponse("修改成功");
	}

	@Override
	public GoQueryResp queryGo(GoQueryReq goQueryReq) throws BusinessException, SystemException {
		// 获取活动信息
		HyGo hyGo = goBusiSV.getHyGo(goQueryReq.getGoId());
		if (hyGo == null) {
			throw new BusinessException("GO_0001", "活动信息不存在");
		}
		Go go = new Go();
		BeanUtils.copyProperties(hyGo, go);
		go.setGoTypeName(
				HyDictUtil.getHyDictDesc(TypeCode.HY_GO.getValue(), ParamCode.GO_TYPE.getValue(), hyGo.getGoType()));
		ResponseHeader responseHeader = ResponseBuilder.buildSuccessResponseHeader("查询成功");
		GoQueryResp resp = new GoQueryResp();
		resp.setGo(go);
		resp.setResponseHeader(responseHeader);
		return resp;
	}

	@Override
	public GoOrderQueryResp queryUserOrderGo(GoOrderQueryReq goOrderQueryReq)
			throws BusinessException, SystemException {
		GoOrder goOrder = null;
		HyGoOrder hyGoOrder = goBusiSV.getHyGoOrder(goOrderQueryReq.getUserId(), goOrderQueryReq.getGoId());
		if (hyGoOrder != null) {
			HyGo hyGo = goBusiSV.getHyGo(hyGoOrder.getGoId());
			if (hyGo == null) {
				throw new BusinessException("GO_0001", "活动信息不存在");
			}

			goOrder = new GoOrder();
			BeanUtils.copyProperties(hyGoOrder, goOrder);
			goOrder.setPublishUserId(hyGo.getUserId());
			goOrder.setTopic(hyGo.getTopic());
			goOrder.setFixedPrice(hyGo.getFixedPrice());
			goOrder.setOrderStatusName(HyDictUtil.getHyDictDesc(TypeCode.HY_GO_ORDER.getValue(),
					ParamCode.ORDER_STATUS.getValue(), hyGoOrder.getOrderStatus()));
		}

		ResponseHeader responseHeader = ResponseBuilder.buildSuccessResponseHeader("查询成功");
		GoOrderQueryResp resp = new GoOrderQueryResp();
		resp.setGoOrder(goOrder);
		resp.setResponseHeader(responseHeader);
		return resp;
	}

	@Override
	public Response confirmGoOrder(GoOrderConfirmReq goOrderConfirmReq) throws BusinessException, SystemException {
		if (!("confirm".equals(goOrderConfirmReq.getAckFlag()) || "reject".equals(goOrderConfirmReq.getAckFlag()))) {
			throw new BusinessException("确认码取值不正确");
		}
		goBusiSV.confirmGoOrder(goOrderConfirmReq);
		return ResponseBuilder.buildSuccessResponse("确认成功");
	}

	@Override
	public Response setGoOrderMeetLocaltion(GoOrderMeetLocaltionReq goOrderMeetLocaltionReq)
			throws BusinessException, SystemException {
		if (StringUtil.isBlank(goOrderMeetLocaltionReq.getExpectedTime2())
				&& StringUtil.isBlank(goOrderMeetLocaltionReq.getExpectedLocation2())) {
			throw new BusinessException("第2条时间地点信息不完整");
		}
		goBusiSV.setGoOrderMeetLocaltion(goOrderMeetLocaltionReq);
		return ResponseBuilder.buildSuccessResponse("设置成功");
	}

	@Override
	public Response confirmGoOrderMeetLocaltion(GoOrderMeetLocaltionConfirmReq goOrderMeetLocaltionConfirmReq)
			throws BusinessException, SystemException {
		goBusiSV.confirmGoOrderMeetLocaltion(goOrderMeetLocaltionConfirmReq);
		return ResponseBuilder.buildSuccessResponse("确认成功");
	}

	@Override
	public Response finishGoOrder(GoOrderFinishReq goOrderFinishReq) throws BusinessException, SystemException {
		goBusiSV.finishGoOrder(goOrderFinishReq);
		return ResponseBuilder.buildSuccessResponse("确认成功");
	}

	@Override
	public QueryMyGoResp queryMyGoes(QueryMyGoReq queryMyGoReq) throws BusinessException, SystemException {
		int start = (queryMyGoReq.getPageNo() - 1) * queryMyGoReq.getPageSize();
		int end = queryMyGoReq.getPageNo() * queryMyGoReq.getPageSize();
		SortBuilder sortBuilder = SortBuilders.fieldSort("createDate").order(SortOrder.DESC);
		BoolQueryBuilder builder = QueryBuilders.boolQuery();
		builder.must(QueryBuilders.termQuery("userId", queryMyGoReq.getUserId()))
				.must(QueryBuilders.termQuery("goType", queryMyGoReq.getGoType()));
		SearchResponse response = ElasticSearchFactory.getClient().prepareSearch(HarborIndex.HY_GO_DB.getValue())
				.setTypes(HarborIndexType.HY_GO.getValue()).setFrom(start).setSize(end - start).setQuery(builder)
				.addSort(sortBuilder).execute().actionGet();
		SearchHits hits = response.getHits();
		long total = hits.getTotalHits();
		List<Go> result = new ArrayList<Go>();
		for (SearchHit hit : hits) {
			Go go = JSON.parseObject(hit.getSourceAsString(), Go.class);
			this.fillGoInfo(go);
			result.add(go);
		}
		PageInfo<Go> pageInfo = new PageInfo<Go>();
		pageInfo.setCount(Integer.parseInt(total + ""));
		pageInfo.setPageNo(queryMyGoReq.getPageNo());
		pageInfo.setPageSize(queryMyGoReq.getPageSize());
		pageInfo.setResult(result);
		ResponseHeader responseHeader = ResponseBuilder.buildSuccessResponseHeader("查询成功");
		QueryMyGoResp resp = new QueryMyGoResp();
		resp.setPagInfo(pageInfo);
		resp.setResponseHeader(responseHeader);
		return resp;
	}

	@Override
	public QueryGoResp queryGoes(QueryGoReq queryGoReq) throws BusinessException, SystemException {
		int start = (queryGoReq.getPageNo() - 1) * queryGoReq.getPageSize();
		int end = queryGoReq.getPageNo() * queryGoReq.getPageSize();
		SortBuilder sortBuilder = SortBuilders.fieldSort("createDate").order(SortOrder.DESC);
		BoolQueryBuilder builder = QueryBuilders.boolQuery();
		builder.must(QueryBuilders.termQuery("goType", queryGoReq.getGoType()));
		if (!StringUtil.isBlank(queryGoReq.getGoTag())) {
			builder.must(QueryBuilders.termQuery("tagId", queryGoReq.getGoTag()));
		}
		if (!StringUtil.isBlank(queryGoReq.getSearchKey())) {
			builder.must(QueryBuilders.queryStringQuery(queryGoReq.getSearchKey()));
		}
		SearchResponse response = ElasticSearchFactory.getClient().prepareSearch(HarborIndex.HY_GO_DB.getValue())
				.setTypes(HarborIndexType.HY_GO.getValue()).setFrom(start).setSize(end - start).setQuery(builder)
				.addSort(sortBuilder).execute().actionGet();
		SearchHits hits = response.getHits();
		long total = hits.getTotalHits();
		List<Go> result = new ArrayList<Go>();
		for (SearchHit hit : hits) {
			Go go = JSON.parseObject(hit.getSourceAsString(), Go.class);
			this.fillGoInfo(go);
			result.add(go);
		}
		PageInfo<Go> pageInfo = new PageInfo<Go>();
		pageInfo.setCount(Integer.parseInt(total + ""));
		pageInfo.setPageNo(queryGoReq.getPageNo());
		pageInfo.setPageSize(queryGoReq.getPageSize());
		pageInfo.setResult(result);
		ResponseHeader responseHeader = ResponseBuilder.buildSuccessResponseHeader("查询成功");
		QueryGoResp resp = new QueryGoResp();
		resp.setPagInfo(pageInfo);
		resp.setResponseHeader(responseHeader);
		return resp;
	}

	/**
	 * 完善GO信息
	 * 
	 * @param go
	 */
	private void fillGoInfo(Go go) {
		go.setGoTypeName(
				HyDictUtil.getHyDictDesc(TypeCode.HY_GO.getValue(), ParamCode.GO_TYPE.getValue(), go.getGoType()));
		go.setPayModeName(
				HyDictUtil.getHyDictDesc(TypeCode.HY_GO.getValue(), ParamCode.PAY_MODE.getValue(), go.getPayMode()));
		go.setOrgModeName(
				HyDictUtil.getHyDictDesc(TypeCode.HY_GO.getValue(), ParamCode.ORG_MODE.getValue(), go.getOrgMode()));
		// 发布用户信息
		UserViewInfo createUserInfo = this.getUserViewInfoByUserId(go.getUserId());
		go.setAtCityName(createUserInfo.getAtCityName());
		go.setEnName(createUserInfo.getEnName());
		go.setIndustryName(createUserInfo.getIndustryName());
		go.setTitle(createUserInfo.getTitle());
		go.setWxHeadimg(createUserInfo.getWxHeadimg());
		go.setUserStatusName(createUserInfo.getUserStatusName());
		go.setAbroadCountryName(createUserInfo.getAbroadCountryName());

		go.setFavorCount(0);
		go.setViewCount(0);
		go.setJoinCount(0);
		go.setCreateTimeStr(DateUtil.getDateString(go.getCreateDate(), DateUtil.DATE_FORMAT));
		go.setCreateTimeInterval(DateUtil.getInterval(go.getCreateDate()));
	}

	/**
	 * 获取用户信息
	 * 
	 * @param userId
	 * @return
	 */
	private UserViewInfo getUserViewInfoByUserId(String userId) {
		UserViewInfo userInfo = null;
		// 从REDIS中读取用户信息
		String userData = HyUserUtil.getUserInfoFromRedis(userId);
		if (StringUtil.isBlank(userData)) {
			// 如果换成没有用户信息，则查询库
			userInfo = userManagerSV.getUserViewInfoByUserId(userId);
			if (userInfo == null) {
				HyUserUtil.storeUserInfo2Redis(userId, JSON.toJSONString(userInfo));
			}
		} else {
			userInfo = JSONObject.parseObject(userData, UserViewInfo.class);
		}
		return userInfo;
	}

}
