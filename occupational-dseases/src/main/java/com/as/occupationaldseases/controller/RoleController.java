package com.as.occupationaldseases.controller;

import com.as.occupationaldseases.api.RoleApi;
import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.domain.role.Role;
import com.as.occupationaldseases.domain.role.responce.RoleResult;
import com.as.occupationaldseases.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/role")
public class RoleController implements RoleApi {
    @Autowired
    private RoleService roleService;

    @Override
    @PostMapping("/add")
    public RoleResult add(@RequestBody Role role) {
        return roleService.add(role);
    }

    @Override
    @PutMapping("/update/{id}")
    public RoleResult update(@PathVariable("id") Long id,@RequestBody Role role) {
        return roleService.update(id,role);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public RoleResult delete(@PathVariable("id") Long id) {
        return roleService.delete(id);
    }

    @Override
    @PostMapping("/select")
    public QueryResponseResult select(@RequestBody Role role) {
        return roleService.select(role);
    }

    @Override
    @GetMapping("/getRole/{yhbh}")
    public QueryResponseResult getRole(@PathVariable("yhbh") String yhbh) {
        return roleService.getRole(yhbh);
    }

    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryResponseResult pagingSelect(@PathVariable("page")int page,@PathVariable("size") int size, Role role) {
        return roleService.pagingSelect(page, size, role);
    }
}
