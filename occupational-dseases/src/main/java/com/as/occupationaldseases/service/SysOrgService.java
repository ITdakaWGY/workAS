package com.as.occupationaldseases.service;

import com.as.occupationaldseases.common.response.CommonCode;
import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.common.response.QueryResult;
import com.as.occupationaldseases.dao.SysOrgMapper;
import com.as.occupationaldseases.domain.sysOrg.SysOrg;
import com.as.occupationaldseases.domain.sysOrg.responce.SysOrgCode;
import com.as.occupationaldseases.domain.sysOrg.responce.SysOrgResult;
import com.as.occupationaldseases.exception.ExceptionCast;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysOrgService {

    @Resource
    private SysOrgMapper sysOrgMapper;

    public SysOrgResult add(SysOrg sysOrg) {
        if (sysOrg == null) {
            //抛出异常，非法参数异常..指定异常信息的内容
            return new SysOrgResult(CommonCode.INVALID_PARAM, null);
        }
        //校验用户Id的唯一性
        //根据用户Id集合，如果查到说明此用户已经存在，如果查询不到再继续添加
        SysOrg sysOrg1 = sysOrgMapper.selectById(sysOrg.getId());
        if (sysOrg1 != null) {
            //用户已经存在
            //抛出异常，异常内容就是用户已经存在
            ExceptionCast.cast(SysOrgCode.SYSORG_ADD_EXISTSNAME);
        }
        Integer row = sysOrgMapper.maxOrg();
        if(row==null||row==0){
            row=1000;
        }
        sysOrg.setOrgno(String.valueOf(row+1));
        //调用dao新增用户
        sysOrgMapper.insert(sysOrg);
        return new SysOrgResult(CommonCode.SUCCESS, sysOrg);

    }

    //根据id修改
    public SysOrgResult update(int id, SysOrg sysOrg) {
        //根据id从数据库查询页面信息
        SysOrg one = sysOrgMapper.selectById(id);
        if (one != null) {
            //提交修改
            sysOrgMapper.updateById(sysOrg);
            return new SysOrgResult(CommonCode.SUCCESS, sysOrg);
        }
        //修改失败
        return new SysOrgResult(SysOrgCode.SYSORG_NOTEXISTS, null);
    }


    //根据id删除用户
    public SysOrgResult delete(Long id) {
        //先查询一下
        SysOrg sysOrg = sysOrgMapper.selectById(id);
        if (sysOrg != null) {
            sysOrgMapper.deleteById(id);
            return new SysOrgResult(CommonCode.SUCCESS, sysOrg);
        }
        return new SysOrgResult(SysOrgCode.SYSORG_NOTEXISTS, null);
    }

    /**
     * 用户查询方法
     *
     * @param current 页码，从1开始记数
     * @param size    每页记录数
     * @param sysOrg    查询条件
     * @return
     */
    public QueryResponseResult findList(int current, int size, SysOrg sysOrg) {
        if (sysOrg == null) {
            sysOrg = new SysOrg();
        }

        //分页参数
        if (current <= 0) {
            current = 1;
        }
//        current = current - 1;
        if (size <= 0) {
            size = 10;
        }


        IPage<SysOrg> sysOrgIPage = null;
        if(sysOrg!=null&&sysOrg.getOrgname()!=null){
            /**
             * wrapper.like参数设置为模糊查询
             */
            IPage<SysOrg> page = new Page<>(current, size);
            QueryWrapper<SysOrg> wrapper = new QueryWrapper<>();
            sysOrgIPage = sysOrgMapper.selectPage(page, wrapper.like("ORGNAME",sysOrg.getOrgname()).orderByDesc("id"));
        }else{
            IPage<SysOrg> page = new Page<>(current, size);
            QueryWrapper<SysOrg> wrapper = new QueryWrapper<>();
            wrapper.setEntity(sysOrg);
            sysOrgIPage = sysOrgMapper.selectPage(page, wrapper.orderByDesc("id"));
        }

        QueryResult queryResult = new QueryResult();

        queryResult.setList(sysOrgIPage.getRecords());//数据列表
        queryResult.setTotal(sysOrgIPage.getTotal());//数据总记录数
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS, queryResult);
        return queryResponseResult;
    }

    /**
     * 查询方法
     * @return
     */
    public QueryResponseResult selectList(SysOrg sysOrg) {
        if (sysOrg == null) {
            sysOrg = new SysOrg();
        }
        QueryWrapper<SysOrg> wrapper = new QueryWrapper<>();
        wrapper.setEntity(sysOrg);
        List<SysOrg> companyinfos = sysOrgMapper.selectList(wrapper);
        QueryResult queryResult = new QueryResult();
        queryResult.setList(companyinfos);//数据列表
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS, queryResult);
        return queryResponseResult;
    }


    public SysOrgResult findBySingle(SysOrg sysOrg) {
        QueryWrapper<SysOrg> wrapper = new QueryWrapper<>();
        wrapper.setEntity(sysOrg);
        Integer row = sysOrgMapper.selectCount(wrapper);
        if (row == 1) {
            return new SysOrgResult(CommonCode.SUCCESS, sysOrgMapper.selectOne(wrapper));
        } else if (row > 1) {
            return new SysOrgResult(SysOrgCode.SYSORG_GREATERTHAN, null);
        }
        return new SysOrgResult(SysOrgCode.SYSORG_NOTEXISTS, null);
    }

}
