package com.as.occupationaldseases.api;

import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.domain.sysMenu.SysMenu;
import com.as.occupationaldseases.domain.sysMenu.responce.SysMenuResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

public interface SysMenuApi  {

    //新增
    @ApiOperation("新增")
    public SysMenuResult add(SysMenu sysMenu);


    //根据id修改
    @ApiOperation("根据id修改")
    public SysMenuResult update(int id, SysMenu sysMenu);

    //根据id删除
    @ApiOperation("根据id删除")
    public SysMenuResult delete(int id);

    /**
     * 分页查询方法
     *
     * @param sysMenu 查询条件
     * @return
     */
    @ApiOperation("查询列表")
    public QueryResponseResult findList(SysMenu sysMenu);


    //查询单条数据
    @ApiOperation("查询单条数据")
    public SysMenuResult findBySingle(SysMenu sysMenu);

    @ApiOperation("根据角色编号查询菜单")
    public QueryResponseResult selectRelation(String roleCode,int menuId);

    @ApiOperation("角色选择菜单")
    public QueryResponseResult roleMenuRelation(String rolecode);

    @ApiOperation("查询所有主菜单方法")
    public QueryResponseResult selectList(String rolecode);
}
