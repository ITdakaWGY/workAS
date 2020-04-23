package com.as.publichealth.api;


import com.as.publichealth.common.response.QueryResponseResult;
import com.as.publichealth.common.response.ResponseResult;
import com.as.publichealth.domain.person.HealthPerson;
import com.as.publichealth.domain.person.responce.HealthPersonResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

public interface HealthPersonApi {
    //新增用户
    @ApiOperation("一键新增")
    public ResponseResult add(String orgno, String stratTjrq, String endTjrq);

    @ApiOperation("修改")
    public HealthPersonResult edit(Long id, HealthPerson healthPerson);

    //删除用户
    @ApiOperation("删除")
    public ResponseResult delete(Long id);

    //页面查询
    @ApiOperation("分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页记录数", required = true, paramType = "path", dataType = "int")
    })
    public QueryResponseResult findList(int page, int size, HealthPerson healthPerson);

}
