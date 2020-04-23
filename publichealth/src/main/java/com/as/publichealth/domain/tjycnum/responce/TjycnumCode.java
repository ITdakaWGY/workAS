package com.as.publichealth.domain.tjycnum.responce;

import com.as.publichealth.common.response.ResultCode;
import lombok.ToString;


@ToString
public enum TjycnumCode implements ResultCode {
    Tjycnum_NOTEXISTS(false, 13001, "不存在！"),
    Tjycnum_ADD_EXISTSNAME(false, 13002, "已存在！");
    //    private static ImmutableMap<Integer, CommonCode> codes ;
    //操作是否成功
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;

    private TjycnumCode(boolean success, int code, String message) {
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
