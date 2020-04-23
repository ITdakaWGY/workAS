package com.as.publichealth.controller;

import com.as.publichealth.api.HealthMainApi;
import com.as.publichealth.domain.main.HealthMain;
import com.as.publichealth.domain.main.HealthMainVo;
import com.as.publichealth.service.HealthMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/healthMain")
public class HealthMainController implements HealthMainApi {
    @Autowired
    HealthMainService healthMainService;

    @Override
    @GetMapping("/list/{orgno}/{stratTjrq}/{endTjrq}")
    public List<HealthMainVo> findList(String orgno, String stratTjrq, String endTjrq) {
        return healthMainService.findList(orgno,stratTjrq,endTjrq);
    }
}
