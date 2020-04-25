package com.as.occupationaldseases.api;

import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.domain.diseaseDevice.DiseaseDevice;
import com.as.occupationaldseases.domain.diseaseDevice.responce.DiseaseDeviceResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

public interface DiseaseDeviceApi {

    //新增
    @ApiOperation("新增")
    public DiseaseDeviceResult add(DiseaseDevice diseaseDevice);


    //根据id修改
    @ApiOperation("根据id修改")
    public DiseaseDeviceResult update(int id, DiseaseDevice diseaseDevice);


    //根据id删除
    @ApiOperation("根据id删除")
    public DiseaseDeviceResult delete(int id);


    /**
     * 分页查询方法
     *
     * @param current       页码，从1开始记数
     * @param size          每页记录数
     * @param diseaseDevice 查询条件
     * @return
     */
    @ApiOperation("分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页记录数", required = true, paramType = "path", dataType = "int")
    })
    public QueryResponseResult findList(int current, int size, DiseaseDevice diseaseDevice);


    //查询单条数据
    @ApiOperation("查询单条数据")
    public DiseaseDeviceResult findBySingle(DiseaseDevice diseaseDevice);


    //根据条件查询数据条数
    @ApiOperation("根据条件查询条数")
    public Integer count(DiseaseDevice diseaseDevice);
}
