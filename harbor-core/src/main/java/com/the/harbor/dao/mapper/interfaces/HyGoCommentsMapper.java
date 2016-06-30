package com.the.harbor.dao.mapper.interfaces;

import com.the.harbor.dao.mapper.bo.HyGoComments;
import com.the.harbor.dao.mapper.bo.HyGoCommentsCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HyGoCommentsMapper {
    int countByExample(HyGoCommentsCriteria example);

    int deleteByExample(HyGoCommentsCriteria example);

    int deleteByPrimaryKey(String commentId);

    int insert(HyGoComments record);

    int insertSelective(HyGoComments record);

    List<HyGoComments> selectByExample(HyGoCommentsCriteria example);

    HyGoComments selectByPrimaryKey(String commentId);

    int updateByExampleSelective(@Param("record") HyGoComments record, @Param("example") HyGoCommentsCriteria example);

    int updateByExample(@Param("record") HyGoComments record, @Param("example") HyGoCommentsCriteria example);

    int updateByPrimaryKeySelective(HyGoComments record);

    int updateByPrimaryKey(HyGoComments record);
}