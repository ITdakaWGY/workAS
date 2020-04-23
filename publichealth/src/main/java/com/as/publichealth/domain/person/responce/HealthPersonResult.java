package com.as.publichealth.domain.person.responce;

import com.as.publichealth.common.response.ResponseResult;
import com.as.publichealth.common.response.ResultCode;
import com.as.publichealth.domain.person.HealthPerson;
import com.as.publichealth.domain.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HealthPersonResult extends ResponseResult {
    HealthPerson healthPerson;

    public HealthPersonResult(ResultCode resultCode, HealthPerson HealthPerson) {
        super(resultCode);
        this.healthPerson = healthPerson;
    }
}
