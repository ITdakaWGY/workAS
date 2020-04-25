package com.as.occupationaldseases.domain.signinfo.responce;

import com.as.occupationaldseases.common.response.ResponseResult;
import com.as.occupationaldseases.common.response.ResultCode;
import com.as.occupationaldseases.domain.signinfo.Signinfo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SigninfoResult extends ResponseResult {
  Signinfo signinfo;

    public SigninfoResult(ResultCode resultCode, Signinfo signinfo) {
        super(resultCode);
        this.signinfo = signinfo;
    }
}
