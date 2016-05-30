package com.the.harbor.dao.mapper.interfaces;

import com.the.harbor.dao.mapper.bo.HySmsSend;
import com.the.harbor.dao.mapper.bo.HySmsSendCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HySmsSendMapper {
    int countByExample(HySmsSendCriteria example);

    int deleteByExample(HySmsSendCriteria example);

    int deleteByPrimaryKey(String recordId);

    int insert(HySmsSend record);

    int insertSelective(HySmsSend record);

    List<HySmsSend> selectByExample(HySmsSendCriteria example);

    HySmsSend selectByPrimaryKey(String recordId);

    int updateByExampleSelective(@Param("record") HySmsSend record, @Param("example") HySmsSendCriteria example);

    int updateByExample(@Param("record") HySmsSend record, @Param("example") HySmsSendCriteria example);

    int updateByPrimaryKeySelective(HySmsSend record);

    int updateByPrimaryKey(HySmsSend record);
}