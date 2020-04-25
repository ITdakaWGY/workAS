package com.as.occupationaldseases.domain.menuRoleRelation.responce;

import com.as.occupationaldseases.common.response.ResultCode;
import lombok.ToString;


@ToString
public enum MenuRoleRelationCode implements ResultCode {
    MENUROLERELATION_NOTEXISTS(false, 22001, "角色菜单关联不存在！"),
    MENUROLERELATION_ADD_EXISTSNAME(false, 22002, "角色菜单关联已存在！"),
    MENUROLERELATION_GREATERTHAN(false, 22003, "查询数据大于一");
    //    private static ImmutableMap<Integer, CommonCode> codes ;
    //操作是否成功
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;

    private MenuRoleRelationCode(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    @Override
    public boolean success() {
        return success;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }


}
