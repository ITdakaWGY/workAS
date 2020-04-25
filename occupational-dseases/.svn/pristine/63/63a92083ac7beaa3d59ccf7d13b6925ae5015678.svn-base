package com.as.occupationaldseases.domain.user.responce;

import com.as.occupationaldseases.common.response.ResponseResult;
import com.as.occupationaldseases.common.response.ResultCode;
import com.as.occupationaldseases.domain.user.User;
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
