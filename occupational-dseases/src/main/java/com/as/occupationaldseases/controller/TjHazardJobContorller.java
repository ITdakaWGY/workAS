package com.as.occupationaldseases.controller;

import com.as.occupationaldseases.api.TjHazardJobApi;
import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.domain.tjHazardJob.TjHazardJob;
import com.as.occupationaldseases.domain.tjHazardJob.responce.TjHazardJobResult;
import com.as.occupationaldseases.service.TjHazardJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/tjHazardJob")
public class TjHazardJobContorller implements TjHazardJobApi {

    @Autowired
    private TjHazardJobService tjHazardJobService;

    @Override
    @PostMapping("/add")
    public TjHazardJobResult add(@RequestBody TjHazardJob tjHazardJob) {
        return tjHazardJobService.add(tjHazardJob);
    }

    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryResponseResult findList(@PathVariable("page") int page, @PathVariable("size") int size, TjHazardJob tjHazardJob) {
        return tjHazardJobService.findList(page, size, tjHazardJob);
    }

    @Override
    @PostMapping("/del")
    public TjHazardJobResult delete(TjHazardJob tjHazardJob) {
        return tjHazardJobService.delete(tjHazardJob);
    }
}
