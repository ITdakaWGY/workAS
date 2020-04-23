package com.as.publichealth.dao;

import com.as.publichealth.domain.tjwz.HealthTjwz;
import com.as.publichealth.domain.tjwz.HealthTjwzVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

@Mapper
public interface HealthTjwzMapper extends BaseMapper<HealthTjwzVo> {

    @Select("select tmh,ZLNLPG,NXGJB,SZJB,YBJB,SJXTJB,XZJB,XGJB,QTXTJB,NXGJBQT,XZJBQT,XGJBQT from health_tjwz where TJRQ BETWEEN #{stratTjrq} AND  #{endTjrq} and orgno = #{orgno}")
    public List<HealthTjwzVo> findList(String orgno, Date stratTjrq, Date endTjrq);
}
