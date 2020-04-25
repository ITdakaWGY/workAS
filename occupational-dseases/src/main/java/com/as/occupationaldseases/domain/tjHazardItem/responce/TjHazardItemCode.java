package com.as.occupationaldseases.domain.tjHazardItem.responce;

import com.as.occupationaldseases.common.response.ResultCode;
import lombok.ToString;


@ToString
public enum TjHazardItemCode implements ResultCode {
    TJHAZARDITEM_NOTEXISTS(false, 21001, "职工岗位信息不存在！"),
    TJHAZARDITEM_ADD_EXISTSNAME(false, 21002, "职工岗位信息已存在！"),
    TJHAZARDITEM_GREATERTHAN(false, 21003, "查询数据大于一");
    //    private static ImmutableMap<Integer, CommonCode> codes ;
    //操作是否成功
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;

    private TjHazardItemCode(boolean success, int code, String message) {
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
