package com.as.publichealth.service;

import com.as.publichealth.dao.HealthTjwzMapper;
import com.as.publichealth.dao.HealthZytzMapper;
import com.as.publichealth.domain.tjwz.HealthTjwzVo;
import com.as.publichealth.domain.zytz.HealthZytzVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class HealthZytzService {
    @Resource
    private HealthZytzMapper healthZytzMapper;

    public List<HealthZytzVo> findList(String orgno, String stratTjrq, String endTjrq){
        Date startTjrj = java.sql.Date.valueOf(stratTjrq);
        Date endTjrj = java.sql.Date.valueOf(endTjrq);
        List<HealthZytzVo> list = healthZytzMapper.findPhzjlList(orgno, startTjrj, endTjrj);
        return list;
    }

}
