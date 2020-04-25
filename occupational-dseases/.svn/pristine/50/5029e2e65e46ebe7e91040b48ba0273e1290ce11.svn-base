package com.as.occupationaldseases.api;

import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.common.response.ResponseResult;
import com.as.occupationaldseases.domain.inquiry_wk.InquiryWk;
import com.as.occupationaldseases.domain.inquiry_wk.responce.WkResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

public interface InquiryWkApi {
    //新增用户
    @ApiOperation("新增外科问诊")
    public WkResult add(InquiryWk inquirywk);

    @ApiOperation("修改外科问诊")
    public WkResult edit(Integer id, InquiryWk inquirywk);

    //删除用户
    @ApiOperation("删除外科问诊")
    public ResponseResult delete(Integer id);

    //页面查询
    @ApiOperation("分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value = "页码",required=true,paramType="path",dataType="int"),
            @ApiImplicitParam(name="size",value = "每页记录数",required=true,paramType="path",dataType="int")
    })
    public QueryResponseResult findList(int page, int size, InquiryWk inquirywk);

}
