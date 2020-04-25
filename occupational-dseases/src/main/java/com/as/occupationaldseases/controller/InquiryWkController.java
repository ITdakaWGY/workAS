package com.as.occupationaldseases.controller;

import com.as.occupationaldseases.api.InquiryWkApi;
import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.common.response.ResponseResult;
import com.as.occupationaldseases.domain.inquiry_wk.InquiryWk;
import com.as.occupationaldseases.domain.inquiry_wk.responce.WkResult;
import com.as.occupationaldseases.service.InquiryWkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/InquiryWk")
@CrossOrigin
public class InquiryWkController implements InquiryWkApi {
    @Autowired
    InquiryWkService inquiryWkService;

    @Override
    @PostMapping("/add")
    public WkResult add(@RequestBody InquiryWk inquiryWk) {
        return inquiryWkService.add(inquiryWk);
    }

    @Override
    @PutMapping("/edit/{id}")//这里使用put方法，http 方法中put表示更新
    public WkResult edit(@PathVariable("id")Integer id, @RequestBody InquiryWk inquiryWk) {
        return inquiryWkService.update(id,inquiryWk);
    }

    @Override
    @DeleteMapping("/del/{id}")
    public ResponseResult delete(@PathVariable("id") Integer id) {
        return inquiryWkService.delete(id);
    }

    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryResponseResult findList(@PathVariable("page") int page, @PathVariable("size") int size, InquiryWk inquiryWk) {
        return inquiryWkService.findList(page,size,inquiryWk);
    }
}
