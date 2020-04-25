package com.as.occupationaldseases.controller;

import com.as.occupationaldseases.api.GrjbxxApi;
import com.as.occupationaldseases.common.response.ProjectState;
import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.common.response.QueryResultSta;
import com.as.occupationaldseases.domain.grjbxx.Grjbxx;
import com.as.occupationaldseases.domain.grjbxx.GrjbxxWhole;
import com.as.occupationaldseases.domain.grjbxx.responce.GrjbxxResult;
import com.as.occupationaldseases.service.GrjbxxService;
import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/grjbxx")
public class GrjbxxController implements GrjbxxApi {
    @Autowired
    GrjbxxService grjbxxService;

    @Value("${base64png}")
    private String path;

    @Override
    @PostMapping("/add")
    public GrjbxxResult add(@RequestBody Grjbxx grjbxx) {
        return grjbxxService.add(grjbxx);
    }

    @Override
    @PostMapping("/insert")
    public GrjbxxResult insertAndUpdate(@RequestBody Grjbxx grjbxx) {
        return grjbxxService.insertAndUpdate(grjbxx);
    }

    @Override
    @PutMapping("/update/{id}")
    public GrjbxxResult update(@PathVariable("id")Long id, @RequestBody Grjbxx grjbxx) {
        return grjbxxService.update(id,grjbxx);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public GrjbxxResult delete(@PathVariable("id")Long id) {
        return grjbxxService.delete(id);
    }

    @Override
    @PostMapping("/select")
    public QueryResponseResult select(@RequestBody Grjbxx grjbxx) {
        return grjbxxService.select(grjbxx);
    }

    @Override
    @PostMapping("/selectWhole")
    public QueryResponseResult selectWhole(@RequestBody Grjbxx grjbxx) {
        return grjbxxService.selectWhole(grjbxx);
    }

    @Override
    @PostMapping("/Import")
    public GrjbxxResult Import(@RequestParam MultipartFile excelFile) throws IOException {
        return grjbxxService.Import(excelFile);
    }



    @Override
    @RequestMapping(value = "/selecsta", method = {RequestMethod.POST, RequestMethod.GET})
    public QueryResultSta selecsta(@RequestParam Map<String, Object> params) {
        return grjbxxService.selecsta(params);
    }

    @Override
    @PostMapping("/adddWhole")
    public GrjbxxResult adddWhole(@RequestBody GrjbxxWhole grjbxxWhole) {
        System.out.println("数据是："+grjbxxWhole);
        return grjbxxService.adddWhole(grjbxxWhole);
    }

    @Override
    @RequestMapping(value = "/list/{page}/{size}", method = {RequestMethod.POST, RequestMethod.GET})
    public QueryResponseResult findList(@PathVariable("page") int page, @PathVariable("size")int size, @RequestParam Map<String,Object> params) {
        System.out.println("page:"+page+",size:"+size);
        return grjbxxService.findList(page,size,params);
    }

    @Override
    @GetMapping("/prog/{page}/{size}")
    public QueryResponseResult ProgList(@PathVariable("page") int page, @PathVariable("size") int size, Grjbxx grjbxx) {
        return grjbxxService.ProgList(page,size,grjbxx);
    }

    @Override
    @PostMapping("/base64Url")
    public String base64Url(@RequestBody String str) {
        return grjbxxService.getBase64(path,str);
    }

    @Override
    @GetMapping("/getProject/{tmh}")
    public List<ProjectState> getProject(@PathVariable("tmh") String tmh) {
        return grjbxxService.getProject(tmh);
    }

    @Override
    @RequestMapping("/download")
    public R downloadFile(HttpServletResponse response) throws UnsupportedEncodingException {
        return grjbxxService.downloadFile(response);
    }


}
