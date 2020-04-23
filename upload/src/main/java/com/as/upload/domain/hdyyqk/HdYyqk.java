package com.as.upload.domain.hdyyqk;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * Autor: Administrator
 * CreatedTime: 2020/2/27 0027
 * UpdateTime:2020/2/27 0027 10:28
 * Des:老随访/高血压用药情况
 * UpdateContent:
 **/
@TableName("HD_YYQK")
@KeySequence(value="S_HD_YYQK",clazz=Long.class)
public class HdYyqk  {
    /**
     * ID
     */
    @TableId(type= IdType.INPUT)  //注意主键类型要指定为Input
    private Long id;

    /**
     * 随访id(SFBH)
     */
    private String sfbh;

    /**
     * 主表名称(ZBMC)
     */
    private String zbmc;

    /**
     * 药物名称(YWMC)
     */
    private String ywmc;

    /**
     * 用法(YF)
     */
    private String yf;

    /**
     * 用量(YL)
     */
    private String yl;

    /**
     * 用药单位(YYDW)
     */
    private String yydw;

    /**
     * 药物类别(1是当前用药，2是调整用药)(YWLB)
     */
    private String ywlb;

    /**
     * 药物来源(YWLY)
     */
    private String ywly;

    /**
     * 用药方式(YYFS)
     */
    private String yyfs;

    /**
     * 用药方式其他(YYFSQT)
     */
    private String yyfsqt;

    /**
     * 用药类别(YPLB)
     */
    private String yplb;




    /*getter setter*/


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSfbh() {
        return sfbh;
    }


    public void setSfbh(String sfbh) {
        this.sfbh = sfbh;
    }

    public String getZbmc() {
        return zbmc;
    }

    public void setZbmc(String zbmc) {
        this.zbmc = zbmc;
    }

    public String getYwmc() {
        return ywmc;
    }

    public void setYwmc(String ywmc) {
        this.ywmc = ywmc;
    }

    public String getYf() {
        return yf;
    }

    public void setYf(String yf) {
        this.yf = yf;
    }

    public String getYl() {
        return yl;
    }

    public void setYl(String yl) {
        this.yl = yl;
    }

    public String getYydw() {
        return yydw;
    }

    public void setYydw(String yydw) {
        this.yydw = yydw;
    }

    public String getYwlb() {
        return ywlb;
    }

    public void setYwlb(String ywlb) {
        this.ywlb = ywlb;
    }

    public String getYwly() {
        return ywly;
    }

    public void setYwly(String ywly) {
        this.ywly = ywly;
    }

    public String getYyfs() {
        return yyfs;
    }

    public void setYyfs(String yyfs) {
        this.yyfs = yyfs;
    }

    public String getYyfsqt() {
        return yyfsqt;
    }

    public void setYyfsqt(String yyfsqt) {
        this.yyfsqt = yyfsqt;
    }

    public String getYplb() {
        return yplb;
    }

    public void setYplb(String yplb) {
        this.yplb = yplb;
    }

    @Override
    public String toString() {
        return "HdYyqk{" +
                "id=" + id +
                ", sfbh='" + sfbh + '\'' +
                ", zbmc='" + zbmc + '\'' +
                ", ywmc='" + ywmc + '\'' +
                ", yf='" + yf + '\'' +
                ", yl='" + yl + '\'' +
                ", yydw='" + yydw + '\'' +
                ", ywlb='" + ywlb + '\'' +
                ", ywly='" + ywly + '\'' +
                ", yyfs='" + yyfs + '\'' +
                ", yyfsqt='" + yyfsqt + '\'' +
                ", yplb='" + yplb + '\'' +
                '}';
    }


}