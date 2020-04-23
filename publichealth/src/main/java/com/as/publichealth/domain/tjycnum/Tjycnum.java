package com.as.publichealth.domain.tjycnum;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * TJYCNUM
 * 
 * @author wgy
 * @version 1.0.0 2020-04-02
 */
@Data
@ToString
@TableName("tjycnum")
public class Tjycnum implements Serializable {
    /** 版本号 */
    private static final long serialVersionUID = -359682792243222854L;


    /** 主键 */
    @TableId(value = "id", type = IdType.AUTO)
    @ExcelIgnore
    private Integer id;

    /** 体检日期 */
    @ExcelIgnore
    private Date tjrq;

    /** 机构编号 */
    @ExcelIgnore
    private String orgno;

    /** 机构名称 */
    //@ExcelProperty(index = 0)
    private String orgname;

    /** 65岁及以上常住居民数(人) */
    private Integer num;

    /** 月末累计65岁及以上老年人体检人数(人) */
    private Integer ymtjnum;

    /** 月末累计中医体质辨识人数（人） */
    private Integer ymzytzbs;

    /** 月末累计65岁-70岁老年人体检人数（人） */
    private Integer s65num;

    /** 月末累计65岁-70岁老年人体检人数（人）男 */
    private Integer s65m;

    /** 月末累计65岁-70岁老年人体检人数（人）女 */
    private Integer s65w;

    /** 月末累计健康老年人数（体检无异常）
     （人） */
    private Integer s65jknum;

    /** 月末累计健康老年人数（体检无异常）
     （人）男 */
    private Integer s65jkm;

    /** 月末累计健康老年人数（体检无异常）
     （人）女 */
    private Integer s65jkw;

    /** 65岁-70岁生活不能自理老年总人数（自理能力评估≥19分） */
    private Integer s65zlnlpgnum;

    /** 65岁-70岁生活不能自理老年男性人数 */
    private Integer s65zlnlpgm;

    /** 65岁-70岁生活不能自理老年女性人数 */
    private Integer s65zlnlpgw;

    /** 65岁-70岁血压异常总人数 */
    private Integer s65xyycnum;

    /** 65岁-70岁血压异常男性人数 */
    private Integer s65xyycm;

    /** 65岁-70岁血压异常女性人数 */
    private Integer s65xyycw;

    /** 65岁-70岁体质指数（BMI）≥28总人数 */
    private Integer s65tzzsnum;

    /** 65岁-70岁体质指数（BMI）≥28男性人数 */
    private Integer s65tzzsm;

    /** 65岁-70岁体质指数（BMI）≥28女性人数 */
    private Integer s65tzzsw;

    /** 65岁-70岁血常规异常总人数 */
    private Integer s65xcgycnum;

    /** 65岁-70岁血常规异常男性人数 */
    private Integer s65xcgycm;

    /** 65岁-70岁血常规异常女性人数 */
    private Integer s65xcgycw;

    /** 65岁-70岁尿常规异常总人数 */
    private Integer s65ncgycnum;

    /** 65岁-70岁尿常规异常男性人数 */
    private Integer s65ncgycm;

    /** 65岁-70岁尿常规异常女性人数 */
    private Integer s65ncgycw;

    /** 65岁-70岁血糖异常总人数 */
    private Integer s65xtycnum;

    /** 65岁-70岁血糖异常男性人数 */
    private Integer s65xtycm;

    /** 65岁-70岁血糖异常女性人数 */
    private Integer s65xtycw;

    /** 65岁-70岁心电图异常总人数 */
    private Integer s65xdtycnum;

    /** 65岁-70岁心电图异常男性人数 */
    private Integer s65xdtycm;

    /** 65岁-70岁心电图异常女性人数 */
    private Integer s65xdtycw;

    /** 65岁-70岁肝功能异常总人数 */
    private Integer s65gnycnum;

    /** 65岁-70岁肝功能异常男性人数 */
    private Integer s65gnycm;

    /** 65岁-70岁肝功能异常女性人数 */
    private Integer s65gnycw;

    /** 65岁-70岁肾功能异常总人数 */
    private Integer s65sgnycnum;

    /** 65岁-70岁肾功能异常男性人数 */
    private Integer s65sgnycm;

    /** 65岁-70岁肾功能异常女性人数 */
    private Integer s65sgnycw;

    /** 65岁-70岁血脂异常总人数 */
    private Integer s65xzycnum;

    /** 65岁-70岁血脂异常男性人数 */
    private Integer s65xzycm;

