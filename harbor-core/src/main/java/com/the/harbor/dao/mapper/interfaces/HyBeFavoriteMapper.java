package com.the.harbor.dao.mapper.interfaces;

import com.the.harbor.dao.mapper.bo.HyBeFavorite;
import com.the.harbor.dao.mapper.bo.HyBeFavoriteCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HyBeFavoriteMapper {
    int countByExample(HyBeFavoriteCriteria example);

    int deleteByExample(HyBeFavoriteCriteria example);

    int deleteByPrimaryKey(String favoriteId);

    int insert(HyBeFavorite record);

    int insertSelective(HyBeFavorite record);

    List<HyBeFavorite> selectByExample(HyBeFavoriteCriteria example);

    HyBeFavorite selectByPrimaryKey(String favoriteId);

    int updateByExampleSelective(@Param("record") HyBeFavorite record, @Param("example") HyBeFavoriteCriteria example);

    int updateByExample(@Param("record") HyBeFavorite record, @Param("example") HyBeFavoriteCriteria example);

    int updateByPrimaryKeySelective(HyBeFavorite record);

    int updateByPrimaryKey(HyBeFavorite record);
}