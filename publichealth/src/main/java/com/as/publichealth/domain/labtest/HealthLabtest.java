package com.as.publichealth.domain.labtest;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
public class HealthLabtest {
    /** 版本号 */
    private static final long serialVersionUID = -2436739572186587522L;

    /* This code was generated by TableGo tools, mark 1 begin. */

    /** id */
    private Integer id;

    /** 体检编号 */
    private String tjbh;

    /** tmh */
    private String tmh;

    /** tjrq */
    private Date tjrq;

    /** 左侧收缩压 */
    private Integer zcgy;

    /** 左侧舒张压 */
    private Integer zcdy;

    /** 右侧收缩压 */
    private Integer ycgy;

    /** 右侧舒张压 */
    private Integer ycdy;

    /** 身高 */
    private Float height;

    /** 体重 */
    private Float weight;

    /** 体质指数 */
    private Float bmi;

    /** 血红蛋白 */
    private Integer hgb;

    /** 白细胞 */
    private Float wbc;

    /** 血小板 */
    private Integer plt;

    /** 红细胞 */
    private Float rbc;

    /** 红细胞压积 */
    private Float hct;

    /** 淋巴细胞数目 */
    private Float lymphnum;

    /** 中间细胞数目 */
    private Float midnum;

    /** 中性粒细胞数 */
    private Float grannum;

    /** 平均血红蛋白浓度 */
    private Float mchc;

    /** 红细胞平均体积 */
    private Float mcv;

    /** 平均血红蛋白含量 */
    private Float mch;

    /** 淋巴细胞百分比 */
    private Float lymphperc;

    /** 中间细胞百分比 */
    private Float midperc;

    /** 中性粒细胞百分比 */
    private Float granperc;

    /** 红细胞分布宽度标准差 */
    private Float rdwsd;

    /** 平均血小板体积 */
    private Float mpv;

    /** 血小板分布宽度标准差 */
    private Float pdwsd;

    /** 血小板压积 */
    private Float pct;

    /** rdwcv */
    private Float rdwcv;

    /** 血小板分布宽度变异系数 */
    private Float pdwcv;

    /** 大血小板比率 */
    private Float plcr;

    /** xcgqt */
    private String xcgqt;

    /** 尿蛋白 */
    private String npro;

    /** 尿糖 */
    private String nglu;

    /** 尿酮体 */
    private String nket;

    /** 潜血 */
    private String nblo;

    /** ncgqt */
    private String ncgqt;

    /** 尿白细胞 */
    private String nleu;

    /** 亚硝酸盐 */
    private String nnit;

    /** 尿胆原 */
    private String nuro;

    /** PH */
    private String nph;

    /** 尿比重 */
    private String nsg;

    /** 尿胆红素 */
    private String nbil;

    /** 抗坏血酸 */
    private String nvc;

    /** niaoma */
    private String niaoma;

    /** niaocr */
    private String niaocr;

    /** niaomabcr */
    private String niaomabcr;

    /** 谷丙转氨酶 */
    private Float alt;

    /** 谷草转氨酶 */
    private Float ast;

    /** 总蛋白 */
    private Float tp;

    /** 白蛋白 */
    private Float alb;

    /** 总胆红素 */
    private Float tbil;

    /** 直接胆红素 */
    private Float dbil;

    /** 肌酐 */
    private Float crea;

    /** 尿素氮 */
    private Float urea;

    /** 总胆固醇 */
    private Float cho;

    /** 甘油三酯 */
    private Float tg;

    /** 低密度脂蛋白 */
    private Float ldlc;

    /** 高密度脂蛋白 */
    private Float hdlc;

    /** 血糖 */
    private Float glu;

    /** 尿酸 */
    private Float ua;

    /** 甲胎蛋白 */
    private Float afp;

    /** 癌胚抗原 */
    private Float cea;

    /** 同型半恍氨酸 */
    private Float hcy;

    /** 乙肝表面抗原 */
    private String hbsag;

    /** 球蛋白 */
    private Float glo;

    /** 碱性磷酸酶 */
    private Float alp;

    /** Y-谷氨酰转移酶 */
    private Float ggt;

    /** 血钾 */
    private Float kB;

    /** 血钠 */
    private Float naB;

    /** 血钙 */
    private Float caB;

    /** 血氯 */
    private Float clB;

    /** tiwen */
    private Float tiwen;

    /** 胸部X片 */
    private String xbxp;

    /** 胸部X片异常描述 */
    private String xbxpyc;

    /** 心率 */
    private Integer xl;

    /** 心电图 */
    private String xdt;

    /** 心电图异常描述 */
    private String xdtyc;

    /** 心电图审核结论 */
    private String xdtjl;

    /** 心电图其他 */
    private String xdtqt;

    /** B超 */
    private String bc;

    /** B超提示 */
    private String bcts;

    /** B超所见 */
    private String bcsj;

    /** B超医生 */
    private String bcdoctor;

    /** 机构编号 */
    private String orgno;

    /** 机构名称 */
    private String orgname;

    /** createtime */
    private Date createtime;

    /** updatetime */
    private Date updatetime;

    /** 状态 */
    private String state;

}