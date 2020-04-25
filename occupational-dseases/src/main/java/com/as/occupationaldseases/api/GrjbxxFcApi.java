package com.as.occupationaldseases.api;

import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.common.response.ResponseResult;
import com.as.occupationaldseases.domain.grjbxx_fc.Grjbxx_fc;
import com.as.occupationaldseases.domain.grjbxx_fc.Grjbxx_fczj;
import com.as.occupationaldseases.domain.grjbxx_fc.responce.FcResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

public interface GrjbxxFcApi {

    //新增
    @ApiOperation("新增")
    public FcResult add(Grjbxx_fc grjbxxFc);

    @ApiOperation("修改")
    public FcResult edit(Integer id, Grjbxx_fc grjbxxFc);

    //删除
    @ApiOperation("删除")
    public ResponseResult delete(Integer id);

    //页面查询
    @ApiOperation("分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value = "页码",required=true,paramType="path",dataType="int"),
            @ApiImplicitParam(name="size",value = "每页记录数",required=true,paramType="path",dataType="int")
    })
    public QueryResponseResult findList(int page, int size, Grjbxx_fc grjbxxFc);

    //页面查询
    @ApiOperation("分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value = "页码",required=true,paramType="path",dataType="int"),
            @ApiImplicitParam(name="size",value = "每页记录数",required=true,paramType="path",dataType="int")
    })
    public QueryResponseResult inspectionList(int page, int size, Grjbxx_fczj grjbxx_fczj);
}
