package com.the.harbor.dao.mapper.interfaces;

import com.the.harbor.dao.mapper.bo.HyBeTags;
import com.the.harbor.dao.mapper.bo.HyBeTagsCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HyBeTagsMapper {
    int countByExample(HyBeTagsCriteria example);

    int deleteByExample(HyBeTagsCriteria example);

    int deleteByPrimaryKey(long recordId);

    int insert(HyBeTags record);

    int insertSelective(HyBeTags record);

    List<HyBeTags> selectByExample(HyBeTagsCriteria example);

    HyBeTags selectByPrimaryKey(long recordId);

    int updateByExampleSelective(@Param("record") HyBeTags record, @Param("example") HyBeTagsCriteria example);

    int updateByExample(@Param("record") HyBeTags record, @Param("example") HyBeTagsCriteria example);

    int updateByPrimaryKeySelective(HyBeTags record);

    int updateByPrimaryKey(HyBeTags record);
}