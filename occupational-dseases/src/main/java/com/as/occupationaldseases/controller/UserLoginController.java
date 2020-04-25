package com.as.occupationaldseases.controller;

import com.as.occupationaldseases.api.UserLoginApi;
import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.domain.userLogin.UserLogin;
import com.as.occupationaldseases.domain.userLogin.responce.UserLoginResult;
import com.as.occupationaldseases.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@RequestMapping("/userLogin")
public class UserLoginController implements UserLoginApi {
         @Autowired
         private UserLoginService userLoginService;
         @Value("${headPortrait}")
         private String path;
         @Value("${newheadPortrait}")
         private String newheadPortrait;

    @Override
    @PostMapping("/add")
    public UserLoginResult add(@RequestBody UserLogin userLogin) {
        return userLoginService.add(userLogin,newheadPortrait);
    }

    @Override
    @PutMapping("update/{id}")
    public UserLoginResult update(@PathVariable("id") Long id, @RequestBody UserLogin userLogin) {
        return userLoginService.update(id,userLogin,newheadPortrait);
    }

    @Override
    @DeleteMapping("delete/{id}")
    public UserLoginResult delete(@PathVariable("id") Long id) {
        System.out.println("id:"+id);
        return userLoginService.delete(id);
    }

    @Override
    @PostMapping("/select")
    public UserLoginResult select(@RequestBody UserLogin userLogin) {
        return userLoginService.select(userLogin);
    }

    @Override
    @PostMapping("/upload")
    public String upload(@RequestParam("img") MultipartFile file) {
        System.out.println(file);
        return userLoginService.upload(file,path);
    }

    @Override
    @GetMapping("/updatePassword/{yhbh}/{usedPassword}/{newPassword}")
    public UserLoginResult updatePassword(@PathVariable("yhbh")String yhbh,@PathVariable("usedPassword") String usedPassword,@PathVariable("newPassword") String newPassword) {
        return userLoginService.updatePassword(yhbh,usedPassword,newPassword);
    }

    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryResponseResult findList(@PathVariable("page")int page, @PathVariable("size") int size, UserLogin userLogin) {
        System.out.println("page:"+page);
        return userLoginService.findList(page,size,userLogin);
    }


}
