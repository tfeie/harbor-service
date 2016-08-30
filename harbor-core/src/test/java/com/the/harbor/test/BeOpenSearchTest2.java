package com.the.harbor.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.the.harbor.base.enumeration.hybe.HideFlag;
import com.the.harbor.base.enumeration.hygo.TopFlag;
import com.the.harbor.commons.redisdata.util.HyBeUtil;
import com.the.harbor.commons.util.DateUtil;
import com.the.harbor.service.interfaces.IBeBusiSV;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class BeOpenSearchTest2 {

	@Autowired
	IBeBusiSV beSV;

	@Test
	public void rebuildAllBesOpenSearchIndex() {
		beSV.rebuildAllBesOpenSearchIndex();

	}

	@Test
	public void hideBe() {
		beSV.hideBeToOpenSearch("A01E2BAE43994A1DAB38F55F87A8F4AD", HideFlag.YES.getValue());
	}
	
	@Test
	public void getBeFromRDS(){
		String d = HyBeUtil.getBe("7F78AC5A3BBF47FEB43D277FDC6E68F8");
		System.out.println(d);
	}
	
	@Test
	public void pushBeOpenSearch(){
		beSV.topBeToOpenSearch("181105E3B07A4552A30B58D6D92677CB", TopFlag.NO.getValue(), DateUtil.getSysDate());
	}

}
