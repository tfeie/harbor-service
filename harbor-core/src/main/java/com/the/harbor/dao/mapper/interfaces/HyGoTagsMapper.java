package com.the.harbor.dao.mapper.interfaces;

import com.the.harbor.dao.mapper.bo.HyGoTags;
import com.the.harbor.dao.mapper.bo.HyGoTagsCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HyGoTagsMapper {
    int countByExample(HyGoTagsCriteria example);

    int deleteByExample(HyGoTagsCriteria example);

    int deleteByPrimaryKey(long recordId);

    int insert(HyGoTags record);

    int insertSelective(HyGoTags record);

    List<HyGoTags> selectByExample(HyGoTagsCriteria example);

    HyGoTags selectByPrimaryKey(long recordId);

    int updateByExampleSelective(@Param("record") HyGoTags record, @Param("example") HyGoTagsCriteria example);

    int updateByExample(@Param("record") HyGoTags record, @Param("example") HyGoTagsCriteria example);

    int updateByPrimaryKeySelective(HyGoTags record);

    int updateByPrimaryKey(HyGoTags record);
}