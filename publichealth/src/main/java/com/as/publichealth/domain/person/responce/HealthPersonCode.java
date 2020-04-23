package com.as.publichealth.domain.person.responce;

import com.as.publichealth.common.response.ResultCode;
import lombok.ToString;


@ToString
public enum HealthPersonCode implements ResultCode {
    HealthPerson_NOTEXISTS(false, 12001, "不存在！"),
    HealthPerson_ADD_EXISTSNAME(false, 12002, "已存在！");
    //    private static ImmutableMap<Integer, CommonCode> codes ;
    //操作是否成功
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;

    private HealthPersonCode(boolean success, int code, String message) {
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
