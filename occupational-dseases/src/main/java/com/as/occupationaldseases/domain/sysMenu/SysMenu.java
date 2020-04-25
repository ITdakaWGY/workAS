package com.as.occupationaldseases.domain.sysMenu;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@TableName("sys_menu")
public class SysMenu {

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("pid")
    private Integer pid;

    @ApiModelProperty("菜单名称")
    private String menuname;

    @ApiModelProperty("菜单路径")
    private String menuurl;

    @ApiModelProperty("菜单状态")
    private String menustatus;

    @ApiModelProperty("菜单说明")
    private String menuexplains;

    @ApiModelProperty("菜单图标")
    private String icon;


    @ApiModelProperty("res0")
    private String res0;

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
