package com.as.occupationaldseases.controller;

import com.as.occupationaldseases.api.GrjbxxWsApi;
import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.domain.grjbxx.Grjbxx_ws;
import com.as.occupationaldseases.domain.grjbxx.responce.GrjbxxWsResult;
import com.as.occupationaldseases.service.GrjbxxWsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 外伤
 */

@RestController
@CrossOrigin
@RequestMapping("/grjbxxWs")
public class GrjbxxWsController implements GrjbxxWsApi {
    @Autowired
    GrjbxxWsService grjbxxWsService;

    @Override
    @PostMapping("/add")
    public GrjbxxWsResult add(@RequestBody Grjbxx_ws grjbxx_ws) {
        return grjbxxWsService.add(grjbxx_ws);
    }

    @Override
    @PostMapping("/update")
    public GrjbxxWsResult update(@PathVariable("id")Long id, @RequestBody Grjbxx_ws grjbxx_ws) {
        return grjbxxWsService.update(id,grjbxx_ws);
    }

    @Override
    @PostMapping("/delete")
    public GrjbxxWsResult delete(Grjbxx_ws grjbxx_ws) {
        return grjbxxWsService.delete(grjbxx_ws);
    }

    @Override
    @PostMapping("/select")
    public QueryResponseResult select(Grjbxx_ws grjbxx_ws) {
        return grjbxxWsService.select(grjbxx_ws);
    }
}
