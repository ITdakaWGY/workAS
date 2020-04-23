package com.as.publichealth.dao;

import com.as.publichealth.domain.zytz.HealthZytzVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;


@Mapper
public interface HealthZytzMapper extends BaseMapper<HealthZytzVo> {

    @Select("select tmh,PHZJL from health_zytz where TJRQ BETWEEN #{stratTjrq} AND  #{endTjrq} and orgno = #{orgno}")
    public List<HealthZytzVo> findPhzjlList(String orgno, Date stratTjrq, Date endTjrq);
}
