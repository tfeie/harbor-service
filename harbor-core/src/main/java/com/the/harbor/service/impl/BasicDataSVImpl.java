package com.the.harbor.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.the.harbor.base.enumeration.hytags.Status;
import com.the.harbor.dao.mapper.bo.HyCountry;
import com.the.harbor.dao.mapper.bo.HyCountryCriteria;
import com.the.harbor.dao.mapper.bo.HyIndustry;
import com.the.harbor.dao.mapper.bo.HyIndustryCriteria;
import com.the.harbor.dao.mapper.bo.HyTags;
import com.the.harbor.dao.mapper.bo.HyTagsCriteria;
import com.the.harbor.dao.mapper.bo.HyUniversity;
import com.the.harbor.dao.mapper.bo.HyUniversityCriteria;
import com.the.harbor.dao.mapper.interfaces.HyCountryMapper;
import com.the.harbor.dao.mapper.interfaces.HyIndustryMapper;
import com.the.harbor.dao.mapper.interfaces.HyTagsMapper;
import com.the.harbor.dao.mapper.interfaces.HyUniversityMapper;
import com.the.harbor.service.interfaces.IBasicDataSV;

@Component
@Transactional
public class BasicDataSVImpl implements IBasicDataSV {

	@Autowired
	private transient HyCountryMapper hyCountryMapper;

	@Autowired
	private transient HyUniversityMapper hyUniversityMapper;

	@Autowired
	private transient HyIndustryMapper hyIndustryMapper;

	@Autowired
	private transient HyTagsMapper hyTagsMapper;

	@Override
	public List<HyCountry> getAllHyCountries() {
		HyCountryCriteria sql = new HyCountryCriteria();
		sql.setOrderByClause(" sort_id asc");
		return hyCountryMapper.selectByExample(sql);
	}

	@Override
	public List<HyUniversity> getAllHyUniversities() {
		HyUniversityCriteria sql = new HyUniversityCriteria();
		return hyUniversityMapper.selectByExample(sql);
	}

	@Override
	public List<HyIndustry> getAllHyIndustries() {
		HyIndustryCriteria sql = new HyIndustryCriteria();
		sql.setOrderByClause(" sort_id asc");
		return hyIndustryMapper.selectByExample(sql);
	}

	@Override
	public List<HyTags> getAllHyTags() {
		HyTagsCriteria sql = new HyTagsCriteria();
		sql.or().andStatusEqualTo(Status.VALID.getValue());
		sql.setOrderByClause(" sort_id asc");
		return hyTagsMapper.selectByExample(sql);
	}

}
