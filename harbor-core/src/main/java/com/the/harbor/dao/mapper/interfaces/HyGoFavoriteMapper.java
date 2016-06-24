package com.the.harbor.dao.mapper.interfaces;

import com.the.harbor.dao.mapper.bo.HyGoFavorite;
import com.the.harbor.dao.mapper.bo.HyGoFavoriteCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HyGoFavoriteMapper {
    int countByExample(HyGoFavoriteCriteria example);

    int deleteByExample(HyGoFavoriteCriteria example);

    int deleteByPrimaryKey(String favoriteId);

    int insert(HyGoFavorite record);

    int insertSelective(HyGoFavorite record);

    List<HyGoFavorite> selectByExample(HyGoFavoriteCriteria example);

    HyGoFavorite selectByPrimaryKey(String favoriteId);

    int updateByExampleSelective(@Param("record") HyGoFavorite record, @Param("example") HyGoFavoriteCriteria example);

    int updateByExample(@Param("record") HyGoFavorite record, @Param("example") HyGoFavoriteCriteria example);

    int updateByPrimaryKeySelective(HyGoFavorite record);

    int updateByPrimaryKey(HyGoFavorite record);
}