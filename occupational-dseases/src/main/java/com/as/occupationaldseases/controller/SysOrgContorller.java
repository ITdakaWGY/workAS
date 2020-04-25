package com.as.occupationaldseases.controller;

import com.as.occupationaldseases.api.SysOrgApi;
import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.domain.sysOrg.SysOrg;
import com.as.occupationaldseases.domain.sysOrg.responce.SysOrgResult;
import com.as.occupationaldseases.service.SysOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/sysOrg")
public class SysOrgContorller implements SysOrgApi {

    @Autowired
    private SysOrgService sysOrgService;


    @Override
    @PostMapping("/add")
    public SysOrgResult add(@RequestBody SysOrg sysOrg) {
        return sysOrgService.add(sysOrg);
    }

    @Override
    @PutMapping("/update/{id}")//这里使用put方法，http 方法中put表示更新
    public SysOrgResult update(@PathVariable("id") int id, @RequestBody SysOrg sysOrg) {
        return sysOrgService.update(id,sysOrg);
    }

    @Override
    @DeleteMapping("/del/{id}")
    public SysOrgResult delete(@PathVariable("id") Long id) {
        return sysOrgService.delete(id);
    }

    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryResponseResult findList(@PathVariable("page") int page,@PathVariable("size") int size, SysOrg sysOrg) {
        return sysOrgService.findList(page,size,sysOrg);
    }

    @Override
    @GetMapping("/selectList")
    public QueryResponseResult selectList(SysOrg sysOrg) {
        return sysOrgService.selectList(sysOrg);
    }

    @Override
    @GetMapping("/findBySingle")
    public SysOrgResult findBySingle(SysOrg sysOrg) {
        return sysOrgService.findBySingle(sysOrg);
    }

}
