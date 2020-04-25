package com.as.occupationaldseases.service;


import com.as.occupationaldseases.common.response.CommonCode;
import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.common.response.QueryResult;
import com.as.occupationaldseases.dao.GrjbxxFcMapper;
import com.as.occupationaldseases.dao.GrjbxxJjMapper;
import com.as.occupationaldseases.domain.grjbxx_fc.Grjbxx_fc;
import com.as.occupationaldseases.domain.grjbxx_fc.responce.FcCode;
import com.as.occupationaldseases.domain.grjbxx_fc.responce.FcResult;
import com.as.occupationaldseases.domain.grjbxx_jj.Grjbxx_jj;
import com.as.occupationaldseases.domain.grjbxx_jj.responce.JjCode;
import com.as.occupationaldseases.domain.grjbxx_jj.responce.JjResult;
import com.as.occupationaldseases.exception.ExceptionCast;
import com.as.occupationaldseases.utils.StringUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GrjbxxJjService {
    @Resource
    private GrjbxxJjMapper grjbxxJjMapper;

    //新增症状
    public JjResult add(Grjbxx_jj grjbxxJj){
        {
            if(grjbxxJj == null){
                //抛出异常，非法参数异常..指定异常信息的内容
                return new JjResult(CommonCode.INVALID_PARAM,null);
            }
            //校验Id的唯一性
            Grjbxx_jj Grjbxx_jj1 = grjbxxJjMapper.selectById(grjbxxJj.getId());
            if(Grjbxx_jj1!=null){
                //数据已经存在
                //抛出异常，异常内容就是数据已经存在
                ExceptionCast.cast(JjCode.Jj_ADD_EXISTSNAME);
            }

            //调用dao新增
            grjbxxJj.setId(null);//id自增
            grjbxxJjMapper.insert(grjbxxJj);
            return new JjResult(CommonCode.SUCCESS,grjbxxJj);

        }



    }


    //修改
    public JjResult update(Integer id, Grjbxx_jj grjbxxJj){
        //根据id从数据库查询页面信息
        Grjbxx_jj one = grjbxxJjMapper.selectById(id);
        if(one!=null){
            //提交修改
            grjbxxJjMapper.updateById(grjbxxJj);
            return new JjResult(CommonCode.SUCCESS,grjbxxJj);
        }
        //修改失败
        return new JjResult(JjCode.Jj_NOTEXISTS,null);

    }

    //根据id删除
    public JjResult delete(Integer id){
        //先查询一下
        Grjbxx_jj grjbxxJj = grjbxxJjMapper.selectById(id);
        if (grjbxxJj!=null){
            grjbxxJjMapper.deleteById(id);
            return new JjResult(CommonCode.SUCCESS,grjbxxJj);
        }
        return new JjResult(JjCode.Jj_NOTEXISTS,null);
    }


    //查询一条记录
    public Grjbxx_jj selectone(Grjbxx_jj grjbxxJj){
        QueryWrapper<Grjbxx_jj> wrapper = new QueryWrapper<>();
        wrapper.setEntity(grjbxxJj);
        return grjbxxJjMapper.selectOne(wrapper);
    }


    //查询多条记录
    public List<Grjbxx_jj> select(Grjbxx_jj grjbxxJj){
        QueryWrapper<Grjbxx_jj> wrapper = new QueryWrapper<>();
        wrapper.setEntity(grjbxxJj);
        return grjbxxJjMapper.selectList(wrapper);
    }




    /**
     * 查询方法
     * @param current 页码，从1开始记数
     * @param size 每页记录数
     * @param grjbxxJj 查询条件
     * @return
     */
    public QueryResponseResult findList(int current, int size, Grjbxx_jj grjbxxJj){


        if(grjbxxJj == null){
            grjbxxJj = new Grjbxx_jj();
        }
        grjbxxJj.setJjzbs("1");
        //分页参数
        if(current <=0){
            current = 1;
        }
        //   current = current -1;
        if(size<=0){
            size = 10;
        }

        IPage<Grjbxx_jj> page = new Page<>(current, size);
        QueryWrapper<Grjbxx_jj> wrapper = new QueryWrapper<>();

        if(StringUtil.nonNullRequired(grjbxxJj.getTjrqstart())){
            wrapper.ge("tjrq",grjbxxJj.getTjrqstart());
        }
        if(StringUtil.nonNullRequired(grjbxxJj.getTjrqend())){
            wrapper.le("tjrq",grjbxxJj.getTjrqend());
        }
        if(StringUtil.nonNullRequired(grjbxxJj.getSfz())){
            wrapper.eq("sfz",grjbxxJj.getSfz());
        }

        if(StringUtil.nonNullRequired(grjbxxJj.getCompanycode())){
            wrapper.eq("companycode",grjbxxJj.getCompanycode());
        }
        if(StringUtil.nonNullRequired(grjbxxJj.getName())){
            wrapper.like("name", grjbxxJj.getName());
        }
        if(StringUtil.nonNullRequired(grjbxxJj.getJjzbs())){
            wrapper.eq("jjzbs", grjbxxJj.getJjzbs());
        }
        if(grjbxxJj.getId()!=null){
            wrapper.eq("id",grjbxxJj.getId());
        }

        IPage<Grjbxx_jj> GrjbxxJjIPage = grjbxxJjMapper.selectPage(page, wrapper);

        QueryResult queryResult = new QueryResult();

        queryResult.setList(GrjbxxJjIPage.getRecords());//数据列表
        queryResult.setTotal(GrjbxxJjIPage.getTotal());//数据总记录数
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS,queryResult);
        return queryResponseResult;
    }
}
