package com.the.harbor.dao.mapper.interfaces;

import com.the.harbor.dao.mapper.bo.HyUserBuyMember;
import com.the.harbor.dao.mapper.bo.HyUserBuyMemberCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HyUserBuyMemberMapper {
    int countByExample(HyUserBuyMemberCriteria example);

    int deleteByExample(HyUserBuyMemberCriteria example);

    int deleteByPrimaryKey(String buyOrderId);

    int insert(HyUserBuyMember record);

    int insertSelective(HyUserBuyMember record);

    List<HyUserBuyMember> selectByExample(HyUserBuyMemberCriteria example);

    HyUserBuyMember selectByPrimaryKey(String buyOrderId);

    int updateByExampleSelective(@Param("record") HyUserBuyMember record, @Param("example") HyUserBuyMemberCriteria example);

    int updateByExample(@Param("record") HyUserBuyMember record, @Param("example") HyUserBuyMemberCriteria example);

    int updateByPrimaryKeySelective(HyUserBuyMember record);

    int updateByPrimaryKey(HyUserBuyMember record);
}