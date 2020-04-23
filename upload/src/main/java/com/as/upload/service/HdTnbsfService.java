package com.as.upload.service;


import com.as.upload.common.response.CommonCode;
import com.as.upload.dao.HdGrjbxxMapper;
import com.as.upload.dao.HdTnbsfMapper;

import com.as.upload.dao.HdYyqkMapper;
import com.as.upload.domain.hdgrjbxx.HdGrjbxx;
import com.as.upload.domain.hdyyqk.HdYyqk;
import com.as.upload.domain.tnbsf.HdTnbsf;
import com.as.upload.domain.tnbsf.responce.HdTnbsfCode;
import com.as.upload.domain.tnbsf.responce.HdTnbsfResult;
import com.as.upload.exception.ExceptionCast;
import com.as.upload.utils.FileUnloadUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;


@Service
public class HdTnbsfService {

    @Resource
    private HdTnbsfMapper hdTnbsfMapper;

    @Resource
    private HdGrjbxxMapper hdGrjbxxMapper;

    @Resource
    private HdYyqkMapper hdYyqkMapper;

    @Value("${basePath}")
    private String basePath;

    //新增 
    public HdTnbsfResult add(HdTnbsf hdTnbsf, List<HdYyqk> hdYyqkList, MultipartFile JmImg, MultipartFile SfysImg) {
        if (hdTnbsf == null) {
            //抛出异常，非法参数异常..指定异常信息的内容
            return new HdTnbsfResult(CommonCode.INVALID_PARAM, null);
        }
        //校验 Id的唯一性
        //根据 Id集合，如果查到说明此 已经存在，如果查询不到再继续添加
       /* HdTnbsf hdTnbsf1 = hdTnbsfMapper.selectById(hdTnbsf.getId());
        if (hdTnbsf1 != null) {
            // 已经存在
            //抛出异常，异常内容就是已经存在
            ExceptionCast.cast(HdTnbsfCode.HdTnbsf_ADD_EXISTSNAME);
        }*/

        //调用dao新增 
//        hdTnbsf.setId(null);//id自增
        //有ID走跟新
        System.out.println(hdTnbsf.getId());
        if (hdTnbsf.getId() != null){
            return this.update(hdTnbsf,hdYyqkList,JmImg,SfysImg);
        }



        String sfz = hdTnbsf.getSfz();
        if (StringUtils.isEmpty(sfz)) {
            // 未传入身份证信息
            //抛出异常，异常内容就是已经存在
            ExceptionCast.cast(HdTnbsfCode.HdTnbsf_SFZ_NOTEXISTS);
        }else {
            //传入身份证信息，根据身份证查询个人基本信息表内容
            QueryWrapper<HdGrjbxx> wrapper = new QueryWrapper<>();
            HdGrjbxx hdGrjbxx = new HdGrjbxx();
            hdGrjbxx.setSfz(hdTnbsf.getSfz());
            wrapper.setEntity(hdGrjbxx);
            HdGrjbxx hdGrjbxx1 = hdGrjbxxMapper.selectOne(wrapper);
            if (hdGrjbxx1 != null){
                //查询到了此人已有个人基本信息，将Personalid存储到hdTnbsf
                hdTnbsf.setPersonalid(hdGrjbxx1.getPersonalid());
            }else {
                //未查询到了此人个人基本信息，新建
                HdGrjbxx hdGrjbxx2 = new HdGrjbxx();
                hdGrjbxx2.setCreateorg(hdTnbsf.getCreateorg());
                hdGrjbxx2.setUpdateorg(hdTnbsf.getUpdateorg());
                hdGrjbxx2.setSfz(hdTnbsf.getSfz());
                hdGrjbxx2.setXm(hdTnbsf.getXm());
                //查询当前机构下的最大Personalid
                String maxPersonalid = hdGrjbxxMapper.selectMaxPersonalid(hdGrjbxx2.getCreateorg());
                if (StringUtils.isEmpty(maxPersonalid)){
                    maxPersonalid = hdGrjbxx2.getCreateorg() + "00000001";
                }
                long num = Long.parseLong(maxPersonalid);
                Long bbb = num + 1;
                String personalidnew = String.valueOf(bbb);
                hdGrjbxx2.setPersonalid(personalidnew);

                System.out.println(hdGrjbxx2.toString());
                int i = hdGrjbxxMapper.insert(hdGrjbxx2);
                if (i > 0){
                    //个人基本信息创建完毕
                    hdTnbsf.setPersonalid(hdGrjbxx2.getPersonalid());
                }
            }
        }
        //设置随访编号
        hdTnbsf.setSfbh(UUID.randomUUID().toString());
        if (hdYyqkList != null && hdYyqkList.size()>0){
            for (HdYyqk hdYyqk : hdYyqkList) {
                hdYyqk.setZbmc("HD_TNBSF");
                hdYyqk.setSfbh(hdTnbsf.getSfbh());
                hdYyqkMapper.insert(hdYyqk);
            }
        }


        //文件上传
        try {
            String JmUrl = FileUnloadUtil.getInstance().FileUpload(JmImg, basePath, "imgs/tnb/JmImgs");
            String SfysUrl = FileUnloadUtil.getInstance().FileUpload(SfysImg, basePath, "imgs/tnb/SfysImgs");
            hdTnbsf.setJmUrl(JmUrl);
            hdTnbsf.setSfysUrl(SfysUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return new HdTnbsfResult(CommonCode.FILE_FAIL, null);
        }

        hdTnbsfMapper.insert(hdTnbsf);
        return new HdTnbsfResult(CommonCode.SUCCESS, hdTnbsf.getId());

    }

   //修改
    public HdTnbsfResult update(HdTnbsf hdTnbsf, List<HdYyqk> hdYyqkList, MultipartFile JmImg, MultipartFile SfysImg) {
        //根据id从数据库查询页面信息
        HdTnbsf one = hdTnbsfMapper.selectById(hdTnbsf.getId());
        if (one != null) {
            //删除文件
            if (JmImg == null && !StringUtils.isEmpty(one.getJmUrl())){
                FileUnloadUtil.getInstance().delFile(basePath + one.getJmUrl());
                hdTnbsf.setJmUrl(null);
            }
            if (SfysImg == null && !StringUtils.isEmpty(one.getSfysUrl())){
                FileUnloadUtil.getInstance().delFile(basePath + one.getSfysUrl());
                hdTnbsf.setSfysUrl(null);
            }

            //文件上传
            try {
                if (JmImg != null && !StringUtils.isEmpty(one.getJmUrl())){
                    FileUnloadUtil.getInstance().delFile(basePath + one.getJmUrl());
                }
                String JmUrl = FileUnloadUtil.getInstance().FileUpload(JmImg, basePath, "imgs/tnb/JmImgs");
                hdTnbsf.setJmUrl(JmUrl);
                if (SfysImg != null && !StringUtils.isEmpty(one.getSfysUrl())){
                    FileUnloadUtil.getInstance().delFile(basePath + one.getSfysUrl());
                }
                String SfysUrl = FileUnloadUtil.getInstance().FileUpload(SfysImg, basePath, "imgs/tnb/SfysImgs");
                hdTnbsf.setSfysUrl(SfysUrl);
            } catch (Exception e) {
                e.printStackTrace();
                return new HdTnbsfResult(CommonCode.FILE_FAIL, null);
            }

            //提交修改
            hdTnbsf.setSfbh(one.getSfbh());
            hdTnbsfMapper.updateById(hdTnbsf);

            QueryWrapper<HdYyqk> wrapper = new QueryWrapper<>();
            if (hdYyqkList != null && hdYyqkList.size()>0){
                for (HdYyqk hdYyqk : hdYyqkList) {
                    wrapper.setEntity(hdYyqk);
                    hdYyqkMapper.update(hdYyqk,wrapper);
                }
            }


            return new HdTnbsfResult(CommonCode.SUCCESS, hdTnbsf.getId());
        }
        //修改失败
        return new HdTnbsfResult(HdTnbsfCode.HdTnbsf_NOTEXISTS, null);

    }
/*
    //根据id删除 
    public HdTnbsfResult delete(Long id) {
        //先查询一下
        HdTnbsf hdTnbsf = hdTnbsfMapper.selectById(id);
        if (hdTnbsf != null) {
            hdTnbsfMapper.deleteById(id);
            return new HdTnbsfResult(CommonCode.SUCCESS, hdTnbsf);
        }
        return new HdTnbsfResult(HdTnbsfCode.HdTnbsf_NOTEXISTS, null);
    }

    *//**
     *  查询方法
     *
     * @param current 页码，从1开始记数
     * @param size    每页记录数
     * @param hdTnbsf    查询条件
     * @return
     *//*
    public QueryResponseResult findList(int current, int size, HdTnbsf hdTnbsf) {
        if (hdTnbsf == null) {
            hdTnbsf = new HdTnbsf();
        }

        //分页参数
        if (current <= 0) {
            current = 1;
        }
//        current = current - 1;
        if (size <= 0) {
            size = 10;
        }

        IPage<HdTnbsf> page = new Page<>(current, size);
        QueryWrapper<HdTnbsf> wrapper = new QueryWrapper<>();
        wrapper.setEntity(hdTnbsf);

        IPage<HdTnbsf> hdTnbsfIPage = hdTnbsfMapper.selectPage(page, wrapper);

        QueryResult queryResult = new QueryResult();

        queryResult.setList(hdTnbsfIPage.getRecords());//数据列表
        queryResult.setTotal(hdTnbsfIPage.getTotal());//数据总记录数
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS, queryResult);
        return queryResponseResult;
    }
*/

}
