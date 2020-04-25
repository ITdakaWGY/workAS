package com.as.occupationaldseases.api;

import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.domain.grjbxx.Grjbxx_ss;
import com.as.occupationaldseases.domain.grjbxx.responce.GrjbxxSsResult;
import io.swagger.annotations.ApiOperation;

/**
 * 输血
 */
public interface GrjbxxSsApi {

    @ApiOperation("新增输血附表")
    public GrjbxxSsResult add(Grjbxx_ss grjbxx_ss);

    @ApiOperation("修改输血附表")
    public GrjbxxSsResult update(Long id, Grjbxx_ss grjbxx_ss);

    @ApiOperation("删除输血附表")
    public GrjbxxSsResult delete(Grjbxx_ss grjbxx_ss);

    @ApiOperation("查询输血附表")
    public QueryResponseResult select(Grjbxx_ss grjbxx_ss);

}
