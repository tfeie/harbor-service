package com.the.harbor.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.the.harbor.api.user.param.UserViewInfo;
import com.the.harbor.commons.redisdata.util.HyUserUtil;
import com.the.harbor.indices.HyUniversityIndexBuilder;
import com.the.harbor.service.interfaces.IUserManagerSV;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class HyUniversityIndexTest {

	@Autowired
	HyUniversityIndexBuilder builder;

	@Autowired
	IUserManagerSV userSV;

	@Test
	public void buildUniversityIndex() {
		builder.buildIndex();
	}

	@Test
	public void storeUser() {
		String userId = "hy00000072";
		UserViewInfo userInfo = userSV.getUserViewInfoByUserId(userId);
		HyUserUtil.storeUserInfo2Redis(userId, JSON.toJSONString(userInfo));
	}
	
	@Test
	public void getUser() {
		String userId = "hy00000072";
		String userData = HyUserUtil.getUserInfoFromRedis(userId);
		System.out.println(userData);
	}
	
	@Test
	public void setOpenId() {
		HyUserUtil.buildOpenIdAndUserIdMapped("oztCUs_Ci25lT7IEMeDLtbK6nr1M","hy00000062");
		HyUserUtil.buildOpenIdAndUserIdMapped("oztCUsxAjtgqU8AfsHuen-C9ouMI","hy00000063");
		HyUserUtil.buildOpenIdAndUserIdMapped("oztCUs7prwrkXp4tWsntCfg5fWpw","hy00000064");
		HyUserUtil.buildOpenIdAndUserIdMapped("oztCUs2X5d-j0Ykczx0eUXJmlzcA","hy00000065");
		HyUserUtil.buildOpenIdAndUserIdMapped("oztCUsz-82r7NQM_eFGEUngcZTyk","hy00000072");
		
	}
	
	
	
	

}
