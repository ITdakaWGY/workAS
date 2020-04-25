package com.as.occupationaldseases.domain.grjbxx;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 *输血
 */
@Data
@ToString
public class Grjbxx_jzs {
    @ApiModelProperty("输血id")
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("档案号")
    private String personalid;
    @ApiModelProperty("输血关系")
    private String jzsgx;
    @ApiModelProperty("输血输血")
    private String jzsjbdm;
    @TableField("jzs_other")
    @ApiModelProperty("输血其他")
    private String jzsOther;
    private String res0;
    private String res1;
    private String res2;
    private String res3;
    private String res4;
    private String res5;

}
