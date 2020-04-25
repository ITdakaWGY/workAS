package com.as.occupationaldseases.domain.sysOrg.responce;

import com.as.occupationaldseases.common.response.ResultCode;
import lombok.ToString;


@ToString
public enum SysOrgCode implements ResultCode {
    SYSORG_NOTEXISTS(false, 22001, "危险因素进度不存在！"),
    SYSORG_ADD_EXISTSNAME(false, 22002, "危险因素进度已存在！"),
    SYSORG_GREATERTHAN(false, 22003, "查询数据大于一");
    //    private static ImmutableMap<Integer, CommonCode> codes ;
    //操作是否成功
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;

    private SysOrgCode(boolean success, int code, String message) {
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
