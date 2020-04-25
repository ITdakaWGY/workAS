package com.as.occupationaldseases.api;

import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.common.response.ResponseResult;
import com.as.occupationaldseases.domain.symptominfo.Symptominfo;
import com.as.occupationaldseases.domain.symptominfo.responce.SymptomResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.List;

public interface SymptominfoApi {
    //新增用户
    @ApiOperation("新增症状")
    public SymptomResult add(Symptominfo symptominfo);

    @ApiOperation("修改症状")
    public SymptomResult edit(Integer id, Symptominfo symptominfo);

    //删除用户
    @ApiOperation("删除症状")
    public ResponseResult delete(Integer id);

    //查询用户
    @ApiOperation("条件查询用户")
    public List<Symptominfo> select(Symptominfo symptominfo);

    //页面查询
    @ApiOperation("分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value = "页码",required=true,paramType="path",dataType="int"),
            @ApiImplicitParam(name="size",value = "每页记录数",required=true,paramType="path",dataType="int")
    })
    public QueryResponseResult findList(int page, int size, Symptominfo symptominfo);
}
