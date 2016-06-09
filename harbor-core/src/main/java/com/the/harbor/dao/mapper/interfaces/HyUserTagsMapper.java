package com.the.harbor.dao.mapper.interfaces;

import com.the.harbor.dao.mapper.bo.HyUserTags;
import com.the.harbor.dao.mapper.bo.HyUserTagsCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HyUserTagsMapper {
    int countByExample(HyUserTagsCriteria example);

    int deleteByExample(HyUserTagsCriteria example);

    int deleteByPrimaryKey(long recordId);

    int insert(HyUserTags record);

    int insertSelective(HyUserTags record);

    List<HyUserTags> selectByExample(HyUserTagsCriteria example);

    HyUserTags selectByPrimaryKey(long recordId);

    int updateByExampleSelective(@Param("record") HyUserTags record, @Param("example") HyUserTagsCriteria example);

    int updateByExample(@Param("record") HyUserTags record, @Param("example") HyUserTagsCriteria example);

    int updateByPrimaryKeySelective(HyUserTags record);

    int updateByPrimaryKey(HyUserTags record);
}