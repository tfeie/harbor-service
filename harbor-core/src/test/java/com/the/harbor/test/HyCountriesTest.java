package com.the.harbor.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.the.harbor.cacheimpl.HyCountryCacheImpl;
import com.the.harbor.cacheimpl.HyIndustryCacheImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class HyCountriesTest {

	@Autowired
	HyCountryCacheImpl cache;

	@Autowired
	HyIndustryCacheImpl cache1;

	@Test
	public void build() throws Exception {
		cache.write();
	}

}
