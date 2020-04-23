package com.as.upload.dao;

import com.as.upload.domain.hdgrjbxx.HdGrjbxx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface HdGrjbxxMapper extends BaseMapper<HdGrjbxx> {
    @Select("SELECT MAX(personalid) from HD_GRJBXX WHERE createorg = #{createorg} ")
    String selectMaxPersonalid(@Param("createorg") String createorg);
}
