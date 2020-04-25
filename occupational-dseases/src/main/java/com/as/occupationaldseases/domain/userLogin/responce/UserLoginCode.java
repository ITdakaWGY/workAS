package com.as.occupationaldseases.domain.userLogin.responce;

import com.as.occupationaldseases.common.response.ResultCode;


import lombok.ToString;


@ToString
public enum UserLoginCode implements ResultCode {
    UserLogin_NOTEXISTS(false,15001,"用户名不存在！"),
    UserLogin_TEXISTS(false,15001,"用户名已存在！"),
    UserLogin_NOCORRECT_PASSWORD(false,15002,"密码不正确！"),
    UserLogin_NOCORRECT_USEDPASSWORD(false,15002,"原密码不正确！"),
    USER_LOGIN_NOT_ENABLED(false,15002,"用户未启用！");

    //操作是否成功
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;
    private UserLoginCode(boolean success, int code, String message){
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
