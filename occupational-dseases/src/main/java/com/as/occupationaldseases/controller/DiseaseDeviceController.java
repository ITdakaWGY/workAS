package com.as.occupationaldseases.controller;

import com.as.occupationaldseases.api.DiseaseDeviceApi;
import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.domain.diseaseDevice.DiseaseDevice;
import com.as.occupationaldseases.domain.diseaseDevice.responce.DiseaseDeviceResult;
import com.as.occupationaldseases.service.DiseaseDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/DiseaseDevice")
public class DiseaseDeviceController implements DiseaseDeviceApi {

    @Autowired
    DiseaseDeviceService diseaseDeviceService;

    @Override
    @PostMapping("/add")
    public DiseaseDeviceResult add(@RequestBody DiseaseDevice diseaseDevice) {
        return diseaseDeviceService.add(diseaseDevice);
    }

    @Override
    @PutMapping("/update/{id}")//这里使用put方法，http 方法中put表示更新
    public DiseaseDeviceResult update(@PathVariable("id") int id, @RequestBody DiseaseDevice diseaseDevice) {
        return diseaseDeviceService.update(id, diseaseDevice);
    }

    @Override
    @DeleteMapping("/del/{id}")
    public DiseaseDeviceResult delete(@PathVariable("id") int id) {
        return diseaseDeviceService.delete(id);
    }

    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryResponseResult findList(@PathVariable("page") int page, @PathVariable("size") int size,DiseaseDevice diseaseDevice) {
        return diseaseDeviceService.findList(page, size, diseaseDevice);
    }

    @Override
    @GetMapping("/findBySingle")
    public DiseaseDeviceResult findBySingle(DiseaseDevice diseaseDevice) {
        return diseaseDeviceService.findBySingle(diseaseDevice);
    }

    @Override
    @GetMapping("/count")
    public Integer count(DiseaseDevice diseaseDevice) {
        return diseaseDeviceService.count(diseaseDevice);
    }
}
