package com.as.occupationaldseases.controller;

import com.as.occupationaldseases.api.TemplateApi;
import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.common.response.ResponseResult;
import com.as.occupationaldseases.domain.template.Template;
import com.as.occupationaldseases.domain.template.responce.TemplateResult;
import com.as.occupationaldseases.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/template")
public class TemplateController implements TemplateApi {
    @Autowired
    TemplateService templateService;

    @Override
    @PostMapping("/add")
    public TemplateResult add(@RequestBody Template template) {
        return templateService.add(template);
    }

    @Override
    @PutMapping("/edit/{id}")//这里使用put方法，http 方法中put表示更新
    public TemplateResult edit(@PathVariable("id")Long id, @RequestBody Template template) {
        return templateService.update(id, template);
    }

    @Override
    @DeleteMapping("/del/{id}")
    public ResponseResult delete(@PathVariable("id") Long id) {
        return templateService.delete(id);
    }

    @Override
    @GetMapping("/all")
    public QueryResponseResult findAll(Template template) {
        return templateService.findAll(template);
    }

    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryResponseResult findList(@PathVariable("page") int page,@PathVariable("size") int size, Template template) {
        return templateService.findList(page,size,template);
    }

}
