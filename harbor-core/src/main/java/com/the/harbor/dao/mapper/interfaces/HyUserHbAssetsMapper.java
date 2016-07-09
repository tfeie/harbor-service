package com.the.harbor.dao.mapper.interfaces;

import com.the.harbor.dao.mapper.bo.HyUserHbAssets;
import com.the.harbor.dao.mapper.bo.HyUserHbAssetsCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HyUserHbAssetsMapper {
    int countByExample(HyUserHbAssetsCriteria example);

    int deleteByExample(HyUserHbAssetsCriteria example);

    int deleteByPrimaryKey(String assetsId);

    int insert(HyUserHbAssets record);

    int insertSelective(HyUserHbAssets record);

    List<HyUserHbAssets> selectByExample(HyUserHbAssetsCriteria example);

    HyUserHbAssets selectByPrimaryKey(String assetsId);

    int updateByExampleSelective(@Param("record") HyUserHbAssets record, @Param("example") HyUserHbAssetsCriteria example);

    int updateByExample(@Param("record") HyUserHbAssets record, @Param("example") HyUserHbAssetsCriteria example);

    int updateByPrimaryKeySelective(HyUserHbAssets record);

    int updateByPrimaryKey(HyUserHbAssets record);
}