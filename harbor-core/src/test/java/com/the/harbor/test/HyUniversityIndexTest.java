package com.the.harbor.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.the.harbor.api.be.IBeSV;
import com.the.harbor.api.be.param.TopBeReq;
import com.the.harbor.api.go.IGoSV;
import com.the.harbor.api.go.param.TopGoReq;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/dubbo/provider/dubbo-provider.xml" })
public class HyUniversityIndexTest {

	@Autowired
	IGoSV goSV;

	@Autowired
	IBeSV beSV;

	@Test
	public void topGo() {
		TopGoReq topGoReq = new TopGoReq();
		topGoReq.setGoId("BAF8790A175041779A62A0215CCECAA7");
		topGoReq.setTop(true);
		goSV.topGo(topGoReq);

	}
	
	@Test
	public void topBe() {
		TopBeReq topBeReq = new TopBeReq();
		topBeReq.setBeId("06B821FEC5274467B2C8E144D2A1BAAE");
		topBeReq.setTop(true);
		
		beSV.topBe(topBeReq);
	}

}
