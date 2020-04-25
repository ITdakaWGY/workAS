package com.as.occupationaldseases.domain.userLogin.responce;

import com.as.occupationaldseases.common.response.ResponseResult;
import com.as.occupationaldseases.common.response.ResultCode;
import com.as.occupationaldseases.domain.userLogin.UserLogin;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserLoginResult extends ResponseResult {
         UserLogin userLogin;

    public UserLoginResult(ResultCode resultCode, UserLogin userLogin) {
        super(resultCode);
        this.userLogin = userLogin;
    }
}
