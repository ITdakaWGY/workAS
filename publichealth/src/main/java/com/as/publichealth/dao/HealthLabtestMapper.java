package com.as.publichealth.dao;

import com.as.publichealth.domain.labtest.HealthLabtest;
import com.as.publichealth.domain.labtest.HealthLabtestVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

@Mapper
public interface HealthLabtestMapper extends BaseMapper<HealthLabtestVo> {

    @Select("select tmh,BMI,GLU,XDT,BC,ycgy,zcgy,hgb,wbc,plt,NPRO,NGLU,NKET,NBLO,AST,TBIL,CREA,CHO,TG,LDLC,HDLC from health_labtest where TJRQ BETWEEN #{stratTjrq} AND  #{endTjrq} and orgno = #{orgno}")
    public List<HealthLabtestVo> findList(String orgno, Date stratTjrq, Date endTjrq);
}
