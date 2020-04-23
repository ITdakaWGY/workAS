package com.as.upload.domain.gxysf.responce;

import com.as.upload.common.response.ResultCode;
import lombok.ToString;

@ToString
public enum HdGxysfCode implements ResultCode {

    HdGxysf_NOTEXISTS(false, 12001, "不存在！"),
    HdGxysf_SFZ_NOTEXISTS(false, 13002, "未传入身份证信息！"),
    HdGxysf_ADD_EXISTSNAME(false, 12002, "已存在！");

    //操作是否成功
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;

    private HdGxysfCode(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    @Override
    public boolean success() {
        return false;
    }

    @Override
    public int code() {
        return 0;
    }

    @Override
    public String message() {
        return null;
    }
}
