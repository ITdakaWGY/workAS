package com.as.occupationaldseases.controller;

import com.as.occupationaldseases.api.GrjbxxYsApi;
import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.common.response.ResponseResult;
import com.as.occupationaldseases.domain.grjbxx_ys.Grjbxx_ys;
import com.as.occupationaldseases.domain.grjbxx_ys.responce.YsResult;
import com.as.occupationaldseases.service.GrjbxxYsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
@RequestMapping("/grjbxxys")
public class GrjbxxYsController implements GrjbxxYsApi {
    @Autowired
    GrjbxxYsService grjbxxyYservice;

    @Override
    @PostMapping("/add")
    public YsResult add(@RequestBody Grjbxx_ys grjbxxYs) {
        return grjbxxyYservice.add(grjbxxYs);
    }

    @Override
    @PutMapping("/edit/{id}")
    public YsResult edit(@PathVariable("id")Integer id, @RequestBody Grjbxx_ys grjbxxYs) {
        return grjbxxyYservice.update(id, grjbxxYs);
    }

    @Override
    @DeleteMapping("/del/{id}")
    public ResponseResult delete(@PathVariable("id") Integer id) {
        return grjbxxyYservice.delete(id);
    }

    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryResponseResult findList(@PathVariable("page") int page, @PathVariable("size") int size, Grjbxx_ys grjbxxYs) {
        return grjbxxyYservice.findList(page,size,grjbxxYs);
    }
}
