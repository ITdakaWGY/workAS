package com.as.occupationaldseases.domain.diseaseDevice.responce;

import com.as.occupationaldseases.common.response.ResultCode;
import lombok.ToString;


@ToString
public enum DiseaseDeviceCode implements ResultCode {
    DISEASEDEVICE_NOTEXISTS(false, 24001, "体检项目进度不存在！"),
    DISEASEDEVICE_ADD_EXISTSNAME(false, 24002, "体检项目进度已存在！"),
    DISEASEDEVICE_GREATERTHAN(false, 24003, "查询数据大于一");
    //    private static ImmutableMap<Integer, CommonCode> codes ;
    //操作是否成功
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;

    private DiseaseDeviceCode(boolean success, int code, String message) {
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
