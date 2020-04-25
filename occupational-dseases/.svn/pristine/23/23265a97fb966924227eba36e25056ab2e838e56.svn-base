package com.as.occupationaldseases.service;

import com.as.occupationaldseases.common.response.CommonCode;
import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.common.response.QueryResult;
import com.as.occupationaldseases.dao.RoleRelationUserloginMapper;
import com.as.occupationaldseases.domain.role_relation_userlogin.RoleRelationUserlogin;
import com.as.occupationaldseases.domain.role_relation_userlogin.responce.RoleRelationUserloginResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleRelationUserloginService{
    @Resource
    private RoleRelationUserloginMapper roleRelationUserloginMapper;


    /**
     * 新增
     * @param roleRelationUserlogin
     * @return
     */
    public RoleRelationUserloginResult add(RoleRelationUserlogin roleRelationUserlogin) {
        if (roleRelationUserlogin==null)
            return  new RoleRelationUserloginResult(CommonCode.INVALID_PARAM,null);
        Map<String, Object> map = new HashMap<>();
        map.put("yhbh",roleRelationUserlogin.getYhbh());
        roleRelationUserloginMapper.deleteByMap(map);
        //获取所有的角色编号
        String[] split = roleRelationUserlogin.getRolecode().split(",");
        RoleRelationUserlogin roleRelationUserlogin1 = new RoleRelationUserlogin();
        roleRelationUserlogin1.setYhbh(roleRelationUserlogin.getYhbh());
        for (String rolecode:split) {
            roleRelationUserlogin1.setRolecode(rolecode);
            roleRelationUserloginMapper.insert(roleRelationUserlogin1);
        }

        return new RoleRelationUserloginResult(CommonCode.SUCCESS,roleRelationUserlogin);
    }


    /**
     * 查询
     * @param roleRelationUserlogin
     * @return
     */
    public QueryResponseResult select(RoleRelationUserlogin roleRelationUserlogin) {
        Map<String,Object> map = new HashMap<>();
        map.put("yhbh",roleRelationUserlogin.getYhbh());
        List<RoleRelationUserlogin> roleRelationUserlogins = roleRelationUserloginMapper.selectByMap(map);
        QueryResult result = new QueryResult();
        result.setList(roleRelationUserlogins);
        result.setTotal(roleRelationUserlogins.size());
        return  new QueryResponseResult(CommonCode.SUCCESS,result);
    }
}
