package com.the.harbor.dao.mapper.interfaces;

import com.the.harbor.dao.mapper.bo.HyBeComments;
import com.the.harbor.dao.mapper.bo.HyBeCommentsCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HyBeCommentsMapper {
    int countByExample(HyBeCommentsCriteria example);

    int deleteByExample(HyBeCommentsCriteria example);

    int deleteByPrimaryKey(String commentId);

    int insert(HyBeComments record);

    int insertSelective(HyBeComments record);

    List<HyBeComments> selectByExample(HyBeCommentsCriteria example);

    HyBeComments selectByPrimaryKey(String commentId);

    int updateByExampleSelective(@Param("record") HyBeComments record, @Param("example") HyBeCommentsCriteria example);

    int updateByExample(@Param("record") HyBeComments record, @Param("example") HyBeCommentsCriteria example);

    int updateByPrimaryKeySelective(HyBeComments record);

    int updateByPrimaryKey(HyBeComments record);
}