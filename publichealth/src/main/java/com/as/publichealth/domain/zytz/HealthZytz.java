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
public class HealthZytz {
    /** 版本号 */
    private static final long serialVersionUID = 1583631012838044101L;

    /* This code was generated by TableGo tools, mark 1 begin. */

    /** id */
    private Integer id;

    /** tjbh */
    private String tjbh;

    /** 身份证 */
    private String sfz;

    /** 姓名 */
    private String xm;

    /** 条码号 */
    private String tmh;

    /** 随访日期 */
    private Date sfrq;

    /** sfys */
    private String sfys;

    /** q1 */
    private Integer q1;

    /** q2 */
    private Integer q2;

    /** q3 */
    private Integer q3;

    /** q4 */
    private Integer q4;

    /** q5 */
    private Integer q5;

    /** q6 */
    private Integer q6;

    /** q7 */
    private Integer q7;

    /** q8 */
    private Integer q8;

    /** q9 */
    private Integer q9;

    /** q10 */
    private Integer q10;

    /** q11 */
    private Integer q11;

    /** q12 */
    private Integer q12;

    /** q13 */
    private Integer q13;

    /** q14 */
    private Integer q14;

    /** q15 */
    private Integer q15;

    /** q16 */
    private Integer q16;

    /** q17 */
    private Integer q17;

    /** q18 */
    private Integer q18;

    /** q19 */
    private Integer q19;

    /** q20 */
    private Integer q20;

    /** q21 */
    private Integer q21;

    /** q22 */
    private Integer q22;

    /** q23 */
    private Integer q23;

    /** q24 */
    private Integer q24;

    /** q25 */
    private Integer q25;

    /** q26 */
    private Integer q26;

    /** q27 */
    private Integer q27;

    /** q28 */
    private Integer q28;

    /** q29 */
    private Integer q29;

    /** q30 */
    private Integer q30;

    /** q31 */
    private Integer q31;

    /** q32 */
    private Integer q32;

    /** q33 */
    private Integer q33;

    /** 平和质得分 */
    private Integer phzdf;

    /** 平和质结论 1否 2是 3基本是 */
    private Integer phzjl;

    /** 平和质指导 */
    private String phzzd;

    /** 气虚质得分 */
    private Integer qxzdf;

    /** 气虚质结论1否 2是 3倾向是 */
    private Integer qxzjl;

    /** 气虚质指导 */
    private String qxzzd;

    /** yxzdf */
    private Integer yxzdf;

    /** yxzjl */
    private Integer yxzjl;

    /** yxzzd */
    private String yxzzd;

    /** yinxzdf */
    private Integer yinxzdf;

    /** yinxzjl */
    private Integer yinxzjl;

    /** yinxzzd */
    private String yinxzzd;

    /** tszdf */
    private Integer tszdf;

    /** tszjl */
    private Integer tszjl;

    /** tszzd */
    private String tszzd;

    /** srzdf */
    private Integer srzdf;

    /** srzjl */
    private Integer srzjl;

    /** srzzd */
    private String srzzd;

    /** xyzdf */
    private Integer xyzdf;

    /** xyzjl */
    private Integer xyzjl;

    /** xyzzd */
    private String xyzzd;

    /** qyzdf */
    private Integer qyzdf;

    /** qyzjl */
    private Integer qyzjl;

    /** qyzzd */
    private String qyzzd;

    /** tbzdf */
    private Integer tbzdf;

    /** tbzjl */
    private Integer tbzjl;

    /** tbzzd */
    private String tbzzd;

    /** sczt */
    private Integer sczt;

    /** tbzt */
    private Integer tbzt;

    /** tjnf */
    private Date tjnf;

    /** orgno */
    private String orgno;

    /** orgname */
    private String orgname;

    /** createtime */
    private Date createtime;

    /** updatetime */
    private Date updatetime;


}