package com.as.publichealth.service;

import com.as.publichealth.dao.HealthLabtestMapper;
import com.as.publichealth.dao.HealthTjwzMapper;
import com.as.publichealth.domain.labtest.HealthLabtest;
import com.as.publichealth.domain.labtest.HealthLabtestVo;
import com.as.publichealth.domain.tjwz.HealthTjwzVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class HealthTjwzService {
    @Resource
    private HealthTjwzMapper healthTjwzMapper;

    public List<HealthTjwzVo> findList(String orgno, String stratTjrq, String endTjrq){
        Date startTjrj = java.sql.Date.valueOf(stratTjrq);
        Date endTjrj = java.sql.Date.valueOf(endTjrq);
        List<HealthTjwzVo> list = healthTjwzMapper.findList(orgno, startTjrj, endTjrj);
        return list;
    }

}
