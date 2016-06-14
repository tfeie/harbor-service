package com.the.harbor.service.interfaces;

import java.util.List;

import com.the.harbor.dao.mapper.bo.HyCountry;
import com.the.harbor.dao.mapper.bo.HyDicts;
import com.the.harbor.dao.mapper.bo.HyIndustry;
import com.the.harbor.dao.mapper.bo.HyTags;
import com.the.harbor.dao.mapper.bo.HyUniversity;

public interface IBasicDataSV {

	List<HyCountry> getAllHyCountries();

	List<HyUniversity> getAllHyUniversities();

	List<HyIndustry> getAllHyIndustries();

	List<HyTags> getAllHyTags();

	List<HyDicts> getAllHyDicts();

}
