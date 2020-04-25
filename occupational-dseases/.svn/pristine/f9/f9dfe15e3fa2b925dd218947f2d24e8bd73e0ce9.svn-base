package com.as.occupationaldseases.api;

import com.as.occupationaldseases.common.response.CommonCode;
import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.common.response.QueryResult;
import com.as.occupationaldseases.domain.sysOrg.SysOrg;
import com.as.occupationaldseases.domain.sysOrg.responce.SysOrgCode;
import com.as.occupationaldseases.domain.sysOrg.responce.SysOrgResult;
import com.as.occupationaldseases.exception.ExceptionCast;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.List;

public interface SysOrgApi {

    @ApiOperation("新增")
    public SysOrgResult add(SysOrg sysOrg);

    //根据id修改
    @ApiOperation("根据id修改")
    public SysOrgResult update(int id, SysOrg sysOrg);


    //根据id删除用户
    @ApiOperation("根据id删除")
    public SysOrgResult delete(Long id);

    /**
     * 用户查询方法
     *
     * @param current 页码，从1开始记数
     * @param size    每页记录数
     * @param sysOrg    查询条件
     * @return
     */
    @ApiOperation("分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页记录数", required = true, paramType = "path", dataType = "int")
    })
    public QueryResponseResult findList(int current, int size, SysOrg sysOrg);

    /**
     * 查询方法
     * @return
     */
    @ApiOperation("根据查询所有")
    public QueryResponseResult selectList(SysOrg sysOrg);

    @ApiOperation("查询单条")
    public SysOrgResult findBySingle(SysOrg sysOrg);
}
