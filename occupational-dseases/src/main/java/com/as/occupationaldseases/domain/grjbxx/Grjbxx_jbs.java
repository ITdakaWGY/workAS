package com.as.occupationaldseases.domain.grjbxx;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 *输血
 */

@Data
@ToString
public class Grjbxx_jbs {
    @ApiModelProperty("输血id")
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("档案号")
    private String personalid;
    @ApiModelProperty("输血代码")
    private String jbsdm;
    @ApiModelProperty("确诊时间")
    private String qzsj;
    @ApiModelProperty("其他输血描述")
    private String qtjbs;
    @ApiModelProperty("职业病描述")
    private String zybms;
    @ApiModelProperty("恶性肿瘤描述")
    private String exzlms;
    private String res0;
    private String res1;
    private String res2;
    private String res3;
    private String res4;
    private String res5;

}
