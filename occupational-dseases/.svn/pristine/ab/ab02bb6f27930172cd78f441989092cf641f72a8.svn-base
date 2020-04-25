package com.as.occupationaldseases.domain.jobinfo.responce;

import com.as.occupationaldseases.common.response.ResponseResult;
import com.as.occupationaldseases.common.response.ResultCode;
import com.as.occupationaldseases.domain.companyinfo.Companyinfo;
import com.as.occupationaldseases.domain.jobinfo.Jobinfo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JobinfoResult extends ResponseResult {
    Jobinfo jobinfo;

    public JobinfoResult(ResultCode resultCode, Jobinfo jobinfo) {
        super(resultCode);
        this.jobinfo = jobinfo;
    }
}
