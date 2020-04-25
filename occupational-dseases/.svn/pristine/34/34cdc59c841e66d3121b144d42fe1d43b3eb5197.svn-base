package com.as.occupationaldseases.domain.inquiry_wk.responce;

import com.as.occupationaldseases.common.response.ResponseResult;
import com.as.occupationaldseases.common.response.ResultCode;
import com.as.occupationaldseases.domain.inquiry_wk.InquiryWk;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WkResult extends ResponseResult {
    InquiryWk inquiryWk;
    public WkResult(ResultCode resultCode, InquiryWk inquiryWk) {
        super(resultCode);
        this.inquiryWk = inquiryWk;
    }
}
