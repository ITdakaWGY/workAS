package com.as.occupationaldseases.domain.diseaseItem.responce;

import com.as.occupationaldseases.common.response.ResultCode;
import lombok.ToString;


@ToString
public enum DiseaseItemCode implements ResultCode {
    DISEASEITEM_NOTEXISTS(false, 23001, "体检项目包进度不存在！"),
    DISEASEITEM_ADD_EXISTSNAME(false, 23002, "体检项目包进度已存在！"),
    DISEASEITEM_GREATERTHAN(false, 23003, "查询数据大于一");
    //    private static ImmutableMap<Integer, CommonCode> codes ;
    //操作是否成功
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;

    private DiseaseItemCode(boolean success, int code, String message) {
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
