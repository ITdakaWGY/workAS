package com.as.occupationaldseases.domain.diseaseDevice;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@TableName("disease_device")
public class DiseaseDevice {

    @ApiModelProperty("体检项目id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("体检项目编码")
    @TableField("ITEM_CODE")
    private String itemCode;

    @ApiModelProperty("体检项目名称")
    @TableField("ITEM_NAME")
    private String itemName;

    @ApiModelProperty("单位")
    @TableField("RESULT_UNIT")
    private String resultUnit;

    @ApiModelProperty("下限")
    @TableField("VALUE_LOWER")
    private String valueLower;

    @ApiModelProperty("上限")
    @TableField("VALUE_UPPER")
    private String valueUpper;

    @ApiModelProperty("设备编码")
    @TableField("DEVICE_CODE")
    private String deviceCode;

    @ApiModelProperty("设备名称")
    @TableField("DEVICE_NAME")
    private String deviceName;

    @ApiModelProperty("机构编码")
    @TableField("ORGNO")
    private String orgno;

    @ApiModelProperty("状态")
    @TableField("STATE")
    private String state;


}
