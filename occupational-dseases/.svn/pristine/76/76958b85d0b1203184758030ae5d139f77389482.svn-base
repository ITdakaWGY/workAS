package com.as.occupationaldseases.domain.sysOrg;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@TableName("sys_org")
public class SysOrg {
    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("ORGNO")
    @ApiModelProperty("机构编号")
    private String orgno;

    @TableField("ORGNAME")
    @ApiModelProperty("机构名称")
    private String orgname;

    @TableField("PHONE")
    @ApiModelProperty("机构电话")
    private String phone;

    @TableField("ADDRESS")
    @ApiModelProperty("机构地址")
    private String address;

    @TableField("STATE")
    @ApiModelProperty("状态")
    private String state;
}
