package com.as.publichealth.domain.user.responce;

import com.as.publichealth.common.response.ResultCode;
import lombok.ToString;


@ToString
public enum UserCode implements ResultCode {
    USER_NOTEXISTS(false, 11001, "用户不存在！"),
    USER_ADD_EXISTSNAME(false, 11002, "用户已存在！");
    //    private static ImmutableMap<Integer, CommonCode> codes ;
    //操作是否成功
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;

    private UserCode(boolean success, int code, String message) {
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
