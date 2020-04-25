package com.as.occupationaldseases.service;

import com.as.occupationaldseases.common.response.CommonCode;
import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.common.response.QueryResult;
import com.as.occupationaldseases.dao.GrjbxxMapper;
import com.as.occupationaldseases.dao.SymptominfoMapper;
import com.as.occupationaldseases.domain.grjbxx.Grjbxx;
import com.as.occupationaldseases.domain.symptominfo.Symptominfo;
import com.as.occupationaldseases.domain.symptominfo.responce.SymptomCode;
import com.as.occupationaldseases.domain.symptominfo.responce.SymptomResult;
import com.as.occupationaldseases.exception.ExceptionCast;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SymptominfoService {

    @Resource
    private SymptominfoMapper symptominfoMapper;

    @Resource
    private GrjbxxMapper grjbxxMapper;


    //新增症状
    public SymptomResult add(Symptominfo symptominfo){
        {
            if(symptominfo == null){
                //抛出异常，非法参数异常..指定异常信息的内容
                return new SymptomResult(CommonCode.INVALID_PARAM,null);
            }
            //校验Id的唯一性
            Symptominfo symptominfo1 = symptominfoMapper.selectById(symptominfo.getId());
            if(symptominfo1!=null){
                //数据已经存在
                //抛出异常，异常内容就是数据已经存在
                ExceptionCast.cast(SymptomCode.Symptom_ADD_EXISTSNAME);
            }

            //调用dao新增
            symptominfo.setId(null);//id自增
            symptominfoMapper.insert(symptominfo);
            //更新个人信息主表
            QueryWrapper<Grjbxx> wrapper = new QueryWrapper<>();
            Grjbxx grjbxx = new Grjbxx();
            if(symptominfo.getTmh()!=null&&symptominfo.getTmh().length()>0){
                grjbxx.setTmh(symptominfo.getTmh());
                wrapper.setEntity(grjbxx);
                Grjbxx grjbxx2 = new Grjbxx();
                grjbxx2.setTjflagzz("1");
                grjbxxMapper.update(grjbxx2,wrapper);
            }

            //更新个人信息主表



            return new SymptomResult(CommonCode.SUCCESS,symptominfo);

        }



    }


    //修改
    public SymptomResult update(Integer id, Symptominfo symptominfo){
        //根据id从数据库查询页面信息
        Symptominfo one = symptominfoMapper.selectById(id);
        if(one!=null){
            //提交修改
            symptominfoMapper.updateById(symptominfo);
            return new SymptomResult(CommonCode.SUCCESS,symptominfo);
        }
        //修改失败
        return new SymptomResult(SymptomCode.Symptom_NOTEXISTS,null);

    }

    //根据id删除
    public SymptomResult delete(Integer id){
        //先查询一下
        Symptominfo symptominfo = symptominfoMapper.selectById(id);
        if (symptominfo!=null){
            symptominfoMapper.deleteById(id);
            return new SymptomResult(CommonCode.SUCCESS,symptominfo);
        }
        return new SymptomResult(SymptomCode.Symptom_NOTEXISTS,null);
    }


    //查询一条记录
    public Symptominfo selectone(QueryWrapper<Symptominfo> queryWrapper){
        return symptominfoMapper.selectOne(queryWrapper);
    }


    //查询多条记录
    public List<Symptominfo> select(Symptominfo symptominfo){
        QueryWrapper<Symptominfo> wrapper = new QueryWrapper<>();
        wrapper.setEntity(symptominfo);
        return symptominfoMapper.selectList(wrapper);
    }

/*    //查询多条记录
    public List<Symptominfo> select(Symptominfo symptominfo){
        return symptominfoMapper.selectList(symptominfo);
    }*/



    /**
     * 查询方法
     * @param current 页码，从1开始记数
     * @param size 每页记录数
     * @param symptominfo 查询条件
     * @return
     */
    public QueryResponseResult findList(int current, int size, Symptominfo symptominfo){


        if(symptominfo == null){
            symptominfo = new Symptominfo();
        }

        //分页参数
        if(current <=0){
            current = 1;
        }
     //   current = current -1;
        if(size<=0){
            size = 10;
        }

        IPage<Symptominfo> page = new Page<>(current, size);
        QueryWrapper<Symptominfo> wrapper = new QueryWrapper<>();
        wrapper.setEntity(symptominfo);

        IPage<Symptominfo> SymptominfoIPage = symptominfoMapper.selectPage(page, wrapper);

        QueryResult queryResult = new QueryResult();

        queryResult.setList(SymptominfoIPage.getRecords());//数据列表
        queryResult.setTotal(SymptominfoIPage.getTotal());//数据总记录数
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS,queryResult);
        return queryResponseResult;
    }


}
