package com.as.occupationaldseases.domain.grjbxx_ys.responce;

import com.as.occupationaldseases.common.response.ResponseResult;
import com.as.occupationaldseases.common.response.ResultCode;
import com.as.occupationaldseases.domain.grjbxx_ys.Grjbxx_ys;

public class YsResult extends ResponseResult {
    Grjbxx_ys grjbxxYs;
    public YsResult(ResultCode resultCode, Grjbxx_ys grjbxxYs) {
        super(resultCode);
        this.grjbxxYs = grjbxxYs;
    }

}
