package com.as.upload.service;


import com.as.upload.common.response.CommonCode;
import com.as.upload.common.response.QueryResponseResult;
import com.as.upload.common.response.QueryResult;
import com.as.upload.dao.HdGrjbxxMapper;
import com.as.upload.dao.HdGxysfMapper;

import com.as.upload.dao.HdYyqkMapper;
import com.as.upload.domain.gxysf.HdGxysf;
import com.as.upload.domain.gxysf.responce.HdGxysfCode;
import com.as.upload.domain.gxysf.responce.HdGxysfResult;
import com.as.upload.domain.hdgrjbxx.HdGrjbxx;
import com.as.upload.domain.hdyyqk.HdYyqk;
import com.as.upload.domain.hdyyqk.HdYyqkList;
import com.as.upload.domain.tnbsf.responce.HdTnbsfResult;
import com.as.upload.exception.ExceptionCast;
import com.as.upload.utils.FileUnloadUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;


@Service
public class HdGxysfService {

    @Resource
    private HdGxysfMapper hdGxysfMapper;

    @Resource
    private HdGrjbxxMapper hdGrjbxxMapper;

    @Resource
    private HdYyqkMapper hdYyqkMapper;

    @Value("${basePath}")
    private String basePath;



    //新增 
    public HdGxysfResult add(HdGxysf hdGxysf, List<HdYyqk> hdYyqkList , MultipartFile JmImg, MultipartFile SfysImg) {
        if (hdGxysf == null) {
            //抛出异常，非法参数异常..指定异常信息的内容
            return new HdGxysfResult(CommonCode.INVALID_PARAM, null);
        }
        //校验 Id的唯一性
        //根据 Id集合，如果查到说明此 已经存在，如果查询不到再继续添加
        /*HdGxysf hdGxysf1 = hdGxysfMapper.selectById(hdGxysf.getId());
        if (hdGxysf1 != null) {
            // 已经存在
            //抛出异常，异常内容就是已经存在
            ExceptionCast.cast(HdGxysfCode.HdGxysf_ADD_EXISTSNAME);
        }*/

        //调用dao新增
//       hdGxysf.setId(0L);//id自增

        //有ID走跟新
        if (hdGxysf.getId() != null){
           return this.update(hdGxysf,hdYyqkList,JmImg,SfysImg);
        }



        String sfz = hdGxysf.getSfz();
        if (StringUtils.isEmpty(sfz)) {
            // 未传入身份证信息
            //抛出异常，异常内容就是已经存在
            ExceptionCast.cast(HdGxysfCode.HdGxysf_SFZ_NOTEXISTS);
        }else {
            //传入身份证信息，根据身份证查询个人基本信息表内容
            QueryWrapper<HdGrjbxx> wrapper = new QueryWrapper<>();
            HdGrjbxx hdGrjbxx = new HdGrjbxx();
            hdGrjbxx.setSfz(hdGxysf.getSfz());
            wrapper.setEntity(hdGrjbxx);
            HdGrjbxx hdGrjbxx1 = hdGrjbxxMapper.selectOne(wrapper);
            if (hdGrjbxx1 != null){
                //查询到了此人已有个人基本信息，将Personalid存储到hdGxysf
                hdGxysf.setPersonalid(hdGrjbxx1.getPersonalid());
            }else {
                //未查询到了此人个人基本信息，新建
                HdGrjbxx hdGrjbxx2 = new HdGrjbxx();
                hdGrjbxx2.setCreateorg(hdGxysf.getCreateorg());
                hdGrjbxx2.setUpdateorg(hdGxysf.getUpdateorg());
                hdGrjbxx2.setSfz(hdGxysf.getSfz());
                //查询当前机构下的最大Personalid
                String maxPersonalid = hdGrjbxxMapper.selectMaxPersonalid(hdGrjbxx2.getCreateorg());
                if (StringUtils.isEmpty(maxPersonalid)){
                    maxPersonalid = hdGrjbxx2.getCreateorg() + "00000001";
                }
                long num = Long.parseLong(maxPersonalid);
                Long bbb = num + 1;
                String personalidnew = String.valueOf(bbb);
                hdGrjbxx2.setPersonalid(personalidnew);

                int i = hdGrjbxxMapper.insert(hdGrjbxx2);
                if (i > 0){
                    //个人基本信息创建完毕
                    hdGxysf.setPersonalid(hdGrjbxx2.getPersonalid());
                }
            }
        }
        //设置随访编号
        hdGxysf.setSfbh(UUID.randomUUID().toString());
        if (hdYyqkList != null && hdYyqkList.size()>0){
            for (HdYyqk hdYyqk : hdYyqkList) {
                hdYyqk.setZbmc("HD_GXYSF");
                hdYyqk.setSfbh(hdGxysf.getSfbh());
                hdYyqkMapper.insert(hdYyqk);
            }
        }



        try {
            String JmUrl = FileUnloadUtil.getInstance().FileUpload(JmImg, basePath, "imgs/gxy/JmImgs");
            String SfysUrl = FileUnloadUtil.getInstance().FileUpload(SfysImg, basePath, "imgs/gxy/SfysImgs");
            hdGxysf.setJmUrl(JmUrl);
            hdGxysf.setSfysUrl(SfysUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return new HdGxysfResult(CommonCode.FILE_FAIL, null);
        }

        hdGxysfMapper.insert(hdGxysf);
        return new HdGxysfResult(CommonCode.SUCCESS, hdGxysf.getId());

    }

    //修改
    public HdGxysfResult update(HdGxysf hdGxysf, List<HdYyqk> hdYyqkList , MultipartFile JmImg, MultipartFile SfysImg) {
        //根据id从数据库查询页面信息
        HdGxysf one = hdGxysfMapper.selectById(hdGxysf.getId());
        if (one != null) {
            //提交修改
            //删除文件
            if (JmImg == null && !StringUtils.isEmpty(one.getJmUrl())){
                System.out.println(basePath + one.getJmUrl());
                FileUnloadUtil.getInstance().delFile(basePath + one.getJmUrl());
                hdGxysf.setJmUrl(null);
            }
            if (SfysImg == null && !StringUtils.isEmpty(one.getSfysUrl())){
                FileUnloadUtil.getInstance().delFile(basePath + one.getSfysUrl());
                hdGxysf.setSfysUrl(null);
            }

            //文件上传
            try {
                if (JmImg != null && !StringUtils.isEmpty(one.getJmUrl())){
                    FileUnloadUtil.getInstance().delFile(basePath + one.getJmUrl());
                }
                String JmUrl = FileUnloadUtil.getInstance().FileUpload(JmImg, basePath, "imgs/gxy/JmImgs");
                hdGxysf.setJmUrl(JmUrl);
                if (SfysImg != null && !StringUtils.isEmpty(one.getSfysUrl())){
                    FileUnloadUtil.getInstance().delFile(basePath + one.getSfysUrl());
                }
                String SfysUrl = FileUnloadUtil.getInstance().FileUpload(SfysImg, basePath, "imgs/gxy/SfysImgs");
                hdGxysf.setSfysUrl(SfysUrl);
            } catch (Exception e) {
                e.printStackTrace();
                return new HdGxysfResult(CommonCode.FILE_FAIL, null);
            }

            hdGxysf.setSfbh(one.getSfbh());
            hdGxysfMapper.updateById(hdGxysf);

            QueryWrapper<HdYyqk> wrapper = new QueryWrapper<>();
            if (hdYyqkList != null && hdYyqkList.size()>0){
                for (HdYyqk hdYyqk : hdYyqkList) {
                    wrapper.setEntity(hdYyqk);
                    hdYyqkMapper.update(hdYyqk,wrapper);
                }
            }


            return new HdGxysfResult(CommonCode.SUCCESS, hdGxysf.getId());
        }
        //修改失败
        return new HdGxysfResult(HdGxysfCode.HdGxysf_NOTEXISTS, null);

    }
/*
    //根据id删除 
    public HdGxysfResult delete(Long id) {
        //先查询一下
        HdGxysf hdGxysf = hdGxysfMapper.selectById(id);
        if (hdGxysf != null) {
            hdGxysfMapper.deleteById(id);
            return new HdGxysfResult(CommonCode.SUCCESS, hdGxysf);
        }
        return new HdGxysfResult(HdGxysfCode.HdGxysf_NOTEXISTS, null);
    }

    *//**
     *  查询方法
     *
     * @param current 页码，从1开始记数
     * @param size    每页记录数
     * @param hdGxysf    查询条件
     * @return
     *//*
    public QueryResponseResult findList(int current, int size, HdGxysf hdGxysf) {
        if (hdGxysf == null) {
            hdGxysf = new HdGxysf();
        }

        //分页参数
        if (current <= 0) {
            current = 1;
        }
//        current = current - 1;
        if (size <= 0) {
            size = 10;
        }

        IPage<HdGxysf> page = new Page<>(current, size);
        QueryWrapper<HdGxysf> wrapper = new QueryWrapper<>();
        wrapper.setEntity(hdGxysf);

        IPage<HdGxysf> hdGxysfIPage = hdGxysfMapper.selectPage(page, wrapper);

        QueryResult queryResult = new QueryResult();

        queryResult.setList(hdGxysfIPage.getRecords());//数据列表
        queryResult.setTotal(hdGxysfIPage.getTotal());//数据总记录数
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS, queryResult);
        return queryResponseResult;
    }

*/
}
