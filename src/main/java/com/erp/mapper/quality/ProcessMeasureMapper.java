package com.erp.mapper.quality;

import com.erp.bean.quality.ProcessMeasure;
import com.erp.bean.quality.ProcessMeasureExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProcessMeasureMapper {
    long countByExample(ProcessMeasureExample example);

    int deleteByExample(ProcessMeasureExample example);

    int deleteByPrimaryKey(String pMeasureCheckId);

    int insert(ProcessMeasure record);

    int insertSelective(ProcessMeasure record);

    List<ProcessMeasure> selectByExample(ProcessMeasureExample example);

    ProcessMeasure selectByPrimaryKey(String pMeasureCheckId);

    int updateByExampleSelective(@Param("record") ProcessMeasure record, @Param("example") ProcessMeasureExample example);

    int updateByExample(@Param("record") ProcessMeasure record, @Param("example") ProcessMeasureExample example);

    int updateByPrimaryKeySelective(ProcessMeasure record);

    int updateByPrimaryKey(ProcessMeasure record);

    int countAllProcessMeasure();

    List<ProcessMeasure> selectPMeasureCheckLeft(@Param("pMeasureCheckId") String pMeasureCheckId);

    int countAllProcessMeasureBySomething(@Param("pMeasureCheckId") String pMeasureCheckId);
}