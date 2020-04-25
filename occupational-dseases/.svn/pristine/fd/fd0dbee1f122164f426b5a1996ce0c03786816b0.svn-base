package com.as.occupationaldseases.controller;

import com.as.occupationaldseases.api.GrjbxxJbsApi;
import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.domain.grjbxx.Grjbxx_jbs;
import com.as.occupationaldseases.domain.grjbxx.responce.GrjbxxJbsResult;
import com.as.occupationaldseases.service.GrjbxxJbsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/grjbxxJbs")
public class GrjbxxJbsController implements GrjbxxJbsApi {
  @Autowired
  GrjbxxJbsService grjbxxJbsService;

    @Override
    @PostMapping("/add")
    public GrjbxxJbsResult add(@RequestBody Grjbxx_jbs grjbxxJbs) {
        return grjbxxJbsService.add(grjbxxJbs);
    }

    @Override
    @PostMapping("/update")
    public GrjbxxJbsResult update(Long id, Grjbxx_jbs grjbxxJbs) {
        return grjbxxJbsService.update(id,grjbxxJbs);
    }

    @Override
    @PostMapping("/delete")
    public GrjbxxJbsResult delete(Grjbxx_jbs grjbxxJbs) {
        return grjbxxJbsService.delete(grjbxxJbs);
    }

    @Override
    @GetMapping("/select")
    public QueryResponseResult select(Grjbxx_jbs grjbxxJbs) {
        return grjbxxJbsService.select(grjbxxJbs);
    }
}
