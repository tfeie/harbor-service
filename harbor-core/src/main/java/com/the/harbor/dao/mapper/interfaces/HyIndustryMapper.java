package com.the.harbor.dao.mapper.interfaces;

import com.the.harbor.dao.mapper.bo.HyIndustry;
import com.the.harbor.dao.mapper.bo.HyIndustryCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HyIndustryMapper {
    int countByExample(HyIndustryCriteria example);

    int deleteByExample(HyIndustryCriteria example);

    int deleteByPrimaryKey(String industryCode);

    int insert(HyIndustry record);

    int insertSelective(HyIndustry record);

    List<HyIndustry> selectByExample(HyIndustryCriteria example);

    HyIndustry selectByPrimaryKey(String industryCode);

    int updateByExampleSelective(@Param("record") HyIndustry record, @Param("example") HyIndustryCriteria example);

    int updateByExample(@Param("record") HyIndustry record, @Param("example") HyIndustryCriteria example);

    int updateByPrimaryKeySelective(HyIndustry record);

    int updateByPrimaryKey(HyIndustry record);
}