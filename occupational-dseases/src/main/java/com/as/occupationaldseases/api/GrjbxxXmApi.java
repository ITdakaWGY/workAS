package com.as.occupationaldseases.api;

import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.domain.grjbxx.Grjbxx_xm;
import com.as.occupationaldseases.domain.grjbxx.responce.GrjbxxXmResult;
import io.swagger.annotations.ApiOperation;

public interface GrjbxxXmApi {
    @ApiOperation("个人与项目的新增")
    public GrjbxxXmResult add(Grjbxx_xm grjbxxXm);

    public QueryResponseResult select(Grjbxx_xm grjbxxXm);

    @ApiOperation("拼接打印条码号")
    public QueryResponseResult selectList(Grjbxx_xm grjbxxXm);
}
