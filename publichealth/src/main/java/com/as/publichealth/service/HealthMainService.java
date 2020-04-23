package com.as.publichealth.service;

import com.as.publichealth.dao.HealthMainMapper;
import com.as.publichealth.domain.main.HealthMain;
import com.as.publichealth.domain.main.HealthMainVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class HealthMainService {
    @Resource
    private HealthMainMapper healthMainMapper;

    public List<HealthMainVo> findList(String orgno, String stratTjrq, String endTjrq){
        Date startTjrj = java.sql.Date.valueOf(stratTjrq);
        Date endTjrj = java.sql.Date.valueOf(endTjrq);
        List<HealthMainVo> list = healthMainMapper.findList(orgno, startTjrj, endTjrj);
        return list;
    }

}
