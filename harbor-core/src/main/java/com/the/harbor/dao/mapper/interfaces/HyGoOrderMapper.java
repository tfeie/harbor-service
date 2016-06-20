package com.the.harbor.dao.mapper.interfaces;

import com.the.harbor.dao.mapper.bo.HyGoOrder;
import com.the.harbor.dao.mapper.bo.HyGoOrderCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HyGoOrderMapper {
    int countByExample(HyGoOrderCriteria example);

    int deleteByExample(HyGoOrderCriteria example);

    int deleteByPrimaryKey(String orderId);

    int insert(HyGoOrder record);

    int insertSelective(HyGoOrder record);

    List<HyGoOrder> selectByExample(HyGoOrderCriteria example);

    HyGoOrder selectByPrimaryKey(String orderId);

    int updateByExampleSelective(@Param("record") HyGoOrder record, @Param("example") HyGoOrderCriteria example);

    int updateByExample(@Param("record") HyGoOrder record, @Param("example") HyGoOrderCriteria example);

    int updateByPrimaryKeySelective(HyGoOrder record);

    int updateByPrimaryKey(HyGoOrder record);
}