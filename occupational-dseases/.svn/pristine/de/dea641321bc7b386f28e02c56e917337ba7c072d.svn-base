package com.as.occupationaldseases.service;

import com.as.occupationaldseases.common.response.CommonCode;
import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.common.response.QueryResult;
import com.as.occupationaldseases.dao.GrjbxxWsMapper;
import com.as.occupationaldseases.domain.grjbxx.Grjbxx_ws;
import com.as.occupationaldseases.domain.grjbxx.responce.GrjbxxWsResult;
import com.as.occupationaldseases.domain.user.responce.UserCode;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
public class GrjbxxWsService {



        @Resource
        private GrjbxxWsMapper grjbxxWsMapper;

    /**
     * 新增
     * @param grjbxxWs
     * @return
     */
    public GrjbxxWsResult add(Grjbxx_ws grjbxxWs) {
            if(grjbxxWs == null){
                //抛出异常，非法参数异常..指定异常信息的内容
                return new GrjbxxWsResult(CommonCode.INVALID_PARAM,null);
            }
        System.out.println(grjbxxWs);
        HashMap<String, Object> map = new HashMap<>();
        map.put("personalid",grjbxxWs.getPersonalid());
        grjbxxWsMapper.deleteByMap(map);
        if (grjbxxWs.getSfws().contains("1")) {
            //调用dao新增
            grjbxxWs.setId(null);//id自增
            grjbxxWsMapper.insert(grjbxxWs);
            return new GrjbxxWsResult(CommonCode.SUCCESS,grjbxxWs);
        }
        String[] wsmc = grjbxxWs.getWsmc().split(",");
        String[] wssj = grjbxxWs.getWssj().split(",");
        for (int i = 0; i < wsmc.length; i++) {
            Grjbxx_ws grjbxx_ws = new Grjbxx_ws();
            grjbxx_ws.setId(null);
            grjbxx_ws.setPersonalid(grjbxxWs.getPersonalid());
            grjbxx_ws.setWsmc(wsmc[i]);
            grjbxx_ws.setWssj(wssj[i]);
            grjbxx_ws.setSfws("2");
            grjbxxWsMapper.insert(grjbxx_ws);

        }
        return new GrjbxxWsResult(CommonCode.SUCCESS,grjbxxWs);

        }

    /**
     * 修改
     * @param id
     * @param grjbxxWs
     * @return
     */
        public GrjbxxWsResult update(Long id,Grjbxx_ws grjbxxWs){
            //根据id从数据库查询页面信息
            Grjbxx_ws one = grjbxxWsMapper.selectById(id);
            if(one!=null){
                //提交修改
                grjbxxWsMapper.updateById(grjbxxWs);
                return new GrjbxxWsResult(CommonCode.SUCCESS,grjbxxWs);
            }
            //修改失败
            return new GrjbxxWsResult(UserCode.USER_NOTEXISTS,null);

        }

    /**
     * 删除
     * @param grjbxx_ws
     * @return
     */
    public GrjbxxWsResult delete(Grjbxx_ws grjbxx_ws){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.setEntity(grjbxx_ws);
            //先查询一下
            Grjbxx_ws grjbxxWs= grjbxxWsMapper.selectOne(wrapper);
            if (grjbxxWs!=null){
                wrapper.setEntity(grjbxxWs);
                grjbxxWsMapper.delete(wrapper);
                return new GrjbxxWsResult(CommonCode.SUCCESS,grjbxxWs);
            }
            return new GrjbxxWsResult(UserCode.USER_NOTEXISTS,null);
        }

    /**
     * 查询
     * @param grjbxxWs
     * @return
     */
    public QueryResponseResult select(Grjbxx_ws grjbxxWs){
        QueryWrapper<Grjbxx_ws> wrapper = new QueryWrapper<>();
        wrapper.setEntity(grjbxxWs);
        List<Grjbxx_ws> grjbxxWsList = grjbxxWsMapper.selectList(wrapper);
        QueryResult queryResult = new QueryResult();
        queryResult.setList(grjbxxWsList);//数据列表
        queryResult.setTotal(grjbxxWsList.size());//数据总记录数
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS,queryResult);
        return queryResponseResult;

    }




}
