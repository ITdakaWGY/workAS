package com.as.occupationaldseases.domain.grjbxx_fc;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Grjbxx_fczj {

    @ApiModelProperty("开始时间")
    private String tjrqstart;

    @ApiModelProperty("结束时间")
    private String tjrqend;

    @ApiModelProperty("身份证")
    private String sfz;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("用人单位编号")
    private String danwei;

    @ApiModelProperty("是否总检")
    private String tjflagzj;


}
