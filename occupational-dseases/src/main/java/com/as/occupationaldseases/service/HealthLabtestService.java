package com.as.occupationaldseases.service;

import com.as.occupationaldseases.common.response.CommonCode;
import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.common.response.QueryResult;
import com.as.occupationaldseases.dao.HealthLabtestMapper;
import com.as.occupationaldseases.domain.healthLabtest.HealthLabtest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HealthLabtestService {

    @Resource
    private HealthLabtestMapper healthLabtestMapper;


    /**
     * 查询方法
     * @return
     */
    public QueryResponseResult selectList(HealthLabtest healthLabtest) {
        QueryWrapper<HealthLabtest> wrapper = new QueryWrapper<>();
        wrapper.setEntity(healthLabtest);
        List<HealthLabtest> hazardinfos = healthLabtestMapper.selectList(wrapper);
        QueryResult queryResult = new QueryResult();
        Integer row = healthLabtestMapper.selectCount(wrapper);
        queryResult.setList(hazardinfos);//数据列表
        queryResult.setTotal(row);//数据总记录数
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS, queryResult);
        return queryResponseResult;
    }
}
