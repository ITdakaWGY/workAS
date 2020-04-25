package com.as.occupationaldseases.service;

import com.as.occupationaldseases.common.response.CommonCode;
import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.common.response.QueryResult;
import com.as.occupationaldseases.dao.RoleMapper;
import com.as.occupationaldseases.dao.UserLoginMapper;
import com.as.occupationaldseases.domain.role.Role;
import com.as.occupationaldseases.domain.userLogin.UserLogin;
import com.as.occupationaldseases.domain.userLogin.responce.UserLoginCode;
import com.as.occupationaldseases.domain.userLogin.responce.UserLoginResult;
import com.as.occupationaldseases.utils.FileUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserLoginService {

    @Resource
    private UserLoginMapper userLoginMapper;







    /**
     * 上传头像
     * @param file 图片对象
     * @param path 上传的位置
     * @return
     */
    public  String  upload(MultipartFile file,String path){
        //获取图片名称
        String filename = file.getOriginalFilename();
        //获取图片后缀
        String suffix = filename.substring(filename.lastIndexOf("."));
        //生成新的图片名称
        String newName = System.currentTimeMillis()+suffix;
        //生成新的图片路径
        StringBuffer buffer = new StringBuffer().append(path).append(newName.trim());
        //创建文件的路径
        File file1 = new File(buffer.toString());
        if (!file1.exists()&&!file1.isDirectory())
            file1.mkdirs();
        try {
            file.transferTo(file1);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return buffer.toString();
    }



    /**
     * 新增
     * @param userLogin
     * @return
     */
    public UserLoginResult add(UserLogin userLogin,String ptbh){

        if (userLogin==null)
            return new UserLoginResult(CommonCode.INVALID_PARAM,null);
        HashMap<String, Object> map = new HashMap<>();
        map.put("name",userLogin.getName());
        List<UserLogin> userLogins = userLoginMapper.selectByMap(map);
        if (userLogins!=null&&userLogins.size()>0)
            return new UserLoginResult(UserLoginCode.UserLogin_TEXISTS,null);
        if (userLogin.getRes0()!=null&&userLogin.getRes0().length()>0) {
            try{
                System.out.println("临时文件路径："+userLogin.getRes0());
                //写入文件
              String   newPtbh =    FileUtil.copeFile(userLogin.getRes0(), ptbh,userLogin.getYhbh());
                //删除临时文件
                 deleteFile(userLogin.getRes0());
                System.out.println("新的文件路径 ："+newPtbh);
                userLogin.setRes0(newPtbh);
            }catch (Exception e){

            }



        }


        userLogin.setId(null);
        userLogin.setPassword(addOrSolution(userLogin.getPassword()));
        userLoginMapper.insert(userLogin);

        return new UserLoginResult(CommonCode.SUCCESS,userLogin);
    }

    /**
     * 修改
     * @param id
     * @param userLogin
     * @return
     */
    public UserLoginResult update(Long id, UserLogin userLogin,String newheadPortrait){
        UserLogin userLogin1 = userLoginMapper.selectById(id);
        if (userLogin1!=null) {
            userLogin.setPassword(addOrSolution(userLogin.getPassword()));
            if (userLogin1.getRes0()!=null&&userLogin1.getRes0().length()>0)
                HandleFile(userLogin1,userLogin,newheadPortrait);
            userLoginMapper.updateById(userLogin);
            return new UserLoginResult(CommonCode.SUCCESS,userLogin);
        }
        return  new UserLoginResult(CommonCode.FAIL,null);
    }

    /**
     * 处理图片路径
     * @param userLogin  原对象
     * @param userLogin2 新对象
     * @param newPath  文件存储的地址
     */
    private void  HandleFile(UserLogin userLogin,UserLogin userLogin2,String newPath){
        System.out.println("新对象："+userLogin2);
        System.out.println("旧对象："+userLogin);
        if (userLogin2.getRes0().equals(userLogin.getRes0()))
             return;
            //获取旧文件的名称
            String usedName = new File(userLogin.getRes0()).getName();
            //删除临时文件
            FileUtil.deleteFileOrDir(new File(newPath+usedName));
            try {
                //生成新的路径
                String   pabh =    FileUtil.copeFile(userLogin2.getRes0(), newPath,userLogin2.getYhbh());
                //删除临时文件
                deleteFile(userLogin2.getRes0());
                userLogin2.setRes0(pabh);
            } catch (IOException e) {
                e.printStackTrace();
            }


    }

    /**
     * 删除临时文件
     * @param path
     */
    private void  deleteFile(String path){
        //获取临时文件名称
        String name = new File(path).getName();
        //剔除临时文件名称
        String   temporaryPath= path.replace(name,"");
        //删除临时文件
        FileUtil.deleteFileOrDir(new File(temporaryPath));
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public UserLoginResult delete(Long id){
        UserLogin userLogin = userLoginMapper.selectById(id);
        if (userLogin!=null) {
            userLoginMapper.deleteById(id);
            return  new UserLoginResult(CommonCode.SUCCESS,userLogin);
        }

        return  new UserLoginResult(CommonCode.FAIL,null);
    }

    /**
     * 查询
     * @param userLogin
     * @return
     */
    public UserLoginResult select(UserLogin userLogin) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name",userLogin.getName());
        List<UserLogin> userLogins = userLoginMapper.selectByMap(map);
        //判断用户是否存在
        if(userLogins.size()==0||userLogins==null)
            return new UserLoginResult(UserLoginCode.UserLogin_NOTEXISTS,null);


        for (UserLogin userLoginList:userLogins) {

            //判断用户是否启用
            if (!userLoginList.getUserState().equals(userLogin.getUserState()))
                return new UserLoginResult(UserLoginCode.USER_LOGIN_NOT_ENABLED,null);
            //判断密码是否正确
            if (addOrSolution(userLoginList.getPassword()).equals(userLogin.getPassword())){
                return new UserLoginResult(CommonCode.SUCCESS,userLogins.get(0));
            }


        }
        //密码错误返回
        return  new UserLoginResult(UserLoginCode.UserLogin_NOCORRECT_PASSWORD,null);

    }

    public QueryResponseResult findList(int current, int size, UserLogin userLogin) {
        if(userLogin == null){
            userLogin = new UserLogin();
        }

        //分页参数
        if(current <=1){
            current = 0;
        }

        if(size<=0){
            size = 10;
        }
       if("".equals(userLogin.getUserType()))
           userLogin.setUserType(null);
       if("".equals(userLogin.getUserState()))
           userLogin.setUserState(null);
        if("".equals(userLogin.getName()))
            userLogin.setName(null);


        IPage<UserLogin> page = new Page<>(current, size);
        QueryWrapper<UserLogin> wrapper = new QueryWrapper<>();
        wrapper.setEntity(userLogin);

        IPage<UserLogin> userLoginIPage = userLoginMapper.selectPage(page, wrapper);
        if (userLoginIPage!=null&&userLoginIPage.getRecords()!=null&&userLoginIPage.getRecords().size()>0) {
            for (UserLogin userLoginList:userLoginIPage.getRecords())
                userLoginList.setPassword(addOrSolution(userLoginList.getPassword()));
        }

        QueryResult queryResult = new QueryResult();

        queryResult.setList(userLoginIPage.getRecords());//数据列表
        queryResult.setTotal(userLoginIPage.getTotal());//数据总记录数
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS,queryResult);
        return queryResponseResult;
    }


    private static  String addOrSolution(String str){
        char[] strArray= str.toCharArray();
        for (int i = 0; i < strArray.length; i++) {
            strArray[i] =(char)(strArray[i] ^ 't');
        }
        String s = new String(strArray);
        return s;
    }


    /**
     * 修改密码
     * @param yhbh 用户编号
     * @param usedPassword  旧密码
     * @param newPassword  新密码
     * @return
     */
    public UserLoginResult updatePassword(String yhbh, String usedPassword, String newPassword) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("yhbh",yhbh);
        map.put("password",addOrSolution(usedPassword));
        List<UserLogin> userLogins = userLoginMapper.selectByMap(map);
        if (!userLogins.isEmpty()){
         userLoginMapper.updatePassword(yhbh, addOrSolution(newPassword));
            return new UserLoginResult(CommonCode.SUCCESS,null);
        }
         return new UserLoginResult(UserLoginCode.UserLogin_NOCORRECT_USEDPASSWORD,null);

    }
}
