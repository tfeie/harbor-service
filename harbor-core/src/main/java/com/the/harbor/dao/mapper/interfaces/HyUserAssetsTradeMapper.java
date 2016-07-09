package com.the.harbor.dao.mapper.interfaces;

import com.the.harbor.dao.mapper.bo.HyUserAssetsTrade;
import com.the.harbor.dao.mapper.bo.HyUserAssetsTradeCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HyUserAssetsTradeMapper {
    int countByExample(HyUserAssetsTradeCriteria example);

    int deleteByExample(HyUserAssetsTradeCriteria example);

    int deleteByPrimaryKey(String logId);

    int insert(HyUserAssetsTrade record);

    int insertSelective(HyUserAssetsTrade record);

    List<HyUserAssetsTrade> selectByExample(HyUserAssetsTradeCriteria example);

    HyUserAssetsTrade selectByPrimaryKey(String logId);

    int updateByExampleSelective(@Param("record") HyUserAssetsTrade record, @Param("example") HyUserAssetsTradeCriteria example);

    int updateByExample(@Param("record") HyUserAssetsTrade record, @Param("example") HyUserAssetsTradeCriteria example);

    int updateByPrimaryKeySelective(HyUserAssetsTrade record);

    int updateByPrimaryKey(HyUserAssetsTrade record);
}