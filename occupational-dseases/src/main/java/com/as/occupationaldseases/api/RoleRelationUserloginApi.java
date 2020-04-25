package com.as.occupationaldseases.api;

import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.domain.role_relation_userlogin.RoleRelationUserlogin;
import com.as.occupationaldseases.domain.role_relation_userlogin.responce.RoleRelationUserloginResult;
import io.swagger.annotations.ApiOperation;

public interface RoleRelationUserloginApi {
    @ApiOperation("角色和用户关联新增")
    public RoleRelationUserloginResult add(RoleRelationUserlogin roleRelationUserlogin);

    public QueryResponseResult select(RoleRelationUserlogin roleRelationUserlogin);
   /* @ApiOperation("角色和用户关联删除")
    public RoleRelationUserloginResult delete(String yhbh);*/
}
