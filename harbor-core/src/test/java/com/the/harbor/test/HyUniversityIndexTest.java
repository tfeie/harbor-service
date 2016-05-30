package com.the.harbor.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.the.harbor.commons.indices.hyuniversity.UniversityHandler;
import com.the.harbor.indices.HyUniversityIndexBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class HyUniversityIndexTest {

	@Autowired
	HyUniversityIndexBuilder builder;

	@Test
	public void buildUniversityIndex() {
		builder.buildIndex();
	}

	@Test
	public void querySuggest() {
		List<String> l = UniversityHandler.querySuggestByUniversityName("University");
		System.out.println(JSON.toJSONString(l));
	}

}
