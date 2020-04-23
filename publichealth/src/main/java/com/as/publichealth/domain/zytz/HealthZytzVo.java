package com.as.publichealth.domain.zytz;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * HEALTH_ZYTZ
 * 
 * @author wgy
 * @version 1.0.0 2020-04-09
 */
@Data
@ToString
@TableName("HEALTH_ZYTZ")
public class HealthZytzVo {

    /** 条码号 */
    private String tmh;

    /** 平和质结论 1否 2是 3基本是 */
    private Integer phzjl;


}