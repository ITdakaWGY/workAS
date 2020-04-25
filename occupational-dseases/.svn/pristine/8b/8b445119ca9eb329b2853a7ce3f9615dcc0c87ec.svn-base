package com.as.occupationaldseases.domain.menuRoleRelation;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@TableName("menu_role_relation")
public class MenuRoleRelation {

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("角色编号")
    private String rolecode;

    @ApiModelProperty("菜单id")
    private String menuid;
}
