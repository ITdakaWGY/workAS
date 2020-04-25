package com.as.occupationaldseases.controller;

import com.as.occupationaldseases.api.MenuRoleRelationApi;
import com.as.occupationaldseases.domain.menuRoleRelation.MenuRoleRelation;
import com.as.occupationaldseases.domain.menuRoleRelation.MenuRoleRelations;
import com.as.occupationaldseases.domain.menuRoleRelation.responce.MenuRoleRelationResult;
import com.as.occupationaldseases.service.MenuRoleRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/menuRoleRelation")
public class MenuRoleRelationController implements MenuRoleRelationApi {


    @Autowired
    private MenuRoleRelationService menuRoleRelationService;

    @Override
    @PostMapping("/add")
    public MenuRoleRelationResult add(@RequestBody MenuRoleRelations menuRoleRelations) {
        return menuRoleRelationService.add(menuRoleRelations);
    }

    @Override
    @PostMapping("/delete")
    public MenuRoleRelationResult delete(@RequestBody MenuRoleRelation menuRoleRelation) {
        return menuRoleRelationService.delete(menuRoleRelation);
    }
}
