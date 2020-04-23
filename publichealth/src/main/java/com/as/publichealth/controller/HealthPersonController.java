package com.as.publichealth.controller;

import com.as.publichealth.api.HealthPersonApi;
import com.as.publichealth.common.response.QueryResponseResult;
import com.as.publichealth.common.response.ResponseResult;
import com.as.publichealth.domain.person.HealthPerson;
import com.as.publichealth.domain.person.responce.HealthPersonResult;
import com.as.publichealth.service.HealthPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/healthPerson")
public class HealthPersonController implements HealthPersonApi {
    @Autowired
    HealthPersonService healthPersonService;

    @Override
    @GetMapping("/add/{orgno}/{stratTjrq}/{endTjrq}")
    public ResponseResult add(@PathVariable("orgno") String orgno,@PathVariable("stratTjrq") String stratTjrq,@PathVariable("endTjrq") String endTjrq) {
        return healthPersonService.add(orgno,stratTjrq,endTjrq);
    }

    @Override
    @PutMapping("/edit/{id}")//这里使用put方法，http 方法中put表示更新
    public HealthPersonResult edit(@PathVariable("id") Long id,@RequestBody  HealthPerson healthPerson) {
        return healthPersonService.update(id,healthPerson);
    }

    @Override
    @DeleteMapping("/del/{id}")
    public ResponseResult delete(@PathVariable("id") Long id) {
        return healthPersonService.delete(id);
    }

    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryResponseResult findList(@PathVariable("page") int page,@PathVariable("size") int size, HealthPerson healthPerson) {
        return healthPersonService.findList(page,size,healthPerson);
    }
}
