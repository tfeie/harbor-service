package com.the.harbor.dao.mapper.interfaces;

import com.the.harbor.dao.mapper.bo.HyBeDetail;
import com.the.harbor.dao.mapper.bo.HyBeDetailCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HyBeDetailMapper {
    int countByExample(HyBeDetailCriteria example);

    int deleteByExample(HyBeDetailCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(HyBeDetail record);

    int insertSelective(HyBeDetail record);

    List<HyBeDetail> selectByExample(HyBeDetailCriteria example);

    HyBeDetail selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") HyBeDetail record, @Param("example") HyBeDetailCriteria example);

    int updateByExample(@Param("record") HyBeDetail record, @Param("example") HyBeDetailCriteria example);

    int updateByPrimaryKeySelective(HyBeDetail record);

    int updateByPrimaryKey(HyBeDetail record);
}