package com.the.harbor.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.the.harbor.api.user.param.DoIMUserSync;
import com.the.harbor.service.interfaces.IUserInterfactionSV;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class OpenImTest {
	
	@Autowired
	IUserInterfactionSV sv;

	@Test
	public void build() throws Exception {
		DoIMUserSync notify= new DoIMUserSync();
		notify.setUserId("hy00000062");
		notify.setHandleType(DoIMUserSync.HandleType.ADD.name());
		sv.userSync2OpenIM(notify);
	}

}
