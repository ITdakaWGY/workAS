package com.as.android.dao;

import com.as.android.domain.autoupdate.Autoupdate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface AutoupdateMapper extends BaseMapper<Autoupdate> {


    @Select("select id,appcode,appname,serverVersion,lastForce,updateurl,upgradeinfo,uploadtime from autoupdate where appname = #{appname} ORDER BY id DESC LIMIT 1 ")
    public Autoupdate findNewServerVersionByAppName(String appname);

    @Select("select id from autoupdate where serverVersion = #{serverVersion} and appname = #{appname}")
    public Integer findByServerVersionAndAppName(String serverVersion,String appname);

    @Select("select upgradeinfo from autoupdate where id = #{id}")
    public Autoupdate findUpgradeinfoById(Integer id);

    @Select("select id,appcode,appname,serverVersion,lastForce,updateurl,uploadtime from autoupdate where appID = #{appID} ")
    public List<Autoupdate>  findAllByAppID(Integer appID);

    @Select("select id,appcode,appname,serverVersion,lastForce,updateurl,uploadtime from autoupdate")
    public List<Autoupdate> findAll();


}
