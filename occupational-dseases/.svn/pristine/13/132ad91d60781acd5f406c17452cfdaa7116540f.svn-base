package com.as.occupationaldseases.controller;

import com.as.occupationaldseases.api.RoleRelationUserloginApi;
import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.domain.role_relation_userlogin.RoleRelationUserlogin;
import com.as.occupationaldseases.domain.role_relation_userlogin.responce.RoleRelationUserloginResult;
import com.as.occupationaldseases.service.RoleRelationUserloginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/roleRelationUserlogin")
public class RoleRelationUserloginController implements RoleRelationUserloginApi {
    @Autowired
    private RoleRelationUserloginService roleRelationUserloginService;

    @Override
    @PostMapping("/add")
    public RoleRelationUserloginResult add(@RequestBody  RoleRelationUserlogin roleRelationUserlogin) {
        return roleRelationUserloginService.add(roleRelationUserlogin);
    }

    @Override
    @PostMapping("/select")
    public QueryResponseResult select(@RequestBody RoleRelationUserlogin roleRelationUserlogin) {
        return roleRelationUserloginService.select(roleRelationUserlogin);
    }


}
