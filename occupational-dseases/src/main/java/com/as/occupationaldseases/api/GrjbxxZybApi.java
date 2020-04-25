package com.as.occupationaldseases.api;

import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.domain.grjbxx.Grjbxx_zyb;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
public interface GrjbxxZybApi {
    @ApiOperation("查询职业病史")
    public QueryResponseResult select(Grjbxx_zyb grjbxxZyb);
}
