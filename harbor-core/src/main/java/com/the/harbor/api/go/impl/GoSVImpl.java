package com.the.harbor.api.go.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.opensearch.CloudsearchClient;
import com.aliyun.opensearch.CloudsearchSearch;
import com.the.harbor.api.go.IGoSV;
import com.the.harbor.api.go.param.CheckUserOrderGoReq;
import com.the.harbor.api.go.param.CheckUserOrderGoResp;
import com.the.harbor.api.go.param.CreateGoPaymentOrderReq;
import com.the.harbor.api.go.param.CreateGoPaymentOrderResp;
import com.the.harbor.api.go.param.DeleteGoReq;
import com.the.harbor.api.go.param.DoGoDelete;
import com.the.harbor.api.go.param.GiveHBReq;
import com.the.harbor.api.go.param.Go;
import com.the.harbor.api.go.param.GoCreateReq;
import com.the.harbor.api.go.param.GoCreateResp;
import com.the.harbor.api.go.param.GoDetail;
import com.the.harbor.api.go.param.GoJoin;
import com.the.harbor.api.go.param.GoJoinQueryReq;
import com.the.harbor.api.go.param.GoJoinQueryResp;
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
import com.the.harbor.api.go.param.GoStory;
import com.the.harbor.api.go.param.GoTag;
import com.the.harbor.api.go.param.GroupApplyReq;
import com.the.harbor.api.go.param.GroupApplyResp;
import com.the.harbor.api.go.param.HideGoReq;
import com.the.harbor.api.go.param.QueryGoReq;
import com.the.harbor.api.go.param.QueryGoResp;
import com.the.harbor.api.go.param.QueryMyFavorGoReq;
import com.the.harbor.api.go.param.QueryMyFavorGoResp;
import com.the.harbor.api.go.param.QueryMyGoReq;
import com.the.harbor.api.go.param.QueryMyGoResp;
import com.the.harbor.api.go.param.QueryMyJointGoReq;
import com.the.harbor.api.go.param.QueryMyJointGoResp;
import com.the.harbor.api.go.param.QueryOrderGoRecordReq;
import com.the.harbor.api.go.param.QueryOrderGoRecordResp;
import com.the.harbor.api.go.param.SubmitGoHelpReq;
import com.the.harbor.api.go.param.TopGoReq;
import com.the.harbor.api.go.param.UpdateGoJoinPayReq;
import com.the.harbor.api.go.param.UpdateGoOrderPayReq;
import com.the.harbor.api.user.param.UserViewInfo;
import com.the.harbor.base.constants.ExceptCodeConstants;
import com.the.harbor.base.enumeration.dict.ParamCode;
import com.the.harbor.base.enumeration.dict.TypeCode;
import com.the.harbor.base.enumeration.hygo.GoDetailType;
import com.the.harbor.base.enumeration.hygo.GoType;
import com.the.harbor.base.enumeration.hygo.HideFlag;
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
import com.the.harbor.commons.components.aliyuncs.opensearch.OpenSearchFactory;
import com.the.harbor.commons.redisdata.util.HyDictUtil;
import com.the.harbor.commons.redisdata.util.HyGoUtil;
import com.the.harbor.commons.util.CollectionUtil;
import com.the.harbor.commons.util.DateUtil;
import com.the.harbor.commons.util.StringUtil;
import com.the.harbor.dao.mapper.bo.HyGo;
import com.the.harbor.dao.mapper.bo.HyGoJoin;
import com.the.harbor.dao.mapper.bo.HyGoOrder;
import com.the.harbor.service.interfaces.IGoBusiSV;
import com.the.harbor.service.interfaces.IUserManagerSV;
import com.the.harbor.util.BeGoDeleteMQSend;

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

		if (GoType.ONE_ON_ONE.getValue().equals(goCreateReq.getGoType())) {
			if (CollectionUtil.isEmpty(goCreateReq.getGoStories())) {
				throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "请填写您的故事");
			}

			for (GoStory detail : goCreateReq.getGoStories()) {
				if (StringUtil.isBlank(detail.getType())) {
					throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "故事详情类型为空");
				}
				boolean valid = ValidatorUtil.validate(detail.getType(), GoDetailType.class.getEnumConstants());
				if (!valid) {
					throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "故事详情类型取值不合规");
				}
				if (GoDetailType.TEXT.getValue().equals(detail.getType())) {
					if (StringUtil.isBlank(detail.getDetail())) {
						throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "故事详情为空");
					}
				} else if (GoDetailType.IMAGE.getValue().equals(detail.getType())) {
					if (StringUtil.isBlank(detail.getImageUrl())) {
						throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "请上传故事图片");
					}
				}
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
		UserViewInfo user = userManagerSV.getUserViewInfoByUserId(createGoPaymentOrderReq.getUserId());
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
		Go go = goBusiSV.getGoInfo(hyGoOrder.getGoId());
		GoOrder goOrder = new GoOrder();
		BeanUtils.copyProperties(hyGoOrder, goOrder);
		goOrder.setPublishUserId(go.getUserId());
		goOrder.setTopic(go.getTopic());
		goOrder.setFixedPrice(go.getFixedPrice());
		goOrder.setOrderStatusName(HyDictUtil.getHyDictDesc(TypeCode.HY_GO_ORDER.getValue(),
				ParamCode.ORDER_STATUS.getValue(), hyGoOrder.getOrderStatus()));
		goOrder.setHelpValueName(HyDictUtil.getHyDictDesc(TypeCode.HY_GO_ORDER.getValue(),
				ParamCode.HELP_VALUE.getValue(), hyGoOrder.getHelpValue()));
		goOrder.setOrgModeName(go.getOrgModeName());
		goOrder.setOrderCount(goBusiSV.getOrderCount(go.getGoId(), go.getGoType()));

		UserViewInfo userInfo = userManagerSV.getUserViewInfoByUserId(goOrder.getUserId());
		goOrder.setAbroadCountryName(userInfo.getAbroadCountryName());
		goOrder.setAbroadCountryRGB(userInfo.getAbroadCountryRGB());
		goOrder.setAtCityName(userInfo.getAtCityName());
		goOrder.setEnName(userInfo.getEnName());
		goOrder.setWxHeadimg(userInfo.getWxHeadimg());
		goOrder.setHomePageBg(userInfo.getHomePageBg());
		goOrder.setIndustryName(userInfo.getIndustryName());
		goOrder.setTitle(userInfo.getTitle());
		goOrder.setUserStatus(userInfo.getUserStatus());
		goOrder.setUserStatusName(userInfo.getUserStatusName());

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
		Go go = goBusiSV.getGoInfo(goQueryReq.getGoId());
		ResponseHeader responseHeader = ResponseBuilder.buildSuccessResponseHeader("查询成功");
		GoQueryResp resp = new GoQueryResp();
		resp.setGo(go);
		resp.setResponseHeader(responseHeader);
		return resp;
	}

	@Override
	public GoOrderQueryResp queryUserOrderGoes(GoOrderQueryReq goOrderQueryReq)
			throws BusinessException, SystemException {
		List<GoOrder>  goOrders = new ArrayList<GoOrder>();
		List<HyGoOrder> hyGoOrders = goBusiSV.getHyGoOrders(goOrderQueryReq.getUserId(), goOrderQueryReq.getGoId());
		if (!CollectionUtil.isEmpty(hyGoOrders)) {
			for (HyGoOrder hyGoOrder : hyGoOrders) {
				HyGo hyGo = goBusiSV.getHyGo(hyGoOrder.getGoId());
				if (hyGo == null) {
					continue;
				}
				GoOrder goOrder = new GoOrder();
				BeanUtils.copyProperties(hyGoOrder, goOrder);
				goOrder.setPublishUserId(hyGo.getUserId());
				goOrder.setTopic(hyGo.getTopic());
				goOrder.setFixedPrice(hyGo.getFixedPrice());
				goOrder.setOrderStatusName(HyDictUtil.getHyDictDesc(TypeCode.HY_GO_ORDER.getValue(),
						ParamCode.ORDER_STATUS.getValue(), hyGoOrder.getOrderStatus()));
				goOrders.add(goOrder);
			}

		}

		ResponseHeader responseHeader = ResponseBuilder.buildSuccessResponseHeader("查询成功");
		GoOrderQueryResp resp = new GoOrderQueryResp();
		resp.setGoOrders(goOrders);
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
		PageInfo<Go> pageInfo = this.queryMyGoesFromOpenSearch(queryMyGoReq);
		ResponseHeader responseHeader = ResponseBuilder.buildSuccessResponseHeader("查询成功");
		QueryMyGoResp resp = new QueryMyGoResp();
		resp.setPagInfo(pageInfo);
		resp.setResponseHeader(responseHeader);
		return resp;
	}

	private PageInfo<Go> queryMyGoesFromOpenSearch(QueryMyGoReq queryMyGoReq) {
		int start = (queryMyGoReq.getPageNo() - 1) * queryMyGoReq.getPageSize();
		CloudsearchClient client = OpenSearchFactory.getClient();
		CloudsearchSearch search = new CloudsearchSearch(client);
		search.addIndex("harbor_go");
		search.addCustomConfig("start", start);
		search.addCustomConfig("hit", queryMyGoReq.getPageSize());
		// 状态必须是有效的
		StringBuffer query = new StringBuffer();
		query.append("(status:'" + com.the.harbor.base.enumeration.hygo.Status.ING.getValue() + "' OR status:'"
				+ com.the.harbor.base.enumeration.hygo.Status.END.getValue() + "')");
		query.append(" AND userid:'" + queryMyGoReq.getUserId() + "'");
		query.append(" AND gotype:'" + queryMyGoReq.getGoType() + "'");
		search.setQueryString(query.toString());
		// 按照BEID进行聚合搜索排重
		search.addDistinct("goid", 1, 1, "false");
		search.setPair("duniqfield:goid");
		// 指定搜索返回的格式。
		search.setFormat("json");
		// 设定排序方式 + 表示正序 - 表示降序
		search.addSort("createdate", "-");
		// 返回搜索结果
		List<Go> result = new ArrayList<Go>();
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
				String goId = data.getString("goid");
				Go go = goBusiSV.getGoInfo(goId);
				if (go != null) {
					result.add(go);
				}
			}

		}

		PageInfo<Go> pageInfo = new PageInfo<Go>();
		pageInfo.setCount(Integer.parseInt(total + ""));
		pageInfo.setPageNo(queryMyGoReq.getPageNo());
		pageInfo.setPageSize(queryMyGoReq.getPageSize());
		pageInfo.setResult(result);
		return pageInfo;
	}

	@Override
	public QueryGoResp queryGoes(QueryGoReq queryGoReq) throws BusinessException, SystemException {
		PageInfo<Go> pageInfo = this.queryHyGoesFromOpenSearch(queryGoReq);
		ResponseHeader responseHeader = ResponseBuilder.buildSuccessResponseHeader("查询成功");
		QueryGoResp resp = new QueryGoResp();
		resp.setPagInfo(pageInfo);
		resp.setResponseHeader(responseHeader);
		return resp;
	}

	private PageInfo<Go> queryHyGoesFromOpenSearch(QueryGoReq queryGoReq) {
		int start = (queryGoReq.getPageNo() - 1) * queryGoReq.getPageSize();
		CloudsearchClient client = OpenSearchFactory.getClient();
		CloudsearchSearch search = new CloudsearchSearch(client);
		search.addIndex("harbor_go");
		search.addCustomConfig("start", start);
		search.addCustomConfig("hit", queryGoReq.getPageSize());
		// 状态必须是有效的
		StringBuffer query = new StringBuffer();
		query.append(" gotype:'" + queryGoReq.getGoType() + "'");
		query.append(" AND (status:'" + com.the.harbor.base.enumeration.hygo.Status.ING.getValue() + "' OR status:'"
				+ com.the.harbor.base.enumeration.hygo.Status.END.getValue() + "')");
		if (!StringUtil.isBlank(queryGoReq.getTagId())) {
			query.append(" AND betagids:'" + queryGoReq.getTagId() + "'");
		}
		if (!StringUtil.isBlank(queryGoReq.getPolyTagId())) {
			query.append(" AND polytagids:'" + queryGoReq.getPolyTagId() + "'");
		}
		if (!StringUtil.isBlank(queryGoReq.getSearchKey())) {
			query.append(" AND default:'" + queryGoReq.getSearchKey() + "'");
		}
		if (!queryGoReq.isQueryhide()) {
			// 不查询隐藏记录
			query.append(" AND hideflag:'" + HideFlag.NO.getValue() + "'");
		}
		search.setQueryString(query.toString());
		// 按照BEID进行聚合搜索排重
		search.addDistinct("goid", 1, 1, "false");
		search.setPair("duniqfield:goid");
		// 指定搜索返回的格式。
		search.setFormat("json");
		// 设定排序方式 + 表示正序 - 表示降序
		search.addSort("topdate", "-");
		search.addSort("createdate", "-");
		// 返回搜索结果
		List<Go> result = new ArrayList<Go>();
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
				String goId = data.getString("goid");
				Go go = goBusiSV.getGoInfo(goId);
				if (go != null) {
					result.add(go);
				}
			}

		}

		PageInfo<Go> pageInfo = new PageInfo<Go>();
		pageInfo.setCount(Integer.parseInt(total + ""));
		pageInfo.setPageNo(queryGoReq.getPageNo());
		pageInfo.setPageSize(queryGoReq.getPageSize());
		pageInfo.setResult(result);
		return pageInfo;
	}

	@Override
	public GroupApplyResp applyGroup(GroupApplyReq groupApplyReq) throws BusinessException, SystemException {
		GroupApplyResp resp = goBusiSV.applyGroup(groupApplyReq);
		ResponseHeader responseHeader = ResponseBuilder.buildSuccessResponseHeader("处理成功");
		resp.setResponseHeader(responseHeader);
		return resp;
	}

	@Override
	public Response updateGoJoinPay(UpdateGoJoinPayReq updateGoJoinPayReq) throws BusinessException, SystemException {
		if (!"SUCCESS".equals(updateGoJoinPayReq.getPayStatus()) && !"FAIL".equals(updateGoJoinPayReq.getPayStatus())) {
			throw new BusinessException("GO_0001", "支付结果状态不合格");
		}
		goBusiSV.updateGoJoinPay(updateGoJoinPayReq);
		return ResponseBuilder.buildSuccessResponse("修改成功");
	}

	@Override
	public CheckUserOrderGoResp checkUserOrderGo(CheckUserOrderGoReq checkUserOrderGoReq)
			throws BusinessException, SystemException {
		boolean check = goBusiSV.checkUserOrderGo(checkUserOrderGoReq);
		ResponseHeader responseHeader = ResponseBuilder.buildSuccessResponseHeader("处理成功");
		CheckUserOrderGoResp resp = new CheckUserOrderGoResp();
		resp.setResponseHeader(responseHeader);
		resp.setCheckflag(check);
		return resp;
	}

	@Override
	public QueryMyFavorGoResp queryMyFavorGoes(QueryMyFavorGoReq queryMyGoReq)
			throws BusinessException, SystemException {
		long total = HyGoUtil.getUserFavorGoesCount(queryMyGoReq.getUserId(), queryMyGoReq.getGoType());
		Set<String> goIds = HyGoUtil.getUserFavorGoesPage(queryMyGoReq.getUserId(), queryMyGoReq.getGoType(),
				queryMyGoReq.getPageNo(), queryMyGoReq.getPageSize(), false);
		List<Go> result = new ArrayList<Go>();
		for (String goId : goIds) {
			result.add(goBusiSV.getGoInfo(goId));
		}
		PageInfo<Go> pageInfo = new PageInfo<Go>();
		pageInfo.setCount(Integer.parseInt(total + ""));
		pageInfo.setPageNo(queryMyGoReq.getPageNo());
		pageInfo.setPageSize(queryMyGoReq.getPageSize());
		pageInfo.setResult(result);
		ResponseHeader responseHeader = ResponseBuilder.buildSuccessResponseHeader("查询成功");
		QueryMyFavorGoResp resp = new QueryMyFavorGoResp();
		resp.setPagInfo(pageInfo);
		resp.setResponseHeader(responseHeader);
		return resp;
	}

	@Override
	public QueryMyJointGoResp queryMyJointGoes(QueryMyJointGoReq queryMyGoReq)
			throws BusinessException, SystemException {
		String goType = queryMyGoReq.getGoType();
		String userId = queryMyGoReq.getUserId();
		int total = goBusiSV.getMyJointGoCount(userId, goType);
		List<Go> result = new ArrayList<Go>();
		if (GoType.ONE_ON_ONE.getValue().equals(goType)) {
			List<HyGoOrder> list = goBusiSV.getMyJointGroupGoes(queryMyGoReq);
			if (!CollectionUtil.isEmpty(list)) {
				for (HyGoOrder go : list) {
					result.add(goBusiSV.getGoInfo(go.getGoId()));
				}
			}
		} else if (GoType.GROUP.getValue().equals(goType)) {
			List<HyGoJoin> list = goBusiSV.getMyJointOnOGoes(queryMyGoReq);
			if (!CollectionUtil.isEmpty(list)) {
				for (HyGoJoin go : list) {
					result.add(goBusiSV.getGoInfo(go.getGoId()));
				}
			}
		}
		PageInfo<Go> pageInfo = new PageInfo<Go>();
		pageInfo.setCount(Integer.parseInt(total + ""));
		pageInfo.setPageNo(queryMyGoReq.getPageNo());
		pageInfo.setPageSize(queryMyGoReq.getPageSize());
		pageInfo.setResult(result);
		ResponseHeader responseHeader = ResponseBuilder.buildSuccessResponseHeader("查询成功");
		QueryMyJointGoResp resp = new QueryMyJointGoResp();
		resp.setPagInfo(pageInfo);
		resp.setResponseHeader(responseHeader);
		return resp;
	}

	@Override
	public Response submitGoHelp(SubmitGoHelpReq submitGoHelpReq) throws BusinessException, SystemException {
		goBusiSV.submitGoHelp(submitGoHelpReq);
		return ResponseBuilder.buildSuccessResponse("操作成功");
	}

	@Override
	public Response giveHaibei(GiveHBReq giveHBReq) throws BusinessException, SystemException {
		goBusiSV.giveHaibei(giveHBReq);
		return ResponseBuilder.buildSuccessResponse("操作成功");
	}

	@Override
	public QueryOrderGoRecordResp queryOrderGoRecords(QueryOrderGoRecordReq queryOrderGoRecordReq)
			throws BusinessException, SystemException {
		String goId = queryOrderGoRecordReq.getGoId();
		String goType = queryOrderGoRecordReq.getGoType();
		ResponseHeader responseHeader = ResponseBuilder.buildSuccessResponseHeader("查询成功");
		QueryOrderGoRecordResp resp = new QueryOrderGoRecordResp();
		if (GoType.GROUP.getValue().equals(goType)) {
			List<GoJoin> goJoins = goBusiSV.getGoJoins(goId);
			resp.setGoJoins(goJoins);
		} else if (GoType.ONE_ON_ONE.getValue().equals(goType)) {
			List<GoOrder> goOrders = goBusiSV.getGoOrders(goId);
			resp.setGoOrders(goOrders);
		}
		resp.setResponseHeader(responseHeader);
		return resp;
	}

	@Override
	public GoJoinQueryResp queryGoJoin(GoJoinQueryReq goJoinQueryReq) throws BusinessException, SystemException {// 获取预约流水记录
		HyGoJoin hyGoJoin = goBusiSV.getHyGoJoin(goJoinQueryReq.getGoOrderId());
		if (hyGoJoin == null) {
			throw new BusinessException("GO_0001", "预约记录不存在");
		}
		// 获取活动信息
		Go go = goBusiSV.getGoInfo(hyGoJoin.getGoId());
		GoJoin goJoin = new GoJoin();
		BeanUtils.copyProperties(hyGoJoin, goJoin);
		goJoin.setPublishUserId(go.getUserId());
		goJoin.setTopic(go.getTopic());
		goJoin.setFixedPrice(go.getFixedPrice());
		goJoin.setOrderStatusName(HyDictUtil.getHyDictDesc(TypeCode.HY_GO_JOIN.getValue(),
				ParamCode.ORDER_STATUS.getValue(), hyGoJoin.getOrderStatus()));
		goJoin.setHelpValueName(HyDictUtil.getHyDictDesc(TypeCode.HY_GO_JOIN.getValue(),
				ParamCode.HELP_VALUE.getValue(), hyGoJoin.getHelpValue()));
		goJoin.setOrgModeName(go.getOrgModeName());
		goJoin.setOrderCount(goBusiSV.getOrderCount(go.getGoId(), go.getGoType()));

		UserViewInfo userInfo = userManagerSV.getUserViewInfoByUserId(goJoin.getUserId());
		goJoin.setAbroadCountryName(userInfo.getAbroadCountryName());
		goJoin.setAbroadCountryRGB(userInfo.getAbroadCountryRGB());
		goJoin.setAtCityName(userInfo.getAtCityName());
		goJoin.setEnName(userInfo.getEnName());
		goJoin.setWxHeadimg(userInfo.getWxHeadimg());
		goJoin.setHomePageBg(userInfo.getHomePageBg());
		goJoin.setIndustryName(userInfo.getIndustryName());
		goJoin.setTitle(userInfo.getTitle());
		goJoin.setUserStatus(userInfo.getUserStatus());
		goJoin.setUserStatusName(userInfo.getUserStatusName());
		ResponseHeader responseHeader = ResponseBuilder.buildSuccessResponseHeader("查询成功");
		GoJoinQueryResp resp = new GoJoinQueryResp();
		resp.setGoJoin(goJoin);
		resp.setResponseHeader(responseHeader);
		return resp;
	}

	@Override
	public GoJoinQueryResp queryUserJoinGo(GoJoinQueryReq goJoinQueryReq) throws BusinessException, SystemException {
		List<HyGoJoin> list = goBusiSV.getHyGoJoins(goJoinQueryReq.getUserId(), goJoinQueryReq.getGoId());
		HyGoJoin hyGoJoin = null;
		if (!CollectionUtil.isEmpty(list)) {
			hyGoJoin = list.get(0);
		}
		GoJoin goJoin = null;
		if (hyGoJoin != null) {
			Go go = goBusiSV.getGoInfo(hyGoJoin.getGoId());
			goJoin = new GoJoin();
			BeanUtils.copyProperties(hyGoJoin, goJoin);
			goJoin.setPublishUserId(go.getUserId());
			goJoin.setTopic(go.getTopic());
			goJoin.setFixedPrice(go.getFixedPrice());
			goJoin.setOrderStatusName(HyDictUtil.getHyDictDesc(TypeCode.HY_GO_JOIN.getValue(),
					ParamCode.ORDER_STATUS.getValue(), hyGoJoin.getOrderStatus()));
			goJoin.setHelpValueName(HyDictUtil.getHyDictDesc(TypeCode.HY_GO_JOIN.getValue(),
					ParamCode.HELP_VALUE.getValue(), hyGoJoin.getHelpValue()));
			goJoin.setOrgModeName(go.getOrgModeName());
			goJoin.setOrderCount(goBusiSV.getOrderCount(go.getGoId(), go.getGoType()));
			long time = DateUtil.getSysDate().getTime() - hyGoJoin.getCreateDate().getTime();
			goJoin.setDiffHours((int) (time / 3600000));
			UserViewInfo userInfo = userManagerSV.getUserViewInfoByUserId(goJoin.getUserId());
			goJoin.setAbroadCountryName(userInfo.getAbroadCountryName());
			goJoin.setAbroadCountryRGB(userInfo.getAbroadCountryRGB());
			goJoin.setAtCityName(userInfo.getAtCityName());
			goJoin.setEnName(userInfo.getEnName());
			goJoin.setWxHeadimg(userInfo.getWxHeadimg());
			goJoin.setHomePageBg(userInfo.getHomePageBg());
			goJoin.setIndustryName(userInfo.getIndustryName());
			goJoin.setTitle(userInfo.getTitle());
			goJoin.setUserStatus(userInfo.getUserStatus());
			goJoin.setUserStatusName(userInfo.getUserStatusName());
		}

		ResponseHeader responseHeader = ResponseBuilder.buildSuccessResponseHeader("查询成功");
		GoJoinQueryResp resp = new GoJoinQueryResp();
		resp.setGoJoin(goJoin);
		resp.setResponseHeader(responseHeader);
		return resp;
	}

	@Override
	public Response doGoFavorite(GroupApplyReq groupApplyReq) throws BusinessException, SystemException {
		goBusiSV.doGoFavorite(groupApplyReq);
		return ResponseBuilder.buildSuccessResponse("收藏成功");
	}

	@Override
	public Response deleteGo(DeleteGoReq deleteGoReq) throws BusinessException, SystemException {
		Go go = goBusiSV.getGoInfo(deleteGoReq.getGoId());
		if (go != null) {
			// 判断是否是发布者
			if (!go.getUserId().equals(deleteGoReq.getUserId())) {
				throw new BusinessException("您无权删除");
			}
			// 判断是否可以删除
			int count = goBusiSV.getOrderCount(go.getGoId(), go.getGoType());
			if (count > 0) {
				throw new BusinessException("有人已报名，不可删除");
			}
			// 将搜索引擎数据标记为撤销
			go.setStatus(com.the.harbor.base.enumeration.hygo.Status.CANCEL.getValue());

			HyGoUtil.recordGo(go.getGoId(), JSON.toJSONString(go));
			// 发送删除消息处理
			DoGoDelete body = new DoGoDelete();
			body.setGoId(deleteGoReq.getGoId());
			body.setGoType(go.getGoType());
			BeGoDeleteMQSend.sendMQ(body);
		}
		return ResponseBuilder.buildSuccessResponse("删除成功");
	}

	@Override
	public Response topGo(TopGoReq topGoReq) throws BusinessException, SystemException {
		Go go = goBusiSV.getGoInfo(topGoReq.getGoId());
		if (go != null) {
			if (topGoReq.isTop()) {
				go.setTopFlag(com.the.harbor.base.enumeration.hygo.TopFlag.YES.getValue());
				go.setTopDate(DateUtil.getSysDate());
			} else {
				go.setTopFlag(com.the.harbor.base.enumeration.hygo.TopFlag.NO.getValue());
				go.setTopDate(null);
			}
			HyGoUtil.recordGo(go.getGoId(), JSON.toJSONString(go));
			goBusiSV.topGo(go.getGoId(), go.getTopFlag(), go.getTopDate());
		}
		return ResponseBuilder.buildSuccessResponse("操作成功");
	}

	@Override
	public Response hideGo(HideGoReq hideGoReq) throws BusinessException, SystemException {
		Go go = goBusiSV.getGoInfo(hideGoReq.getGoId());
		if (go != null) {
			go.setHideFlag(hideGoReq.isHide() ? HideFlag.YES.getValue() : HideFlag.NO.getValue());
			HyGoUtil.recordGo(go.getGoId(), JSON.toJSONString(go));
			goBusiSV.hideGo(go.getGoId(), go.getHideFlag());
		}
		return ResponseBuilder.buildSuccessResponse("操作成功");
	}

}
