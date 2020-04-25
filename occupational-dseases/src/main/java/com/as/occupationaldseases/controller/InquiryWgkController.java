package com.as.occupationaldseases.controller;

import com.as.occupationaldseases.api.InquiryWgkApi;
import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.common.response.ResponseResult;
import com.as.occupationaldseases.domain.inquiry_wgk.InquiryWgk;
import com.as.occupationaldseases.domain.inquiry_wgk.responce.WgkResult;
import com.as.occupationaldseases.service.InquiryWgkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/InquiryWgk")
@CrossOrigin
public class InquiryWgkController implements InquiryWgkApi {
    @Autowired
    InquiryWgkService inquiryWgkService;


    @Override
    @PostMapping("/add")
    public WgkResult add(@RequestBody InquiryWgk inquiryWgk) {
        return inquiryWgkService.add(inquiryWgk);
    }

    @Override
    @PutMapping("/edit/{id}")//这里使用put方法，http 方法中put表示更新
    public WgkResult edit(@PathVariable("id")Integer id, @RequestBody InquiryWgk inquiryWgk) {
        return inquiryWgkService.update(id,inquiryWgk);
    }

    @Override
    @DeleteMapping("/del/{id}")
    public ResponseResult delete(@PathVariable("id") Integer id) {
        return inquiryWgkService.delete(id);
    }

    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryResponseResult findList(@PathVariable("page") int page, @PathVariable("size") int size, InquiryWgk inquiryWgk) {
        return inquiryWgkService.findList(page,size,inquiryWgk);
    }
}
