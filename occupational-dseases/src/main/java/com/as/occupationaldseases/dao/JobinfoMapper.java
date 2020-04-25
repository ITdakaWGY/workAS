package com.as.occupationaldseases.dao;

import com.as.occupationaldseases.domain.jobinfo.Jobinfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface JobinfoMapper extends BaseMapper<Jobinfo> {
    @Select("select MAX(jobcode) FROM jobinfo ")
    public Integer maxCode();

}
