package com.the.harbor.test;

import java.sql.Timestamp;

import com.taobao.api.internal.toplink.LinkException;
import com.the.harbor.commons.util.DateUtil;

public class Test {

	public static void main(String[] args) throws LinkException {
		Timestamp d=DateUtil.getTimestamp(0);
		System.out.println(d.getTime());

	}

}
