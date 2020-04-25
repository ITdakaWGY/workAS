package com.as.occupationaldseases.controller;

import com.as.occupationaldseases.api.PersonalProgressApi;
import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.domain.personal_progress.PersonalProgress;
import com.as.occupationaldseases.domain.personal_progress.responce.PersonalProgressResult;
import com.as.occupationaldseases.service.PersonalProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/progress")
public class PersonalProgressController implements PersonalProgressApi {
    @Autowired
    PersonalProgressService personalProgressService;

    @Override
    @PostMapping("/add")
    public PersonalProgressResult add(String flag,@RequestBody PersonalProgress progress) {
        return personalProgressService.add(flag,progress);
    }


    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryResponseResult findList(@PathVariable("page") int page,@PathVariable("size") int size, PersonalProgress progress) {
        return personalProgressService.findList(page,size,progress);
    }

    @Override
    @GetMapping("/tjxm/{flag}/{sfz}")
    public List<String> getTjxm(@PathVariable("flag") Integer flag, @PathVariable("sfz") String sfz) {
        return personalProgressService.getTjxm(flag,sfz);
    }
}
