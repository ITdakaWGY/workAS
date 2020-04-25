package com.as.occupationaldseases.service;


import com.as.occupationaldseases.common.response.CommonCode;
import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.common.response.QueryResult;
import com.as.occupationaldseases.dao.MenuRoleRelationMapper;
import com.as.occupationaldseases.dao.SysMenuMapper;
import com.as.occupationaldseases.domain.menuRoleRelation.MenuRoleRelation;
import com.as.occupationaldseases.domain.role.Role;
import com.as.occupationaldseases.domain.sysMenu.SysMenu;
import com.as.occupationaldseases.domain.sysMenu.responce.SysMenuCode;
import com.as.occupationaldseases.domain.sysMenu.responce.SysMenuResult;
import com.as.occupationaldseases.exception.ExceptionCast;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class SysMenuService {

    @Resource
    private SysMenuMapper sysMenuMapper;

    @Resource
    private MenuRoleRelationMapper menuRoleRelationMapper;


    //新增
    public SysMenuResult add(SysMenu sysMenu) {
        if (sysMenu == null) {
            //抛出异常，非法参数异常..指定异常信息的内容
            return new SysMenuResult(CommonCode.INVALID_PARAM, null);
        }
        //校验用户Id的唯一性
        //根据用户Id集合，如果查到说明此用户已经存在，如果查询不到再继续添加
        SysMenu sysMenu1 = sysMenuMapper.selectById(sysMenu.getId());
        if (sysMenu1 != null) {
            //用户已经存在
            //抛出异常，异常内容就是用户已经存在
            ExceptionCast.cast(SysMenuCode.SYSMENU_ADD_EXISTSNAME);
        }
        //调用dao新增用户
        sysMenuMapper.insert(sysMenu);
        return new SysMenuResult(CommonCode.SUCCESS, sysMenu);

    }


    //根据id修改
    public SysMenuResult update(int id, SysMenu sysMenu) {
        //根据id从数据库查询页面信息
        SysMenu one = sysMenuMapper.selectById(id);
        if (one != null) {
            //提交修改
            sysMenuMapper.updateById(sysMenu);
            return new SysMenuResult(CommonCode.SUCCESS, sysMenu);
        }
        //修改失败
        return new SysMenuResult(SysMenuCode.SYSMENU_NOTEXISTS, null);
    }


    //根据id删除
    public SysMenuResult delete(int id) {
        //先查询一下
        SysMenu sysMenu = sysMenuMapper.selectById(id);
        if (sysMenu != null) {
            sysMenuMapper.deleteById(id);
            return new SysMenuResult(CommonCode.SUCCESS, sysMenu);
        }
        return new SysMenuResult(SysMenuCode.SYSMENU_NOTEXISTS, null);
    }



    /**
     * 查询所有主菜单方法
     *
     * @return
     */
    public QueryResponseResult selectList(String rolecode) {
        List<SysMenu> sysMenus = sysMenuMapper.selectManu(rolecode);
        QueryResult queryResult = new QueryResult();
        queryResult.setList(sysMenus);//数据列表
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS, queryResult);
        return queryResponseResult;
    }




    /**
     * 分页查询方法
     * @param sysMenu 查询条件
     * @return
     */
    public QueryResponseResult findList(SysMenu sysMenu) {
        if (sysMenu == null) {
            sysMenu = new SysMenu();
        }
        QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
        wrapper.setEntity(sysMenu);

        List<SysMenu> sysMenus = sysMenuMapper.selectList(wrapper);//查出所有菜单
        List<Object> list = new ArrayList<>();
        for (SysMenu s : sysMenus) {

            if(s.getPid()==0){//判断是否是一级菜单
                LinkedHashMap<String,Object> param = new LinkedHashMap<>();
                boolean flag = true;
                param.put("name", s.getMenuname());//tree的节点名称
                param.put("id", s.getId());
                param.put("pid", s.getPid());
                param.put("open", flag);//layui中的是否禁用
                param.put("children", getChildren(s.getId(),sysMenus));//菜单节点，递归遍历
                list.add(param);
            }
        }
        QueryResult queryResult = new QueryResult();
        queryResult.setList(list);//数据列表
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS, queryResult);
        return queryResponseResult;
    }


    public List<Object> getChildren(Integer pid,List<SysMenu> sysMenus){
        List<Object> list = new ArrayList<>();
        for (SysMenu s : sysMenus) {
            if(s.getPid()==pid){
                LinkedHashMap<String,Object> mparam = new LinkedHashMap<>();
                boolean flag = true;
                mparam.put("name", s.getMenuname());
                mparam.put("id", s.getId());
                mparam.put("pid", s.getPid());
                mparam.put("open", flag);//layui中的是否禁用
                mparam.put("children", getChildren(s.getId(),sysMenus));
                list.add(mparam);
            }
        }
        return list;
    }

    /**
     * 角色关联菜单
     * @param rolecode
     * @return
     */
    public QueryResponseResult roleMenuRelation(String rolecode) {
        SysMenu sysMenu = new SysMenu();
        QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
        wrapper.setEntity(sysMenu);
        List<SysMenu> sysMenus = sysMenuMapper.selectList(wrapper);//查出所有菜单
        QueryWrapper<MenuRoleRelation> rolewrapper = new QueryWrapper<>();
        List<MenuRoleRelation> menuRoleRelations = menuRoleRelationMapper.selectList(rolewrapper.eq("rolecode", rolecode));
        List<Object> list = new ArrayList<>();
        for (SysMenu s : sysMenus) {
            if(s.getPid()==0){//判断是否是一级菜单
                boolean bool = roleRelation(String.valueOf(s.getId()), menuRoleRelations);
                LinkedHashMap<String,Object> param = new LinkedHashMap<>();
                boolean flag = true;
                param.put("name", s.getMenuname());//tree的节点名称
                param.put("id", s.getId());
                param.put("pid", s.getPid());
                param.put("checked",bool);
                param.put("open", flag);//layui中的是否禁用
                param.put("children", getRoleRelation(s.getId(),sysMenus,menuRoleRelations));//菜单节点，递归遍历
                list.add(param);
            }
        }
        QueryResult queryResult = new QueryResult();
        queryResult.setList(list);//数据列表
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS, queryResult);
        return queryResponseResult;
    }


    public boolean roleRelation(String menuId, List<MenuRoleRelation> menuRoleRelations){
        boolean flag = false;
        for (MenuRoleRelation menuRoleRelation : menuRoleRelations) {
            if(menuRoleRelation.getMenuid().equals(menuId)){
                    flag = true;
            }
        }
        return flag;
    }


    public List<Object> getRoleRelation(Integer pid,List<SysMenu> sysMenus,List<MenuRoleRelation> menuRoleRelations){
        List<Object> list = new ArrayList<>();
        for (SysMenu s : sysMenus) {
            if(s.getPid()==pid){
                boolean bool = roleRelation(String.valueOf(s.getId()), menuRoleRelations);
                LinkedHashMap<String,Object> mparam = new LinkedHashMap<>();
                boolean flag = true;
                mparam.put("name", s.getMenuname());
                mparam.put("id", s.getId());
                mparam.put("pid", s.getPid());
                mparam.put("checked",bool);
                mparam.put("open", flag);//layui中的是否禁用
                mparam.put("children", getRoleRelation(s.getId(),sysMenus,menuRoleRelations));
                list.add(mparam);
            }
        }
        return list;
    }

    //查询单条数据
    public SysMenuResult findBySingle(SysMenu sysMenu) {
        QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
        wrapper.setEntity(sysMenu);
        Integer row = sysMenuMapper.selectCount(wrapper);
        if (row == 1) {
            return new SysMenuResult(CommonCode.SUCCESS, sysMenuMapper.selectOne(wrapper));
        } else if (row > 1) {
            return new SysMenuResult(SysMenuCode.SYSMENU_GREATERTHAN, null);
        }
        return new SysMenuResult(SysMenuCode.SYSMENU_NOTEXISTS, null);
    }


    public QueryResponseResult selectRelation(String roleCode,int menuId){
        QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
        List<SysMenu> sysMenus = sysMenuMapper.selectRelation(roleCode);//查出所有菜单
        List<Object> list = new ArrayList<>();
        for (SysMenu s : sysMenus) {
            if(s.getPid()==menuId){//判断是否是一级菜单
                LinkedHashMap<String,Object> param = new LinkedHashMap<>();
                boolean flag = true;
                param.put("title", s.getMenuname());//tree的节点名称
                param.put("icon","&#xe61c;");
                param.put("href",s.getMenuurl());
                param.put("spread", flag);//layui中的是否禁用
                param.put("children", getSelectRelation(s.getId(),sysMenus));//菜单节点，递归遍历
                list.add(param);
            }
        }
        QueryResult queryResult = new QueryResult();
        queryResult.setList(list);//数据列表
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS, queryResult);
        return queryResponseResult;
    }


    public List<Object> getSelectRelation(Integer pid,List<SysMenu> sysMenus){
        List<Object> list = new ArrayList<>();
        for (SysMenu s : sysMenus) {
            if(s.getPid()==pid){
                LinkedHashMap<String,Object> param = new LinkedHashMap<>();
                boolean flag = true;
                param.put("title", s.getMenuname());//tree的节点名称
                param.put("icon","&#xe61c;");
                param.put("href",s.getMenuurl());
                param.put("spread", flag);//layui中的是否禁用
                param.put("children", getSelectRelation(s.getId(),sysMenus));//菜单节点，递归遍历
                list.add(param);
            }
        }
        return list;
    }

}
