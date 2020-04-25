package com.as.occupationaldseases.api;

import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.domain.jobinfo.Jobinfo;
import com.as.occupationaldseases.domain.jobinfo.responce.JobinfoResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

public interface JobinfoApi {
    //新增
    @ApiOperation("新增")
    public JobinfoResult add(Jobinfo jobinfo);

    @ApiOperation("根据id修改")
    public JobinfoResult update(int id, Jobinfo jobinfo);

    @ApiOperation("根据id删除")
    public JobinfoResult delete(int id);

    //页面查询
    @ApiOperation("分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页记录数", required = true, paramType = "path", dataType = "int")
    })
    public QueryResponseResult findList(int current, int size, Jobinfo jobinfo);

    @ApiOperation("查询单条数据")
    public JobinfoResult findBySingle(Jobinfo jobinfo);

    @ApiOperation("根据条件查询条数")
    public Integer count(Jobinfo jobinfo);

}
