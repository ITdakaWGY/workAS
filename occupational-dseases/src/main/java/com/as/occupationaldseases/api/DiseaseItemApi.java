package com.as.occupationaldseases.api;

import com.as.occupationaldseases.common.response.CommonCode;
import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.common.response.QueryResult;
import com.as.occupationaldseases.domain.diseaseItem.DiseaseItem;
import com.as.occupationaldseases.domain.diseaseItem.responce.DiseaseItemCode;
import com.as.occupationaldseases.domain.diseaseItem.responce.DiseaseItemResult;
import com.as.occupationaldseases.exception.ExceptionCast;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

public interface DiseaseItemApi {

    //新增
    @ApiOperation("新增")
    public DiseaseItemResult add(DiseaseItem diseaseItem);


    //根据id修改
    @ApiOperation("根据id修改")
    public DiseaseItemResult update(int id, DiseaseItem diseaseItem);


    //根据id删除
    @ApiOperation("根据id删除")
    public DiseaseItemResult delete(int id);

    /**
     * 分页查询方法
     *
     * @param current     页码，从1开始记数
     * @param size        每页记录数
     * @param diseaseItem 查询条件
     * @return
     */
    @ApiOperation("分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页记录数", required = true, paramType = "path", dataType = "int")
    })
    public QueryResponseResult findList(int current, int size, DiseaseItem diseaseItem);


    //查询单条数据
    @ApiOperation("查询单条数据")
    public DiseaseItemResult findBySingle(DiseaseItem diseaseItem);


    @ApiOperation("查询全部数据不分页")
    public QueryResponseResult selectList(DiseaseItem diseaseItem);


    //根据条件查询数据条数
    @ApiOperation("根据条件查询条数")
    public Integer count(DiseaseItem diseaseItem);


    @ApiOperation("关联tj_hazard_item分页查询已添加")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页记录数", required = true, paramType = "path", dataType = "int")
    })
    public QueryResponseResult selectRelation(String hazardCode, int page, int size);

    @ApiOperation("关联tj_hazard_item不分页分页查询已添加")
    public QueryResponseResult selectNoPaging(String hazardCode);

    @ApiOperation("关联tj_hazard_job分页查询未添加")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页记录数", required = true, paramType = "path", dataType = "int")
    })
    public QueryResponseResult selectRelationNotin(String hazardCode, int current, int size);
}
