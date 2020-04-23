package com.as.android.controller;

import com.as.android.api.AutoupdateApi;
import com.as.android.common.response.QueryResponseResult;
import com.as.android.common.response.ResponseResult;
import com.as.android.domain.autoupdate.Autoupdate;
import com.as.android.domain.autoupdate.responce.AutoupdateResult;
import com.as.android.service.AutoupdateService;
import com.as.android.utils.FileUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("/autoupdate")
public class AutoupdateController implements AutoupdateApi {

    @Autowired
    AutoupdateService autoupdateService;

    @Override
    @PostMapping("/add/{allname}")
    public AutoupdateResult add(@RequestBody Autoupdate autoupdate ,@PathVariable("allname") String allname) {
        return autoupdateService.add(autoupdate,allname);
    }

    @Override
    //@PutMapping("/edit/{id}")//这里使用put方法，http 方法中put表示更新
    public AutoupdateResult edit(@PathVariable("id") Long id, @RequestBody Autoupdate autoupdate) {
        return autoupdateService.update(id, autoupdate);
    }

    @Override
    //@DeleteMapping("/del/{id}")
    public ResponseResult delete(@PathVariable("id") Long id) {
        return autoupdateService.delete(id);
    }

    @Override
    @GetMapping("/new")
    public AutoupdateResult findNewServerVersion(String appname) {
        return autoupdateService.findNewServerVersion(appname);
    }

    @Override
    @GetMapping("/upgradeinfo/{id}")
    public AutoupdateResult findUpgradeinfoById(@PathVariable("id") Integer id) {
        return autoupdateService.findUpgradeinfoById(id);
    }

    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryResponseResult findList(@PathVariable("page") int page, @PathVariable("size") int size, Autoupdate autoupdate) {
        return autoupdateService.findList(page, size, autoupdate);
    }
}
