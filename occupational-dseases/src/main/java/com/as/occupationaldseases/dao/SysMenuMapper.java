package com.as.occupationaldseases.dao;

import com.as.occupationaldseases.domain.sysMenu.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    @Select("select id,pid,menuname,menuurl,menustatus,menuexplains,icon from sys_menu where id in (select menuid from menu_role_relation where rolecode in (${roleCode})) group by id")
    public List<SysMenu> selectRelation(@Param("roleCode") String roleCode);

    @Select("select id,pid,menuname,menuurl,menustatus,menuexplains,icon from sys_menu where id in (select menuid from menu_role_relation where rolecode in (${roleCode})) and pid=0 group by id")
    public List<SysMenu> selectManu(@Param("roleCode") String roleCode);
}
