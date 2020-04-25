package com.as.occupationaldseases.domain.hazardinfo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@TableName("hazardinfo")
public class Hazardinfo {

    @ApiModelProperty("危险因素id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("危险因素名称")
    @TableField("hazard_name")
    private String hazardName;

    @ApiModelProperty("危险因素编号")
    @TableField("hazard_code")
    private String hazardCode;

    @ApiModelProperty("危险因素类型")
    @TableField("hazard_type")
    private String hazardType;

    @ApiModelProperty("体检类型")
    @TableField("hazard_tj")
    private String hazardTj;

    @ApiModelProperty("用人单位名称")
    @TableField("companyname")
    private String companyname;


    @ApiModelProperty("用人单位编号")
    @TableField("companycode")
    private String companycode;

    @ApiModelProperty("res1")
    private String res1;

    @ApiModelProperty("res2")
    private String res2;

    @ApiModelProperty("res3")
    private String res3;

    @ApiModelProperty("res4")
    private String res4;

    @ApiModelProperty("res5")
    private String res5;

}
