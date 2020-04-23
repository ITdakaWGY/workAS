package com.as.publichealth.domain.person;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * HEALTH_PERSON
 * 
 * @author wgy
 * @version 1.0.0 2020-04-02
 */
@Data
@ToString
@TableName("health_person")
public class HealthPerson {
    /** 版本号 */
    private static final long serialVersionUID = -7791642156547724969L;


    /** 主键 */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /** 体检编号 */
    private String tjbh;

    /** 条码号 */
    private String tmh;

    /** 体检日期 */
    private Date tjrq;

    /** 机构编号 */
    private String orgno;

    /** 机构名称 */
    private String orgname;

    /** 姓名 */
    private String xm;

    /** 性别 */
    private String sex;

    /** 是否在65-70岁 */
    private String s65;

    /** 是否在71-80岁 */
    private String s71;

    /** 是否80岁以上 */
    private String s80;

    /** 生活不能自理老年人数（自理能力评估≥19分） */
    private String zlnlpg;

    /** 血压异常 */
    private String xyyc;

    /** 体质指数（BMI）≥28 */
    private String tzzs;

    /** 血常规异常 */
    private String xcgyc;

    /** 尿常规异常 */
    private String ncgyc;

    /** 血糖异常 */
    private String xtyc;

    /** 心电图异常 */
    private String xdtyc;

    /** 肝功能异常 */
    private String ggnyc;

    /** 肾功能异常 */
    private String sgnyc;

    /** 血脂异常 */
    private String xzyc;

    /** 腹部B超异常 */
    private String bcyc;

    /** 中医体质辨识异常（非平和质） */
    private String zytzyc;

    /** 脑血管疾病 */
    private String nxgjb;

    /** 肾脏疾病 */
    private String szjb;

    /** 心血管疾病 */
    private String xxgjb;

    /** 高血压 */
    private String gxy;

    /** 眼部疾病 */
    private String ybjb;

    /** 其他神经系统疾病 */
    private String qtsjxtjb;

    /** 糖尿病 */
    private String tnb;

    /** 慢性支气管炎 */
    private String mxzqgy;

    /** 慢性阻塞性肺病 */
    private String mxzsxfb;

    /** 恶性肿瘤 */
    private String exzl;

    /** 老年性骨关节病 */
    private String lnxggjb;

    /** 其他 */
    private String qt;

    /** 慢性高血压 */
    private String mxgxy;

    /** 慢性糖尿病 */
    private String mxtnb;

    /** 慢性冠心病 */
    private String mxgxb;

    /** 慢性脑卒中 */
    private String mxnzz;

}