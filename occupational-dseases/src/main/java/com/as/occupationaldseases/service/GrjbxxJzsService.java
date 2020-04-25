package com.as.occupationaldseases.service;

import com.as.occupationaldseases.common.response.CommonCode;
import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.common.response.QueryResult;
import com.as.occupationaldseases.dao.GrjbxxJzsMapper;
import com.as.occupationaldseases.domain.grjbxx.Grjbxx_jzs;
import com.as.occupationaldseases.domain.grjbxx.responce.GrjbxxJzsResult;
import com.as.occupationaldseases.domain.user.responce.UserCode;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
public class GrjbxxJzsService {
    @Resource
    private GrjbxxJzsMapper grjbxxJzsMapper;
    /**
     * 新增
     * @param grjbxxJzs
     * @return
     */
    public GrjbxxJzsResult add(Grjbxx_jzs grjbxxJzs){
        if(grjbxxJzs == null){
            //抛出异常，非法参数异常..指定异常信息的内容
            return new GrjbxxJzsResult(CommonCode.INVALID_PARAM,null);
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("personalid",grjbxxJzs.getPersonalid());
        grjbxxJzsMapper.deleteByMap(map);
        String[] jbdm = grjbxxJzs.getJzsjbdm().split(";");
        String[] other = grjbxxJzs.getJzsOther().split(";");
        for (int i = 0; i < jbdm.length; i++) {
            Grjbxx_jzs grjbxx_jzs = new Grjbxx_jzs();
            grjbxx_jzs.setId(null);
            grjbxx_jzs.setJzsOther(other[i]);
            grjbxx_jzs.setPersonalid(grjbxxJzs.getPersonalid());
            grjbxx_jzs.setJzsgx(Integer.toString(i+1));
            grjbxx_jzs.setJzsjbdm(jbdm[i]);
            grjbxxJzsMapper.insert(grjbxx_jzs);
        }



        return new GrjbxxJzsResult(CommonCode.SUCCESS,grjbxxJzs);
    }

    /**
     * 修改
     * @param id
     * @param grjbxxJzs
     * @return
     */
    public GrjbxxJzsResult update(Long id, Grjbxx_jzs grjbxxJzs){
        //根据id从数据库查询页面信息
        Grjbxx_jzs grjbxx_jzs = grjbxxJzsMapper.selectById(id);
        if(grjbxx_jzs!=null){
            //提交修改
            grjbxxJzsMapper.updateById(grjbxxJzs);
            return new GrjbxxJzsResult(CommonCode.SUCCESS,grjbxxJzs);
        }
        //修改失败
        return new GrjbxxJzsResult(UserCode.USER_NOTEXISTS,null);
    }

    /**
     * 删除
     * @param grjbxxJzs
     * @return
     */
    public GrjbxxJzsResult delete(Grjbxx_jzs grjbxxJzs){
        QueryWrapper<Grjbxx_jzs> wrapper = new QueryWrapper<>();
        wrapper.setEntity(grjbxxJzs);
        Grjbxx_jzs grjbxx_jzs = grjbxxJzsMapper.selectOne(wrapper);
        if(grjbxx_jzs!=null){
            wrapper.setEntity(grjbxx_jzs);
            grjbxxJzsMapper.delete(wrapper);
            return new GrjbxxJzsResult(CommonCode.SUCCESS,grjbxx_jzs);
        }
        return  new GrjbxxJzsResult(UserCode.USER_NOTEXISTS,null);
    }

    /**
     * 查询
     * @param grjbxxJzs
     * @return
     */
    public QueryResponseResult select(Grjbxx_jzs grjbxxJzs){
        QueryWrapper<Grjbxx_jzs> wrapper = new QueryWrapper<>();
        wrapper.setEntity(grjbxxJzs);
        List<Grjbxx_jzs> grjbxxJzsList = grjbxxJzsMapper.selectList(wrapper);
        QueryResult queryResult = new QueryResult();
        queryResult.setList(grjbxxJzsList);//数据列表
        queryResult.setTotal(grjbxxJzsList.size());//数据总记录数
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS,queryResult);
        return queryResponseResult;
    }





}
