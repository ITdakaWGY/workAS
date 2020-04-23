package com.as.android.api;

import com.as.android.common.response.QueryResponseResult;
import com.as.android.common.response.ResponseResult;
import com.as.android.domain.autoupdate.Autoupdate;
import com.as.android.domain.autoupdate.responce.AutoupdateResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AutoupdateApi {
    //新增
    @ApiOperation("新增")
    public AutoupdateResult add(Autoupdate autoupdate , String allname);

    @ApiOperation("修改")
    public AutoupdateResult edit(Long id, Autoupdate autoupdate);

    //删除
    @ApiOperation("删除")
    public ResponseResult delete(Long id);

    //查询最新版本
    @ApiOperation("查询最新版本")
    public AutoupdateResult findNewServerVersion(String appname);

    //查询版本的更新的描述
    @ApiOperation("查询版本的更新的描述")
    public AutoupdateResult findUpgradeinfoById(Integer id);

    //页面查询
    @ApiOperation("分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页记录数", required = true, paramType = "path", dataType = "int")
    })
    public QueryResponseResult findList(int page, int size, Autoupdate autoupdate);

}
