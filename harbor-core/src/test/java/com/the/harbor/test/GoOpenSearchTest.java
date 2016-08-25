package com.the.harbor.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.the.harbor.service.interfaces.IGoBusiSV;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class GoOpenSearchTest {

	@Autowired
	IGoBusiSV goSV;

	@Test
	public void rebuildOneGo() {
		goSV.pushGoToOpenSearch("0991DDE9CA164162A110E8C51CC848BF");

	}
	
	@Test
	public void rebuildAllOpenSearch() {
		goSV.pushAllGoToOpenSearch();
	}

}
