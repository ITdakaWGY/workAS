package com.as.occupationaldseases.controller;

import com.as.occupationaldseases.api.SymptominfoApi;
import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.common.response.ResponseResult;
import com.as.occupationaldseases.domain.symptominfo.Symptominfo;
import com.as.occupationaldseases.domain.symptominfo.responce.SymptomResult;
import com.as.occupationaldseases.service.SymptominfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/symptominfo")
public class SymptominfoController implements SymptominfoApi {
    @Autowired
    SymptominfoService symptominfoService;

    @Override
    @PostMapping("/add")
    public SymptomResult add(@RequestBody Symptominfo symptominfo) {
        return symptominfoService.add(symptominfo);
    }

    @Override
    @PutMapping("/edit/{id}")//这里使用put方法，http 方法中put表示更新
    public SymptomResult edit(@PathVariable("id")Integer id, @RequestBody Symptominfo symptominfo) {
        return symptominfoService.update(id, symptominfo);
    }

    @Override
    @DeleteMapping("/del/{id}")
    public ResponseResult delete(@PathVariable("id") Integer id) {
        return symptominfoService.delete(id);
    }

    @Override
    @RequestMapping(value = "/sel", method = {RequestMethod.POST, RequestMethod.GET})
    public List<Symptominfo> select(@RequestBody Symptominfo symptominfo) {
        return symptominfoService.select(symptominfo);
    }

    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryResponseResult findList(@PathVariable("page") int page, @PathVariable("size") int size, Symptominfo symptominfo) {
        return symptominfoService.findList(page,size,symptominfo);
    }
}
