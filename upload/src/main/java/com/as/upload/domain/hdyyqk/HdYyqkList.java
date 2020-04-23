package com.as.upload.domain.hdyyqk;/*

/**
 * 用药情况表(HD_YYQK)
 * 
 * @author wsg
 * @version 1.0.0 2020-03-02
 */

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class HdYyqkList {
    private List<HdYyqk> hdYyqkList;
}