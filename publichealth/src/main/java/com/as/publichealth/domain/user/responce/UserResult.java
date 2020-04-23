package com.as.publichealth.domain.user.responce;

import com.as.publichealth.common.response.ResponseResult;
import com.as.publichealth.common.response.ResultCode;
import com.as.publichealth.domain.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResult extends ResponseResult {
    User user;

    public UserResult(ResultCode resultCode, User user) {
        super(resultCode);
        this.user = user;
    }
}
