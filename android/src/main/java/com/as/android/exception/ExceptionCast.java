package com.as.android.exception;


import com.as.android.common.response.ResultCode;

public class ExceptionCast {

    public static void cast(ResultCode resultCode) {
        throw new CustomException(resultCode);
    }
}
