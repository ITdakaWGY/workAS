package com.as.occupationaldseases.service;

import com.as.occupationaldseases.common.response.CommonCode;
import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.common.response.QueryResult;
import com.as.occupationaldseases.dao.SigninfoMapper;
import com.as.occupationaldseases.domain.companyinfo.Companyinfo;
import com.as.occupationaldseases.domain.signinfo.Signinfo;
import com.as.occupationaldseases.domain.signinfo.responce.SigninfoResult;
import com.as.occupationaldseases.domain.user.responce.UserCode;
import com.as.occupationaldseases.utils.FileUtil;
import com.as.occupationaldseases.utils.StringUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Map;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SigninfoService {
   @Resource
   SigninfoMapper signinfoMapper;


    @Value("${newpany}")
    private String newpany;

    @Value("${company}")
    private String path;
    /**
     * 新增
     * @param signinfo
     * @return
     */
   public SigninfoResult  add(Signinfo signinfo){
       if (signinfo==null) {
           return new SigninfoResult(CommonCode.INVALID_PARAM,null);
       }
       //调用新增
       signinfo.setId(null);
       try {
       if(StringUtil.nonNullRequired(signinfo.getProtocol())) {
           String url = FileUtil.copeFile(signinfo.getProtocol(), newpany, signinfo.getCompanycode() + "/" + signinfo.getCompanycode());
           signinfo.setProtocol(url);
       }
       if(StringUtil.nonNullRequired(signinfo.getQtcl())){
           String url = FileUtil.copeFile(signinfo.getQtcl(),newpany,signinfo.getCompanycode()+"/"+signinfo.getCompanycode());
           signinfo.setQtcl(url);
       }
       } catch (IOException e) {
           e.printStackTrace();
       }
       signinfoMapper.insert(signinfo);
       return  new SigninfoResult(CommonCode.SUCCESS,signinfo);
   }



    /**
     * 修改
     * @param id
     * @param signinfo
     * @return
     */
   public SigninfoResult update(Long id,Signinfo signinfo){
       Signinfo signinfo1 = signinfoMapper.selectById(id);
       if (signinfo1!=null) {
           signinfoMapper.updateById(signinfo);
           return  new SigninfoResult(CommonCode.SUCCESS,signinfo);
       }
       //修改失败
       return new SigninfoResult(UserCode.USER_NOTEXISTS,null);
   }

    /**
     * 删除
     * @param id
     * @return
     */
   public SigninfoResult delete(Long id ){

       Signinfo signinfo1 = signinfoMapper.selectById(id);
       if (signinfo1!=null) {
           if(StringUtil.nonNullRequired(signinfo1.getProtocol())) {
               File file1 = new File(newpany+"/"+signinfo1.getCompanycode());
               if(!file1.exists()){
                   file1.mkdir();
               }
               File file = new File(signinfo1.getProtocol().replace("NewCompany/",newpany));
               FileUtil.deleteFileOrDir(file);
           }
           if(StringUtil.nonNullRequired(signinfo1.getQtcl())) {
               File file1 = new File(newpany+"/"+signinfo1.getCompanycode());
               if(!file1.exists()){
                   file1.mkdir();
               }
               File file = new File(signinfo1.getQtcl().replace("NewCompany/",newpany));
               FileUtil.deleteFileOrDir(file);
           }
           signinfoMapper.deleteById(id);
           return new SigninfoResult(CommonCode.SUCCESS,signinfo1);
       }
       return  new SigninfoResult(UserCode.USER_NOTEXISTS,null);
   }

    /**
     * 查询
     * @param signinfo
     * @return
     */

   public  QueryResponseResult select(Signinfo signinfo){
       QueryWrapper<Signinfo> wrapper = new QueryWrapper<>();
       wrapper.setEntity(signinfo);
       List<Signinfo> signinfoList = signinfoMapper.selectList(wrapper);
       QueryResult queryResult = new QueryResult();
       queryResult.setList(signinfoList);//数据列表
       queryResult.setTotal(signinfoList.size());//数据总记录数
       QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS,queryResult);
       return queryResponseResult;
   }


    /**
     * @param current 页码，从1开始记数
     * @param size 每页记录数
     * @param params 条件
     * @return
     */
    public QueryResponseResult findList(int current, int size, Map<String,Object> params){
        //分页参数
        if(current <=1){
            current = 0;
        } if (current > 1)
            current = current*10-10;


        if(size<=0){
            size = 10;
        }

        QueryResult queryResult = new QueryResult();
        queryResult.setTotal(signinfoMapper.queryCount(params));
        String page = current+","+size;
        params.put("page",page);
        List<Signinfo> signinfoList = signinfoMapper.queryStuBaseByXmlPage(params);
        queryResult.setList(signinfoList);

        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS,queryResult);
        return queryResponseResult;
    }

    public String fileUpload(MultipartFile[] files,String qybh,String path) {
        String route = "";
        for (MultipartFile file:files) {
            String name = file.getOriginalFilename();
              //获取文件后缀名
            String suffix = name.substring(name.lastIndexOf("."));
            String str = System.currentTimeMillis()+suffix;
            String newPath = path+qybh;
            //文件的存储路径加上文件
            String newName = newPath+"\\"+str;
            route += newName+",";
            System.out.println("文件的路径是："+newName);
            try {

                File file1 = new File(newName);
                if(!file1.exists()&&!file1.isDirectory()) {
                    file1.mkdirs();
                }
                file.transferTo(file1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("文件名称："+name);
        }
        route = route.substring(0,route.length()-1);
        return  route;
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
