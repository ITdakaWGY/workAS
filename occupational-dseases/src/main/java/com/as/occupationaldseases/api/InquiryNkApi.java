package com.as.occupationaldseases.api;

import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.common.response.ResponseResult;
import com.as.occupationaldseases.domain.inquiry_nk.InquiryNk;
import com.as.occupationaldseases.domain.inquiry_nk.responce.NkResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

public interface InquiryNkApi {
    //新增用户
    @ApiOperation("新增内科问诊")
    public NkResult add(InquiryNk inquiryNk);

    @ApiOperation("修改内科问诊")
    public NkResult edit(Integer id, InquiryNk inquiryNk);

    //删除用户
    @ApiOperation("删除内科问诊")
    public ResponseResult delete(Integer id);

    //页面查询
    @ApiOperation("分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value = "页码",required=true,paramType="path",dataType="int"),
            @ApiImplicitParam(name="size",value = "每页记录数",required=true,paramType="path",dataType="int")
    })
    public QueryResponseResult findList(int page, int size, InquiryNk inquiryNk);

}
