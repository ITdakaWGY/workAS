package com.as.occupationaldseases.domain.inquiry_wgk.responce;

import com.as.occupationaldseases.common.response.ResultCode;
import lombok.ToString;

@ToString
public enum WgkCode implements ResultCode {
    Wgk_NOTEXISTS(false,14001,"不存在！"),
    Wgk_ADD_EXISTSNAME(false,14002,"已存在！");
    //    private static ImmutableMap<Integer, CommonCode> codes ;
    //操作是否成功
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;
    private WgkCode(boolean success, int code, String message){
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