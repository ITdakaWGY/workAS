package com.as.occupationaldseases.controller;

import com.as.occupationaldseases.api.InquirySjkApi;
import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.common.response.ResponseResult;
import com.as.occupationaldseases.domain.inquiry_sjk.InquirySjk;
import com.as.occupationaldseases.domain.inquiry_sjk.responce.SjkResult;
import com.as.occupationaldseases.service.InquirySjkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/InquirySjk")
@CrossOrigin
public class InquirySjkController implements InquirySjkApi {
    @Autowired
    InquirySjkService inquirySjkService;

    @Override
    @PostMapping("/add")
    public SjkResult add(@RequestBody InquirySjk inquirySjk) {
        return inquirySjkService.add(inquirySjk);
    }

    @Override
    @PutMapping("/edit/{id}")//这里使用put方法，http 方法中put表示更新
    public SjkResult edit(@PathVariable("id")Integer id, @RequestBody InquirySjk inquirySjk) {
        return inquirySjkService.update(id,inquirySjk);
    }

    @Override
    @DeleteMapping("/del/{id}")
    public ResponseResult delete(@PathVariable("id") Integer id) {
        return inquirySjkService.delete(id);
    }

    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryResponseResult findList(@PathVariable("page") int page, @PathVariable("size") int size, InquirySjk inquirySjk) {
        return inquirySjkService.findList(page,size,inquirySjk);
    }
}
