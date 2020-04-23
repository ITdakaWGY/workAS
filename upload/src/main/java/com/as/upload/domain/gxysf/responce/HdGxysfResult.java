package com.as.upload.domain.gxysf.responce;

import com.as.upload.common.response.ResponseResult;
import com.as.upload.common.response.ResultCode;
import com.as.upload.domain.gxysf.HdGxysf;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HdGxysfResult extends ResponseResult {
    Long id;

    public HdGxysfResult(ResultCode resultCode, Long id) {
        super(resultCode);
        this.id = id;
    }
}