    /** 65岁-70岁血脂异常女性人数 */
    private Integer s65xzycw;

    /** 65岁-70岁腹部B超异常总人数 */
    private Integer s65bcycnum;

    /** 65岁-70岁腹部B超异常男性人数 */
    private Integer s65bcycm;

    /** 65岁-70岁腹部B超异常女性人数 */
    private Integer s65bcycw;

    /** 65岁-70岁中医体质辨识异常（非平和质）总人数 */
    private Integer s65zytzycnum;

    /** 65岁-70岁中医体质辨识异常（非平和质）男性人数 */
    private Integer s65zytzycm;

    /** 65岁-70岁中医体质辨识异常（非平和质）女性人数 */
    private Integer s65zytzycw;

    /** 65岁-70岁脑血管疾病总人数 */
    private Integer s65nxgjbnum;

    /** 65岁-70岁脑血管疾病男性人数 */
    private Integer s65nxgjbm;

    /** 65岁-70岁脑血管疾病女性人数 */
    private Integer s65nxgjbw;

    /** 65岁-70岁肾脏疾病总人数 */
    private Integer s65szjbnum;

    /** 65岁-70岁肾脏疾病男性人数 */
    private Integer s65szjbm;

    /** 65岁-70岁肾脏疾病女性人数 */
    private Integer s65szjbw;

    /** 65岁-70岁心血管疾病总人数 */
    private Integer s65xxgjbnum;

    /** 65岁-70岁心血管疾病男性人数 */
    private Integer s65xxgjbm;

    /** 65岁-70岁心血管疾病女性人数 */
    private Integer s65xxgjbw;

    /** 65岁-70岁高血压总人数 */
    private Integer s65gxynum;

    /** 65岁-70岁高血压男性人数 */
    private Integer s65gxym;

    /** 65岁-70岁高血压女性人数 */
    private Integer s65gxyw;

    /** 65岁-70岁眼部疾病总人数 */
    private Integer s65ybjbnum;

    /** 65岁-70岁眼部疾病男性人数 */
    private Integer s65ybjbm;

    /** 65岁-70岁眼部疾病女性人数 */
    private Integer s65ybjbw;

    /** 65岁-70岁其他神经系统疾病总人数 */
    private Integer s65qtsjxtjbnum;

    /** 65岁-70岁其他神经系统疾病男性人数 */
    private Integer s65qtsjxtjbm;

    /** 65岁-70岁其他神经系统疾病女性人数 */
    private Integer s65qtsjxtjbw;

    /** 65岁-70岁糖尿病总人数 */
    private Integer s65tnbnum;

    /** 65岁-70岁糖尿病男性人数 */
    private Integer s65tnbm;

    /** 65岁-70岁糖尿病女性人数 */
    private Integer s65tnbw;

    /** 65岁-70岁慢性支气管炎总人数 */
    private Integer s65mxzqgynum;

    /** 65岁-70岁慢性支气管炎男性人数 */
    private Integer s65mxzqgym;

    /** 65岁-70岁慢性支气管炎女性人数 */
    private Integer s65mxzqgyw;

    /** 65岁-70岁慢性阻塞性肺病总人数 */
    private Integer s65mxzsxfbnum;

    /** 65岁-70岁慢性阻塞性肺病男性人数 */
    private Integer s65mxzsxfbm;

    /** 65岁-70岁慢性阻塞性肺病女性人数 */
    private Integer s65mxzsxfbw;

    /** 65岁-70岁恶性肿瘤总人数 */
    private Integer s65exzlnum;

    /** 65岁-70岁恶性肿瘤男性人数 */
    private Integer s65exzlm;

    /** 65岁-70岁恶性肿瘤女性人数 */
    private Integer s65exzlw;

    /** 65岁-70岁老年性骨关节病总人数 */
    private Integer s65lnxggjbnum;

    /** 65岁-70岁老年性骨关节病男性人数 */
    private Integer s65lnxggjbm;

    /** 65岁-70岁老年性骨关节病女性人数 */
    private Integer s65lnxggjbw;

    /** 65岁-70岁其他总人数 */
    private Integer s65qtnum;

    /** 65岁-70岁其他男性人数 */
    private Integer s65qtm;

    /** 65岁-70岁其他女性人数 */
    private Integer s65qtw;

    /** 65岁-70岁慢性高血压总人数 */
    private Integer s65mxgxynum;

    /** 65岁-70岁慢性高血压男性人数 */
    private Integer s65mxgxym;

