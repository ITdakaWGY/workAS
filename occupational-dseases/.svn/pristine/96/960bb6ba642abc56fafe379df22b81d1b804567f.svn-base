package com.as.occupationaldseases.domain.sysOrg.responce;

import com.as.occupationaldseases.common.response.ResponseResult;
import com.as.occupationaldseases.common.response.ResultCode;
import com.as.occupationaldseases.domain.hazardinfo.Hazardinfo;
import com.as.occupationaldseases.domain.sysOrg.SysOrg;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SysOrgResult extends ResponseResult {
    SysOrg sysOrg;

    public SysOrgResult(ResultCode resultCode, SysOrg sysOrg) {
        super(resultCode);
        this.sysOrg = sysOrg;
    }
}
