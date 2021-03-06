package com.as.upload.domain.hdgrjbxx;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 个人基本信息表(居民基本信息表)(HD_GRJBXX)
 * 
 * @author wgy
 * @version 1.0.0 2020-03-02
 */

import java.math.BigDecimal;

@Data
@ToString
@TableName("HD_GRJBXX")
@KeySequence(value="S_HD_GRJBXX",clazz=Long.class)
public class HdGrjbxx {
    /** 版本号 */
    private static final long serialVersionUID = 2887732964346802723L;

    /* This code was generated by TableGo tools, mark 1 begin. */

    /** ID */
    @TableId(type= IdType.INPUT)  //注意主键类型要指定为Input
    private Long id;

    /** 健康档案号 */
    private String personalid;

    /** 身份证 */
    private String sfz;

    /** 居民姓名 */
    private String xm;

    /** 性别编码1男   2女  9未说明的性别   0未知的性别  */
    private String sex;

    /** 证件类别代码 */
    private String idtype;

    /** 单位名称 */
    private String gzdw;

    /** 本人联系电话 */
    private String brdh;

    /** 联系人姓名 */
    private String lxrxm;

    /** 联系人电话 */
    private String lxrdh;

    /** 常住地址户籍代码1户籍   2非户籍  */
    private String czlx;

    /** 民族代码01汉族 99少数民族 */
    private String mzCode;

    /** ABO血型代码1 A型   2 B型   3 O型   4 AB型 */
    private String abo;

    /** Rh血型标志1否  2是  3不详 */
    private String rh;

    /** 文化程度代码1研究生2大学本科3大学专科和专科学校4中等专业学校5技工学校6高中7初中
8小学9文盲或半文盲 
 */
    private String whcd;

    /** 职业类型代码0国家机关、党群组织、企业、事业单位负责人 1专业技术人员 2办事人员和有关人员  3商业、服务业人员 4 农、林、牧、渔、水利业生产人员  5生产、运输设备操作人员及有关人员  6军人  7不便分类的其他从业人员  8无职业   */
    private String job;

    /** 婚姻状况代码1未婚  2 已婚  3丧偶  4离婚  5未说明的婚姻状况 */
    private String hyzk;

    /** 医疗费支付方式代码1城镇或省直职工基本医疗保险  医保卡号:                                              
2居民基本医疗保险            医保卡号:                                     
3贫困救助                    卡号:                                              
4商业医疗保险   5全公费  6全自费  7其他                
 */
    private String paymentCode;

    /** 城镇或省直职工基本医疗保险医保卡号 */
    private String czybkh;

    /** 居民基本医疗保险医保卡号 */
    private String jmybkh;

    /** 贫困救助卡号 */
    private String pkjzkh;

    /** 医疗费支付方式其他描述 */
    private String paymentOther;

    /** 药物过敏史代码1无   2青霉素  3磺胺   4链霉素   5其他 */
    private String ywgms;

    /** 药物过敏史其他描述 */
    private String ywgmsOther;

    /** 暴露史代码1无   2化学品    3毒物    4射线    */
    private String bls;

    /** 遗传代码1无 2有 */
    private String ycbs;

    /** 遗传病史名称 */
    private String ycbsName;

    /** 残疾代码1无残疾 2 视力残疾 3听力残疾 4言语残疾 5 肢体残疾
6智力残疾 7精神残疾  8其他残疾
 */
    private String cjqk;

    /** 其他残疾名称 */
    private String cjqkOther;

    /** 本人与户主关系(字典中的代码) */
    private String relation;

    /** 户主姓名 */
    private String hzxm;

    /** 户主身份证号 */
    private String hzsfz;

    /** 家庭人口数 */
    private String familyNum;

    /** 家庭结构 */
    private String famlyJg;

    /** 居住情况代码1.1.与成年子女同住  2.与子孙三代（四代）同住  3.夫妻二人同住 4.独居 5.计划生育特殊家庭  */
    private String jzqk;

    /** 厨房排风设施类别1无       2油烟机   3换气扇   4烟囱  */
    private String cfpfss;

    /** 家庭燃料类别代码1液化气   2煤       3天然气   4沼气   5柴火  6其他  */
    private String fuel;

    /** 家庭饮水类别代码1自来水   2经净化过滤的水   3井水  4河湖水  5塘水 6其他  */
    private String water;

    /** 家庭厕所类别代码1卫生厕所 2一格或二格粪池式 3马桶  4露天粪坑  5简易棚厕 */
    private String cs;

    /** 家庭禽畜栏类别1无     2单设     3室内     4室外  */
    private String qcl;

    /** 签名人 */
    private String sign;

    /** 签名人URL */
    private String signUrl;

    /** 省 */
    private String province;

    /** 市 */
    private String city;

    /** 县 */
    private String county;

    /** 街道 */
    private String street;

    /** 居委会 */
    private String committee;

    /** 户主标志 */
    private String hzbz;

    /** 户籍地址 */
    private String hjdz;

    /** 现住详细地址 */
    private String xzxxdz;

    /** 活动非活动(0和1) */
    private String daState;

    /** 是否高血压 */
    private String isgxy;

    /** 是否糖尿病 */
    private String istnb;

    /** 是否脑卒中 */
    private String isncz;

    /** 是否冠心病 */
    private String isgxb;

    /** 是否老人 */
    private String isold;

    /** 是否精神病 */
    private String isjsb;

    /** 是否结核病 */
    private String isjhb;

    /** 是否妇女 */
    private String isfn;

    /** 是否儿童 */
    private String iset;

    /** 是否残疾人 */
    private String iscjr;

    /** 是否贫困户 */
    private String ispkh;

    /** 是否计划生育特殊家庭 */
    private String istsjt;

    /** 原个人信息ID（原个人档案编号） */
    private String personalidOld;

    /** 新个人信息ID（新个人档案编号） */
    private String personalidNew;

    /** 村服务签约状态 */
    private String signCondition;

    /** 预防接种卡号 */
    private String idcardVas;

    /** 孕产情况 */
    private String ycqk;

    /** 孕妇孕次 */
    private String yfyc;

    /** 孕妇产次 */
    private String yfcc;

    /** 创建机构编号 */
    private String createorg;

    /** 更新机构编号 */
    private String updateorg;

    /** 创建人（建档医生）ID */
    private String createrUserId;

    /** 修改人ID */
    private String modifiedUserId;

    /** 删除标识 */
    private String delflag;

    /** RES0 */
    private String res0;

    /** RES1 */
    private String res1;

    /** RES2(用作年龄的显示) */
    private String res2;

    /** 采集数据日期(建档日期) */
    private String res3;

    /** 数据来源 */
    private String res4;

    /** 条码号 */
    private String res5;

    /** RES6 */
    private String res6;

    /** RES7 */
    private String res7;

    /** RES8 */
    private String res8;

    /** RES9(图片路径) */
    private String res9;

    /** 是否签约 */
    private String sfqy;

    /** createtime */
    private Date createtime;

    /** updatetime */
    private Date updatetime;

    /** 年龄 */
    private String year;

    /** 登记人 */
    private String resistrant;

    /** 死亡地点 */
    private String swdd;

    /** 死亡原因 */
    private String ssyy;

    /** 申报人 */
    private String declarer;

    /** 申报人联系电话 */
    private String dephone;

    /** 备注 */
    private String beizhu;

    /** 生存状态 */
    private String scztye;

    /** csrq */
    private String csrq;

    /** 死亡日期 */
    private String swrq;

    /* This code was generated by TableGo tools, mark 1 end. */

    /* This code was generated by TableGo tools, mark 2 begin. */


}