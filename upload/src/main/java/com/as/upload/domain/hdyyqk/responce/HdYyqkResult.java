package com.as.upload.domain.hdyyqk.responce;

import com.as.upload.common.response.ResponseResult;
import com.as.upload.common.response.ResultCode;
import com.as.upload.domain.hdyyqk.HdYyqk;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HdYyqkResult extends ResponseResult {
    HdYyqk hdYyqk;

    public HdYyqkResult(ResultCode resultCode, HdYyqk hdYyqk) {
        super(resultCode);
        this.hdYyqk = hdYyqk;
    }
}
