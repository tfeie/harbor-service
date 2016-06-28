package com.the.harbor.dao.mapper.interfaces;

import com.the.harbor.dao.mapper.bo.HyUserFriend;
import com.the.harbor.dao.mapper.bo.HyUserFriendCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HyUserFriendMapper {
    int countByExample(HyUserFriendCriteria example);

    int deleteByExample(HyUserFriendCriteria example);

    int deleteByPrimaryKey(String recordId);

    int insert(HyUserFriend record);

    int insertSelective(HyUserFriend record);

    List<HyUserFriend> selectByExample(HyUserFriendCriteria example);

    HyUserFriend selectByPrimaryKey(String recordId);

    int updateByExampleSelective(@Param("record") HyUserFriend record, @Param("example") HyUserFriendCriteria example);

    int updateByExample(@Param("record") HyUserFriend record, @Param("example") HyUserFriendCriteria example);

    int updateByPrimaryKeySelective(HyUserFriend record);

    int updateByPrimaryKey(HyUserFriend record);
}