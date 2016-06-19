package com.the.harbor.api.go.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.the.harbor.api.go.IGoSV;
import com.the.harbor.api.go.param.GoCreateReq;
import com.the.harbor.api.go.param.GoCreateResp;
import com.the.harbor.api.go.param.GoDetail;
import com.the.harbor.api.go.param.GoTag;
import com.the.harbor.base.constants.ExceptCodeConstants;
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
import com.the.harbor.base.vo.ResponseHeader;
import com.the.harbor.commons.util.CollectionUtil;
import com.the.harbor.commons.util.StringUtil;
import com.the.harbor.service.interfaces.IGoBusiSV;

@Service(validation = "true")
public class GoSVImpl implements IGoSV {

	@Autowired
	private transient IGoBusiSV goBusiSV;

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
			if (GoDetailType.TEXT.equals(detail.getType())) {
				detailTextCount++;
				if (StringUtil.isBlank(detail.getDetail())) {
					throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "活动详情为空");
				}
			} else if (GoDetailType.IMAGE.equals(detail.getType())) {
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

}
