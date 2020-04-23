package com.as.publichealth.dao;

import com.as.publichealth.domain.main.HealthMainVo;
import com.as.publichealth.domain.person.HealthPerson;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface HealthPersonMapper extends BaseMapper<HealthPerson> {
    public Integer queryCount(Map<String,Object> map);

    @Select("select tmh from health_person where TJRQ BETWEEN #{stratTjrq} AND  #{endTjrq} and orgno = #{orgno}")
    public List<String> findTmhList(String orgno, Date stratTjrq, Date endTjrq);

    @Select("SELECT COUNT(id) FROM health_person WHERE tmh = #{tmh}")
    public Integer findByTmh(String tmh);

}
