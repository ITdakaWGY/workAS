package com.as.occupationaldseases.domain.companyinfo.responce;

import com.as.occupationaldseases.common.response.ResultCode;
import lombok.ToString;


@ToString
public enum CompanyinfoCode implements ResultCode {
    COMPANYINFO_NOTEXISTS(false, 25001, "用人单位不存在！"),
    COMPANYINFO_ADD_EXISTSNAME(false, 25002, "用人单位已存在！"),
    COMPANYINFO_GREATERTHAN(false, 25003, "查询数据大于一");
    //    private static ImmutableMap<Integer, CommonCode> codes ;
    //操作是否成功
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;

    private CompanyinfoCode(boolean success, int code, String message) {
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
