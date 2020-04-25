package com.as.occupationaldseases.controller;

import com.as.occupationaldseases.api.InquiryNkApi;
import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.common.response.ResponseResult;
import com.as.occupationaldseases.domain.inquiry_nk.InquiryNk;
import com.as.occupationaldseases.domain.inquiry_nk.responce.NkResult;
import com.as.occupationaldseases.service.InquiryNkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/InquiryNk")
public class InquiryNkController implements InquiryNkApi {
    @Autowired
    InquiryNkService inquiryNkService;


    @Override
    @PostMapping("/add")
    public NkResult add(@RequestBody InquiryNk inquiryNk) {
        return inquiryNkService.add(inquiryNk);
    }

    @Override
    @PutMapping("/edit/{id}")//这里使用put方法，http 方法中put表示更新
    public NkResult edit(@PathVariable("id")Integer id, @RequestBody InquiryNk inquiryNk) {
        return inquiryNkService.update(id,inquiryNk);
    }

    @Override
    @DeleteMapping("/del/{id}")
    public ResponseResult delete(@PathVariable("id") Integer id) {
        return inquiryNkService.delete(id);
    }

    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryResponseResult findList(@PathVariable("page") int page, @PathVariable("size") int size, InquiryNk inquiryNk) {
        return inquiryNkService.findList(page,size,inquiryNk);
    }
}
