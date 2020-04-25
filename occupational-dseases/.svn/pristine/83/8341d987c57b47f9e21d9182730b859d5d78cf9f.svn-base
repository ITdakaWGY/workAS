package com.as.occupationaldseases.domain.diseaseItem.responce;

import com.as.occupationaldseases.common.response.ResponseResult;
import com.as.occupationaldseases.common.response.ResultCode;
import com.as.occupationaldseases.domain.diseaseDevice.DiseaseDevice;
import com.as.occupationaldseases.domain.diseaseItem.DiseaseItem;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DiseaseItemResult extends ResponseResult {
    DiseaseItem diseaseItem;

    public DiseaseItemResult(ResultCode resultCode, DiseaseItem diseaseItem) {
        super(resultCode);
        this.diseaseItem = diseaseItem;
    }
}
