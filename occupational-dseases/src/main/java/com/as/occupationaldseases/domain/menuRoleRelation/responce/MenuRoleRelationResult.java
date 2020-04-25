package com.as.occupationaldseases.domain.menuRoleRelation.responce;

import com.as.occupationaldseases.common.response.ResponseResult;
import com.as.occupationaldseases.common.response.ResultCode;
import com.as.occupationaldseases.domain.menuRoleRelation.MenuRoleRelation;
import com.as.occupationaldseases.domain.sysMenu.SysMenu;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MenuRoleRelationResult extends ResponseResult {
    MenuRoleRelation menuRoleRelation;

    public MenuRoleRelationResult(ResultCode resultCode, MenuRoleRelation menuRoleRelation) {
        super(resultCode);
        this.menuRoleRelation = menuRoleRelation;
    }
}
