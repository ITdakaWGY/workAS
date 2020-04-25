package com.as.occupationaldseases.domain.grjbxx_fc;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@TableName("grjbxx")
public class Grjbxx_fc {
    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("档案编号")
    private String personalid;

    @ApiModelProperty("条码号")
    private String tmh;

    @ApiModelProperty("身份证")
    private String sfz;

    @ApiModelProperty("体检日期")
    private String tjrq;

    @ApiModelProperty("体检年份")
    private String tjnf;

    @ApiModelProperty("用人单位名称")
    private String companyname;

    @ApiModelProperty("用人单位编号")
    private String companycode;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("复查原因")
    private String fcyy;

    @ApiModelProperty("复查时间")
    private String fcsj;

    @ApiModelProperty("复查地点")
    private String fcdd;

    @ApiModelProperty("建议复查标识")
    private String jyfcbs;

    @ApiModelProperty("创建时间")
    private Date createtime;

    @ApiModelProperty("机构名称")
    private String orgname;

    @ApiModelProperty("机构编号")
    private String orgcode;

    //额外增加的信息涉及总检
    @ApiModelProperty("总检处理意见")
    private String  zjclyj;

    @ApiModelProperty("总检检查结论")
    private String  zjjcjl;

    @ApiModelProperty("职业禁忌标识标识")
    private String jjzbs;

    @ApiModelProperty("职业禁忌描述")
    private String jjzwb;

    @ApiModelProperty("职业禁忌异常描述")
    private String jjzyc;

    @ApiModelProperty("本人电话")
    private String brdh;


    @ApiModelProperty("疑似职业病标识")
    private String yszybbs;

    @ApiModelProperty("疑似职业病描述")
    private String yszybwb;

    @ApiModelProperty("疑似职业病异常")
    private String yszybyc;

    @ApiModelProperty("体检标识")
    private String tjflag;

    @ApiModelProperty("总检检查标识")
    private String tjflagzj;

    @ApiModelProperty("主键结论中的多选框")
    private String jielun;

    @TableField(exist=false)
    @ApiModelProperty("查询开始时间")
    private String tjrqstart;

    @TableField(exist=false)
    @ApiModelProperty("查询结束时间")
    private String tjrqend;







    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPersonalid() {
        return personalid;
    }

    public void setPersonalid(String personalid) {
        this.personalid = personalid;
    }

    public String getTmh() {
        return tmh;
    }

    public void setTmh(String tmh) {
        this.tmh = tmh;
    }

    public String getTjrq() {
        return tjrq;
    }

    public void setTjrq(String tjrq) {
        this.tjrq = tjrq;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFcyy() {
        return fcyy;
    }

    public void setFcyy(String fcyy) {
        this.fcyy = fcyy;
    }

    public String getFcsj() {
        return fcsj;
    }

    public void setFcsj(String fcsj) {
        this.fcsj = fcsj;
    }

    public String getFcdd() {
        return fcdd;
    }

    public void setFcdd(String fcdd) {
        this.fcdd = fcdd;
    }


    public String getJyfcbs() {
        return jyfcbs;
    }

    public void setJyfcbs(String jyfcbs) {
        this.jyfcbs = jyfcbs;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getSfz() {
        return sfz;
    }

    public void setSfz(String sfz) {
        this.sfz = sfz;
    }




    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    public String getOrgcode() {
        return orgcode;
    }

    public void setOrgcode(String orgcode) {
        this.orgcode = orgcode;
    }

    public String getZjclyj() {
        return zjclyj;
    }

    public void setZjclyj(String zjclyj) {
        this.zjclyj = zjclyj;
    }

    public String getZjjcjl() {
        return zjjcjl;
    }

    public void setZjjcjl(String zjjcjl) {
        this.zjjcjl = zjjcjl;
    }

    public String getJjzbs() {
        return jjzbs;
    }

    public void setJjzbs(String jjzbs) {
        this.jjzbs = jjzbs;
    }

    public String getJjzwb() {
        return jjzwb;
    }

    public void setJjzwb(String jjzwb) {
        this.jjzwb = jjzwb;
    }

    public String getJjzyc() {
        return jjzyc;
    }

    public void setJjzyc(String jjzyc) {
        this.jjzyc = jjzyc;
    }

    public String getYszybbs() {
        return yszybbs;
    }

    public void setYszybbs(String yszybbs) {
        this.yszybbs = yszybbs;
    }

    public String getYszybwb() {
        return yszybwb;
    }

    public void setYszybwb(String yszybwb) {
        this.yszybwb = yszybwb;
    }

    public String getYszybyc() {
        return yszybyc;
    }

    public void setYszybyc(String yszybyc) {
        this.yszybyc = yszybyc;
    }

    public String getTjflag() {
        return tjflag;
    }

    public void setTjflag(String tjflag) {
        this.tjflag = tjflag;
    }

    public String getBrdh() {
        return brdh;
    }

    public void setBrdh(String brdh) {
        this.brdh = brdh;
    }

    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode;
    }

    public String getTjnf() {
        return tjnf;
    }

    public void setTjnf(String tjnf) {
        this.tjnf = tjnf;
    }

    public String getTjflagzj() {
        return tjflagzj;
    }

    public void setTjflagzj(String tjflagzj) {
        this.tjflagzj = tjflagzj;
    }

    public String getTjrqstart() {
        return tjrqstart;
    }

    public void setTjrqstart(String tjrqstart) {
        this.tjrqstart = tjrqstart;
    }

    public String getTjrqend() {
        return tjrqend;
    }

    public void setTjrqend(String tjrqend) {
        this.tjrqend = tjrqend;
    }

    public String getJielun() {
        return jielun;
    }

    public void setJielun(String jielun) {
        this.jielun = jielun;
    }
}
