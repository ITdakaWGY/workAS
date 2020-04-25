package com.as.occupationaldseases.service;

import com.as.occupationaldseases.common.response.CommonCode;
import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.common.response.QueryResult;
import com.as.occupationaldseases.dao.GrjbxxXmMapper;
import com.as.occupationaldseases.domain.grjbxx.Grjbxx_xm;
import com.as.occupationaldseases.domain.grjbxx.responce.GrjbxxXmResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class GrjbxxXmService {

    @Resource
    GrjbxxXmMapper grjbxxXmMapper;


    public GrjbxxXmResult add(Grjbxx_xm grjbxxXm){
        if (grjbxxXm==null) {
            return  new GrjbxxXmResult(CommonCode.INVALID_PARAM,null);
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("sfz",grjbxxXm.getSfz());
        grjbxxXmMapper.deleteByMap(map);
        grjbxxXmMapper.insert(grjbxxXm);
         return  new GrjbxxXmResult(CommonCode.SUCCESS,null);
    }

    public QueryResponseResult select(Grjbxx_xm grjbxxXm) {
        if (grjbxxXm==null) {
            return  new QueryResponseResult(CommonCode.INVALID_PARAM,null);
        }
            QueryWrapper<Grjbxx_xm> wrapper = new QueryWrapper<>();
            wrapper.setEntity(grjbxxXm);
            List<Grjbxx_xm> grjbxxXmList = grjbxxXmMapper.selectList(wrapper);
            QueryResult queryResult = new QueryResult();
            queryResult.setList(grjbxxXmList);
            queryResult.setTotal(grjbxxXmList.size());
            QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS,queryResult);
            return queryResponseResult;

    }


    public QueryResponseResult selectList(Grjbxx_xm grjbxxXm){
        if (grjbxxXm==null) {
            return  new QueryResponseResult(CommonCode.INVALID_PARAM,null);
        }
        QueryWrapper<Grjbxx_xm> wrapper = new QueryWrapper<>();
        wrapper.setEntity(grjbxxXm);
        List<Grjbxx_xm> grjbxxXmList = grjbxxXmMapper.selectList(wrapper);
        if(grjbxxXmList==null){
            return  new QueryResponseResult(CommonCode.INVALID_PARAM,null);
        }
        String[] jcxmname = grjbxxXmList.get(0).getJcxmname().split(",");

        List<Object> list = new ArrayList<>();
        for (String s : jcxmname) {
            LinkedHashMap<String,Object> param = new LinkedHashMap<>();
            LinkedHashMap<String,Object> params = new LinkedHashMap<>();
                param.clear();
                param.put("name",s);
                param.put("no",grjbxxXmList.get(0).getTmh());
                list.add(param);
                params.clear();
                params.put("name","");
                params.put("no","");
                list.add(params);
        }

        QueryResult queryResult = new QueryResult();
        queryResult.setList(list);
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS,queryResult);
        return queryResponseResult;
    }

}
