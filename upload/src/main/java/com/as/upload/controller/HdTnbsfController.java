package com.as.upload.controller;

import com.alibaba.fastjson.JSON;
import com.as.upload.api.HdTnbsfApi;


import com.as.upload.domain.hdyyqk.HdYyqk;
import com.as.upload.domain.hdyyqk.HdYyqkList;
import com.as.upload.domain.tnbsf.HdTnbsf;
import com.as.upload.domain.tnbsf.responce.HdTnbsfResult;
import com.as.upload.service.HdTnbsfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.spring.web.json.Json;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/HdTnbsf")
public class HdTnbsfController implements HdTnbsfApi {

    @Autowired
    HdTnbsfService hdTnbsfService;

    @Override
    @PostMapping("/add")
    public HdTnbsfResult add( HdTnbsf hdTnbsf, String hdYyqkList,  MultipartFile JmImg,  MultipartFile SfysImg) {
        List<HdYyqk> hdYyqkLists = JSON.parseArray(hdYyqkList, HdYyqk.class);
        return hdTnbsfService.add(hdTnbsf,hdYyqkLists,JmImg,SfysImg);
    }

   /* @Override
    @PutMapping("/edit/{id}")//这里使用put方法，http 方法中put表示更新
    public HdTnbsfResult edit(@PathVariable("id") Long id, @RequestBody HdTnbsf hdTnbsf) {
        return hdTnbsfService.update(id, hdTnbsf);
    }

    @Override
    @DeleteMapping("/del/{id}")
    public ResponseResult delete(@PathVariable("id") Long id) {
        return hdTnbsfService.delete(id);
    }

    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryResponseResult findList(@PathVariable("page") int page,@PathVariable("size") int size, HdTnbsf hdTnbsf) {
        return hdTnbsfService.findList(page, size, hdTnbsf);
    }*/
}
