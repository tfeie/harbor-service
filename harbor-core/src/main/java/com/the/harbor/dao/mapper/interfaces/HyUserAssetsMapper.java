package com.the.harbor.dao.mapper.interfaces;

import com.the.harbor.dao.mapper.bo.HyUserAssets;
import com.the.harbor.dao.mapper.bo.HyUserAssetsCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HyUserAssetsMapper {
    int countByExample(HyUserAssetsCriteria example);

    int deleteByExample(HyUserAssetsCriteria example);

    int deleteByPrimaryKey(String assetsId);

    int insert(HyUserAssets record);

    int insertSelective(HyUserAssets record);

    List<HyUserAssets> selectByExample(HyUserAssetsCriteria example);

    HyUserAssets selectByPrimaryKey(String assetsId);

    int updateByExampleSelective(@Param("record") HyUserAssets record, @Param("example") HyUserAssetsCriteria example);

    int updateByExample(@Param("record") HyUserAssets record, @Param("example") HyUserAssetsCriteria example);

    int updateByPrimaryKeySelective(HyUserAssets record);

    int updateByPrimaryKey(HyUserAssets record);
}