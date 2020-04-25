package com.as.occupationaldseases.dao;

import com.as.occupationaldseases.domain.role.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    @Select("SELECT" +
            " r.rolecode,r.id,r.rolename,rolestate,roleexplain" +
            " FROM role r LEFT JOIN role_relation_userlogin rru ON  r.rolecode = rru.rolecode " +
            "  WHERE rru.yhbh = #{yhbh} AND r.rolestate = '1'")
    public List<Role>  getRoleCode(String yhbh);
}
