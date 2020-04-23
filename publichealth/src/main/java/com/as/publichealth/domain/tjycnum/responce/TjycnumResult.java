package com.as.publichealth.domain.tjycnum.responce;

import com.as.publichealth.common.response.ResponseResult;
import com.as.publichealth.common.response.ResultCode;
import com.as.publichealth.domain.tjycnum.Tjycnum;
import com.as.publichealth.domain.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TjycnumResult extends ResponseResult {
    Tjycnum tjycnum;

    public TjycnumResult(ResultCode resultCode, Tjycnum tjycnum) {
        super(resultCode);
        this.tjycnum = tjycnum;
    }
}
