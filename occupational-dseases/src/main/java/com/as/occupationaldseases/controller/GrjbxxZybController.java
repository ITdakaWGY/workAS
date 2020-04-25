package com.as.occupationaldseases.controller;

import com.as.occupationaldseases.api.GrjbxxZybApi;
import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.domain.grjbxx.Grjbxx_zyb;
import com.as.occupationaldseases.service.GrjbxxZybService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/grjbxxZyb")
public class GrjbxxZybController implements GrjbxxZybApi {
    @Autowired
    GrjbxxZybService grjbxxZybService;
    @Override
    @RequestMapping(value = "/select", method = {RequestMethod.POST, RequestMethod.GET})
    public QueryResponseResult select(Grjbxx_zyb grjbxxZyb) {
        return grjbxxZybService.select(grjbxxZyb);
    }
}
