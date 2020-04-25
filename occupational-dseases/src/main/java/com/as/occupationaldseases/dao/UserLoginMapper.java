package com.as.occupationaldseases.dao;

import com.as.occupationaldseases.domain.userLogin.UserLogin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserLoginMapper extends BaseMapper<UserLogin> {

    @Update("update userlogin set password=#{newPassword} where yhbh = #{yhbh}")
    int updatePassword(String yhbh, String newPassword);
}
