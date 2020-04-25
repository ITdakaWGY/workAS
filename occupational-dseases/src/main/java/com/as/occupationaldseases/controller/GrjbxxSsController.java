package com.as.occupationaldseases.controller;

import com.as.occupationaldseases.api.GrjbxxSsApi;
import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.domain.grjbxx.Grjbxx_ss;
import com.as.occupationaldseases.domain.grjbxx.responce.GrjbxxSsResult;
import com.as.occupationaldseases.domain.user.User;
import com.as.occupationaldseases.service.GrjbxxSsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *输血
 */

@RestController
@CrossOrigin
@RequestMapping("/grjbxxSs")
public class GrjbxxSsController implements GrjbxxSsApi {
    @Autowired
    GrjbxxSsService grjbxxSsService;

    @Override
    @PostMapping("/add")
    public GrjbxxSsResult add(@RequestBody Grjbxx_ss grjbxx_ss) {
        return grjbxxSsService.add(grjbxx_ss);
    }

    @Override
    @PostMapping("/update")
    public GrjbxxSsResult update(@PathVariable("id")Long id, @RequestBody Grjbxx_ss grjbxx_ss) {
        return grjbxxSsService.update(id,grjbxx_ss);
    }


    @Override
    @PostMapping("/delete")
    public GrjbxxSsResult delete(Grjbxx_ss grjbxx_ss) {
        return grjbxxSsService.delete(grjbxx_ss);
    }

    @Override
    @PostMapping("/select")
    public QueryResponseResult select(Grjbxx_ss grjbxx_ss) {
        return grjbxxSsService.select(grjbxx_ss);
    }
}
