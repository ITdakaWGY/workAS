package com.as.occupationaldseases.dao;

import com.as.occupationaldseases.domain.sysOrg.SysOrg;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.poi.ss.formula.functions.T;

@Mapper
public interface SysOrgMapper extends BaseMapper<SysOrg> {

    @Select("select MAX(orgno) from sys_org ")
    public Integer maxOrg();


}
