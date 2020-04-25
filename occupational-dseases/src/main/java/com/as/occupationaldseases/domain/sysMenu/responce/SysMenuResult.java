package com.as.occupationaldseases.domain.sysMenu.responce;

import com.as.occupationaldseases.common.response.ResponseResult;
import com.as.occupationaldseases.common.response.ResultCode;
import com.as.occupationaldseases.domain.sysMenu.SysMenu;
import com.as.occupationaldseases.domain.sysOrg.SysOrg;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SysMenuResult extends ResponseResult {
    SysMenu sysMenu;

    public SysMenuResult(ResultCode resultCode, SysMenu sysMenu) {
        super(resultCode);
        this.sysMenu = sysMenu;
    }
}
