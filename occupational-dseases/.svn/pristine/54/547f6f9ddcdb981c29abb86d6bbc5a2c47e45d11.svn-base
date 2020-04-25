package com.as.occupationaldseases.service;

import com.as.occupationaldseases.common.response.CommonCode;
import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.common.response.QueryResult;
import com.as.occupationaldseases.dao.UserMapper;
import com.as.occupationaldseases.domain.grjbxx.Grjbxx;
import com.as.occupationaldseases.domain.user.User;
import com.as.occupationaldseases.domain.user.responce.UserCode;
import com.as.occupationaldseases.domain.user.responce.UserResult;
import com.as.occupationaldseases.exception.ExceptionCast;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class UserService {

    @Resource
    private UserMapper userMapper;
    //新增用户
    public UserResult add(User user) {
        if(user == null){
            //抛出异常，非法参数异常..指定异常信息的内容
            return new UserResult(CommonCode.INVALID_PARAM,null);
        }
        //校验用户Id的唯一性
        //根据用户Id集合，如果查到说明此用户已经存在，如果查询不到再继续添加
        User user1 = userMapper.selectById(user.getId());
        if(user1!=null){
            //用户已经存在
            //抛出异常，异常内容就是用户已经存在
            ExceptionCast.cast(UserCode.USER_ADD_EXISTSNAME);
        }

        //调用dao新增用户
        user.setId(null);//id自增
        userMapper.insert(user);
        return new UserResult(CommonCode.SUCCESS,user);

    }

    //修改用户
    public UserResult update(Long id,User user){
        //根据id从数据库查询页面信息
        User one = userMapper.selectById(id);
        if(one!=null){
            //提交修改
            userMapper.updateById(user);
            return new UserResult(CommonCode.SUCCESS,user);
        }
        //修改失败
        return new UserResult(UserCode.USER_NOTEXISTS,null);

    }

    //根据id删除用户
    public UserResult delete(Long id){
        //先查询一下
        User user = userMapper.selectById(id);
        if (user!=null){
            userMapper.deleteById(id);
            return new UserResult(CommonCode.SUCCESS,user);
        }
        return new UserResult(UserCode.USER_NOTEXISTS,null);
    }

    /**
     * 用户查询方法
     * @param current 页码，从1开始记数
     * @param size 每页记录数
     * @param user 查询条件
     * @return
     */
    public QueryResponseResult findList(int current, int size, User user){
        if(user == null){
            user = new User();
        }

        //分页参数
        if(current <=0){
            current = 1;
        }
        current = current -1;
        if(size<=0){
            size = 10;
        }

        IPage<User> page = new Page<>(current, size);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.setEntity(user);

        IPage<User> userIPage = userMapper.selectPage(page, wrapper);

        QueryResult queryResult = new QueryResult();

        queryResult.setList(userIPage.getRecords());//数据列表
        queryResult.setTotal(userIPage.getTotal());//数据总记录数
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS,queryResult);
        return queryResponseResult;
    }

    public List<User> queryAllByXml(User user){
        //先查询一下


        List<User> users = userMapper.queryAllByXml(user);

        return users;
    }

}
