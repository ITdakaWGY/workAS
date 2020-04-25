package com.as.occupationaldseases.domain.diseaseItem;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@TableName("disease_item")
public class DiseaseItem {

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("体检项目编码")
    @TableField("ITEM_CODE")
    private String itemCode;

    @ApiModelProperty("体检项目id")
    @TableField("device_id")
    private int deviceId;

    @ApiModelProperty("体检项目名称")
    @TableField("iteam_name")
    private String iteamName;

    @ApiModelProperty("机构编号")
    @TableField("orgcode")
    private String orgcode;

    @ApiModelProperty("机构名称")
    @TableField("orgname")
    private String orgname;

    @ApiModelProperty("创建时间")
    private Date createtime;

    @ApiModelProperty("状态")
    private String state;

    @ApiModelProperty("价格")
    private String price;

    @ApiModelProperty("备注")
    private String remarks;

    @ApiModelProperty("res1")
    @TableField("res1")
    private String res1;

    @ApiModelProperty("res2")
    @TableField("res2")
    private String res2;

    @ApiModelProperty("res3")
    @TableField("res3")
    private String res3;


    @ApiModelProperty("res4")
    @TableField("res4")
    private String res4;

    @ApiModelProperty("res5")
    @TableField("res5")
    private String res5;



}
