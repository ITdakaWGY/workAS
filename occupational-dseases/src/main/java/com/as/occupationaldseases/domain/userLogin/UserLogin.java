package com.as.occupationaldseases.domain.userLogin;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@TableName("userlogin")
public class UserLogin {
    @ApiModelProperty("用户id")
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("登录名称")
    private String name;
    @ApiModelProperty("用户名称")
    private String xm;
    @ApiModelProperty("用户编号")
    private String yhbh;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("用户类型")
    @TableField("userType")
    private String userType;
    @ApiModelProperty("用户状态")
    @TableField("userState")
    private String userState;
    @ApiModelProperty("机构名称")
    private String orgname;
    @ApiModelProperty("机构编号")
    private String orgcode;
    @ApiModelProperty("系统代码")
    private String xtdm;
    @ApiModelProperty("科室")
    private String department;
    @ApiModelProperty("科室其他")
    private String departmentqt;
    @ApiModelProperty("职称")
    private String title;
    @ApiModelProperty("简述")
    private String sketch;
    private String res0;
    private String res1;
    private String res2;
    private String res3;
    private String res4;
    private String res5;
}
