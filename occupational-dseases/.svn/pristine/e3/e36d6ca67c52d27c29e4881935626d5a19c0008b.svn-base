package com.as.occupationaldseases.domain.personal_progress;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@TableName("personal_progress")
public class PersonalProgress {
    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty("工作单位")
    private String companyname;
    @ApiModelProperty("单位编号")
    private String companycode;
    @ApiModelProperty("身份证")
    private String sfz;
    @ApiModelProperty("体检日期")
    private Date tjrq;
    @ApiModelProperty("条码号")
    private String tmh;
    @ApiModelProperty("姓名")
    private String xm;
    @ApiModelProperty("体检项目")
    private String tjxm;
    @ApiModelProperty("体检项目标识")
    private String tjxmflag;
    @ApiModelProperty("总检数")
    private Integer zcount;
    @ApiModelProperty("已检数")
    private Integer yjcount;
    @ApiModelProperty("未检数")
    private Integer wjcount;
    @ApiModelProperty("本人电话")
    private String brdh;

    private String res1;
    private String res2;
    private String res3;
    private String res4;
    private String res5;

}
