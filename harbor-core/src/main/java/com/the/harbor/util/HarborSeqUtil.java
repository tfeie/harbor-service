package com.the.harbor.util;

import com.the.harbor.base.constants.ExceptCodeConstants;
import com.the.harbor.base.exception.BusinessException;
import com.the.harbor.commons.components.sequence.util.SeqUtil;
import com.the.harbor.commons.util.StringUtil;
import com.the.harbor.commons.util.UUIDUtil;
import com.the.harbor.constants.HarborConstants;
import com.the.harbor.constants.HarborSeqConstants;

public final class HarborSeqUtil {
	private HarborSeqUtil() {

	}

	/**
	 * 获取用户的唯一标识
	 * 
	 * @return
	 * @author zhangchao
	 */
	public static String createHyUserId() {
		StringBuffer sb = new StringBuffer(HarborConstants.USER_ID_PREFIX);
		sb.append(SeqUtil.getNewId(HarborSeqConstants.HY_USER$USER_ID$SEQ, 8));
		return sb.toString();
	}

	/**
	 * 创建海湾身份证号码 国家+8位编号
	 * 
	 * @return
	 */
	public static String createHyUserHyId(String countryCode) {
		if (StringUtil.isBlank(countryCode)) {
			throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "生成海湾证件号错误:留学国家不能为空");
		}
		StringBuffer sb = new StringBuffer(countryCode);
		sb.append(SeqUtil.getNewId(HarborSeqConstants.HY_USER$HY_ID$SEQ, 8));
		return sb.toString();
	}

	public static String createHySmsSendRecordId() {
		return UUIDUtil.genId32();
	}

	public static long createHyUserTagsRecordId() {
		return SeqUtil.getNewId(HarborSeqConstants.HY_USER_TAGS$RECORD_ID$SEQ);
	}

	public static String createPayOrderId() {
		return UUIDUtil.genId32();
	}

	public static String createTagId(String tagType) {
		StringBuffer sb = new StringBuffer(tagType);
		sb.append(SeqUtil.getNewId(HarborSeqConstants.CUSTOMIZED$TAG_ID$SEQ, 6));
		return sb.toString();
	}

	public static String createGoId() {
		return UUIDUtil.genId32();
	}

	public static String createGoDetailId() {
		return UUIDUtil.genId32();
	}

	public static String createGoOrderId() {
		return UUIDUtil.genId32();
	}

	public static String createBeId() {
		return UUIDUtil.genId32();
	}

	public static String createBeDetailId() {
		return UUIDUtil.genId32();
	}

	public static String createBeLikesId() {
		return UUIDUtil.genId32();
	}

	public static String createBeCommentsId() {
		return UUIDUtil.genId32();
	}
	
	public static String createGoCommentsId() {
		return UUIDUtil.genId32();
	}
	
	public static String createGoFavoriteId() {
		return UUIDUtil.genId32();
	}

	public static String createGoViewId() {
		return UUIDUtil.genId32();
	}
	
	public static String createUserFansId() {
		return UUIDUtil.genId32();
	}
	
	public static String createUserFriendId() {
		return UUIDUtil.genId32();
	}

}
