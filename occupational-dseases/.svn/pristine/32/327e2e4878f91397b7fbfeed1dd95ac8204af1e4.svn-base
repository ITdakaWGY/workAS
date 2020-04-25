package com.as.occupationaldseases.domain.tjHazardItem.responce;

import com.as.occupationaldseases.common.response.ResponseResult;
import com.as.occupationaldseases.common.response.ResultCode;
import com.as.occupationaldseases.domain.tjHazardItem.TjHazardItem;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TjHazardItemResult extends ResponseResult {
    TjHazardItem tjHazardItem;

    public TjHazardItemResult(ResultCode resultCode, TjHazardItem tjHazardItem) {
        super(resultCode);
        this.tjHazardItem = tjHazardItem;
    }
}
