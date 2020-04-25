package com.as.occupationaldseases.api;

import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.common.response.ResponseResult;
import com.as.occupationaldseases.domain.inquiry_sjk.InquirySjk;
import com.as.occupationaldseases.domain.inquiry_sjk.responce.SjkResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

public interface InquirySjkApi {
    //新增用户
    @ApiOperation("新增神经科问诊")
    public SjkResult add(InquirySjk inquirysjk);

    @ApiOperation("修改神经科问诊")
    public SjkResult edit(Integer id, InquirySjk inquirysjk);

    //删除用户
    @ApiOperation("删除神经科问诊")
    public ResponseResult delete(Integer id);

    //页面查询
    @ApiOperation("分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value = "页码",required=true,paramType="path",dataType="int"),
            @ApiImplicitParam(name="size",value = "每页记录数",required=true,paramType="path",dataType="int")
    })
    public QueryResponseResult findList(int page, int size, InquirySjk inquirysjk);

}
