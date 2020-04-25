package com.as.occupationaldseases.domain.grjbxx_jj.responce;

import com.as.occupationaldseases.common.response.ResultCode;

public enum JjCode implements ResultCode {
    Jj_NOTEXISTS(false,15001,"不存在！"),
    Jj_ADD_EXISTSNAME(false,15002,"已存在！");
    //    private static ImmutableMap<Integer, CommonCode> codes ;
    //操作是否成功
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;
    private JjCode(boolean success, int code, String message){
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
