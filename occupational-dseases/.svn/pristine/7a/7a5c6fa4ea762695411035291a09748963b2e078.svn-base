package com.as.occupationaldseases.controller;

import com.as.occupationaldseases.api.DiseaseItemApi;
import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.domain.diseaseItem.DiseaseItem;
import com.as.occupationaldseases.domain.diseaseItem.responce.DiseaseItemResult;
import com.as.occupationaldseases.service.DiseaseItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/DiseaseItem")
public class DiseaseItemController implements DiseaseItemApi {
    @Autowired
    private DiseaseItemService diseaseItemService;

    @Override
    @PostMapping("/add")
    public DiseaseItemResult add(@RequestBody DiseaseItem diseaseItem) {
        return diseaseItemService.add(diseaseItem);
    }

    @Override
    @PutMapping("/update/{id}")//这里使用put方法，http 方法中put表示更新
    public DiseaseItemResult update(@PathVariable("id") int id, @RequestBody DiseaseItem diseaseItem) {
        return diseaseItemService.update(id, diseaseItem);
    }

    @Override
    @DeleteMapping("/del/{id}")
    public DiseaseItemResult delete(@PathVariable("id") int id) {
        return diseaseItemService.delete(id);
    }

    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryResponseResult findList(@PathVariable("page") int page, @PathVariable("size") int size,DiseaseItem diseaseItem) {
        return diseaseItemService.findList(page, size, diseaseItem);
    }

    @Override
    @GetMapping("/findBySingle")
    public DiseaseItemResult findBySingle(DiseaseItem diseaseItem) {
        return diseaseItemService.findBySingle(diseaseItem);
    }

    @Override
    @GetMapping("/selectList")
    public QueryResponseResult selectList(DiseaseItem diseaseItem) {
        return diseaseItemService.selectList(diseaseItem);
    }

    @Override
    @GetMapping("/count")
    public Integer count(DiseaseItem diseaseItem) {
        return diseaseItemService.count(diseaseItem);
    }

    @Override
    @GetMapping("/hazardItemList/{hazardCode}/{page}/{size}")
    public QueryResponseResult selectRelation(@PathVariable("hazardCode") String hazardCode,@PathVariable("page") int page,@PathVariable("size") int size) {
        return diseaseItemService.selectRelation(hazardCode,page,size);
    }

    @Override
    @GetMapping("/selectNoPaging/{hazardCode}")
    public QueryResponseResult selectNoPaging(@PathVariable("hazardCode") String hazardCode) {
        return diseaseItemService.selectNoPaging(hazardCode);
    }

    @Override
    @GetMapping("/hazardItemNotinList/{hazardCode}/{page}/{size}")
    public QueryResponseResult selectRelationNotin(@PathVariable("hazardCode") String hazardCode,@PathVariable("page") int page,@PathVariable("size") int size) {
        return diseaseItemService.selectRelationNotin(hazardCode,page,size);
    }
}
