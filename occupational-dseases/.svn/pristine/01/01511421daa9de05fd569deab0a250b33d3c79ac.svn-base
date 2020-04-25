package com.as.occupationaldseases.domain.tjHazardJob.responce;

import com.as.occupationaldseases.common.response.ResponseResult;
import com.as.occupationaldseases.common.response.ResultCode;
import com.as.occupationaldseases.domain.tjHazardItem.TjHazardItem;
import com.as.occupationaldseases.domain.tjHazardJob.TjHazardJob;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TjHazardJobResult extends ResponseResult {
    TjHazardJob tjHazardJob;

    public TjHazardJobResult(ResultCode resultCode, TjHazardJob tjHazardJob) {
        super(resultCode);
        this.tjHazardJob = tjHazardJob;
    }
}
