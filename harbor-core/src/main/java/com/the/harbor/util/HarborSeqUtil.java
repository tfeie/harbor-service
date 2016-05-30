package com.the.harbor.util;

import com.the.harbor.commons.components.sequence.util.SeqUtil;
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

	public static String createHySmsSendRecordId() {
		return UUIDUtil.genId32();
	}

}
