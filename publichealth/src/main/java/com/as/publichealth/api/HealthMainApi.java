package com.as.publichealth.api;


import com.as.publichealth.domain.main.HealthMain;
import com.as.publichealth.domain.main.HealthMainVo;
import io.swagger.annotations.ApiOperation;

import java.util.List;

public interface HealthMainApi {
    @ApiOperation("查询")
    public List<HealthMainVo> findList(String orgno, String stratTjrq, String endTjrq);
}
