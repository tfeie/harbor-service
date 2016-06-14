package com.the.harbor.dao.mapper.interfaces;

import com.the.harbor.dao.mapper.bo.HyDicts;
import com.the.harbor.dao.mapper.bo.HyDictsCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HyDictsMapper {
    int countByExample(HyDictsCriteria example);

    int deleteByExample(HyDictsCriteria example);

    int deleteByPrimaryKey(String dictId);

    int insert(HyDicts record);

    int insertSelective(HyDicts record);

    List<HyDicts> selectByExample(HyDictsCriteria example);

    HyDicts selectByPrimaryKey(String dictId);

    int updateByExampleSelective(@Param("record") HyDicts record, @Param("example") HyDictsCriteria example);

    int updateByExample(@Param("record") HyDicts record, @Param("example") HyDictsCriteria example);

    int updateByPrimaryKeySelective(HyDicts record);

    int updateByPrimaryKey(HyDicts record);
}