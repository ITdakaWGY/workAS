package com.as.occupationaldseases.domain.grjbxx.responce;

import com.as.occupationaldseases.common.response.ResponseResult;
import com.as.occupationaldseases.common.response.ResultCode;
import com.as.occupationaldseases.domain.grjbxx.Grjbxx_ss;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GrjbxxSsResult extends ResponseResult {
    Grjbxx_ss grjbxx_ss;

    public GrjbxxSsResult(ResultCode resultCode, Grjbxx_ss grjbxx_ss) {
        super(resultCode);
        this.grjbxx_ss = grjbxx_ss;
    }
}
