package com.as.upload.domain.tnbsf.responce;

import com.as.upload.common.response.ResponseResult;
import com.as.upload.common.response.ResultCode;
import com.as.upload.domain.gxysf.HdGxysf;
import com.as.upload.domain.tnbsf.HdTnbsf;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HdTnbsfResult extends ResponseResult {
    Long id;

    public HdTnbsfResult(ResultCode resultCode, Long id) {
        super(resultCode);
        this.id = id;
    }
}
