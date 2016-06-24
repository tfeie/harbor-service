package com.the.harbor.dao.mapper.interfaces;

import com.the.harbor.dao.mapper.bo.HyGoView;
import com.the.harbor.dao.mapper.bo.HyGoViewCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HyGoViewMapper {
    int countByExample(HyGoViewCriteria example);

    int deleteByExample(HyGoViewCriteria example);

    int deleteByPrimaryKey(String viewId);

    int insert(HyGoView record);

    int insertSelective(HyGoView record);

    List<HyGoView> selectByExample(HyGoViewCriteria example);

    HyGoView selectByPrimaryKey(String viewId);

    int updateByExampleSelective(@Param("record") HyGoView record, @Param("example") HyGoViewCriteria example);

    int updateByExample(@Param("record") HyGoView record, @Param("example") HyGoViewCriteria example);

    int updateByPrimaryKeySelective(HyGoView record);

    int updateByPrimaryKey(HyGoView record);
}