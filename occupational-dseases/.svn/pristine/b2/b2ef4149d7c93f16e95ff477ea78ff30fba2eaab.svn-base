package com.as.occupationaldseases.domain.tjHazardItem;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@TableName("tj_hazard_item")
public class TjHazardItem {


    @ApiModelProperty("职工岗位信息表id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("危险因素编号")
    @TableField("hazard_code")
    private String hazardCode;

    @ApiModelProperty("体检项目编号")
    @TableField("item_code")
    private String itemCode;
}
