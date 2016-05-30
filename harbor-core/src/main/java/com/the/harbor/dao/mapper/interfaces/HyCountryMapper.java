package com.the.harbor.dao.mapper.interfaces;

import com.the.harbor.dao.mapper.bo.HyCountry;
import com.the.harbor.dao.mapper.bo.HyCountryCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HyCountryMapper {
    int countByExample(HyCountryCriteria example);

    int deleteByExample(HyCountryCriteria example);

    int deleteByPrimaryKey(String countryCode);

    int insert(HyCountry record);

    int insertSelective(HyCountry record);

    List<HyCountry> selectByExample(HyCountryCriteria example);

    HyCountry selectByPrimaryKey(String countryCode);

    int updateByExampleSelective(@Param("record") HyCountry record, @Param("example") HyCountryCriteria example);

    int updateByExample(@Param("record") HyCountry record, @Param("example") HyCountryCriteria example);

    int updateByPrimaryKeySelective(HyCountry record);

    int updateByPrimaryKey(HyCountry record);
}