package com.as.occupationaldseases.api;

import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.common.response.ResponseResult;
import com.as.occupationaldseases.domain.grjbxx_ys.Grjbxx_ys;
import com.as.occupationaldseases.domain.grjbxx_ys.responce.YsResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

public interface GrjbxxYsApi {
    //新增
    @ApiOperation("新增")
    public YsResult add(Grjbxx_ys grjbxxYs);

    @ApiOperation("修改")
    public YsResult edit(Integer id, Grjbxx_ys grjbxxYs);

    //删除
    @ApiOperation("删除")
    public ResponseResult delete(Integer id);

    //页面查询
    @ApiOperation("分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value = "页码",required=true,paramType="path",dataType="int"),
            @ApiImplicitParam(name="size",value = "每页记录数",required=true,paramType="path",dataType="int")
    })
    public QueryResponseResult findList(int page, int size, Grjbxx_ys grjbxxYs);
}
