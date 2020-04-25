package com.as.occupationaldseases.domain.hazardinfo.responce;

import com.as.occupationaldseases.common.response.ResponseResult;
import com.as.occupationaldseases.common.response.ResultCode;
import com.as.occupationaldseases.domain.hazardinfo.Hazardinfo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HazardinfoResult extends ResponseResult {
    Hazardinfo hazardinfo;

    public HazardinfoResult(ResultCode resultCode, Hazardinfo hazardinfo) {
        super(resultCode);
        this.hazardinfo = hazardinfo;
    }
}
