package com.the.harbor.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.the.harbor.base.enumeration.common.Status;
import com.the.harbor.base.enumeration.hytags.TagCat;
import com.the.harbor.dao.mapper.bo.HyCfg;
import com.the.harbor.dao.mapper.bo.HyCfgCriteria;
import com.the.harbor.dao.mapper.bo.HyCountry;
import com.the.harbor.dao.mapper.bo.HyCountryCriteria;
import com.the.harbor.dao.mapper.bo.HyDicts;
import com.the.harbor.dao.mapper.bo.HyDictsCriteria;
import com.the.harbor.dao.mapper.bo.HyIndustry;
import com.the.harbor.dao.mapper.bo.HyIndustryCriteria;
import com.the.harbor.dao.mapper.bo.HyTags;
import com.the.harbor.dao.mapper.bo.HyTagsCriteria;
import com.the.harbor.dao.mapper.bo.HyUniversity;
import com.the.harbor.dao.mapper.bo.HyUniversityCriteria;
import com.the.harbor.dao.mapper.interfaces.HyCfgMapper;
import com.the.harbor.dao.mapper.interfaces.HyCountryMapper;
import com.the.harbor.dao.mapper.interfaces.HyDictsMapper;
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

	@Autowired
	private transient HyDictsMapper hyDictsMapper;

	@Autowired
	private transient HyCfgMapper hyCfgMapper;

	@Override
	public List<HyCfg> getAllCfgs() {
		HyCfgCriteria sql = new HyCfgCriteria();
		return hyCfgMapper.selectByExample(sql);
	}

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
		sql.or().andStatusEqualTo(Status.VALID.getValue()).andTagCatEqualTo(TagCat.SYSTEM.getValue());
		sql.setOrderByClause(" sort_id asc");
		return hyTagsMapper.selectByExample(sql);
	}

	@Override
	public List<HyDicts> getAllHyDicts() {
		HyDictsCriteria sql = new HyDictsCriteria();
		sql.setOrderByClause(" DISORDER asc");
		return hyDictsMapper.selectByExample(sql);
	}

}
