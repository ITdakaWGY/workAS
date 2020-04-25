package com.as.occupationaldseases.controller;

import com.as.occupationaldseases.api.GrjbxxFcApi;
import com.as.occupationaldseases.api.GrjbxxJjApi;
import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.common.response.ResponseResult;
import com.as.occupationaldseases.domain.grjbxx_fc.Grjbxx_fc;
import com.as.occupationaldseases.domain.grjbxx_fc.responce.FcResult;
import com.as.occupationaldseases.domain.grjbxx_jj.Grjbxx_jj;
import com.as.occupationaldseases.domain.grjbxx_jj.responce.JjResult;
import com.as.occupationaldseases.domain.symptominfo.Symptominfo;
import com.as.occupationaldseases.service.GrjbxxFcService;
import com.as.occupationaldseases.service.GrjbxxJjService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
@RequestMapping("/grjbxxjj")
public class GrjbxxJjController implements GrjbxxJjApi {
    @Autowired
    GrjbxxJjService grjbxxJjService;

    @Override
    @PostMapping("/add")
    public JjResult add(@RequestBody Grjbxx_jj grjbxxJj) {
        return grjbxxJjService.add(grjbxxJj);
    }

    @Override
    @PutMapping("/edit/{id}")
    public JjResult edit(@PathVariable("id")Integer id, @RequestBody Grjbxx_jj grjbxxJj) {
        return grjbxxJjService.update(id, grjbxxJj);
    }

    @Override
    @DeleteMapping("/del/{id}")
    public ResponseResult delete(@PathVariable("id") Integer id) {
        return grjbxxJjService.delete(id);
    }

    @Override
    @PostMapping("/selectone")
    public Grjbxx_jj selectone(Grjbxx_jj grjbxxJj) {
        return grjbxxJjService.selectone(grjbxxJj);
    }

    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryResponseResult findList(@PathVariable("page") int page, @PathVariable("size") int size, Grjbxx_jj grjbxxJj) {
        return grjbxxJjService.findList(page,size,grjbxxJj);
    }

}
