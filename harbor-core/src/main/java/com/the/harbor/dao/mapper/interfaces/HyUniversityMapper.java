package com.the.harbor.dao.mapper.interfaces;

import com.the.harbor.dao.mapper.bo.HyUniversity;
import com.the.harbor.dao.mapper.bo.HyUniversityCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HyUniversityMapper {
    int countByExample(HyUniversityCriteria example);

    int deleteByExample(HyUniversityCriteria example);

    int deleteByPrimaryKey(String universityId);

    int insert(HyUniversity record);

    int insertSelective(HyUniversity record);

    List<HyUniversity> selectByExample(HyUniversityCriteria example);

    HyUniversity selectByPrimaryKey(String universityId);

    int updateByExampleSelective(@Param("record") HyUniversity record, @Param("example") HyUniversityCriteria example);

    int updateByExample(@Param("record") HyUniversity record, @Param("example") HyUniversityCriteria example);

    int updateByPrimaryKeySelective(HyUniversity record);

    int updateByPrimaryKey(HyUniversity record);
}