    /** 65岁-70岁慢性高血压女性人数 */
    private Integer s65mxgxyw;

    /** 65岁-70岁慢性糖尿病总人数 */
    private Integer s65mxtnbnum;

    /** 65岁-70岁慢性糖尿病男性人数 */
    private Integer s65mxtnbm;

    /** 65岁-70岁慢性糖尿病女性人数 */
    private Integer s65mxtnbw;

    /** 65岁-70岁慢性冠心病总人数 */
    private Integer s65mxgxbnum;

    /** 65岁-70岁慢性冠心病男性人数 */
    private Integer s65mxgxbm;

    /** 65岁-70岁慢性冠心病女性人数 */
    private Integer s65mxgxbw;

    /** 65岁-70岁慢性脑卒中总人数 */
    private Integer s65mxnzznum;

    /** 65岁-70岁慢性脑卒中男性人数 */
    private Integer s65mxnzzm;

    /** 65岁-70岁慢性脑卒中女性人数 */
    private Integer s65mxnzzw;

    /** 月末累计71岁-80岁老年人体检人数（人） */
    private Integer s71num;

    /** 月末累计71岁-80岁老年人体检人数（人）男 */
    private Integer s71m;

    /** 月末累计71岁-80岁老年人体检人数（人）女 */
    private Integer s71w;

    /** 月末累计健康老年人数（体检无异常）
     （人） */
    private Integer s71jknum;

    /** 月末累计健康老年人数（体检无异常）
     （人）男 */
    private Integer s71jkm;

    /** 月末累计健康老年人数（体检无异常）
     （人）女 */
    private Integer s71jkw;

    /** 71-80岁生活不能自理老年总人数（自理能力评估≥19分） */
    private Integer s71zlnlpgnum;

    /** 71-80岁生活不能自理老年男性人数 */
    private Integer s71zlnlpgm;

    /** 71-80岁生活不能自理老年女性人数 */
    private Integer s71zlnlpgw;

    /** 71-80岁血压异常总人数 */
    private Integer s71xyycnum;

    /** 71-80岁血压异常男性人数 */
    private Integer s71xyycm;

    /** 71-80岁血压异常女性人数 */
    private Integer s71xyycw;

    /** 71-80岁体质指数（BMI）≥28总人数 */
    private Integer s71tzzsnum;

    /** 71-80岁体质指数（BMI）≥28男性人数 */
    private Integer s71tzzsm;

    /** 71-80岁体质指数（BMI）≥28女性人数 */
    private Integer s71tzzsw;

    /** 71-80岁血常规异常总人数 */
    private Integer s71xcgycnum;

    /** 71-80岁血常规异常男性人数 */
    private Integer s71xcgycm;

    /** 71-80岁血常规异常女性人数 */
    private Integer s71xcgycw;

    /** 71-80岁尿常规异常总人数 */
    private Integer s71ncgycnum;

    /** 71-80岁尿常规异常男性人数 */
    private Integer s71ncgycm;

    /** 71-80岁尿常规异常女性人数 */
    private Integer s71ncgycw;

    /** 71-80岁血糖异常总人数 */
    private Integer s71xtycnum;

    /** 71-80岁血糖异常男性人数 */
    private Integer s71xtycm;

    /** 71-80岁血糖异常女性人数 */
    private Integer s71xtycw;

    /** 71-80岁心电图异常总人数 */
    private Integer s71xdtycnum;

    /** 71-80岁心电图异常男性人数 */
    private Integer s71xdtycm;

    /** 71-80岁心电图异常女性人数 */
    private Integer s71xdtycw;

    /** 71-80岁肝功能异常总人数 */
    private Integer s71gnycnum;

    /** 71-80岁肝功能异常男性人数 */
    private Integer s71gnycm;

    /** 71-80岁肝功能异常女性人数 */
    private Integer s71gnycw;

    /** 71-80岁肾功能异常总人数 */
    private Integer s71sgnycnum;

    /** 71-80岁肾功能异常男性人数 */
    private Integer s71sgnycm;

    /** 71-80岁肾功能异常女性人数 */
    private Integer s71sgnycw;

    /** 71-80岁血脂异常总人数 */
    private Integer s71xzycnum;

    /** 71-80岁血脂异常男性人数 */
    private Integer s71xzycm;

    /** 71-80岁血脂异常女性人数 */
    private Integer s71xzycw;

    /** 71-80岁腹部B超异常总人数 */
    private Integer s71bcycnum;

