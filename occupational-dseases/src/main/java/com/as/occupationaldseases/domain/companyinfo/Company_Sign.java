package com.as.occupationaldseases.domain.companyinfo;

import com.as.occupationaldseases.domain.signinfo.Signinfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class Company_Sign {
    @ApiModelProperty("用人单位")
    private Companyinfo companyinfo;
    @ApiModelProperty("签约管理")
    private Signinfo signinfo;
    @ApiModelProperty("签约管理列表")
    private List<Signinfo> signinfos;
}
