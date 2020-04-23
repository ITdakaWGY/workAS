package com.as.publichealth.domain.labtest;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * HEALTH_LABTEST
 * 
 * @author wgy
 * @version 1.0.0 2020-04-08
 */
@Data
@ToString
@TableName("health_labtest")
public class HealthLabtestVo {
    /** 右侧收缩压 */
    private Integer ycgy;

    /** 左侧收缩压 */
    private Integer zcgy;

    /** tmh */
    private String tmh;

    /** 体质指数 */
    private Float bmi;

    /** 血糖 */
    private Float glu;

    /** 心电图 */
    private String xdt;

    /** B超 */
    private String bc;

    /** 血红蛋白 */
    private Integer hgb;

    /** 白细胞 */
    private Float wbc;

    /** 血小板 */
    private Integer plt;

    /** 尿蛋白 */
    private String npro;

    /** 尿糖 */
    private String nglu;

    /** 尿酮体 */
    private String nket;

    /** 潜血 */
    private String nblo;

    /** 谷草转氨酶 */
    private Float ast;

    /** 总胆红素 */
    private Float tbil;

    /** 肌酐 */
    private Float crea;

    /** 总胆固醇 */
    private Float cho;

    /** 甘油三酯 */
    private Float tg;

    /** 低密度脂蛋白 */
    private Float ldlc;

    /** 高密度脂蛋白 */
    private Float hdlc;
}