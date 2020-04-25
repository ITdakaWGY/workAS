package com.as.occupationaldseases.dao;

import com.as.occupationaldseases.domain.personal_progress.PersonalProgress;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;



@Mapper
public interface PersonalProgressMapper extends BaseMapper<PersonalProgress> {

    @Select("select * from personal_progress where sfz = #{sfz} ")
    public PersonalProgress selectBySfz (String sfz);
}
