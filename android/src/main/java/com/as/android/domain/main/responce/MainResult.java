package com.as.android.domain.main.responce;

import com.as.android.common.response.ResponseResult;
import com.as.android.common.response.ResultCode;
import com.as.android.domain.main.Main;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MainResult extends ResponseResult {
    Main main;

    public MainResult(ResultCode resultCode, Main main) {
        super(resultCode);
        this.main = main;
    }
}
