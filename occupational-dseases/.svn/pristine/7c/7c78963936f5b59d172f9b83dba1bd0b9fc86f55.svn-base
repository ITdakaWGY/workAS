package com.as.occupationaldseases.domain.inquiry_sjk.responce;

import com.as.occupationaldseases.common.response.ResponseResult;
import com.as.occupationaldseases.common.response.ResultCode;
import com.as.occupationaldseases.domain.inquiry_sjk.InquirySjk;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SjkResult extends ResponseResult {
    InquirySjk inquirySjk;
    public SjkResult(ResultCode resultCode, InquirySjk inquirySjk) {
        super(resultCode);
        this.inquirySjk = inquirySjk;
    }
}