    /** 71-80岁腹部B超异常男性人数 */
    private Integer s71bcycm;

    /** 71-80岁腹部B超异常女性人数 */
    private Integer s71bcycw;

    /** 71-80岁中医体质辨识异常（非平和质）总人数 */
    private Integer s71zytzycnum;

    /** 71-80岁中医体质辨识异常（非平和质）男性人数 */
    private Integer s71zytzycm;

    /** 71-80岁中医体质辨识异常（非平和质）女性人数 */
    private Integer s71zytzycw;

    /** 71-80岁脑血管疾病总人数 */
    private Integer s71nxgjbnum;

    /** 71-80岁脑血管疾病男性人数 */
    private Integer s71nxgjbm;

    /** 71-80岁脑血管疾病女性人数 */
    private Integer s71nxgjbw;

    /** 71-80岁肾脏疾病总人数 */
    private Integer s71szjbnum;

    /** 71-80岁肾脏疾病男性人数 */
    private Integer s71szjbm;

    /** 71-80岁肾脏疾病女性人数 */
    private Integer s71szjbw;

    /** 71-80岁心血管疾病总人数 */
    private Integer s71xxgjbnum;

    /** 71-80岁心血管疾病男性人数 */
    private Integer s71xxgjbm;

    /** 71-80岁心血管疾病女性人数 */
    private Integer s71xxgjbw;

    /** 71-80岁高血压总人数 */
    private Integer s71gxynum;

    /** 71-80岁高血压男性人数 */
    private Integer s71gxym;

    /** 71-80岁高血压女性人数 */
    private Integer s71gxyw;

    /** 71-80岁眼部疾病总人数 */
    private Integer s71ybjbnum;

    /** 71-80岁眼部疾病男性人数 */
    private Integer s71ybjbm;

    /** 71-80岁眼部疾病女性人数 */
    private Integer s71ybjbw;

    /** 71-80岁其他神经系统疾病总人数 */
    private Integer s71qtsjxtjbnum;

    /** 71-80岁其他神经系统疾病男性人数 */
    private Integer s71qtsjxtjbm;

    /** 71-80岁其他神经系统疾病女性人数 */
    private Integer s71qtsjxtjbw;

    /** 71-80岁糖尿病总人数 */
    private Integer s71tnbnum;

    /** 71-80岁糖尿病男性人数 */
    private Integer s71tnbm;

    /** 71-80岁糖尿病女性人数 */
    private Integer s71tnbw;

    /** 71-80岁慢性支气管炎总人数 */
    private Integer s71mxzqgynum;

    /** 71-80岁慢性支气管炎男性人数 */
    private Integer s71mxzqgym;

    /** 71-80岁慢性支气管炎女性人数 */
    private Integer s71mxzqgyw;

    /** 71-80岁慢性阻塞性肺病总人数 */
    private Integer s71mxzsxfbnum;

    /** 71-80岁慢性阻塞性肺病男性人数 */
    private Integer s71mxzsxfbm;

    /** 71-80岁慢性阻塞性肺病女性人数 */
    private Integer s71mxzsxfbw;

    /** 71-80岁恶性肿瘤总人数 */
    private Integer s71exzlnum;

    /** 71-80岁恶性肿瘤男性人数 */
    private Integer s71exzlm;

    /** 71-80岁恶性肿瘤女性人数 */
    private Integer s71exzlw;

    /** 71-80岁老年性骨关节病总人数 */
    private Integer s71lnxggjbnum;

    /** 71-80岁老年性骨关节病男性人数 */
    private Integer s71lnxggjbm;

    /** 71-80岁老年性骨关节病女性人数 */
    private Integer s71lnxggjbw;

    /** 71-80岁其他总人数 */
    private Integer s71qtnum;

    /** 71-80岁其他男性人数 */
    private Integer s71qtm;

    /** 71-80岁其他女性人数 */
    private Integer s71qtw;

    /** 71-80岁慢性高血压总人数 */
    private Integer s71mxgxynum;

    /** 71-80岁慢性高血压男性人数 */
    private Integer s71mxgxym;

    /** 71-80岁慢性高血压女性人数 */
    private Integer s71mxgxyw;

    /** 71-80岁慢性糖尿病总人数 */
    private Integer s71mxtnbnum;

    /** 71-80岁慢性糖尿病男性人数 */
    private Integer s71mxtnbm;

    /** 71-80岁慢性糖尿病女性人数 */
    private Integer s71mxtnbw;

    /** 71-80岁慢性冠心病总人数 */
    private Integer s71mxgxbnum;

