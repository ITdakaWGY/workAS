package com.as.upload.exception;


import com.as.upload.common.response.ResultCode;

public class ExceptionCast {

    public static void cast(ResultCode resultCode) {
        throw new CustomException(resultCode);
    }
}
