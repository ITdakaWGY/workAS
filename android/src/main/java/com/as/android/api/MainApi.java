package com.as.android.api;

import com.as.android.common.response.QueryResponseResult;
import com.as.android.domain.main.Main;
import com.as.android.domain.main.responce.MainResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

public interface MainApi {
    //新增
    @ApiOperation("新增")
    public MainResult add(Main main);


    //页面查询
    @ApiOperation("分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页记录数", required = true, paramType = "path", dataType = "int")
    })
    public QueryResponseResult findList(int page, int size, Main main);
}
