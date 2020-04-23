package com.as.upload.domain.hdgrjbxx.responce;

import com.as.upload.common.response.ResultCode;
import lombok.ToString;

@ToString
public enum HdGrjbxxCode implements ResultCode {

    HdGrjbxx_NOTEXISTS(false, 14001, "个人基本信息不存在！"),
    HdGrjbxx_ADD_EXISTSNAME(false, 14002, "个人基本信息已存在！");

    //操作是否成功
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;

    private HdGrjbxxCode(boolean success, int code, String message) {
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
