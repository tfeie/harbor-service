package com.the.harbor.dao.mapper.interfaces;

import com.the.harbor.dao.mapper.bo.HyGoStory;
import com.the.harbor.dao.mapper.bo.HyGoStoryCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HyGoStoryMapper {
    int countByExample(HyGoStoryCriteria example);

    int deleteByExample(HyGoStoryCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(HyGoStory record);

    int insertSelective(HyGoStory record);

    List<HyGoStory> selectByExample(HyGoStoryCriteria example);

    HyGoStory selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") HyGoStory record, @Param("example") HyGoStoryCriteria example);

    int updateByExample(@Param("record") HyGoStory record, @Param("example") HyGoStoryCriteria example);

    int updateByPrimaryKeySelective(HyGoStory record);

    int updateByPrimaryKey(HyGoStory record);
}