package com.as.occupationaldseases.dao;

import com.as.occupationaldseases.domain.grjbxx.Grjbxx;
import com.as.occupationaldseases.domain.grjbxx.Grjbxx_xm;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface GrjbxxMapper extends BaseMapper<Grjbxx> {

    int queryCount(Map<String, Object> params);


    List<Grjbxx> queryStuBaseByXmlPage(Map<String, Object> params);


    @Select("Select sfz,tjflagwk,tjflagsjk,tjflagzz,tjflagnk from grjbxx where tmh = #{tmh}")
    public List<Grjbxx> findByTmh(String tmh);

    @Select("Select max(tmh) from grjbxx")
    public String getMaxTmh();

}
