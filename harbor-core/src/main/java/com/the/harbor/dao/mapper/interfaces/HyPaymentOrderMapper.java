package com.the.harbor.dao.mapper.interfaces;

import com.the.harbor.dao.mapper.bo.HyPaymentOrder;
import com.the.harbor.dao.mapper.bo.HyPaymentOrderCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HyPaymentOrderMapper {
    int countByExample(HyPaymentOrderCriteria example);

    int deleteByExample(HyPaymentOrderCriteria example);

    int deleteByPrimaryKey(String payOrderId);

    int insert(HyPaymentOrder record);

    int insertSelective(HyPaymentOrder record);

    List<HyPaymentOrder> selectByExample(HyPaymentOrderCriteria example);

    HyPaymentOrder selectByPrimaryKey(String payOrderId);

    int updateByExampleSelective(@Param("record") HyPaymentOrder record, @Param("example") HyPaymentOrderCriteria example);

    int updateByExample(@Param("record") HyPaymentOrder record, @Param("example") HyPaymentOrderCriteria example);

    int updateByPrimaryKeySelective(HyPaymentOrder record);

    int updateByPrimaryKey(HyPaymentOrder record);
}