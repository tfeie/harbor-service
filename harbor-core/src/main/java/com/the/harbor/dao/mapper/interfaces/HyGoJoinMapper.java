package com.the.harbor.dao.mapper.interfaces;

import com.the.harbor.dao.mapper.bo.HyGoJoin;
import com.the.harbor.dao.mapper.bo.HyGoJoinCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HyGoJoinMapper {
    int countByExample(HyGoJoinCriteria example);

    int deleteByExample(HyGoJoinCriteria example);

    int deleteByPrimaryKey(String orderId);

    int insert(HyGoJoin record);

    int insertSelective(HyGoJoin record);

    List<HyGoJoin> selectByExample(HyGoJoinCriteria example);

    HyGoJoin selectByPrimaryKey(String orderId);

    int updateByExampleSelective(@Param("record") HyGoJoin record, @Param("example") HyGoJoinCriteria example);

    int updateByExample(@Param("record") HyGoJoin record, @Param("example") HyGoJoinCriteria example);

    int updateByPrimaryKeySelective(HyGoJoin record);

    int updateByPrimaryKey(HyGoJoin record);
}