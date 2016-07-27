package com.the.harbor.dao.mapper.interfaces;

import com.the.harbor.dao.mapper.bo.HyArea;
import com.the.harbor.dao.mapper.bo.HyAreaCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HyAreaMapper {
    int countByExample(HyAreaCriteria example);

    int deleteByExample(HyAreaCriteria example);

    int deleteByPrimaryKey(String areaCode);

    int insert(HyArea record);

    int insertSelective(HyArea record);

    List<HyArea> selectByExample(HyAreaCriteria example);

    HyArea selectByPrimaryKey(String areaCode);

    int updateByExampleSelective(@Param("record") HyArea record, @Param("example") HyAreaCriteria example);

    int updateByExample(@Param("record") HyArea record, @Param("example") HyAreaCriteria example);

    int updateByPrimaryKeySelective(HyArea record);

    int updateByPrimaryKey(HyArea record);
}