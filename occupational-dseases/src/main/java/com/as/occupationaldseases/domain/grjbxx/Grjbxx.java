package com.as.occupationaldseases.domain.grjbxx;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class Grjbxx {
    @ApiModelProperty("个人基本信息id")
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("档案号")
    private String personalid;
    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("性别")
    private String sex;
    @ApiModelProperty("出生日期")
    private String csrq;
    @ApiModelProperty("身份证")
    private String sfz;
    @ApiModelProperty("工作单位")
    private String companyname;
    @ApiModelProperty("单位编号")
    private String companycode;
    @ApiModelProperty("本人电话")
    private String brdh;
    @ApiModelProperty("民族")
    private String mz;
    @ApiModelProperty("民族其他")
    private String mzqt;
    @ApiModelProperty("出生地")
    private String birthplace;
    @ApiModelProperty("家庭地址")
    private String familyaddress;
    @ApiModelProperty("工龄")
    private String gl;
    @ApiModelProperty("接害工龄")
    private String jhgl;
    @ApiModelProperty("邮政编码")
    private String yzbm;
    @ApiModelProperty("初潮")
    private String cc;
    @ApiModelProperty("经期")
    private String jq;
    @ApiModelProperty("周期")
    private String zq;
    @ApiModelProperty("停经年龄")
    private String tjnl;
    @ApiModelProperty("子女数量")
    private String znnumber;
    @ApiModelProperty("流产次数")
    private String lcnumber;
    @ApiModelProperty("早产次数")
    private String zcnumber;
    @ApiModelProperty("死产次数")
    private String scnumber;
    @ApiModelProperty("异常胎次")
    private String yctcnumber;
    @ApiModelProperty("先天畸形次数")
    private String xtjxnumber;
    @ApiModelProperty("婚姻状况")
    private String hyzk;
    @ApiModelProperty("婚姻状况其他")
    private String hyzkqt;
    @ApiModelProperty("接触的危害因素")
    private String whys;
    @ApiModelProperty("危害因素粉尘")
    private String whysfc;
    @ApiModelProperty("危害因素放射物质")
    private String whysfswz;
    @ApiModelProperty("危害因素物理因素")
    private String whyswl;
    @ApiModelProperty("危害因素化学物质")
    private String whyshxwz;
    @ApiModelProperty("危害因素其他-岗位")
    private String whysqt;
    @ApiModelProperty("吸烟状况")
    private String xyzk;
    @ApiModelProperty("日吸烟量")
    private String  rxyl;
    @ApiModelProperty("开始吸烟年龄")
    private String  ksxynl;
    @ApiModelProperty("戒烟年龄")
    private String  jynl;
    @ApiModelProperty("饮酒频率")
    private String  yjpl;
    @ApiModelProperty("日饮酒量")
    private String ryjl;
    @ApiModelProperty("是否戒酒")
    private String sfjj;
    @ApiModelProperty("戒酒年龄")
    private String jjnl;
    @ApiModelProperty("开始饮酒年龄")
    private String  ksyjnl;
    @ApiModelProperty("是否醉酒")
    private String  sfzj;
    @ApiModelProperty("饮酒种类")
    private String yjzl;
    @ApiModelProperty("饮酒种类其他")
    private String yjzlqt;
    @ApiModelProperty("遗传病史")
    private String ycbs;
    @ApiModelProperty("遗传病史名称")
    private String ycbsms;
    @ApiModelProperty("药物过敏史")
    private String ywgms;
    @ApiModelProperty("药物过敏史其他描述")
    private String  ywgmsqtms;
    @ApiModelProperty("体检日期")
    private String tjrq;
    @ApiModelProperty("体检机构名称")
    private String orgname;
    @ApiModelProperty("机构编号")
    private String  orgcode;
    @ApiModelProperty("创建时间")
    private Date createtime;
    @ApiModelProperty("体检标识")
    private String tjbs;
    @ApiModelProperty("工作岗位")
    private String gzgw;
    @ApiModelProperty("岗位编号")
    @TableField("gzgwCode")
    private  String gzgwCode;
    @ApiModelProperty("建议复查标识")
    private String jyfcbs;
    @ApiModelProperty("复查时间")
    private String fcsj;
    @ApiModelProperty("复查地点")
    private String fcdd;
    @ApiModelProperty("复查原因")
    private String fcyy;
    @ApiModelProperty("疑似职业病标识")
    private String yszybbs;
    @ApiModelProperty("疑似职业病文本")
    private String yszybwb;
    @ApiModelProperty("禁忌症标识")
    private String jjzbs;
    @ApiModelProperty("禁忌症文本")
    private String jjzwb;
    @ApiModelProperty("条码号")
    private String tmh;
    @ApiModelProperty("疑似职业病异常")
    private String yszybyc;
    @ApiModelProperty("禁忌症异常描述")
    private String jjzyc;
    @ApiModelProperty("总检处理意见")
    private String zjclyj;
    @ApiModelProperty("总检检查结论")
    private String zjjcjl;
    @ApiModelProperty("教育程度")
    private String jycd;
    @ApiModelProperty("五官科体检标识")
    private String tjflag;
    @ApiModelProperty("症状体检标识")
    private String tjflagzz;
    @ApiModelProperty("神经科体检标识")
    private String tjflagsjk;
    @ApiModelProperty("内科体检标识")
    private String tjflagnk;
    @ApiModelProperty("外科体检标识")
    private String tjflagwk;
    @ApiModelProperty("登记标记")
    private String djbs;
    @ApiModelProperty("体检类型")
    private String tjlx;
    @ApiModelProperty("主键结论中的多选框")
    private String jielun;
    private String res1;
    @ApiModelProperty("照片标志")
    private String res2;
    @ApiModelProperty("危险因素其他")
    private String jcwhysqt;
    private String res3;
    private String res4;
    private String res5;
    private String res6;
    private String res7;
    private String res8;
    private String res9;



}
