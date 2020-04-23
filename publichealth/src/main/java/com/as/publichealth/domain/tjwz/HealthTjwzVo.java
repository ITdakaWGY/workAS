package com.as.publichealth.domain.tjwz;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * HEALTH_TJWZ
 * 
 * @author wgy
 * @version 1.0.0 2020-04-08
 */
@Data
@ToString
@TableName("health_tjwz")
public class HealthTjwzVo {
    /** 条码号 */
    private String tmh;

    /** 自理能力评估 */
    private Integer zlnlpg;

    /** 脑血管疾病 */
    private String nxgjb;

    /** 肾脏疾病 */
    private String szjb;

    /** 眼部疾病 */
    private String ybjb;

    /** 神经系统疾病 */
    private String sjxtjb;

    /** 心脏疾病 */
    private String xzjb;

    /** 血管疾病 */
    private String xgjb;

    /** qtxtjb */
    private String qtxtjb;

    /** 心脏疾病其他 */
    private String xzjbqt;

    /** 脑血管疾病其他 */
    private String nxgjbqt;

}