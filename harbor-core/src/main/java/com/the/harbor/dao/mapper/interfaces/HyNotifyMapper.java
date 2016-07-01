package com.the.harbor.dao.mapper.interfaces;

import com.the.harbor.dao.mapper.bo.HyNotify;
import com.the.harbor.dao.mapper.bo.HyNotifyCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HyNotifyMapper {
    int countByExample(HyNotifyCriteria example);

    int deleteByExample(HyNotifyCriteria example);

    int deleteByPrimaryKey(String notifyId);

    int insert(HyNotify record);

    int insertSelective(HyNotify record);

    List<HyNotify> selectByExample(HyNotifyCriteria example);

    HyNotify selectByPrimaryKey(String notifyId);

    int updateByExampleSelective(@Param("record") HyNotify record, @Param("example") HyNotifyCriteria example);

    int updateByExample(@Param("record") HyNotify record, @Param("example") HyNotifyCriteria example);

    int updateByPrimaryKeySelective(HyNotify record);

    int updateByPrimaryKey(HyNotify record);
}