    /** 71-80岁慢性冠心病男性人数 */
    private Integer s71mxgxbm;

    /** 71-80岁慢性冠心病女性人数 */
    private Integer s71mxgxbw;

    /** 71-80岁慢性脑卒中总人数 */
    private Integer s71mxnzznum;

    /** 71-80岁慢性脑卒中男性人数 */
    private Integer s71mxnzzm;

    /** 71-80岁慢性脑卒中女性人数 */
    private Integer s71mxnzzw;

    /** 月末累计80岁以上老年人体检人数（人） */
    private Integer s80num;

    /** 月末累计80岁以上老年人体检人数（人）男 */
    private Integer s80m;

    /** 月末累计80岁以上老年人体检人数（人）女 */
    private Integer s80w;

    /** 月末累计健康老年人数（体检无异常）
     （人） */
    private Integer s80jknum;

    /** 月末累计健康老年人数（体检无异常）
     （人）男 */
    private Integer s80jkm;

    /** 月末累计健康老年人数（体检无异常）
     （人）女 */
    private Integer s80jkw;

    /** 80岁以上生活不能自理老年总人数（自理能力评估≥19分） */
    private Integer s80zlnlpgnum;

    /** 80岁以上生活不能自理老年男性人数 */
    private Integer s80zlnlpgm;

    /** 80岁以上生活不能自理老年女性人数 */
    private Integer s80zlnlpgw;

    /** 80岁以上血压异常总人数 */
    private Integer s80xyycnum;

    /** 80岁以上血压异常男性人数 */
    private Integer s80xyycm;

    /** 80岁以上血压异常女性人数 */
    private Integer s80xyycw;

    /** 80岁以上体质指数（BMI）≥28总人数 */
    private Integer s80tzzsnum;

    /** 80岁以上体质指数（BMI）≥28男性人数 */
    private Integer s80tzzsm;

    /** 80岁以上体质指数（BMI）≥28女性人数 */
    private Integer s80tzzsw;

    /** 80岁以上血常规异常总人数 */
    private Integer s80xcgycnum;

    /** 80岁以上血常规异常男性人数 */
    private Integer s80xcgycm;

    /** 80岁以上血常规异常女性人数 */
    private Integer s80xcgycw;

    /** 80岁以上尿常规异常总人数 */
    private Integer s80ncgycnum;

    /** 80岁以上尿常规异常男性人数 */
    private Integer s80ncgycm;

    /** 80岁以上尿常规异常女性人数 */
    private Integer s80ncgycw;

    /** 80岁以上血糖异常总人数 */
    private Integer s80xtycnum;

    /** 80岁以上血糖异常男性人数 */
    private Integer s80xtycm;

    /** 80岁以上血糖异常女性人数 */
    private Integer s80xtycw;

    /** 80岁以上心电图异常总人数 */
    private Integer s80xdtycnum;

    /** 80岁以上心电图异常男性人数 */
    private Integer s80xdtycm;

    /** 80岁以上心电图异常女性人数 */
    private Integer s80xdtycw;

    /** 80岁以上肝功能异常总人数 */
    private Integer s80gnycnum;

    /** 80岁以上肝功能异常男性人数 */
    private Integer s80gnycm;

    /** 80岁以上肝功能异常女性人数 */
    private Integer s80gnycw;

    /** 80岁以上肾功能异常总人数 */
    private Integer s80sgnycnum;

    /** 80岁以上肾功能异常男性人数 */
    private Integer s80sgnycm;

    /** 80岁以上肾功能异常女性人数 */
    private Integer s80sgnycw;

    /** 80岁以上血脂异常总人数 */
    private Integer s80xzycnum;

    /** 80岁以上血脂异常男性人数 */
    private Integer s80xzycm;

    /** 80岁以上血脂异常女性人数 */
    private Integer s80xzycw;

    /** 80岁以上腹部B超异常总人数 */
    private Integer s80bcycnum;

    /** 80岁以上腹部B超异常男性人数 */
    private Integer s80bcycm;

    /** 80岁以上腹部B超异常女性人数 */
    private Integer s80bcycw;

    /** 80岁以上中医体质辨识异常（非平和质）总人数 */
    private Integer s80zytzycnum;

    /** 80岁以上中医体质辨识异常（非平和质）男性人数 */
    private Integer s80zytzycm;

    /** 80岁以上中医体质辨识异常（非平和质）女性人数 */
    private Integer s80zytzycw;

