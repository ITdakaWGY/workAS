package com.as.occupationaldseases.service;

import com.as.occupationaldseases.common.response.CommonCode;
import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.common.response.QueryResult;
import com.as.occupationaldseases.dao.GrjbxxZybMapper;
import com.as.occupationaldseases.domain.grjbxx.Grjbxx_zyb;
import com.as.occupationaldseases.domain.grjbxx.Grjbxx_zys;
import com.as.occupationaldseases.domain.grjbxx_ys.Grjbxx_ys;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GrjbxxZybService {
    @Resource
    private GrjbxxZybMapper grjbxxZybMapper;
    //查询多条记录
    public QueryResponseResult select(Grjbxx_zyb grjbxxZyb){
        QueryWrapper<Grjbxx_zyb> wrapper = new QueryWrapper<>();
        wrapper.setEntity(grjbxxZyb);
        List<Grjbxx_zyb> grjbxxZybList = grjbxxZybMapper.selectList(wrapper);
        QueryResult queryResult = new QueryResult();
        queryResult.setList(grjbxxZybList);//数据列表
        queryResult.setTotal(grjbxxZybList.size());//数据总记录数
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS,queryResult);
        return queryResponseResult;
    }

}
