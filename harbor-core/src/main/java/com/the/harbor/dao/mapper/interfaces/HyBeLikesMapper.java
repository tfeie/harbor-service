package com.the.harbor.dao.mapper.interfaces;

import com.the.harbor.dao.mapper.bo.HyBeLikes;
import com.the.harbor.dao.mapper.bo.HyBeLikesCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HyBeLikesMapper {
    int countByExample(HyBeLikesCriteria example);

    int deleteByExample(HyBeLikesCriteria example);

    int deleteByPrimaryKey(String likesId);

    int insert(HyBeLikes record);

    int insertSelective(HyBeLikes record);

    List<HyBeLikes> selectByExample(HyBeLikesCriteria example);

    HyBeLikes selectByPrimaryKey(String likesId);

    int updateByExampleSelective(@Param("record") HyBeLikes record, @Param("example") HyBeLikesCriteria example);

    int updateByExample(@Param("record") HyBeLikes record, @Param("example") HyBeLikesCriteria example);

    int updateByPrimaryKeySelective(HyBeLikes record);

    int updateByPrimaryKey(HyBeLikes record);
}