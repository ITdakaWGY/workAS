package com.as.occupationaldseases.api;

import com.as.occupationaldseases.common.response.CommonCode;
import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.domain.tjHazardItem.TjHazardItem;
import com.as.occupationaldseases.domain.tjHazardItem.responce.TjHazardItemCode;
import com.as.occupationaldseases.domain.tjHazardItem.responce.TjHazardItemResult;
import com.as.occupationaldseases.exception.ExceptionCast;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

public interface TjHazardItemApi {

    //新增
    @ApiOperation("新增")
    public TjHazardItemResult add(TjHazardItem tjHazardItem);


    //页面查询
    @ApiOperation("分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页记录数", required = true, paramType = "path", dataType = "int")
    })
    public QueryResponseResult findList(int current, int size, TjHazardItem tjHazardItem);
    //根据id删除
    @ApiOperation("删除")
    public TjHazardItemResult delete(TjHazardItem tjHazardItem);
}
