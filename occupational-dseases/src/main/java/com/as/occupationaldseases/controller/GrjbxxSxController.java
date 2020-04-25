package com.as.occupationaldseases.controller;

import com.as.occupationaldseases.api.GrjbxxSxApi;
import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.domain.grjbxx.Grjbxx_sx;
import com.as.occupationaldseases.domain.grjbxx.responce.GrjbxxSxResult;
import com.as.occupationaldseases.service.GrjbxxSxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 输血
 */
@RestController
@CrossOrigin
@RequestMapping("grjbxxSx")
public class GrjbxxSxController implements GrjbxxSxApi {
    @Autowired
    GrjbxxSxService grjbxxSxService;


    @Override
    @PostMapping("/add")
    public GrjbxxSxResult add(@RequestBody Grjbxx_sx grjbxx_sx) {
        return grjbxxSxService.add(grjbxx_sx);
    }

    @Override
    @PostMapping("/update")
    public GrjbxxSxResult update(Long id, Grjbxx_sx grjbxx_sx) {
        return grjbxxSxService.update(id,grjbxx_sx);
    }

    @Override
    @PostMapping("/delete")
    public GrjbxxSxResult delete(Grjbxx_sx grjbxx_sx) {
        return grjbxxSxService.delete(grjbxx_sx);
    }

    @Override
    @PostMapping("/select")
    public QueryResponseResult select(Grjbxx_sx grjbxx_sx) {
        return grjbxxSxService.select(grjbxx_sx);
    }
}
