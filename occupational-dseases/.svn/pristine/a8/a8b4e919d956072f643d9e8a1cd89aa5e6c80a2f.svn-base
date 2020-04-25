package com.as.occupationaldseases.domain.inquiry_wgk.responce;

import com.as.occupationaldseases.common.response.ResponseResult;
import com.as.occupationaldseases.common.response.ResultCode;
import com.as.occupationaldseases.domain.inquiry_wgk.InquiryWgk;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WgkResult extends ResponseResult {
    InquiryWgk inquiryWgk;
    public WgkResult(ResultCode resultCode, InquiryWgk inquiryWgk) {
        super(resultCode);
        this.inquiryWgk = inquiryWgk;
    }
}
