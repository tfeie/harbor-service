package com.the.harbor.dao.mapper.interfaces;

import com.the.harbor.dao.mapper.bo.HyUserBuyHb;
import com.the.harbor.dao.mapper.bo.HyUserBuyHbCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HyUserBuyHbMapper {
    int countByExample(HyUserBuyHbCriteria example);

    int deleteByExample(HyUserBuyHbCriteria example);

    int deleteByPrimaryKey(String buyOrderId);

    int insert(HyUserBuyHb record);

    int insertSelective(HyUserBuyHb record);

    List<HyUserBuyHb> selectByExample(HyUserBuyHbCriteria example);

    HyUserBuyHb selectByPrimaryKey(String buyOrderId);

    int updateByExampleSelective(@Param("record") HyUserBuyHb record, @Param("example") HyUserBuyHbCriteria example);

    int updateByExample(@Param("record") HyUserBuyHb record, @Param("example") HyUserBuyHbCriteria example);

    int updateByPrimaryKeySelective(HyUserBuyHb record);

    int updateByPrimaryKey(HyUserBuyHb record);
}