package com.as.occupationaldseases.domain.diseaseDevice.responce;

import com.as.occupationaldseases.common.response.ResponseResult;
import com.as.occupationaldseases.common.response.ResultCode;
import com.as.occupationaldseases.domain.diseaseDevice.DiseaseDevice;
import com.as.occupationaldseases.domain.hazardinfo.Hazardinfo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DiseaseDeviceResult extends ResponseResult {
    DiseaseDevice diseaseDevice;

    public DiseaseDeviceResult(ResultCode resultCode, DiseaseDevice diseaseDevice) {
        super(resultCode);
        this.diseaseDevice = diseaseDevice;
    }
}
