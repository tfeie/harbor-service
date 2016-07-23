package com.the.harbor.dao.mapper.interfaces;

import com.the.harbor.dao.mapper.bo.HyCfg;
import com.the.harbor.dao.mapper.bo.HyCfgCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HyCfgMapper {
    int countByExample(HyCfgCriteria example);

    int deleteByExample(HyCfgCriteria example);

    int deleteByPrimaryKey(String cfgKey);

    int insert(HyCfg record);

    int insertSelective(HyCfg record);

    List<HyCfg> selectByExample(HyCfgCriteria example);

    HyCfg selectByPrimaryKey(String cfgKey);

    int updateByExampleSelective(@Param("record") HyCfg record, @Param("example") HyCfgCriteria example);

    int updateByExample(@Param("record") HyCfg record, @Param("example") HyCfgCriteria example);

    int updateByPrimaryKeySelective(HyCfg record);

    int updateByPrimaryKey(HyCfg record);
}