    /** 80岁以上脑血管疾病总人数 */
    private Integer s80nxgjbnum;

    /** 80岁以上脑血管疾病男性人数 */
    private Integer s80nxgjbm;

    /** 80岁以上脑血管疾病女性人数 */
    private Integer s80nxgjbw;

    /** 80岁以上肾脏疾病总人数 */
    private Integer s80szjbnum;

    /** 80岁以上肾脏疾病男性人数 */
    private Integer s80szjbm;

    /** 80岁以上肾脏疾病女性人数 */
    private Integer s80szjbw;

    /** 80岁以上心血管疾病总人数 */
    private Integer s80xxgjbnum;

    /** 80岁以上心血管疾病男性人数 */
    private Integer s80xxgjbm;

    /** 80岁以上心血管疾病女性人数 */
    private Integer s80xxgjbw;

    /** 80岁以上高血压总人数 */
    private Integer s80gxynum;

    /** 80岁以上高血压男性人数 */
    private Integer s80gxym;

    /** 80岁以上高血压女性人数 */
    private Integer s80gxyw;

    /** 80岁以上眼部疾病总人数 */
    private Integer s80ybjbnum;

    /** 80岁以上眼部疾病男性人数 */
    private Integer s80ybjbm;

    /** 80岁以上眼部疾病女性人数 */
    private Integer s80ybjbw;

    /** 80岁以上其他神经系统疾病总人数 */
    private Integer s80qtsjxtjbnum;

    /** 80岁以上其他神经系统疾病男性人数 */
    private Integer s80qtsjxtjbm;

    /** 80岁以上其他神经系统疾病女性人数 */
    private Integer s80qtsjxtjbw;

    /** 80岁以上糖尿病总人数 */
    private Integer s80tnbnum;

    /** 80岁以上糖尿病男性人数 */
    private Integer s80tnbm;

    /** 80岁以上糖尿病女性人数 */
    private Integer s80tnbw;

    /** 80岁以上慢性支气管炎总人数 */
    private Integer s80mxzqgynum;

    /** 80岁以上慢性支气管炎男性人数 */
    private Integer s80mxzqgym;

    /** 80岁以上慢性支气管炎女性人数 */
    private Integer s80mxzqgyw;

    /** 80岁以上慢性阻塞性肺病总人数 */
    private Integer s80mxzsxfbnum;

    /** 80岁以上慢性阻塞性肺病男性人数 */
    private Integer s80mxzsxfbm;

    /** 80岁以上慢性阻塞性肺病女性人数 */
    private Integer s80mxzsxfbw;

    /** 80岁以上恶性肿瘤总人数 */
    private Integer s80exzlnum;

    /** 80岁以上恶性肿瘤男性人数 */
    private Integer s80exzlm;

    /** 80岁以上恶性肿瘤女性人数 */
    private Integer s80exzlw;

    /** 80岁以上老年性骨关节病总人数 */
    private Integer s80lnxggjbnum;

    /** 80岁以上老年性骨关节病男性人数 */
    private Integer s80lnxggjbm;

    /** 80岁以上老年性骨关节病女性人数 */
    private Integer s80lnxggjbw;

    /** 80岁以上其他总人数 */
    private Integer s80qtnum;

    /** 80岁以上其他男性人数 */
    private Integer s80qtm;

    /** 80岁以上其他女性人数 */
    private Integer s80qtw;

    /** 80岁以上慢性高血压总人数 */
    private Integer s80mxgxynum;

    /** 80岁以上慢性高血压男性人数 */
    private Integer s80mxgxym;

    /** 80岁以上慢性高血压女性人数 */
    private Integer s80mxgxyw;

    /** 80岁以上慢性糖尿病总人数 */
    private Integer s80mxtnbnum;

    /** 80岁以上慢性糖尿病男性人数 */
    private Integer s80mxtnbm;

    /** 80岁以上慢性糖尿病女性人数 */
    private Integer s80mxtnbw;

    /** 80岁以上慢性冠心病总人数 */
    private Integer s80mxgxbnum;

    /** 80岁以上慢性冠心病男性人数 */
    private Integer s80mxgxbm;

    /** 80岁以上慢性冠心病女性人数 */
    private Integer s80mxgxbw;

    /** 80岁以上慢性脑卒中总人数 */
    private Integer s80mxnzznum;

    /** 80岁以上慢性脑卒中男性人数 */
    private Integer s80mxnzzm;

    /** 80岁以上慢性脑卒中女性人数 */
    private Integer s80mxnzzw;
}