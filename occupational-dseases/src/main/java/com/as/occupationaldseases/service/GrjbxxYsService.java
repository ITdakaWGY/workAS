package com.as.occupationaldseases.service;

import com.as.occupationaldseases.common.response.CommonCode;
import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.common.response.QueryResult;
import com.as.occupationaldseases.dao.GrjbxxFcMapper;
import com.as.occupationaldseases.dao.GrjbxxYsMapper;
import com.as.occupationaldseases.domain.grjbxx_fc.Grjbxx_fc;
import com.as.occupationaldseases.domain.grjbxx_fc.responce.FcCode;
import com.as.occupationaldseases.domain.grjbxx_fc.responce.FcResult;
import com.as.occupationaldseases.domain.grjbxx_ys.Grjbxx_ys;
import com.as.occupationaldseases.domain.grjbxx_ys.responce.YsCode;
import com.as.occupationaldseases.domain.grjbxx_ys.responce.YsResult;
import com.as.occupationaldseases.exception.ExceptionCast;
import com.as.occupationaldseases.utils.StringUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GrjbxxYsService {
    @Resource
    private GrjbxxYsMapper grjbxxYsMapper;

    //新增症状
    public YsResult add(Grjbxx_ys grjbxxYs){
        {
            if(grjbxxYs == null){
                //抛出异常，非法参数异常..指定异常信息的内容
                return new YsResult(CommonCode.INVALID_PARAM,null);
            }
            //校验Id的唯一性
            Grjbxx_ys grjbxxYs1 = grjbxxYsMapper.selectById(grjbxxYs.getId());
            if(grjbxxYs1!=null){
                //数据已经存在
                //抛出异常，异常内容就是数据已经存在
                ExceptionCast.cast(YsCode.Ys_ADD_EXISTSNAME);
            }

            //调用dao新增
            grjbxxYs.setId(null);//id自增
            grjbxxYsMapper.insert(grjbxxYs);
            return new YsResult(CommonCode.SUCCESS,grjbxxYs);

        }



    }


    //修改
    public YsResult update(Integer id, Grjbxx_ys grjbxxYs){
        //根据id从数据库查询页面信息
        Grjbxx_ys one = grjbxxYsMapper.selectById(id);
        if(one!=null){
            //提交修改
            grjbxxYsMapper.updateById(grjbxxYs);
            return new YsResult(CommonCode.SUCCESS,grjbxxYs);
        }
        //修改失败
        return new YsResult(YsCode.Ys_NOTEXISTS,null);

    }

    //根据id删除
    public YsResult delete(Integer id){
        //先查询一下
        Grjbxx_ys grjbxxYs = grjbxxYsMapper.selectById(id);
        if (grjbxxYs!=null){
            grjbxxYsMapper.deleteById(id);
            return new YsResult(CommonCode.SUCCESS,grjbxxYs);
        }
        return new YsResult(YsCode.Ys_NOTEXISTS,null);
    }


    //查询一条记录
    public Grjbxx_ys selectone(QueryWrapper<Grjbxx_ys> queryWrapper){
        return grjbxxYsMapper.selectOne(queryWrapper);
    }


    //查询多条记录
    public List<Grjbxx_ys> select(Grjbxx_ys grjbxxYs){
        QueryWrapper<Grjbxx_ys> wrapper = new QueryWrapper<>();
        wrapper.setEntity(grjbxxYs);
        return grjbxxYsMapper.selectList(wrapper);
    }




    /**
     * 查询方法
     * @param current 页码，从1开始记数
     * @param size 每页记录数
     * @param grjbxxYs 查询条件
     * @return
     */
    public QueryResponseResult findList(int current, int size, Grjbxx_ys grjbxxYs){


        if(grjbxxYs == null){
            grjbxxYs = new Grjbxx_ys();
        }
        grjbxxYs.setYszybbs("1");
        //分页参数
        if(current <=0){
            current = 1;
        }
        //   current = current -1;
        if(size<=0){
            size = 10;
        }

        IPage<Grjbxx_ys> page = new Page<>(current, size);
        QueryWrapper<Grjbxx_ys> wrapper = new QueryWrapper<>();


        if(StringUtil.nonNullRequired(grjbxxYs.getTjrqstart())){
            wrapper.ge("tjrq",grjbxxYs.getTjrqstart());
        }
        if(StringUtil.nonNullRequired(grjbxxYs.getTjrqend())){
            wrapper.le("tjrq",grjbxxYs.getTjrqend());
        }
        if(StringUtil.nonNullRequired(grjbxxYs.getSfz())){
            wrapper.eq("sfz",grjbxxYs.getSfz());
        }

        if(StringUtil.nonNullRequired(grjbxxYs.getCompanycode())){
            wrapper.eq("companycode",grjbxxYs.getCompanycode());
        }
        if(StringUtil.nonNullRequired(grjbxxYs.getName())){
            wrapper.like("name", grjbxxYs.getName());
        }
        if(StringUtil.nonNullRequired(grjbxxYs.getYszybbs())){
            wrapper.eq("yszybbs", grjbxxYs.getYszybbs());
        }
        if(grjbxxYs.getId()!=null){
            wrapper.eq("id",grjbxxYs.getId());
        }

        IPage<Grjbxx_ys> GrjbxxYsIPage = grjbxxYsMapper.selectPage(page, wrapper);
        QueryResult queryResult = new QueryResult();

        queryResult.setList(GrjbxxYsIPage.getRecords());//数据列表
        queryResult.setTotal(GrjbxxYsIPage.getTotal());//数据总记录数
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS,queryResult);
        return queryResponseResult;
    }

}
