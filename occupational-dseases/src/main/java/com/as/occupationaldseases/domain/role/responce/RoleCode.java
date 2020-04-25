package com.as.occupationaldseases.domain.role.responce;

import com.as.occupationaldseases.common.response.ResultCode;
import lombok.ToString;

@ToString
public enum  RoleCode  implements ResultCode {
    ROLE_NOTEXISTS(false,15001,"角色已不存在！"),
    ROLE_TEXISTS(false,15001,"角色已存在！"),
    ROLE_LOGIN_NOT_ENABLED(false,15002,"用户未启用！");
    //操作是否成功
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;

    RoleCode(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    @Override
    public boolean success() {
        return success;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
