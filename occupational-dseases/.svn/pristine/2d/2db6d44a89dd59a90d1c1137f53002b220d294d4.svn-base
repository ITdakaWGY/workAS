package com.as.occupationaldseases.domain.role.responce;

import com.as.occupationaldseases.common.response.ResponseResult;
import com.as.occupationaldseases.common.response.ResultCode;
import com.as.occupationaldseases.domain.role.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoleResult extends ResponseResult {
    Role role;

    public RoleResult(ResultCode resultCode, Role role) {
        super(resultCode);
        this.role = role;
    }
}
