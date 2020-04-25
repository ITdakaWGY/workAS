package com.as.occupationaldseases.api;

import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.common.response.ResponseResult;
import com.as.occupationaldseases.domain.inquiry_wgk.InquiryWgk;
import com.as.occupationaldseases.domain.inquiry_wgk.responce.WgkResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

public interface InquiryWgkApi {
    //新增用户
    @ApiOperation("新增五官科问诊")
    public WgkResult add(InquiryWgk inquirywgk);

    @ApiOperation("修改五官科问诊")
    public WgkResult edit(Integer id, InquiryWgk inquirywgk);

    //删除用户
    @ApiOperation("删除五官科问诊")
    public ResponseResult delete(Integer id);

    //页面查询
    @ApiOperation("分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value = "页码",required=true,paramType="path",dataType="int"),
            @ApiImplicitParam(name="size",value = "每页记录数",required=true,paramType="path",dataType="int")
    })
    public QueryResponseResult findList(int page, int size, InquiryWgk inquirywgk);

}
