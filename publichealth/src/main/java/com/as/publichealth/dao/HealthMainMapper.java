package com.as.publichealth.dao;

import com.as.publichealth.domain.main.HealthMainVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

@Mapper
public interface HealthMainMapper extends BaseMapper<HealthMainVo> {

    @Select("select tjbh,tmh,tjrq,orgno,orgname,xm,sex,age from health_main where TJRQ BETWEEN #{stratTjrq} AND  #{endTjrq} and orgno = #{orgno}")
    public List<HealthMainVo> findList(String orgno, Date stratTjrq, Date endTjrq);


}
