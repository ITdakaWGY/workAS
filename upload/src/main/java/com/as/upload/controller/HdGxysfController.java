package com.as.upload.controller;

import com.alibaba.fastjson.JSON;
import com.as.upload.api.HdGxysfApi;
import com.as.upload.domain.gxysf.HdGxysf;
import com.as.upload.domain.gxysf.responce.HdGxysfResult;

import com.as.upload.domain.hdyyqk.HdYyqk;
import com.as.upload.service.HdGxysfService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/HdGxysf")
public class HdGxysfController implements HdGxysfApi {
    @Autowired
    HdGxysfService hdGxysfService;

    @Override
    @PostMapping("/add")
    public HdGxysfResult add(HdGxysf hdGxysf, String hdYyqkList , MultipartFile JmImg, MultipartFile SfysImg) {
        List<HdYyqk> hdYyqkLists = JSON.parseArray(hdYyqkList, HdYyqk.class);
        return hdGxysfService.add(hdGxysf,hdYyqkLists,JmImg,SfysImg);
    }

    /*@Override
    @PutMapping("/edit/{id}")//这里使用put方法，http 方法中put表示更新
    public HdGxysfResult edit(@PathVariable("id") Long id,@RequestBody HdGxysf hdGxysf) {
        return hdGxysfService.update(id, hdGxysf);
    }

    @Override
    @DeleteMapping("/del/{id}")
    public ResponseResult delete(@PathVariable("id") Long id) {
        return hdGxysfService.delete(id);
    }

    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryResponseResult findList(@PathVariable("page") int page,@PathVariable("size") int size, HdGxysf hdGxysf) {
        return hdGxysfService.findList(page, size, hdGxysf);
    }*/
}
