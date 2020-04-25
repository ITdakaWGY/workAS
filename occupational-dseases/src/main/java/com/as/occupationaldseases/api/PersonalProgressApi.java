package com.as.occupationaldseases.api;

import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.domain.personal_progress.PersonalProgress;
import com.as.occupationaldseases.domain.personal_progress.responce.PersonalProgressResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.Set;

public interface PersonalProgressApi {
    //新增
    @ApiOperation("新增个人进度")
    public PersonalProgressResult add(String flag,PersonalProgress progress);


    //页面查询
    @ApiOperation("分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value = "页码",required=true,paramType="path",dataType="int"),
            @ApiImplicitParam(name="size",value = "每页记录数",required=true,paramType="path",dataType="int")
    })
    public QueryResponseResult findList(int page, int size, PersonalProgress progress);

    //查询体检项目，已检未检总检
    @ApiOperation("查询体检项目，已检未检总检")
    public List<String> getTjxm(Integer flag, String sfz);




}
