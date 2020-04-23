package com.as.publichealth.dao;

import com.as.publichealth.domain.tjycnum.Tjycnum;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TjycnumMapper extends BaseMapper<Tjycnum> {

    @Select("select * from tjycnum")
    public List<Tjycnum> findAll();
}
