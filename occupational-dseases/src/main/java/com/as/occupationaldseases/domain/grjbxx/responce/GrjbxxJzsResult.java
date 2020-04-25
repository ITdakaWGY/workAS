package com.as.occupationaldseases.domain.grjbxx.responce;

import com.as.occupationaldseases.common.response.ResponseResult;
import com.as.occupationaldseases.common.response.ResultCode;
import com.as.occupationaldseases.domain.grjbxx.Grjbxx_jzs;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GrjbxxJzsResult extends ResponseResult {
    Grjbxx_jzs grjbxxJzs;

    public GrjbxxJzsResult(ResultCode resultCode, Grjbxx_jzs grjbxxJzs) {
        super(resultCode);
        this.grjbxxJzs = grjbxxJzs;
    }
}
