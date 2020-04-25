package com.as.occupationaldseases.service;

import com.alibaba.fastjson.JSON;
import com.as.occupationaldseases.common.response.CommonCode;
import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.dao.MenuRoleRelationMapper;
import com.as.occupationaldseases.domain.menuRoleRelation.MenuRoleRelation;
import com.as.occupationaldseases.domain.menuRoleRelation.MenuRoleRelations;
import com.as.occupationaldseases.domain.menuRoleRelation.responce.MenuRoleRelationCode;
import com.as.occupationaldseases.domain.menuRoleRelation.responce.MenuRoleRelationResult;
import com.as.occupationaldseases.domain.sysMenu.SysMenu;
import com.as.occupationaldseases.exception.ExceptionCast;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MenuRoleRelationService {

    @Resource
    private MenuRoleRelationMapper menuRoleRelationMapper;

    public MenuRoleRelationResult add(MenuRoleRelations menuRoleRelations){
        QueryWrapper<MenuRoleRelation> wrapper = new QueryWrapper<>();
        menuRoleRelationMapper.delete(wrapper.eq("rolecode",menuRoleRelations.getRolecode()));
        String[] menuId = menuRoleRelations.getMenuid();
        for (int i = 0; i < menuId.length; i++) {
            MenuRoleRelation menuRoleRelation = new MenuRoleRelation();
            menuRoleRelation.setId(null);
            menuRoleRelation.setRolecode(menuRoleRelations.getRolecode());
            menuRoleRelation.setMenuid(menuId[i]);
            menuRoleRelationMapper.insert(menuRoleRelation);
        }
        //调用dao新增用户
        return new MenuRoleRelationResult(CommonCode.SUCCESS, null);

    }


    //根据id删除
    public MenuRoleRelationResult delete(MenuRoleRelation menuRoleRelation) {
        QueryWrapper<MenuRoleRelation> wrapper = new QueryWrapper<>();
        wrapper.setEntity(menuRoleRelation);
        //先查询一下
        MenuRoleRelation menuRoleRelation1 = menuRoleRelationMapper.selectOne(wrapper);
        if (menuRoleRelation1 != null) {
            menuRoleRelationMapper.delete(wrapper);
            return new MenuRoleRelationResult(CommonCode.SUCCESS, menuRoleRelation);
        }
        return new MenuRoleRelationResult(MenuRoleRelationCode.MENUROLERELATION_NOTEXISTS, null);
    }
}
