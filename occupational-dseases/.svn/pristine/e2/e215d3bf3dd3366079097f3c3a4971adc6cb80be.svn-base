package com.as.occupationaldseases.dao;

import com.as.occupationaldseases.domain.companyinfo.Companyinfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CompanyinfoMapper extends BaseMapper<Companyinfo> {

    @Select("select MAX(companycode) FROM companyinfo ")
    public Integer maxCode();

}
