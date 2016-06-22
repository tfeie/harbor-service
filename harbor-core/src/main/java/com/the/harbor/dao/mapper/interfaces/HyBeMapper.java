package com.the.harbor.dao.mapper.interfaces;

import com.the.harbor.dao.mapper.bo.HyBe;
import com.the.harbor.dao.mapper.bo.HyBeCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HyBeMapper {
    int countByExample(HyBeCriteria example);

    int deleteByExample(HyBeCriteria example);

    int deleteByPrimaryKey(String beId);

    int insert(HyBe record);

    int insertSelective(HyBe record);

    List<HyBe> selectByExample(HyBeCriteria example);

    HyBe selectByPrimaryKey(String beId);

    int updateByExampleSelective(@Param("record") HyBe record, @Param("example") HyBeCriteria example);

    int updateByExample(@Param("record") HyBe record, @Param("example") HyBeCriteria example);

    int updateByPrimaryKeySelective(HyBe record);

    int updateByPrimaryKey(HyBe record);
}