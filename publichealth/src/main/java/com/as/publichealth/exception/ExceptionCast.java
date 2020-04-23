package com.as.publichealth.exception;


import com.as.publichealth.common.response.ResultCode;

public class ExceptionCast {

    public static void cast(ResultCode resultCode) {
        throw new CustomException(resultCode);
    }
}
