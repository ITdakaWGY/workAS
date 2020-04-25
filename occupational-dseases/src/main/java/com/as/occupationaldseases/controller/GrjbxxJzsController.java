package com.as.occupationaldseases.controller;

import com.as.occupationaldseases.api.GrjbxxJzsApi;
import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.domain.grjbxx.Grjbxx_jzs;
import com.as.occupationaldseases.domain.grjbxx.responce.GrjbxxJzsResult;
import com.as.occupationaldseases.service.GrjbxxJzsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/grjbxxJzs")
public class GrjbxxJzsController implements GrjbxxJzsApi {
     @Autowired
     GrjbxxJzsService grjbxxJzsService;

    @Override
    @PostMapping("/add")
    public GrjbxxJzsResult add(@RequestBody Grjbxx_jzs grjbxxJzs) {
        return grjbxxJzsService.add(grjbxxJzs);
    }

    @Override
    @PostMapping("/update")
    public GrjbxxJzsResult update(Long id, Grjbxx_jzs grjbxxJzs) {
        return grjbxxJzsService.update(id,grjbxxJzs);
    }

    @Override
    @PostMapping("/dalete")
    public GrjbxxJzsResult delete(Grjbxx_jzs grjbxxJzs) {
        return grjbxxJzsService.delete(grjbxxJzs);
    }

    @Override
    @GetMapping("/select")
    public QueryResponseResult select(Grjbxx_jzs grjbxxJzs) {
        return grjbxxJzsService.select(grjbxxJzs);
    }
}
