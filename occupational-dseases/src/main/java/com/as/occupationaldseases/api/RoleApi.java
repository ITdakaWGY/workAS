package com.as.occupationaldseases.api;

import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.domain.role.Role;
import com.as.occupationaldseases.domain.role.responce.RoleResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.Map;

public interface RoleApi {
    @ApiOperation("角色添加")
    public RoleResult add(Role role);
    @ApiOperation("角色修改")
    public RoleResult update(Long id,Role role);
    @ApiOperation("角色删除")
    public RoleResult delete(Long id);
    @ApiOperation("角色查询")
    public QueryResponseResult select(Role role);
    @ApiOperation("获取当前用户的角色")
    public QueryResponseResult getRole(String yhbh);

    @ApiOperation("角色分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页记录数", required = true, paramType = "path", dataType = "int")
    })
    public QueryResponseResult pagingSelect(int page,int size,Role role);
}
