package com.the.harbor.service.interfaces;

import java.util.List;

import com.the.harbor.dao.mapper.bo.HyCountry;
import com.the.harbor.dao.mapper.bo.HyUniversity;

public interface IBasicDataSV {

	List<HyCountry> getAllHyCountries();

	List<HyUniversity> getAllHyUniversities();

}
