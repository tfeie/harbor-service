package com.the.harbor.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.the.harbor.base.enumeration.hybe.HideFlag;
import com.the.harbor.service.interfaces.IBeBusiSV;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class BeOpenSearchTest {

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

}
