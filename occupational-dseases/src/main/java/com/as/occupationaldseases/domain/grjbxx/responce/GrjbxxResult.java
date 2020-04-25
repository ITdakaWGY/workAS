package com.as.occupationaldseases.domain.grjbxx.responce;

import com.as.occupationaldseases.common.response.ResponseResult;
import com.as.occupationaldseases.common.response.ResultCode;
import com.as.occupationaldseases.domain.grjbxx.Grjbxx;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GrjbxxResult extends ResponseResult {
    Grjbxx grjbxx;

    public GrjbxxResult(ResultCode resultCode,Grjbxx grjbxx) {
        super(resultCode);
        this.grjbxx = grjbxx;
    }
}
