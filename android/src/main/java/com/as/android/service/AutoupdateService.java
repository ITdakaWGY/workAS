package com.as.android.service;

import com.as.android.common.response.CommonCode;
import com.as.android.common.response.QueryResponseResult;
import com.as.android.common.response.QueryResult;
import com.as.android.common.response.ResponseResult;
import com.as.android.dao.AutoupdateMapper;
import com.as.android.domain.autoupdate.Autoupdate;
import com.as.android.domain.autoupdate.responce.AutoupdateCode;
import com.as.android.domain.autoupdate.responce.AutoupdateResult;
import com.as.android.exception.ExceptionCast;
import com.as.android.utils.FileUploadUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;


@Service
public class AutoupdateService {

    @Resource
    private AutoupdateMapper autoupdateMapper;

    @Value("${uploadHead}")
    private String uploadHead;


    //新增
    public AutoupdateResult add(Autoupdate autoupdate,String allname) {
        if (autoupdate == null) {
            //抛出异常，非法参数异常..指定异常信息的内容
            return new AutoupdateResult(CommonCode.INVALID_PARAM, null);
        }
        //校验Id的唯一性
        //如果查到说明此已经存在，如果查询不到再继续添加
        Integer id = autoupdateMapper.findByServerVersionAndAppName(autoupdate.getServerVersion(), autoupdate.getAppname());
        if (id != null && id > 0) {
            //已经存在
            //抛出异常，异常内容就是已经存在
            ExceptionCast.cast(AutoupdateCode.Autoupdate_ADD_EXISTSNAME);
        }

        //调用dao新增
        autoupdate.setId(null);//id自增
        autoupdate.setUploadtime(new Date());//上传时间
        autoupdate.setUpdateurl(allname);
        autoupdateMapper.insert(autoupdate);
        return new AutoupdateResult(CommonCode.SUCCESS, autoupdate);

    }

    //修改
    public AutoupdateResult update(Long id, Autoupdate autoupdate) {
        //根据id从数据库查询页面信息
        Autoupdate one = autoupdateMapper.selectById(id);
        if (one != null) {
            //提交修改
            autoupdateMapper.updateById(autoupdate);
            return new AutoupdateResult(CommonCode.SUCCESS, autoupdate);
        }
        //修改失败
        return new AutoupdateResult(AutoupdateCode.Autoupdate_NOTEXISTS, null);

    }

    //根据id删除
    public AutoupdateResult delete(Long id) {
        //先查询一下
        Autoupdate autoupdate = autoupdateMapper.selectById(id);
        if (autoupdate != null) {
            autoupdateMapper.deleteById(id);
            return new AutoupdateResult(CommonCode.SUCCESS, autoupdate);
        }
        return new AutoupdateResult(AutoupdateCode.Autoupdate_NOTEXISTS, null);
    }



    /**
     * 查询方法
     *
     * @param current 页码，从1开始记数
     * @param size    每页记录数
     * @param autoupdate    查询条件
     * @return
     */
    public QueryResponseResult findList(int current, int size, Autoupdate autoupdate) {
        if (autoupdate == null) {
            autoupdate = new Autoupdate();
        }

        //分页参数
        if (current <= 0) {
            current = 1;
        }
//        current = current - 1;
        if (size <= 0) {
            size = 10;
        }

        IPage<Autoupdate> page = new Page<>(current, size);
        QueryWrapper<Autoupdate> wrapper = new QueryWrapper<>();
        wrapper.setEntity(autoupdate);

        IPage<Autoupdate> autoupdateIPage = autoupdateMapper.selectPage(page, wrapper);

        QueryResult queryResult = new QueryResult();

        queryResult.setList(autoupdateIPage.getRecords());//数据列表
        queryResult.setTotal(autoupdateIPage.getTotal());//数据总记录数
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS, queryResult);
        return queryResponseResult;
    }

    //查询最新版本
    public AutoupdateResult findNewServerVersion(String appname){
        //如果appname为空，返回错误
        if (StringUtils.isEmpty(appname)){
            return new AutoupdateResult(CommonCode.FAIL_APPNAME,null);
        }
        Autoupdate autoupdate = autoupdateMapper.findNewServerVersionByAppName(appname);
        //如果没有查询到，返回错误
        if (autoupdate == null || StringUtils.isEmpty(autoupdate.getServerVersion())){
            return new AutoupdateResult(CommonCode.FAIL_NULL,null);
        }
        String updateurl = autoupdate.getUpdateurl();
        autoupdate.setUpdateurl(uploadHead + updateurl);
        return new AutoupdateResult(CommonCode.SUCCESS,autoupdate);
    }

    //查询版本的更新的描述
    public AutoupdateResult findUpgradeinfoById (Integer id){
        //id，返回错误
        if (id == null || id <= 0){
            return new AutoupdateResult(CommonCode.FAIL,null);
        }
        Autoupdate autoupdate = autoupdateMapper.findUpgradeinfoById(id);
        //如果没有查询到，返回错误
        if (autoupdate == null || StringUtils.isEmpty(autoupdate.getUpgradeinfo())){
            return new AutoupdateResult(CommonCode.FAIL_NULL,null);
        }
        return new AutoupdateResult(CommonCode.SUCCESS,autoupdate);
    }


}
