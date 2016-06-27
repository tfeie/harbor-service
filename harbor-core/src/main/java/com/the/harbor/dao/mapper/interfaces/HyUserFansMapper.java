package com.the.harbor.dao.mapper.interfaces;

import com.the.harbor.dao.mapper.bo.HyUserFans;
import com.the.harbor.dao.mapper.bo.HyUserFansCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HyUserFansMapper {
    int countByExample(HyUserFansCriteria example);

    int deleteByExample(HyUserFansCriteria example);

    int deleteByPrimaryKey(String fansId);

    int insert(HyUserFans record);

    int insertSelective(HyUserFans record);

    List<HyUserFans> selectByExample(HyUserFansCriteria example);

    HyUserFans selectByPrimaryKey(String fansId);

    int updateByExampleSelective(@Param("record") HyUserFans record, @Param("example") HyUserFansCriteria example);

    int updateByExample(@Param("record") HyUserFans record, @Param("example") HyUserFansCriteria example);

    int updateByPrimaryKeySelective(HyUserFans record);

    int updateByPrimaryKey(HyUserFans record);
}