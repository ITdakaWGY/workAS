package com.as.publichealth.controller;

import com.as.publichealth.api.TjycnumApi;
import com.as.publichealth.common.response.QueryResponseResult;
import com.as.publichealth.common.response.ResponseResult;
import com.as.publichealth.domain.tjycnum.Tjycnum;
import com.as.publichealth.domain.tjycnum.responce.TjycnumResult;
import com.as.publichealth.domain.user.responce.UserResult;
import com.as.publichealth.service.HealthPersonService;
import com.as.publichealth.service.TjycnumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/tjycnum")
public class TjycnumController implements TjycnumApi {
    @Autowired
    TjycnumService tjycnumService;

    @Override
    @GetMapping("/add/{orgno}/{tjrq}/{orgname}")
    public TjycnumResult add(@PathVariable("orgno") String orgno,@PathVariable("tjrq") String tjrq,@PathVariable("orgname") String orgname) {
        return tjycnumService.add(orgno,tjrq,orgname);
    }

    @Override
    @PostMapping("/add")
    public TjycnumResult add2(@RequestBody Tjycnum tjycnum) {
        return tjycnumService.add2(tjycnum);
    }

    @Override
    @PutMapping("/edit/{id}")//这里使用put方法，http 方法中put表示更新
    public TjycnumResult edit(@PathVariable("id") Long id,@RequestBody Tjycnum tjycnum) {
        return tjycnumService.update(id,tjycnum);
    }

    @Override
    @DeleteMapping("/del/{id}")
    public ResponseResult delete(@PathVariable("id") Long id) {
        return tjycnumService.delete(id);
    }

    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryResponseResult findList(@PathVariable("page") int page,@PathVariable("size") int size, Tjycnum tjycnum) {
        return tjycnumService.findList(page, size, tjycnum);
    }

    @Override
    @GetMapping("/excel")
    public String exportExcel() {
        return tjycnumService.exportExcel();
    }


}
