package com.as.publichealth.controller;

import com.as.publichealth.api.UserApi;
import com.as.publichealth.common.response.QueryResponseResult;
import com.as.publichealth.common.response.ResponseResult;
import com.as.publichealth.domain.user.User;
import com.as.publichealth.domain.user.responce.UserResult;
import com.as.publichealth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController implements UserApi {
    @Autowired
    UserService userService;

    @Override
    //@PostMapping("/add")
    public UserResult add(@RequestBody User user) {
        return userService.add(user);
    }

    @Override
    //@PutMapping("/edit/{id}")//这里使用put方法，http 方法中put表示更新
    public UserResult edit(@PathVariable("id") Long id, @RequestBody User user) {
        return userService.update(id, user);
    }

    @Override
    //@DeleteMapping("/del/{id}")
    public ResponseResult delete(@PathVariable("id") Long id) {
        return userService.delete(id);
    }

    @Override
    //@GetMapping("/list/{page}/{size}")
    public QueryResponseResult findList(@PathVariable("page") int page, @PathVariable("size") int size, User user) {
        return userService.findList(page, size, user);
    }
}
