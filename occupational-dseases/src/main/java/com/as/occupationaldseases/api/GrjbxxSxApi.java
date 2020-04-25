package com.as.occupationaldseases.api;

import com.as.occupationaldseases.common.response.QueryResponseResult;

import com.as.occupationaldseases.domain.grjbxx.Grjbxx_sx;
import com.as.occupationaldseases.domain.grjbxx.responce.GrjbxxSxResult;
import io.swagger.annotations.ApiOperation;

/**
 * 输血
 */
public interface GrjbxxSxApi {

    @ApiOperation("新增输血附表")
    public GrjbxxSxResult add(Grjbxx_sx grjbxx_sx);

    @ApiOperation("修改输血附表")
    public GrjbxxSxResult update(Long id, Grjbxx_sx grjbxx_sx);

    @ApiOperation("删除输血附表")
    public GrjbxxSxResult delete(Grjbxx_sx grjbxx_sx);

    @ApiOperation("查询输血附表")
    public QueryResponseResult select(Grjbxx_sx grjbxx_sx);

}
