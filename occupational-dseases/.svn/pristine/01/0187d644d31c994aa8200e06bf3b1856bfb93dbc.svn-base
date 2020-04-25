package com.as.occupationaldseases.controller;

import com.as.occupationaldseases.api.JobinfoApi;
import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.domain.companyinfo.Companyinfo;
import com.as.occupationaldseases.domain.companyinfo.responce.CompanyinfoResult;
import com.as.occupationaldseases.domain.jobinfo.Jobinfo;
import com.as.occupationaldseases.domain.jobinfo.responce.JobinfoResult;
import com.as.occupationaldseases.service.CompanyinfoService;
import com.as.occupationaldseases.service.JobinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/Jobinfo")
public class JobinfoContorller implements JobinfoApi {

    @Autowired
    private JobinfoService jobinfoService;

    @Override
    @PostMapping("/add")
    public JobinfoResult add(@RequestBody Jobinfo jobinfo) {
        return jobinfoService.add(jobinfo);
    }

    @Override
    @PutMapping("/update/{id}")//这里使用put方法，http 方法中put表示更新
    public JobinfoResult update(@PathVariable("id") int id, @RequestBody Jobinfo jobinfo) {
        return jobinfoService.update(id, jobinfo);
    }

    @Override
    @DeleteMapping("/del/{id}")
    public JobinfoResult delete(@PathVariable("id") int id) {
        return jobinfoService.delete(id);
    }

    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryResponseResult findList(@PathVariable("page") int page, @PathVariable("size") int size,Jobinfo jobinfo) {
        return jobinfoService.findList(page, size, jobinfo);
    }

    @Override
    @GetMapping("/findBySingle")
    public JobinfoResult findBySingle(Jobinfo jobinfo) {
        return jobinfoService.findBySingle(jobinfo);
    }

    @Override
    @GetMapping("/count")
    public Integer count(Jobinfo jobinfo) {
        return jobinfoService.count(jobinfo);
    }
}
