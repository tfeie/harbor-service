package com.the.harbor.test;

import com.alibaba.fastjson.JSON;
import com.the.harbor.api.be.impl.BeSVImpl;
import com.the.harbor.api.be.param.QueryOneBeReq;
import com.the.harbor.api.be.param.QueryOneBeResp;

public class QueryBeTest {

	public static void main(String[] args) {
		BeSVImpl b = new BeSVImpl();
		QueryOneBeReq queryMyBeReq = new QueryOneBeReq();
		queryMyBeReq.setBeId("33BE0E40387F4FA4AB4982EFB1D26B57");
		QueryOneBeResp resp = b.queryOneBe(queryMyBeReq);
		System.out.println(JSON.toJSONString(resp));

	}

}
