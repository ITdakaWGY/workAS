package com.as.upload.domain.hdgrjbxx.responce;

import com.as.upload.common.response.ResponseResult;
import com.as.upload.common.response.ResultCode;
import com.as.upload.domain.hdgrjbxx.HdGrjbxx;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HdGrjbxxResult extends ResponseResult {
    HdGrjbxx hdGrjbxx;

    public HdGrjbxxResult(ResultCode resultCode, HdGrjbxx hdGrjbxx) {
        super(resultCode);
        this.hdGrjbxx = hdGrjbxx;
    }
}
