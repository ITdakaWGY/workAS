package com.as.occupationaldseases.controller;

import com.as.occupationaldseases.api.SigninfoApi;
import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.domain.signinfo.Signinfo;
import com.as.occupationaldseases.domain.signinfo.responce.SigninfoResult;
import com.as.occupationaldseases.service.SigninfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.Map;


@RestController
@CrossOrigin
@RequestMapping("/signinfo")
public class SigninfoController implements SigninfoApi {
    @Autowired
    private SigninfoService signinfoService;

    @Value("${company}")
    private String path;
    @Override
    @PostMapping("/add")
    public SigninfoResult add(@RequestBody Signinfo signinfo) {
        return signinfoService.add(signinfo);
    }

    @Override
    @PutMapping("/update/{id}")
    public SigninfoResult update(@PathVariable("id") Long id, @RequestBody Signinfo signinfo) {
        return signinfoService.update(id,signinfo);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public SigninfoResult delete(@PathVariable("id")Long id) {
        return signinfoService.delete(id);
    }

    @Override
    @PostMapping("/select")
    public QueryResponseResult select(@RequestBody Signinfo signinfo) {
        return signinfoService.select(signinfo);
    }

    @Override
    @PostMapping("/fileUpload")
    public String fileUpload(@RequestParam("files") MultipartFile files) {
        return signinfoService.fileUpload(files,path);
    }
    @Override
    @RequestMapping(value = "/list/{page}/{size}", method = {RequestMethod.POST,RequestMethod.GET})
    public QueryResponseResult findList(@PathVariable("page") int page, @PathVariable("size")int size,@RequestParam Map<String,Object> params) {
        return signinfoService.findList(page,size,params);
    }
}
