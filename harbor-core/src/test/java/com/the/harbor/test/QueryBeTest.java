package com.the.harbor.test;

import com.alibaba.fastjson.JSON;
import com.the.harbor.api.be.impl.BeSVImpl;
import com.the.harbor.api.be.param.QueryMyBeReq;
import com.the.harbor.api.be.param.QueryMyBeResp;

public class QueryBeTest {

	public static void main(String[] args) {
		BeSVImpl b = new BeSVImpl();
		QueryMyBeReq queryMyBeReq = new QueryMyBeReq();
		queryMyBeReq.setUserId("hy00000062");
		queryMyBeReq.setPageNo(1);
		queryMyBeReq.setPageSize(2);
		QueryMyBeResp resp = b.queryMyBe(queryMyBeReq);
		System.out.println(JSON.toJSONString(resp));

	}

}
