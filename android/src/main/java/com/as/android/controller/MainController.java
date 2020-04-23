package com.as.android.controller;

import com.as.android.api.MainApi;
import com.as.android.common.response.QueryResponseResult;
import com.as.android.domain.main.Main;
import com.as.android.domain.main.responce.MainResult;
import com.as.android.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@RequestMapping("/main")
public class MainController implements MainApi {
    @Autowired
    MainService mainService;

    @Override
    @PostMapping("/add")
    public MainResult add( @RequestBody Main main) {
        return mainService.add(main);
    }

    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryResponseResult findList(@PathVariable("page") int page, @PathVariable("size") int size, Main main) {
        return mainService.findList(page, size, main);
    }


/*    @PostMapping("/setSession")
    public void setSession(HttpServletRequest request,String aaa) {
        System.out.println(aaa);
        request.getSession().setAttribute("Token",aaa);
    }

    @PostMapping("/GetSession")
    public String getSession(HttpServletRequest request) {
        String tonken = (String) request.getSession().getAttribute("Token");
        System.out.println(tonken);
        return tonken;
    }*/

}
