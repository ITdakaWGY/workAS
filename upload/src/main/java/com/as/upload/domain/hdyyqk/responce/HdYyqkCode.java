package com.as.upload.domain.hdyyqk.responce;

import com.as.upload.common.response.ResultCode;
import lombok.ToString;

@ToString
public enum HdYyqkCode implements ResultCode {

    HdYyqk_NOTEXISTS(false, 15001, "用药情况不存在！"),
    HdYyqk_ADD_EXISTSNAME(false, 15002, "用药情况已存在！");

    //操作是否成功
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;

    private HdYyqkCode(boolean success, int code, String message) {
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
