package com.as.occupationaldseases.controller;

import com.as.occupationaldseases.api.CompanyinfoApi;
import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.domain.companyinfo.Company_Sign;
import com.as.occupationaldseases.domain.companyinfo.Companyinfo;
import com.as.occupationaldseases.domain.companyinfo.responce.CompanyinfoResult;
import com.as.occupationaldseases.service.CompanyinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@RequestMapping("/companyinfo")
public class CompanyinfoController implements CompanyinfoApi {
    @Autowired
    private CompanyinfoService companyinfoService;
    @Value("${company}")
    private String path;


    @Override
    @PostMapping("/add")
    public CompanyinfoResult add(@RequestBody Company_Sign companySign) {
        return companyinfoService.add(companySign);
    }
    @Override
    @PutMapping("/update/{id}")//这里使用put方法，http 方法中put表示更新
    public CompanyinfoResult update(@PathVariable("id") int id, @RequestBody Companyinfo companyinfo) {
        return companyinfoService.update(id, companyinfo);
    }

    @Override
    @DeleteMapping("/del/{id}")
    public CompanyinfoResult delete(@PathVariable("id") int id) {
        return companyinfoService.delete(id);
    }

    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryResponseResult findList(@PathVariable("page") int page, @PathVariable("size") int size, Companyinfo companyinfo) {
        return companyinfoService.findList(page, size, companyinfo);
    }

    @Override
    @GetMapping("/selectlist")
    public QueryResponseResult selectList(Companyinfo companyinfo){
        return companyinfoService.selectList(companyinfo);
    }

    @Override
    @GetMapping("/findBySingle")
    public CompanyinfoResult findBySingle(Companyinfo companyinfo) {
        return companyinfoService.findBySingle(companyinfo);
    }

    @Override
    @GetMapping("/count")
    public Integer count(Companyinfo companyinfo) {
        return companyinfoService.count(companyinfo);
    }



    @PostMapping("/fileUpload")
    public String fileUpload(@RequestParam("files") MultipartFile files) {
        return companyinfoService.fileUpload(files,path);
    }


}
