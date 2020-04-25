package com.as.occupationaldseases.service;

import com.as.occupationaldseases.common.response.CommonCode;
import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.common.response.QueryResult;
import com.as.occupationaldseases.dao.GrjbxxFcMapper;
import com.as.occupationaldseases.domain.grjbxx_fc.Grjbxx_fc;
import com.as.occupationaldseases.domain.grjbxx_fc.Grjbxx_fczj;
import com.as.occupationaldseases.domain.grjbxx_fc.responce.FcCode;
import com.as.occupationaldseases.domain.grjbxx_fc.responce.FcResult;
import com.as.occupationaldseases.exception.ExceptionCast;
import com.as.occupationaldseases.utils.StringUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class GrjbxxFcService {
    @Resource
    private GrjbxxFcMapper grjbxxFcMapper;

    //新增症状
    public FcResult add(Grjbxx_fc grjbxxFc){
        {
            if(grjbxxFc == null){
                //抛出异常，非法参数异常..指定异常信息的内容
                return new FcResult(CommonCode.INVALID_PARAM,null);
            }
            //校验Id的唯一性
            Grjbxx_fc grjbxxFc1 = grjbxxFcMapper.selectById(grjbxxFc.getId());
            if(grjbxxFc1!=null){
                //数据已经存在
                //抛出异常，异常内容就是数据已经存在
                ExceptionCast.cast(FcCode.Fc_ADD_EXISTSNAME);
            }

            //调用dao新增
            grjbxxFc.setId(null);//id自增
            grjbxxFcMapper.insert(grjbxxFc);
            return new FcResult(CommonCode.SUCCESS,grjbxxFc);

        }



    }


    //修改
    public FcResult update(Integer id, Grjbxx_fc grjbxxFc){
        //根据id从数据库查询页面信息
        Grjbxx_fc one = grjbxxFcMapper.selectById(id);
        if(one!=null){
            //提交修改
            grjbxxFcMapper.updateById(grjbxxFc);
            return new FcResult(CommonCode.SUCCESS,grjbxxFc);
        }
        //修改失败
        return new FcResult(FcCode.Fc_NOTEXISTS,null);

    }

    //根据id删除
    public FcResult delete(Integer id){
        //先查询一下
        Grjbxx_fc grjbxxFc = grjbxxFcMapper.selectById(id);
        if (grjbxxFc!=null){
            grjbxxFcMapper.deleteById(id);
            return new FcResult(CommonCode.SUCCESS,grjbxxFc);
        }
        return new FcResult(FcCode.Fc_NOTEXISTS,null);
    }


    //查询一条记录
    public Grjbxx_fc selectone(QueryWrapper<Grjbxx_fc> queryWrapper){
        return grjbxxFcMapper.selectOne(queryWrapper);
    }


    //查询多条记录
    public List<Grjbxx_fc> select(Grjbxx_fc grjbxxFc){
        QueryWrapper<Grjbxx_fc> wrapper = new QueryWrapper<>();
        wrapper.setEntity(grjbxxFc);
        return grjbxxFcMapper.selectList(wrapper);
    }




    /**
     * 查询方法
     * @param current 页码，从1开始记数
     * @param size 每页记录数
     * @param grjbxxFc 查询条件
     * @return
     */
    public QueryResponseResult findList(int current, int size, Grjbxx_fc grjbxxFc){


        if(grjbxxFc == null){
            grjbxxFc = new Grjbxx_fc();
        }


        if("2".equals(grjbxxFc.getJyfcbs())){
            grjbxxFc.setJyfcbs(null);
        }
        //分页参数
        if(current <=0){
            current = 1;
        }
     //   current = current -1;
        if(size<=0){
            size = 10;
        }

        IPage<Grjbxx_fc> page = new Page<>(current, size);
        QueryWrapper<Grjbxx_fc> wrapper = new QueryWrapper<>();


        if(StringUtil.nonNullRequired(grjbxxFc.getTjrqstart())){
            wrapper.ge("tjrq",grjbxxFc.getTjrqstart());
        }
        if(StringUtil.nonNullRequired(grjbxxFc.getTjrqend())){
            wrapper.le("tjrq",grjbxxFc.getTjrqend());
        }
        if(StringUtil.nonNullRequired(grjbxxFc.getSfz())){
            wrapper.eq("sfz",grjbxxFc.getSfz());
        }

        if(StringUtil.nonNullRequired(grjbxxFc.getCompanycode())){
            wrapper.eq("companycode",grjbxxFc.getCompanycode());
        }

        if(grjbxxFc.getId()!=null){
            wrapper.eq("id",grjbxxFc.getId());
        }

        if(StringUtil.nonNullRequired(grjbxxFc.getName())){
            wrapper.like("name", grjbxxFc.getName());
        }

        if("1".equals(grjbxxFc.getJyfcbs())){
            wrapper.eq("jyfcbs",grjbxxFc.getJyfcbs());
        }
     //   wrapper.setEntity(grjbxxFc);
        IPage<Grjbxx_fc> GrjbxxFcIPage = null;
        //查询全部
        if("0".equals(grjbxxFc.getTjflagzj())){
            grjbxxFc.setTjflagzj(null);
            GrjbxxFcIPage = grjbxxFcMapper.selectPage(page, wrapper);
        }
        //查询未总检的人数
  /*      if("2".equals(grjbxxFc.getTjflagzj())){
            grjbxxFc.setTjflagzj(null);
           GrjbxxFcIPage = grjbxxFcMapper.selectPage(page, wrapper.isNull("tjflagzj"));
        }else{
          GrjbxxFcIPage = grjbxxFcMapper.selectPage(page, wrapper);
        }*/
        GrjbxxFcIPage = grjbxxFcMapper.selectPage(page, wrapper);
        QueryResult queryResult = new QueryResult();
        queryResult.setList(GrjbxxFcIPage.getRecords());//数据列表
        queryResult.setTotal(GrjbxxFcIPage.getTotal());//数据总记录数
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS,queryResult);
        return queryResponseResult;
    }


    /**
     * 查询方法
     * @param current 页码，从1开始记数
     * @param size 每页记录数
     * @param grjbxx_fczj 查询条件
     * @return
     */
    public QueryResponseResult inspectionList(int current, int size, Grjbxx_fczj grjbxx_fczj){


        if(grjbxx_fczj == null){
            grjbxx_fczj = new Grjbxx_fczj();
        }

        //分页参数
        if(current <=0){
            current = 1;
        }
        //   current = current -1;
        if(size<=0){
            size = 10;
        }

        IPage<Grjbxx_fc> page = new Page<>(current, size);
        QueryWrapper<Grjbxx_fc> wrapper = new QueryWrapper<>();

        if(StringUtil.nonNullRequired(grjbxx_fczj.getTjrqstart())){
            wrapper.ge("tjrq",grjbxx_fczj.getTjrqstart());
        }
        if(StringUtil.nonNullRequired(grjbxx_fczj.getTjrqend())){
            wrapper.le("tjrq",grjbxx_fczj.getTjrqend());
        }
        if(StringUtil.nonNullRequired(grjbxx_fczj.getSfz())){
            wrapper.eq("sfz",grjbxx_fczj.getSfz());
        }
        if(StringUtil.nonNullRequired(grjbxx_fczj.getDanwei())){
            wrapper.eq("companycode",grjbxx_fczj.getDanwei());
        }
        if(StringUtil.nonNullRequired(grjbxx_fczj.getName())){
            wrapper.like("name", grjbxx_fczj.getName());
        }
        if(grjbxx_fczj.getTjflagzj()!=null&&grjbxx_fczj.getTjflagzj().equals("1")){
            wrapper.eq("tjflagzj",grjbxx_fczj.getTjflagzj());
        }else if(grjbxx_fczj.getTjflagzj()==""||grjbxx_fczj.getTjflagzj()==null){

        } else{
            wrapper.isNull("tjflagzj").or().eq("tjflagzj","");
        }
        IPage<Grjbxx_fc> GrjbxxFcIPage = null;
        GrjbxxFcIPage = grjbxxFcMapper.selectPage(page, wrapper);
        QueryResult queryResult = new QueryResult();
        queryResult.setList(GrjbxxFcIPage.getRecords());//数据列表
        queryResult.setTotal(GrjbxxFcIPage.getTotal());//数据总记录数
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS,queryResult);
        return queryResponseResult;
    }


}
