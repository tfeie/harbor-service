package com.the.harbor.dao.mapper.interfaces;

import com.the.harbor.dao.mapper.bo.HyUser;
import com.the.harbor.dao.mapper.bo.HyUserCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HyUserMapper {
    int countByExample(HyUserCriteria example);

    int deleteByExample(HyUserCriteria example);

    int deleteByPrimaryKey(String userId);

    int insert(HyUser record);

    int insertSelective(HyUser record);

    List<HyUser> selectByExample(HyUserCriteria example);

    HyUser selectByPrimaryKey(String userId);

    int updateByExampleSelective(@Param("record") HyUser record, @Param("example") HyUserCriteria example);

    int updateByExample(@Param("record") HyUser record, @Param("example") HyUserCriteria example);

    int updateByPrimaryKeySelective(HyUser record);

    int updateByPrimaryKey(HyUser record);
}