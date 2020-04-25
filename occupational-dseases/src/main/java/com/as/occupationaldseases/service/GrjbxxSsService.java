package com.as.occupationaldseases.service;

import com.as.occupationaldseases.common.response.CommonCode;
import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.common.response.QueryResult;
import com.as.occupationaldseases.domain.grjbxx.Grjbxx_ss;
import com.as.occupationaldseases.domain.grjbxx.responce.GrjbxxSsResult;
import com.as.occupationaldseases.domain.user.responce.UserCode;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import com.as.occupationaldseases.dao.GrjbxxSsMapper;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
public class GrjbxxSsService {



        @Resource
        private  GrjbxxSsMapper grjbxxSsMapper;

    /**
     * 新增
     * @param grjbxxSs
     * @return
     */
    public GrjbxxSsResult add(Grjbxx_ss grjbxxSs) {
            if(grjbxxSs == null){
                //抛出异常，非法参数异常..指定异常信息的内容
                return new GrjbxxSsResult(CommonCode.INVALID_PARAM,null);
            }
        HashMap<String, Object> map = new HashMap<>();
        map.put("personalid",grjbxxSs.getPersonalid());
        grjbxxSsMapper.deleteByMap(map);
            if (grjbxxSs.getSfss().equals("1")) {
                //调用dao新增
                grjbxxSs.setId(null);//id自增
                grjbxxSsMapper.insert(grjbxxSs);
                return new GrjbxxSsResult(CommonCode.SUCCESS,grjbxxSs);
            }
        String[] ssmc = grjbxxSs.getSsmc().split(",");
        String[] sssj = grjbxxSs.getSssj().split(",");
        for (int i = 0; i < ssmc.length; i++) {
            Grjbxx_ss grjbxx_ss = new Grjbxx_ss();
            grjbxx_ss.setId(null);
            grjbxx_ss.setSfss("2");
            grjbxx_ss.setSsmc(ssmc[i]);
            grjbxx_ss.setSssj(sssj[i]);
            grjbxx_ss.setPersonalid(grjbxxSs.getPersonalid());
            grjbxxSsMapper.insert(grjbxx_ss);
        }

        return new GrjbxxSsResult(CommonCode.SUCCESS,grjbxxSs);

        }

    /**
     * 修改
     * @param id
     * @param grjbxxSs
     * @return
     */
        public GrjbxxSsResult update(Long id,Grjbxx_ss grjbxxSs){
            //根据id从数据库查询页面信息
            Grjbxx_ss one = grjbxxSsMapper.selectById(id);
            if(one!=null){
                //提交修改
                grjbxxSsMapper.updateById(grjbxxSs);
                return new GrjbxxSsResult(CommonCode.SUCCESS,grjbxxSs);
            }
            //修改失败
            return new GrjbxxSsResult(UserCode.USER_NOTEXISTS,null);

        }

    /**
     * 删除
     * @param grjbxx_ss
     * @return
     */
    public GrjbxxSsResult delete(Grjbxx_ss grjbxx_ss){
        QueryWrapper<Grjbxx_ss> wrapper = new QueryWrapper<>();
        wrapper.setEntity(grjbxx_ss);
            //先查询一下
            Grjbxx_ss grjbxxSs= grjbxxSsMapper.selectOne(wrapper);
            if (grjbxxSs!=null){

                wrapper.setEntity(grjbxxSs);
                grjbxxSsMapper.delete(wrapper);
                return new GrjbxxSsResult(CommonCode.SUCCESS,grjbxxSs);
            }
            return new GrjbxxSsResult(UserCode.USER_NOTEXISTS,null);
        }

    /**
     * 查询
     * @param grjbxxSs
     * @return
     */
    public QueryResponseResult select(Grjbxx_ss grjbxxSs){
        QueryWrapper<Grjbxx_ss> wrapper = new QueryWrapper<>();
        wrapper.setEntity(grjbxxSs);
        List<Grjbxx_ss> grjbxxWsList = grjbxxSsMapper.selectList(wrapper);
        QueryResult queryResult = new QueryResult();
        queryResult.setList(grjbxxWsList);//数据列表
        queryResult.setTotal(grjbxxWsList.size());//数据总记录数
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS,queryResult);
        return queryResponseResult;

    }




}
