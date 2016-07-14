package com.the.harbor.api.go.param;

import com.the.harbor.base.vo.PageInfo;
import com.the.harbor.base.vo.Response;

public class QueryMyFavorGoResp extends Response {

	private static final long serialVersionUID = 1L;

	private PageInfo<Go> pagInfo;

	public PageInfo<Go> getPagInfo() {
		return pagInfo;
	}

	public void setPagInfo(PageInfo<Go> pagInfo) {
		this.pagInfo = pagInfo;
	}

}
