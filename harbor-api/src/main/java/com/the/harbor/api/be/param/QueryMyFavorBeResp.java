package com.the.harbor.api.be.param;

import com.the.harbor.base.vo.PageInfo;
import com.the.harbor.base.vo.Response;

public class QueryMyFavorBeResp extends Response {

	private static final long serialVersionUID = 1L;

	private PageInfo<Be> pagInfo;

	public PageInfo<Be> getPagInfo() {
		return pagInfo;
	}

	public void setPagInfo(PageInfo<Be> pagInfo) {
		this.pagInfo = pagInfo;
	}
	
	

}
