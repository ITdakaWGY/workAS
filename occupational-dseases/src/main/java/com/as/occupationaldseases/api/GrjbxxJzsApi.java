package com.as.occupationaldseases.api;

import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.domain.grjbxx.Grjbxx_jzs;
import com.as.occupationaldseases.domain.grjbxx.responce.GrjbxxJzsResult;
import io.swagger.annotations.ApiOperation;

/**
 *输血
 */
public interface GrjbxxJzsApi {

    @ApiOperation("新增输血附表")
    public GrjbxxJzsResult add(Grjbxx_jzs grjbxxJzs);

    @ApiOperation("修改输血附表")
    public GrjbxxJzsResult update(Long id, Grjbxx_jzs grjbxxJzs);

    @ApiOperation("删除输血附表")
    public GrjbxxJzsResult delete(Grjbxx_jzs grjbxxJzs);

    @ApiOperation("查询输血附表")
    public QueryResponseResult select(Grjbxx_jzs grjbxxJzs);

}
