package com.as.occupationaldseases.domain.personal_progress.responce;

import com.as.occupationaldseases.common.response.ResponseResult;
import com.as.occupationaldseases.common.response.ResultCode;
import com.as.occupationaldseases.domain.personal_progress.PersonalProgress;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonalProgressResult extends ResponseResult {
    PersonalProgress personalProgress;
    public PersonalProgressResult(ResultCode resultCode, PersonalProgress personalProgress) {
        super(resultCode);
        this.personalProgress = personalProgress;
    }
}
