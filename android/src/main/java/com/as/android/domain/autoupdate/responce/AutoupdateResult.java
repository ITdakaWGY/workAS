package com.as.android.domain.autoupdate.responce;

import com.as.android.common.response.ResponseResult;
import com.as.android.common.response.ResultCode;
import com.as.android.domain.autoupdate.Autoupdate;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AutoupdateResult extends ResponseResult {
    Autoupdate autoupdate;

    public AutoupdateResult(ResultCode resultCode, Autoupdate autoupdate) {
        super(resultCode);
        this.autoupdate = autoupdate;
    }
}
