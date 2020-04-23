package com.as.android.domain.main;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * MAIN
 * 
 * @author wgy
 * @version 1.0.0 2020-04-18
 */
@Data
@ToString
@TableName("main")
public class Main {
    /** 版本号 */
    private static final long serialVersionUID = 5506038561650148679L;

    /** app标识 */
    @ApiModelProperty("appId")
    @TableId(value = "appId", type = IdType.AUTO)
    private Integer appId;

    /** app名字 */
    @ApiModelProperty("app名字")
    @TableField(value = "appname")
    private String appname;

    /** 创建人 */
    @ApiModelProperty("创建人")
    @TableField(value = "creatUser")
    private String creatUser;

    /** 创建时间 */
    @ApiModelProperty("创建时间")
    @TableField(value = "creatTime")
    private Date creatTime;

}