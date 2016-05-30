package com.the.harbor.dao.mapper.interfaces;

import com.the.harbor.dao.mapper.bo.HxSmsSend;
import com.the.harbor.dao.mapper.bo.HxSmsSendCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HxSmsSendMapper {
    int countByExample(HxSmsSendCriteria example);

    int deleteByExample(HxSmsSendCriteria example);

    int deleteByPrimaryKey(String recordId);

    int insert(HxSmsSend record);

    int insertSelective(HxSmsSend record);

    List<HxSmsSend> selectByExample(HxSmsSendCriteria example);

    HxSmsSend selectByPrimaryKey(String recordId);

    int updateByExampleSelective(@Param("record") HxSmsSend record, @Param("example") HxSmsSendCriteria example);

    int updateByExample(@Param("record") HxSmsSend record, @Param("example") HxSmsSendCriteria example);

    int updateByPrimaryKeySelective(HxSmsSend record);

    int updateByPrimaryKey(HxSmsSend record);
}