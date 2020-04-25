package com.as.occupationaldseases.controller;

import com.as.occupationaldseases.api.SysMenuApi;
import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.domain.sysMenu.SysMenu;
import com.as.occupationaldseases.domain.sysMenu.responce.SysMenuResult;
import com.as.occupationaldseases.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/sysMenu")
public class SysMenuController implements SysMenuApi {


    @Autowired
    private SysMenuService sysMenuService;

    @Override
    @PostMapping("/add")
    public SysMenuResult add(@RequestBody SysMenu sysMenu) {
        return sysMenuService.add(sysMenu);
    }

    @Override
    @PutMapping("/update/{id}")//这里使用put方法，http 方法中put表示更新
    public SysMenuResult update(@PathVariable("id") int id,@RequestBody SysMenu sysMenu) {
        return sysMenuService.update(id,sysMenu);
    }

    @Override
    @DeleteMapping("/del/{id}")
    public SysMenuResult delete(@PathVariable("id") int id) {
        return sysMenuService.delete(id);
    }

    @Override
    @GetMapping("/list")
    public QueryResponseResult findList(SysMenu sysMenu) {
        return sysMenuService.findList(sysMenu);
    }

    @Override
    @GetMapping("/findBySingle")
    public SysMenuResult findBySingle(SysMenu sysMenu) {
        return sysMenuService.findBySingle(sysMenu);
    }

    @Override
    @GetMapping("/selectRelation/{roleCode}/{menuId}")
    public QueryResponseResult selectRelation(@PathVariable("roleCode") String roleCode,@PathVariable("menuId") int menuId) {
        return sysMenuService.selectRelation(roleCode,menuId);
    }

    @Override
    @GetMapping("/roleMenuRelation/{rolecode}")
    public QueryResponseResult roleMenuRelation(@PathVariable("rolecode") String rolecode) {
        return sysMenuService.roleMenuRelation(rolecode);
    }

    @Override
    @GetMapping("/selectList/{rolecode}")
    public QueryResponseResult selectList(@PathVariable("rolecode") String rolecode) {
        return sysMenuService.selectList(rolecode);
    }


}
