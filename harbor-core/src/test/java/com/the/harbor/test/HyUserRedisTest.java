package com.the.harbor.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.the.harbor.service.interfaces.IUserManagerSV;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class HyUserRedisTest {

	@Autowired
	IUserManagerSV sv;

	@Test
	public void geta() {
		sv.resetUsersInRedis();
	}

}
