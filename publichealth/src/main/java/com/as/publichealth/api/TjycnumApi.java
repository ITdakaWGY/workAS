package com.as.publichealth.api;


import com.as.publichealth.common.response.QueryResponseResult;
import com.as.publichealth.common.response.ResponseResult;
import com.as.publichealth.domain.tjycnum.Tjycnum;
import com.as.publichealth.domain.tjycnum.responce.TjycnumResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

public interface TjycnumApi {
    //新增
    @ApiOperation("新增")
    public TjycnumResult add(String orgno, String tjrq, String orgname);

    @ApiOperation("新增测试")
    public TjycnumResult add2(Tjycnum tjycnum);

    @ApiOperation("修改")
    public TjycnumResult edit(Long id, Tjycnum tjycnum);

    //删除
    @ApiOperation("删除")
    public ResponseResult delete(Long id);

    //页面查询
    @ApiOperation("分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页记录数", required = true, paramType = "path", dataType = "int")
    })
    public QueryResponseResult findList(int page, int size, Tjycnum tjycnum);

    @ApiOperation("导出Excel")
    public String exportExcel();

}
