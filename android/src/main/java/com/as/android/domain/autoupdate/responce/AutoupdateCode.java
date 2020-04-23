package com.as.android.domain.autoupdate.responce;

import com.as.android.common.response.ResultCode;
import lombok.ToString;


@ToString
public enum AutoupdateCode implements ResultCode {
    Autoupdate_NOTEXISTS(false, 11001, "不存在！"),
    Autoupdate_ADD_EXISTSNAME(false, 11002, "已存在！");
    //    private static ImmutableMap<Integer, CommonCode> codes ;
    //操作是否成功
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;

    private AutoupdateCode(boolean success, int code, String message) {
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
