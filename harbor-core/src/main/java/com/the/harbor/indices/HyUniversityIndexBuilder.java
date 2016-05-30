package com.the.harbor.indices;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.the.harbor.commons.indices.hyuniversity.University;
import com.the.harbor.commons.indices.hyuniversity.UniversityHandler;
import com.the.harbor.commons.util.BeanUtils;
import com.the.harbor.commons.util.CollectionUtil;
import com.the.harbor.dao.mapper.bo.HyUniversity;
import com.the.harbor.service.interfaces.IBasicDataSV;

@Component
public class HyUniversityIndexBuilder {

	@Autowired
	private transient IBasicDataSV basicDataSV;

	public void buildIndex() {
		List<HyUniversity> list = basicDataSV.getAllHyUniversities();
		if (CollectionUtil.isEmpty(list)) {
			return;
		}
		List<University> l = new ArrayList<University>();
		for (HyUniversity u : list) {
			University b = new University();
			BeanUtils.copyProperties(b, u);
			l.add(b);
		}
		UniversityHandler.batchAddIndex(l);
	}

}
