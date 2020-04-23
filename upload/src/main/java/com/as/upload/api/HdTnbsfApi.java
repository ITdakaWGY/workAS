package com.as.upload.api;

import com.as.upload.domain.hdyyqk.HdYyqkList;
import com.as.upload.domain.tnbsf.HdTnbsf;
import com.as.upload.domain.tnbsf.responce.HdTnbsfResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;


public interface HdTnbsfApi {
    //新增
    @ApiOperation("新增糖尿病随访")
    public HdTnbsfResult add(HdTnbsf hdTnbsf, String hdYyqkList , MultipartFile JmImg, MultipartFile SfysImg);

    /*@ApiOperation("修改用户")
    public HdTnbsfResult edit(Long id, HdTnbsf hdTnbsf);

    //删除用户
    @ApiOperation("删除用户")
    public ResponseResult delete(Long id);

    //页面查询
    @ApiOperation("分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页记录数", required = true, paramType = "path", dataType = "int")
    })
    public QueryResponseResult findList(int page, int size, HdTnbsf hdTnbsf);*/
}
