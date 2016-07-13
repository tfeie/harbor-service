package com.the.harbor.dao.mapper.interfaces;

import com.the.harbor.dao.mapper.bo.HyBeView;
import com.the.harbor.dao.mapper.bo.HyBeViewCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HyBeViewMapper {
    int countByExample(HyBeViewCriteria example);

    int deleteByExample(HyBeViewCriteria example);

    int deleteByPrimaryKey(String viewId);

    int insert(HyBeView record);

    int insertSelective(HyBeView record);

    List<HyBeView> selectByExample(HyBeViewCriteria example);

    HyBeView selectByPrimaryKey(String viewId);

    int updateByExampleSelective(@Param("record") HyBeView record, @Param("example") HyBeViewCriteria example);

    int updateByExample(@Param("record") HyBeView record, @Param("example") HyBeViewCriteria example);

    int updateByPrimaryKeySelective(HyBeView record);

    int updateByPrimaryKey(HyBeView record);
}