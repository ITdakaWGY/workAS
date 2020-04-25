package com.as.occupationaldseases.domain.inquiry_nk.responce;

import com.as.occupationaldseases.common.response.ResponseResult;
import com.as.occupationaldseases.common.response.ResultCode;
import com.as.occupationaldseases.domain.inquiry_nk.InquiryNk;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NkResult extends ResponseResult {
    InquiryNk inquiryNk;
    public NkResult(ResultCode resultCode, InquiryNk inquiryNk) {
        super(resultCode);
        this.inquiryNk = inquiryNk;
    }
}
