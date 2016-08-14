package com.the.harbor.api.user.param;

import java.io.Serializable;

import com.the.harbor.base.vo.MNSBody;

/**
 * 同步给OPEN-IM的账户信息
 * @author zhangchao
 *
 */
public class DoIMUserSync extends MNSBody {

	private static final long serialVersionUID = 1L;

	public enum HandleType implements Serializable {
		ADD, UPDATE, DELETE;
	}

	private String handleType;

	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getHandleType() {
		return handleType;
	}

	public void setHandleType(String handleType) {
		this.handleType = handleType;
	}

}
