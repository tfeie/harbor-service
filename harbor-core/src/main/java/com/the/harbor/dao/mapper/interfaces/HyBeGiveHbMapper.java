package com.the.harbor.dao.mapper.interfaces;

import com.the.harbor.dao.mapper.bo.HyBeGiveHb;
import com.the.harbor.dao.mapper.bo.HyBeGiveHbCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HyBeGiveHbMapper {
    int countByExample(HyBeGiveHbCriteria example);

    int deleteByExample(HyBeGiveHbCriteria example);

    int deleteByPrimaryKey(String giveId);

    int insert(HyBeGiveHb record);

    int insertSelective(HyBeGiveHb record);

    List<HyBeGiveHb> selectByExample(HyBeGiveHbCriteria example);

    HyBeGiveHb selectByPrimaryKey(String giveId);

    int updateByExampleSelective(@Param("record") HyBeGiveHb record, @Param("example") HyBeGiveHbCriteria example);

    int updateByExample(@Param("record") HyBeGiveHb record, @Param("example") HyBeGiveHbCriteria example);

    int updateByPrimaryKeySelective(HyBeGiveHb record);

    int updateByPrimaryKey(HyBeGiveHb record);
}