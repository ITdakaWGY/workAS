package com.as.occupationaldseases.dao;

import com.as.occupationaldseases.domain.user.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    //xml配置
    List<User> queryAllByXml(User user);

    List<User> queryAllByXmlMap(Map map);

}
