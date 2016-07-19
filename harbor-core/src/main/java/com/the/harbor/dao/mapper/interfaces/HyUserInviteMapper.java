package com.the.harbor.dao.mapper.interfaces;

import com.the.harbor.dao.mapper.bo.HyUserInvite;
import com.the.harbor.dao.mapper.bo.HyUserInviteCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HyUserInviteMapper {
    int countByExample(HyUserInviteCriteria example);

    int deleteByExample(HyUserInviteCriteria example);

    int deleteByPrimaryKey(String inviteCode);

    int insert(HyUserInvite record);

    int insertSelective(HyUserInvite record);

    List<HyUserInvite> selectByExample(HyUserInviteCriteria example);

    HyUserInvite selectByPrimaryKey(String inviteCode);

    int updateByExampleSelective(@Param("record") HyUserInvite record, @Param("example") HyUserInviteCriteria example);

    int updateByExample(@Param("record") HyUserInvite record, @Param("example") HyUserInviteCriteria example);

    int updateByPrimaryKeySelective(HyUserInvite record);

    int updateByPrimaryKey(HyUserInvite record);
}