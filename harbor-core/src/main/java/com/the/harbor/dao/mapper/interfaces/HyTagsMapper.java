package com.the.harbor.dao.mapper.interfaces;

import com.the.harbor.dao.mapper.bo.HyTags;
import com.the.harbor.dao.mapper.bo.HyTagsCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HyTagsMapper {
    int countByExample(HyTagsCriteria example);

    int deleteByExample(HyTagsCriteria example);

    int deleteByPrimaryKey(String tagId);

    int insert(HyTags record);

    int insertSelective(HyTags record);

    List<HyTags> selectByExample(HyTagsCriteria example);

    HyTags selectByPrimaryKey(String tagId);

    int updateByExampleSelective(@Param("record") HyTags record, @Param("example") HyTagsCriteria example);

    int updateByExample(@Param("record") HyTags record, @Param("example") HyTagsCriteria example);

    int updateByPrimaryKeySelective(HyTags record);

    int updateByPrimaryKey(HyTags record);
}