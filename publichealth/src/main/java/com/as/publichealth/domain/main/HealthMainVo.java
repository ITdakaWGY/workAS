package com.as.publichealth.domain.main;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * HEALTH_MAIN
 * 
 * @author wgy
 * @version 1.0.0 2020-04-08
 */
@Data
@ToString
@TableName("health_main")
public class HealthMainVo {

    /** 体检编号 */
    private String tjbh;

    /** tmh */
    private String tmh;

    /** 体检日期 */
    private Date tjrq;

    /** 机构编号 */
    private Integer orgno;

    /** 机构名称 */
    private String orgname;

    /** 姓名 */
    private String xm;

    /** 性别 */
    private String sex;

    /** 年龄 */
    private Integer age;

}