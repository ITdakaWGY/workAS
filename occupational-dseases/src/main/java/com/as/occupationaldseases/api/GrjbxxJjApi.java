package com.as.occupationaldseases.api;

import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.common.response.ResponseResult;
import com.as.occupationaldseases.domain.grjbxx_fc.Grjbxx_fc;
import com.as.occupationaldseases.domain.grjbxx_fc.responce.FcResult;
import com.as.occupationaldseases.domain.grjbxx_jj.Grjbxx_jj;
import com.as.occupationaldseases.domain.grjbxx_jj.responce.JjResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

public interface GrjbxxJjApi {
    //新增
    @ApiOperation("新增")
    public JjResult add(Grjbxx_jj grjbxxJj);

    @ApiOperation("修改")
    public JjResult edit(Integer id, Grjbxx_jj grjbxxJj);

    //删除
    @ApiOperation("删除")
    public ResponseResult delete(Integer id);

    @ApiOperation("查询一条")
    public Grjbxx_jj selectone(Grjbxx_jj grjbxxJj);

    //页面查询
    @ApiOperation("分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value = "页码",required=true,paramType="path",dataType="int"),
            @ApiImplicitParam(name="size",value = "每页记录数",required=true,paramType="path",dataType="int")
    })
    public QueryResponseResult findList(int page, int size, Grjbxx_jj grjbxxJj);
}
