package com.as.occupationaldseases.service;

import com.as.occupationaldseases.common.response.CommonCode;
import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.common.response.QueryResult;
import com.as.occupationaldseases.dao.GrjbxxJbsMapper;
import com.as.occupationaldseases.domain.grjbxx.Grjbxx_jbs;
import com.as.occupationaldseases.domain.grjbxx.responce.GrjbxxJbsResult;
import com.as.occupationaldseases.domain.user.responce.UserCode;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
public class GrjbxxJbsService {
    @Resource
    GrjbxxJbsMapper grjbxxJbsMapper;

    /**
     * 新增
     * @param grjbxxJbs
     * @return
     */
    public GrjbxxJbsResult add(Grjbxx_jbs grjbxxJbs){
        if (grjbxxJbs == null){
            //抛出异常，非法参数异常..指定异常信息的内容
         return new GrjbxxJbsResult(CommonCode.INVALID_PARAM,null);
        }
        System.out.println("---------------");
        System.out.println(grjbxxJbs);
        System.out.println("---------------");
        HashMap<String, Object> map = new HashMap<>();
        map.put("personalid",grjbxxJbs.getPersonalid());
        grjbxxJbsMapper.deleteByMap(map);
        if (grjbxxJbs.getJbsdm().contains("13")) {
            String[] qzsj = grjbxxJbs.getQzsj().split(",");
            String[] qtjbs = grjbxxJbs.getQtjbs().split(",");
            for (int i = 0; i < qzsj.length; i++) {
                Grjbxx_jbs grjbxx_jbs = new Grjbxx_jbs();
                grjbxx_jbs.setId(null);
                grjbxx_jbs.setQtjbs(qtjbs[i]);
                grjbxx_jbs.setQzsj(qzsj[i]);
                grjbxx_jbs.setJbsdm(grjbxxJbs.getJbsdm());
                grjbxx_jbs.setZybms(grjbxxJbs.getZybms());
                grjbxx_jbs.setExzlms(grjbxxJbs.getExzlms());
                grjbxx_jbs.setPersonalid(grjbxxJbs.getPersonalid());
                grjbxxJbsMapper.insert(grjbxx_jbs);

            }
            return new GrjbxxJbsResult(CommonCode.SUCCESS,grjbxxJbs);
        }
        //调用dao新增
        grjbxxJbs.setId(null);
        grjbxxJbsMapper.insert(grjbxxJbs);
        return new GrjbxxJbsResult(CommonCode.SUCCESS,grjbxxJbs);
    }

    /**
     * 修改
     * @param id
     * @param grjbxxJbs
     * @return
     */
    public GrjbxxJbsResult update(Long id,Grjbxx_jbs grjbxxJbs){
        Grjbxx_jbs grjbxx_jbs = grjbxxJbsMapper.selectById(id);
        if (grjbxx_jbs!=null){
            //提交修改
            grjbxxJbsMapper.updateById(grjbxxJbs);
            return new GrjbxxJbsResult(CommonCode.SUCCESS,grjbxxJbs);
        }
        //修改失败
        return new GrjbxxJbsResult(UserCode.USER_NOTEXISTS,null);

    }

    /**
     * 删除
     * @param grjbxxJbs
     * @return
     */
    public GrjbxxJbsResult delete(Grjbxx_jbs grjbxxJbs){
        QueryWrapper<Grjbxx_jbs> wrapper = new QueryWrapper<>();
        wrapper.setEntity(grjbxxJbs);
        Grjbxx_jbs grjbxx_jbs = grjbxxJbsMapper.selectOne(wrapper);
        if (grjbxx_jbs!=null){
            wrapper.setEntity(grjbxx_jbs);
            grjbxxJbsMapper.delete(wrapper);
            return new GrjbxxJbsResult(CommonCode.SUCCESS,grjbxx_jbs);
        }
            return new GrjbxxJbsResult(UserCode.USER_NOTEXISTS,null);
    }

    /**
     * 查询
     * @param grjbxxJbs
     * @return
     */
    public QueryResponseResult select(Grjbxx_jbs grjbxxJbs){
        QueryWrapper<Grjbxx_jbs>  wrapper  = new QueryWrapper<>();
        wrapper.setEntity(grjbxxJbs);
        List<Grjbxx_jbs> grjbxxJbsList = grjbxxJbsMapper.selectList(wrapper);
        QueryResult queryResult = new QueryResult();
        queryResult.setList(grjbxxJbsList);//数据列表
        queryResult.setTotal(grjbxxJbsList.size());//数据总记录数
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS,queryResult);
        return queryResponseResult;

    }


}
