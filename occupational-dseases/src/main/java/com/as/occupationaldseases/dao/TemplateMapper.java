package com.as.occupationaldseases.dao;

import com.as.occupationaldseases.domain.template.Template;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TemplateMapper extends BaseMapper<Template> {

    @Select("select id,ysxx,mbxx from template where ysxx = #{ysxx} ")
    public List<Template> findAll(String ysxx);

    @Delete("delete from template where ysxx = #{ysxx}")
    public void del(String ysxx);
}
