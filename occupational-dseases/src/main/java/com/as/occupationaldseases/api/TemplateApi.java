package com.as.occupationaldseases.api;

import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.common.response.ResponseResult;
import com.as.occupationaldseases.domain.template.Template;
import com.as.occupationaldseases.domain.template.responce.TemplateResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

public interface TemplateApi {
    //新增用户
    @ApiOperation("新增模板")
    public TemplateResult add(Template template);

    @ApiOperation("修改模板")
    public TemplateResult edit(Long id,Template template);

    //删除用户
    @ApiOperation("删除模板")
    public ResponseResult delete(Long id);

    //页面查询
    @ApiOperation("根据医生信息查询所有")
    public QueryResponseResult findAll(Template template);

    //页面查询
    @ApiOperation("分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value = "页码",required=true,paramType="path",dataType="int"),
            @ApiImplicitParam(name="size",value = "每页记录数",required=true,paramType="path",dataType="int")
    })
    public QueryResponseResult findList(int page, int size, Template template);
}
