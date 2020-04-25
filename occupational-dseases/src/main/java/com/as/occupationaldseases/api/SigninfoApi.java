package com.as.occupationaldseases.api;

import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.domain.signinfo.Signinfo;
import com.as.occupationaldseases.domain.signinfo.responce.SigninfoResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface SigninfoApi {
    @ApiOperation("签约新增")
    public SigninfoResult add(Signinfo signinfo);
    @ApiOperation("签约更新")
    public SigninfoResult update(Long id, Signinfo signinfo);
    @ApiOperation("签约删除")
    public SigninfoResult delete(Long id);
    @ApiOperation("签约查询")
    public QueryResponseResult select(Signinfo signinfo);
    @ApiOperation("文件上传")
    public String fileUpload(MultipartFile files);

    //页面查询
    @ApiOperation("分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value = "页码",required=true,paramType="path",dataType="int"),
            @ApiImplicitParam(name="size",value = "每页记录数",required=true,paramType="path",dataType="int")
    })
    public QueryResponseResult findList(int page, int size, Map<String, Object> params);
}
