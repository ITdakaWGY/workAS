package com.as.occupationaldseases.service;

import com.as.occupationaldseases.common.response.CommonCode;
import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.common.response.QueryResult;
import com.as.occupationaldseases.dao.TjHazardJobMapper;
import com.as.occupationaldseases.domain.tjHazardJob.TjHazardJob;
import com.as.occupationaldseases.domain.tjHazardJob.responce.TjHazardJobCode;
import com.as.occupationaldseases.domain.tjHazardJob.responce.TjHazardJobResult;
import com.as.occupationaldseases.exception.ExceptionCast;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TjHazardJobService {
    @Resource
    private TjHazardJobMapper tjHazardJobMapper;

    //新增
    public TjHazardJobResult add(TjHazardJob tjHazardJob) {
        if (tjHazardJob == null) {
            //抛出异常，非法参数异常..指定异常信息的内容
            return new TjHazardJobResult(CommonCode.INVALID_PARAM, null);
        }

        //校验用户Id的唯一性
        //根据用户Id集合，如果查到说明此用户已经存在，如果查询不到再继续添加
        TjHazardJob tjHazardItem1 = tjHazardJobMapper.selectById(tjHazardJob.getId());
        if (tjHazardItem1 != null) {
            //用户已经存在
            //抛出异常，异常内容就是用户已经存在
            ExceptionCast.cast(TjHazardJobCode.TJHAZARDJOB_ADD_EXISTSNAME);
        }
        //调用dao新增用户
        tjHazardJobMapper.insert(tjHazardJob);
        return new TjHazardJobResult(CommonCode.SUCCESS, tjHazardJob);
    }

    /**
     * 分页查询方法
     *
     * @param current     页码，从1开始记数
     * @param size        每页记录数
     * @param tjHazardJob 查询条件
     * @return
     */
    public QueryResponseResult findList(int current, int size, TjHazardJob tjHazardJob) {
        if (tjHazardJob == null) {
            tjHazardJob = new TjHazardJob();
        }
        //分页参数
        if (current <= 0) {
            current = 1;
        }
//        current = current - 1;

        if (size <= 0) {
            size = 10;
        }
        IPage<TjHazardJob> page = new Page<>(current, size);
        QueryWrapper<TjHazardJob> wrapper = new QueryWrapper<>();
        wrapper.setEntity(tjHazardJob);

        IPage<TjHazardJob> companyinfoIPage = tjHazardJobMapper.selectPage(page, wrapper);
        QueryResult queryResult = new QueryResult();

        queryResult.setList(companyinfoIPage.getRecords());//数据列表
        queryResult.setTotal(companyinfoIPage.getTotal());//数据总记录数
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS, queryResult);
        return queryResponseResult;
    }



    //根据id删除
    public TjHazardJobResult delete(TjHazardJob tjHazardJob) {
        //先查询一下
        QueryWrapper<TjHazardJob> wrapper = new QueryWrapper<>();
        wrapper.setEntity(tjHazardJob);
        TjHazardJob tjHazardIob1 = tjHazardJobMapper.selectOne(wrapper);
        if (tjHazardIob1 != null) {
            tjHazardJobMapper.delete(wrapper);
            return new TjHazardJobResult(CommonCode.SUCCESS, tjHazardJob);
        }
        return new TjHazardJobResult(TjHazardJobCode.TJHAZARDJOB_NOTEXISTS, null);
    }
}
