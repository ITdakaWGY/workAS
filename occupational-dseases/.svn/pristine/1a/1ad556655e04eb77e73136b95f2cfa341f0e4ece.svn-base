package com.as.occupationaldseases.controller;

import com.as.occupationaldseases.api.GrjbxxZysApi;
import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.domain.grjbxx.Grjbxx_zys;
import com.as.occupationaldseases.domain.grjbxx.responce.GrjbxxZysResult;
import com.as.occupationaldseases.service.GrjbxxZysSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("grjbxxZys")
public class GrjbxxZysController implements GrjbxxZysApi {
    @Autowired
    private GrjbxxZysSerivce grjbxxZysSerivce;

    @Override
    @PostMapping("/add")
    public GrjbxxZysResult add(@RequestBody Grjbxx_zys grjbxxZys) {
        return grjbxxZysSerivce.add(grjbxxZys);
    }

    @Override
    @PostMapping("/update")
    public GrjbxxZysResult update(Long id, Grjbxx_zys grjbxxZys) {
        return grjbxxZysSerivce.update(id,grjbxxZys);
    }

    @Override
    @PostMapping("/delete")
    public GrjbxxZysResult delete(Grjbxx_zys grjbxxZys) {
        return grjbxxZysSerivce.delete(grjbxxZys);
    }

    @Override
    @GetMapping("/select")
    public QueryResponseResult select(Grjbxx_zys grjbxxZys) {
        return grjbxxZysSerivce.select(grjbxxZys);
    }


}
