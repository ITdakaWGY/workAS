package com.as.occupationaldseases.service;

import com.as.occupationaldseases.common.response.CommonCode;
import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.common.response.QueryResult;
import com.as.occupationaldseases.dao.CompanyinfoMapper;
import com.as.occupationaldseases.dao.SigninfoMapper;
import com.as.occupationaldseases.dao.SysOrgMapper;
import com.as.occupationaldseases.domain.companyinfo.Company_Sign;
import com.as.occupationaldseases.domain.companyinfo.Companyinfo;
import com.as.occupationaldseases.domain.companyinfo.responce.CompanyinfoCode;
import com.as.occupationaldseases.domain.companyinfo.responce.CompanyinfoResult;
import com.as.occupationaldseases.domain.signinfo.Signinfo;
import com.as.occupationaldseases.domain.sysOrg.SysOrg;
import com.as.occupationaldseases.exception.ExceptionCast;
import com.as.occupationaldseases.utils.FileUtil;
import com.as.occupationaldseases.utils.StringUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyinfoService {

    @Resource
    private CompanyinfoMapper companyinfoMapper;

    @Resource
    private SysOrgMapper sysOrgMapper;

    @Resource
    private SigninfoMapper signinfoMapper;

    @Value("${newpany}")
    private String newpany;

    @Value("${company}")
    private String path;



    public CompanyinfoResult add(Company_Sign companySign){

        if (companySign == null) {
            //抛出异常，非法参数异常..指定异常信息的内容
            return new CompanyinfoResult(CommonCode.INVALID_PARAM, null);
        }
        Companyinfo companyinfo = companySign.getCompanyinfo();
        Signinfo signinfo = companySign.getSigninfo();
        //校验用户Id的唯一性
        //根据用户Id集合，如果查到说明此用户已经存在，如果查询不到再继续添加
        Companyinfo companyinfo1 = companyinfoMapper.selectById(companyinfo.getId());
        Signinfo signinfo1 = signinfoMapper.selectById(signinfo.getId());
        if (companyinfo1 != null|| signinfo1!=null) {
            //用户已经存在
            //抛出异常，异常内容就是用户已经存在
            ExceptionCast.cast(CompanyinfoCode.COMPANYINFO_ADD_EXISTSNAME);
        }
        Integer row = companyinfoMapper.maxCode();
        if(row==null||row ==0){
            row=1000;
        }
        companyinfo.setCompanycode(String.valueOf(row + 1));
        signinfo.setCompanycode(String.valueOf(row + 1));
        if(companyinfo.getOrgcode()!=null&&companyinfo.getOrgcode().length()>0){
            SysOrg sysOrg = new SysOrg();
            sysOrg.setOrgno(companyinfo.getOrgcode());
            QueryWrapper<SysOrg> wrapper = new QueryWrapper<>();
            wrapper.setEntity(sysOrg);
            SysOrg sysOrg1 = sysOrgMapper.selectOne(wrapper);
            companyinfo.setOrgname(sysOrg1.getOrgname());
        }
        try {
        if(StringUtil.nonNullRequired(companyinfo.getHarmevaluatenamecode())){
                File file1 = new File(newpany+"/"+companyinfo.getCompanycode());
                if(!file1.exists()){
                    file1.mkdir();
                }
                String url = FileUtil.copeFile(companyinfo.getHarmevaluatenamecode(),newpany,companyinfo.getCompanycode()+"/"+companyinfo.getCompanycode());
                companyinfo.setHarmevaluatenamecode(url);
        }

        if(StringUtil.nonNullRequired(signinfo.getProtocol())){
                File file1 = new File(newpany+"/"+companyinfo.getCompanycode());
                if(!file1.exists()){
                    file1.mkdir();
                }
                String url = FileUtil.copeFile(signinfo.getProtocol(),newpany,companyinfo.getCompanycode()+"/"+companyinfo.getCompanycode());
                signinfo.setProtocol(url);
        }

        if(StringUtil.nonNullRequired(signinfo.getQtcl())){
                File file1 = new File(newpany+"/"+companyinfo.getCompanycode());
                if(!file1.exists()){
                    file1.mkdir();
                }
                String url = FileUtil.copeFile(signinfo.getQtcl(),newpany,companyinfo.getCompanycode()+"/"+companyinfo.getCompanycode());
                signinfo.setQtcl(url);
            }

        } catch (IOException e) {
            e.printStackTrace();
            ExceptionCast.cast(CompanyinfoCode.COMPANYINFO_FILE);
        }
        //调用dao新增用户
        companyinfoMapper.insert(companyinfo);
        signinfoMapper.insert(signinfo);
        File file = new File(path);
        FileUtil.deleteFileOrDir(file);
        return new CompanyinfoResult(CommonCode.SUCCESS, companyinfo);
    }


    //根据id修改
    public CompanyinfoResult update(int id, Companyinfo companyinfo) {
        //根据id从数据库查询页面信息
        Companyinfo one = companyinfoMapper.selectById(id);
        if (one != null) {
            if(companyinfo.getOrgcode()!=null){
                SysOrg sysOrg = new SysOrg();
                sysOrg.setOrgno(companyinfo.getOrgcode());
                QueryWrapper<SysOrg> wrapper = new QueryWrapper<>();
                wrapper.setEntity(sysOrg);
                SysOrg sysOrg1 = sysOrgMapper.selectOne(wrapper);
                companyinfo.setOrgname(sysOrg1.getOrgname());
            }
            if(StringUtil.nonNullRequired(one.getHarmevaluatenamecode())&&!(one.getHarmevaluatenamecode().equals(companyinfo.getHarmevaluatenamecode()))){
                File file = new File(one.getHarmevaluatenamecode().replace("newpany/",newpany));
                FileUtil.deleteFileOrDir(file);
                try {
                    String url = FileUtil.copeFile(companyinfo.getHarmevaluatenamecode(),newpany,one.getCompanycode());
                    companyinfo.setHarmevaluatenamecode(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //提交修改
            companyinfoMapper.updateById(companyinfo);
            File file = new File(path);
            FileUtil.deleteFileOrDir(file);
            return new CompanyinfoResult(CommonCode.SUCCESS, companyinfo);
        }
        //修改失败
        return new CompanyinfoResult(CompanyinfoCode.COMPANYINFO_NOTEXISTS, null);
    }


    //根据id删除
    public CompanyinfoResult delete(int id) {
        //先查询一下
        Companyinfo companyinfo = companyinfoMapper.selectById(id);
        if (companyinfo != null) {
            if(StringUtil.nonNullRequired(companyinfo.getHarmevaluatenamecode())) {
                File file = new File(newpany+"/"+companyinfo.getCompanycode());
                FileUtil.deleteFileOrDir(file);
            }
            QueryWrapper<Signinfo> wrapper = new QueryWrapper<>();
            signinfoMapper.delete(wrapper.eq("companycode", companyinfo.getCompanycode()));

            companyinfoMapper.deleteById(id);
            return new CompanyinfoResult(CommonCode.SUCCESS, companyinfo);
        }
        return new CompanyinfoResult(CompanyinfoCode.COMPANYINFO_NOTEXISTS, null);
    }


    /**
     * 分页查询方法
     *
     * @param current     页码，从1开始记数
     * @param size        每页记录数
     * @param companyinfo 查询条件
     * @return
     */
    public QueryResponseResult findList(int current, int size, Companyinfo companyinfo) {
        if (companyinfo == null) {
            companyinfo = new Companyinfo();
        }
        //分页参数
        if (current <= 0) {
            current = 1;
        }
//        current = current - 1;

        if (size <= 0) {
            size = 10;
        }
        IPage<Companyinfo> companyinfoIPage = null;
        if(companyinfo!=null&&companyinfo.getCompanyname()!=null){
            IPage<Companyinfo> page = new Page<>(current, size);
            QueryWrapper<Companyinfo> wrapper = new QueryWrapper<>();
            companyinfoIPage = companyinfoMapper.selectPage(page, wrapper.like("companyname", companyinfo.getCompanyname()).orderByDesc("id"));
        }else{
            IPage<Companyinfo> page = new Page<>(current, size);
            QueryWrapper<Companyinfo> wrapper = new QueryWrapper<>();
            wrapper.setEntity(companyinfo);
            companyinfoIPage = companyinfoMapper.selectPage(page, wrapper.orderByDesc("id"));
        }


        QueryResult queryResult = new QueryResult();

        queryResult.setList(companyinfoIPage.getRecords());//数据列表
        queryResult.setTotal(companyinfoIPage.getTotal());//数据总记录数
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS, queryResult);
        return queryResponseResult;
    }


    /**
     * 查询方法
     * @return
     */
    public QueryResponseResult selectList(Companyinfo companyinfo) {
        if (companyinfo == null) {
            companyinfo = new Companyinfo();
        }
        QueryWrapper<Companyinfo> wrapper = new QueryWrapper<>();
        wrapper.setEntity(companyinfo);
        List<Companyinfo> companyinfos = companyinfoMapper.selectList(wrapper.orderByDesc("id"));
        QueryResult queryResult = new QueryResult();
        queryResult.setList(companyinfos);//数据列表
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS, queryResult);
        return queryResponseResult;
    }


    //查询单条数据
    public CompanyinfoResult findBySingle(Companyinfo companyinfo) {
        QueryWrapper<Companyinfo> wrapper = new QueryWrapper<>();
        wrapper.setEntity(companyinfo);
        Integer row = companyinfoMapper.selectCount(wrapper);
        if (row == 1) {
            return new CompanyinfoResult(CommonCode.SUCCESS, companyinfoMapper.selectOne(wrapper));
        } else if (row > 1) {
            return new CompanyinfoResult(CompanyinfoCode.COMPANYINFO_GREATERTHAN, null);
        }
        return new CompanyinfoResult(CompanyinfoCode.COMPANYINFO_NOTEXISTS, null);
    }


    //根据条件查询数据条数
    public Integer count(Companyinfo companyinfo) {
        QueryWrapper<Companyinfo> wrapper = new QueryWrapper<>();
        wrapper.setEntity(companyinfo);
        return companyinfoMapper.selectCount(wrapper);
    }


    public String fileUpload(MultipartFile files, String path) {

            String route = "";
            String name = files.getOriginalFilename();
            //获取文件后缀名
            String suffix = name.substring(name.lastIndexOf("."));
            String str = System.currentTimeMillis()+suffix;
            //文件的存储路径
            String newName = path+str;
            if (newName!=null)
                route = newName;
            try {
                File file = new File(path);
                FileUtil.createFolder(file);
                InputStream inputStream = files.getInputStream();
                File file1 = new File(newName);
                files.transferTo(file1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return  route;
    }







}
