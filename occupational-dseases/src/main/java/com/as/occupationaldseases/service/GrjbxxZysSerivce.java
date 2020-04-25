package com.as.occupationaldseases.service;

import com.as.occupationaldseases.common.response.CommonCode;
import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.common.response.QueryResult;
import com.as.occupationaldseases.dao.GrjbxxZysMapper;
import com.as.occupationaldseases.domain.grjbxx.Grjbxx_zys;
import com.as.occupationaldseases.domain.grjbxx.responce.GrjbxxZysResult;
import com.as.occupationaldseases.domain.user.responce.UserCode;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
public class GrjbxxZysSerivce {
    @Resource
    private GrjbxxZysMapper grjbxxZysMapper;

    /**
     * 新增
     * @param grjbxxZys
     * @return
     */
   public GrjbxxZysResult add(Grjbxx_zys grjbxxZys){
       if (grjbxxZys==null) {
           return new GrjbxxZysResult(CommonCode.INVALID_PARAM,null);
       }
       HashMap<String, Object> map = new HashMap<>();
       map.put("personalid",grjbxxZys.getPersonalid());
       grjbxxZysMapper.deleteByMap(map);
       String[] qzrq = grjbxxZys.getQzrq().split(",");
       String[] jssj = grjbxxZys.getJssj().split(",");
       String[] fhcs = grjbxxZys.getFhcs().split(",");
       String[] cj = grjbxxZys.getCj().split(",");
       String[] gz = grjbxxZys.getGz().split(",");
       String[] gzdw = grjbxxZys.getGzdw().split(",");
       String[] yhys = grjbxxZys.getYhys().split(",");
       for (int i = 0; i < qzrq.length; i++) {
           Grjbxx_zys grjbxx_zys = new Grjbxx_zys();
           grjbxx_zys.setId(null);
           grjbxx_zys.setQzrq(qzrq[i]);
           grjbxx_zys.setCj(cj[i]);
           grjbxx_zys.setFhcs(fhcs[i]);
           grjbxx_zys.setGz(gz[i]);
           grjbxx_zys.setYhys(yhys[i]);
           grjbxx_zys.setJssj(jssj[i]);
           grjbxx_zys.setGzdw(gzdw[i]);
           grjbxx_zys.setPersonalid(grjbxxZys.getPersonalid());
           grjbxxZysMapper.insert(grjbxx_zys);
       }

       return  new GrjbxxZysResult(CommonCode.SUCCESS,grjbxxZys);
    }

    /**
     * 修改
     * @param id
     * @param grjbxxZys
     * @return
     */
    public GrjbxxZysResult update(Long id,Grjbxx_zys grjbxxZys){
        Grjbxx_zys grjbxx_zys = grjbxxZysMapper.selectById(id);
        if (grjbxx_zys != null){
           grjbxxZysMapper.updateById(grjbxxZys);
           return new GrjbxxZysResult(CommonCode.SUCCESS,grjbxxZys);
       }
        return new GrjbxxZysResult(UserCode.USER_NOTEXISTS,null);
    }


    public GrjbxxZysResult delete(Grjbxx_zys grjbxxZys){
        QueryWrapper<Grjbxx_zys> wrapper = new QueryWrapper<>();
        wrapper.setEntity(grjbxxZys);
        Grjbxx_zys grjbxx_zys = grjbxxZysMapper.selectOne(wrapper);
        if (grjbxx_zys!=null){
            wrapper.setEntity(grjbxxZys);
            grjbxxZysMapper.delete(wrapper);
            return new GrjbxxZysResult(CommonCode.SUCCESS,grjbxxZys);
        }
        return new GrjbxxZysResult(UserCode.USER_NOTEXISTS,null);
    }
    /**
     * 查询
     * @param grjbxxZys
     * @return
     */
    public QueryResponseResult select(Grjbxx_zys grjbxxZys){
        QueryWrapper<Grjbxx_zys> wrapper = new QueryWrapper<>();
        wrapper.setEntity(grjbxxZys);
        List<Grjbxx_zys> grjbxxZysList = grjbxxZysMapper.selectList(wrapper);
        QueryResult queryResult = new QueryResult();
        queryResult.setList(grjbxxZysList);//数据列表
        queryResult.setTotal(grjbxxZysList.size());//数据总记录数
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS,queryResult);
        return queryResponseResult;
    }


}
