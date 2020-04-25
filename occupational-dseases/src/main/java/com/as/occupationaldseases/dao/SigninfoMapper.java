package com.as.occupationaldseases.dao;

import com.as.occupationaldseases.domain.signinfo.Signinfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SigninfoMapper extends BaseMapper<Signinfo> {
    int queryCount(Map<String, Object> params);


    List<Signinfo> queryStuBaseByXmlPage(Map<String, Object> params);
}
