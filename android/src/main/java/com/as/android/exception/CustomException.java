package com.as.android.exception;


import com.as.android.common.response.ResultCode;

/**
 * 自定义异常类型
 **/
public class CustomException extends RuntimeException {

    //错误代码
    ResultCode resultCode;

    public CustomException(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }


}
