package com.as.occupationaldseases.domain.tjHazardJob;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@TableName("tj_hazard_job")
public class TjHazardJob {

    @ApiModelProperty("职工岗位危险因素关联表id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("危险因素编号")
    @TableField("hazard_code")
    private String hazardCode;

    @ApiModelProperty("岗位编号")
    private String jobcode;
}
