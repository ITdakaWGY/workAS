package com.as.occupationaldseases.service;

import com.as.occupationaldseases.common.response.CommonCode;
import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.common.response.QueryResult;
import com.as.occupationaldseases.dao.MenuRoleRelationMapper;
import com.as.occupationaldseases.dao.RoleMapper;
import com.as.occupationaldseases.domain.menuRoleRelation.MenuRoleRelation;
import com.as.occupationaldseases.domain.role.Role;
import com.as.occupationaldseases.domain.role.responce.RoleCode;
import com.as.occupationaldseases.domain.role.responce.RoleResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleService{

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private MenuRoleRelationMapper menuRoleRelationMapper;


    /**
     * 获取当前用户的角色
     * @param yhbh 用户id
     * @return
     */
    public QueryResponseResult getRole(String yhbh){
        List<Role> roleList = roleMapper.getRoleCode(yhbh);
        QueryResult<Role>  result= new QueryResult();
        result.setTotal(roleList.size());
        result.setList(roleList);
        return new QueryResponseResult(CommonCode.SUCCESS,result);
    }



    /**
     * 新增
     * @param role
     * @return
     */
    public RoleResult add(Role role) {
        if (role==null)
        return new RoleResult(CommonCode.INVALID_PARAM,null);
        role.setId(null);
        roleMapper.insert(role);
        return new RoleResult(CommonCode.SUCCESS,role);
    }

    /**
     * 修改
     * @param id
     * @param role
     * @return
     */
    public RoleResult update(Long id, Role role) {
        Role role1 = roleMapper.selectById(id);
        if (role1!=null) {
         roleMapper.updateById(role);
         return  new RoleResult(CommonCode.SUCCESS,role);
        }

        return new RoleResult(RoleCode.ROLE_NOTEXISTS,null);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public RoleResult delete(Long id) {
        Role role1 = roleMapper.selectById(id);

        if (role1!=null) {
            roleMapper.deleteById(id);
            QueryWrapper<MenuRoleRelation> wrapper = new QueryWrapper<>();
            menuRoleRelationMapper.delete(wrapper.eq("rolecode",role1.getRolecode()));
            return  new RoleResult(CommonCode.SUCCESS,role1);
        }

        return new RoleResult(RoleCode.ROLE_NOTEXISTS,null);
    }


    /**
     * 查询全部角色
     * @param role
     * @return
     */
    public QueryResponseResult select(Role role) {
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        wrapper.setEntity(role);
        List<Role> roleList = roleMapper.selectList(wrapper);
        QueryResult<Role> result = new QueryResult<>();
        result.setList(roleList);
        result.setTotal(roleList.size());
        return new QueryResponseResult(CommonCode.SUCCESS,result);
    }

    /**
     * 分页查询
     * @param page 页码
     * @param size 每页记录条
     * @param role 条件
     * @return
     */
    public QueryResponseResult pagingSelect(int page, int size, Role role) {
        if (role==null)
           role = new Role();
        if (page<=1)
            page = 0;
        if (size<=0)
            size = 10;
        IPage<Role> roleIPage = new Page<>(page,size);
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        wrapper.setEntity(role);
        IPage<Role> selectPage = roleMapper.selectPage(roleIPage, wrapper);
        QueryResult<Role> result = new QueryResult<>();
        result.setList(selectPage.getRecords());
        result.setTotal(selectPage.getTotal());
        return new QueryResponseResult(CommonCode.SUCCESS,result);
    }
}
