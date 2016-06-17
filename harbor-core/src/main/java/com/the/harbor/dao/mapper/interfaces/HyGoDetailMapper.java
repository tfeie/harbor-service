package com.the.harbor.dao.mapper.interfaces;

import com.the.harbor.dao.mapper.bo.HyGoDetail;
import com.the.harbor.dao.mapper.bo.HyGoDetailCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HyGoDetailMapper {
    int countByExample(HyGoDetailCriteria example);

    int deleteByExample(HyGoDetailCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(HyGoDetail record);

    int insertSelective(HyGoDetail record);

    List<HyGoDetail> selectByExample(HyGoDetailCriteria example);

    HyGoDetail selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") HyGoDetail record, @Param("example") HyGoDetailCriteria example);

    int updateByExample(@Param("record") HyGoDetail record, @Param("example") HyGoDetailCriteria example);

    int updateByPrimaryKeySelective(HyGoDetail record);

    int updateByPrimaryKey(HyGoDetail record);
}