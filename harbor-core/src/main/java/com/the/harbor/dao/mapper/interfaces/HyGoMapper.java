package com.the.harbor.dao.mapper.interfaces;

import com.the.harbor.dao.mapper.bo.HyGo;
import com.the.harbor.dao.mapper.bo.HyGoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HyGoMapper {
    int countByExample(HyGoCriteria example);

    int deleteByExample(HyGoCriteria example);

    int deleteByPrimaryKey(String goId);

    int insert(HyGo record);

    int insertSelective(HyGo record);

    List<HyGo> selectByExample(HyGoCriteria example);

    HyGo selectByPrimaryKey(String goId);

    int updateByExampleSelective(@Param("record") HyGo record, @Param("example") HyGoCriteria example);

    int updateByExample(@Param("record") HyGo record, @Param("example") HyGoCriteria example);

    int updateByPrimaryKeySelective(HyGo record);

    int updateByPrimaryKey(HyGo record);
}