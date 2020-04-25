package com.as.occupationaldseases.domain.grjbxx;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class GrjbxxProg {

    @ApiModelProperty("条码号")
    private String tmh;

    @ApiModelProperty("用人单位名称")
    private String companyname;

    @ApiModelProperty("体检日期")
    private String tjrq;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("工作岗位")
    private String gzgw;

    @ApiModelProperty("应体检项目数")
    private Integer ytjxms;

    @ApiModelProperty("应体检项目")
    private String ytjxm;

    @ApiModelProperty("已体检项目数")
    private Integer wtjxms;

    @ApiModelProperty("已体检项目")
    private String wtjxm;

    @ApiModelProperty("未体检项目数")
    private Integer notjxms;

    @ApiModelProperty("未体检项目")
    private String notjxm;

    @ApiModelProperty("电话")
    private String phone;
}
