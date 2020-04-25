package com.as.occupationaldseases.controller;

import com.as.occupationaldseases.api.HealthLabtestApi;
import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.domain.healthLabtest.HealthLabtest;
import com.as.occupationaldseases.service.HealthLabtestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/healthLabtest")
public class HealthLabtestContorller implements HealthLabtestApi {

    @Autowired
    private HealthLabtestService healthLabtestService;

    @Override
    @GetMapping("/selectList")
    public QueryResponseResult selectList(HealthLabtest healthLabtest) {
        return healthLabtestService.selectList(healthLabtest);
    }
}
