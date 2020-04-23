package com.as.upload.api;

import com.as.upload.domain.gxysf.HdGxysf;
import com.as.upload.domain.gxysf.responce.HdGxysfResult;
import com.as.upload.domain.hdyyqk.HdYyqkList;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;


public interface HdGxysfApi {
    //新增
    @ApiOperation("新增高血压随访")
    public HdGxysfResult add(HdGxysf hdGxysf , String hdYyqkList , MultipartFile JmImg, MultipartFile SfysImg);

   /* @ApiOperation("修改用户")
    public HdGxysfResult edit(Long id, HdGxysf hdGxysf);

    //删除用户
    @ApiOperation("删除用户")
    public ResponseResult delete(Long id);

    //页面查询
    @ApiOperation("分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页记录数", required = true, paramType = "path", dataType = "int")
    })
    public QueryResponseResult findList(int page, int size, HdGxysf hdGxysf );*/
}
