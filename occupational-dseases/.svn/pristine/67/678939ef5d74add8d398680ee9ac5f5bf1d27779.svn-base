package com.as.occupationaldseases.api;

import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.common.response.ResponseResult;
import com.as.occupationaldseases.domain.user.User;
import com.as.occupationaldseases.domain.user.responce.UserResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.List;

public interface UserApi {
    //新增用户
    @ApiOperation("新增用户")
    public UserResult add(User user);

    @ApiOperation("修改用户")
    public UserResult edit(Long id,User user);

    //删除用户
    @ApiOperation("删除用户")
    public ResponseResult delete(Long id);

    //页面查询
    @ApiOperation("分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value = "页码",required=true,paramType="path",dataType="int"),
            @ApiImplicitParam(name="size",value = "每页记录数",required=true,paramType="path",dataType="int")
    })
    public QueryResponseResult findList(int page, int size, User user);

    @ApiOperation("测试xml")
    public List<User> queryAllByXml(User user);

}
