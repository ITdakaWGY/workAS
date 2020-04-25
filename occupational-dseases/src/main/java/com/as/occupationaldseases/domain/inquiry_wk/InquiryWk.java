package com.as.occupationaldseases.domain.inquiry_wk;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@TableName("inquiry_wk")
public class InquiryWk {
    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty("用户id")
    private String personalid;
    @ApiModelProperty("甲状腺")
    private String jzx;
    @ApiModelProperty("甲状腺描述")
    private String jzxms;
    @ApiModelProperty("甲状腺备注")
    private String jxzbz;
    @ApiModelProperty("浅表淋巴结")
    private String qblbj;
    @ApiModelProperty("浅表淋巴结描述")
    private String qblbjms;
    @ApiModelProperty("浅表淋巴结备注")
    private String qblbjbz;
    @ApiModelProperty("其他")
    private String qt;
    @ApiModelProperty("医师签字")
    private String ysqz;
    @ApiModelProperty(" ")
    private String ysqzurl;
    private String xm;
    private String tmh;
    private String res1;
    private String res2;
    private String res3;
    private String res4;
    private String res5;

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

    public String getJzx() {
        return jzx;
    }

    public void setJzx(String jzx) {
        this.jzx = jzx;
    }

    public String getJzxms() {
        return jzxms;
    }

    public void setJzxms(String jzxms) {
        this.jzxms = jzxms;
    }

    public String getJxzbz() {
        return jxzbz;
    }

    public void setJxzbz(String jxzbz) {
        this.jxzbz = jxzbz;
    }

    public String getQblbj() {
        return qblbj;
    }

    public void setQblbj(String qblbj) {
        this.qblbj = qblbj;
    }

    public String getQblbjms() {
        return qblbjms;
    }

    public void setQblbjms(String qblbjms) {
        this.qblbjms = qblbjms;
    }

    public String getQblbjbz() {
        return qblbjbz;
    }

    public void setQblbjbz(String qblbjbz) {
        this.qblbjbz = qblbjbz;
    }

    public String getQt() {
        return qt;
    }

    public void setQt(String qt) {
        this.qt = qt;
    }

    public String getYsqz() {
        return ysqz;
    }

    public void setYsqz(String ysqz) {
        this.ysqz = ysqz;
    }

    public String getYsqzurl() {
        return ysqzurl;
    }

    public void setYsqzurl(String ysqzurl) {
        this.ysqzurl = ysqzurl;
    }

    public String getRes1() {
        return res1;
    }

    public void setRes1(String res1) {
        this.res1 = res1;
    }

    public String getRes2() {
        return res2;
    }

    public void setRes2(String res2) {
        this.res2 = res2;
    }

    public String getRes3() {
        return res3;
    }

    public void setRes3(String res3) {
        this.res3 = res3;
    }

    public String getRes4() {
        return res4;
    }

    public void setRes4(String res4) {
        this.res4 = res4;
    }

    public String getRes5() {
        return res5;
    }

    public void setRes5(String res5) {
        this.res5 = res5;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getTmh() {
        return tmh;
    }

    public void setTmh(String tmh) {
        this.tmh = tmh;
    }
}

