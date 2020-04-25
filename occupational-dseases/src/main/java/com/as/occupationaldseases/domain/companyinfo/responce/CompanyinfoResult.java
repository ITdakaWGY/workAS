package com.as.occupationaldseases.domain.companyinfo.responce;

import com.as.occupationaldseases.common.response.ResponseResult;
import com.as.occupationaldseases.common.response.ResultCode;
import com.as.occupationaldseases.domain.companyinfo.Companyinfo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CompanyinfoResult extends ResponseResult {
    Companyinfo companyinfo;

    public CompanyinfoResult(ResultCode resultCode, Companyinfo companyinfo) {
        super(resultCode);
        this.companyinfo = companyinfo;
    }
}
