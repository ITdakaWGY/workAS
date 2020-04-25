package com.as.occupationaldseases.domain.grjbxx_ys;

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
public class Grjbxx_ys {
    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("档案编号")
    private String personalid;

    @ApiModelProperty("条码号")
    private String tmh;

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

    @ApiModelProperty("疑似职业病标识")
    private String yszybbs;

    @ApiModelProperty("疑似职业病描述")
    private String yszybwb;

    @ApiModelProperty("疑似职业病异常")
    private String yszybyc;

    @ApiModelProperty("体检机构名称")
    private String orgname;

    @ApiModelProperty("体检机构编号")
    private String orgcode;

    @ApiModelProperty("创建时间")
    private Date createtime;
    @ApiModelProperty("身份证")
    private String sfz;
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

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    public String getYszybyc() {
        return yszybyc;
    }

    public void setYszybyc(String yszybyc) {
        this.yszybyc = yszybyc;
    }

    public String getOrgcode() {
        return orgcode;
    }

    public void setOrgcode(String orgcode) {
        this.orgcode = orgcode;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
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

    public String getSfz() {
        return sfz;
    }

    public void setSfz(String sfz) {
        this.sfz = sfz;
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
}
