package com.as.occupationaldseases.domain.grjbxx;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 外伤附表
 */
@Data
@ToString
public  class Grjbxx_ws {
    @ApiModelProperty("外伤id")
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("档案号")
    private String personalid;
    @ApiModelProperty("外伤名称")
    private String wsmc;
    @ApiModelProperty("外伤时间")
    private String wssj;
    @ApiModelProperty("是否外伤")
    private String sfws;
    private String res0;
    private String res1;
    private String res2;
    private String res3;
    private String res4;
    private String res5;

}
