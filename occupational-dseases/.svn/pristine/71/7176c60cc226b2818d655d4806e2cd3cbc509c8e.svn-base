package com.as.occupationaldseases.service;

import com.as.occupationaldseases.common.response.CommonCode;
import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.common.response.QueryResult;
import com.as.occupationaldseases.dao.GrjbxxMapper;
import com.as.occupationaldseases.dao.InquiryWkMapper;
import com.as.occupationaldseases.domain.grjbxx.Grjbxx;
import com.as.occupationaldseases.domain.inquiry_wk.InquiryWk;
import com.as.occupationaldseases.domain.inquiry_wk.responce.WkCode;
import com.as.occupationaldseases.domain.inquiry_wk.responce.WkResult;
import com.as.occupationaldseases.exception.ExceptionCast;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class InquiryWkService {

    @Resource
    private InquiryWkMapper inquiryWkMapper;
    @Resource
    private GrjbxxMapper grjbxxMapper;

    //新增
    public WkResult add(InquiryWk inquiryWk) {
        if(inquiryWk == null){
            //抛出异常，非法参数异常..指定异常信息的内容
            return new WkResult(CommonCode.INVALID_PARAM,null);
        }
        //校验Id的唯一性
        //根据Id集合，如果查到说明已经存在，如果查询不到再继续添加
        InquiryWk inquiryWk1 = inquiryWkMapper.selectById(inquiryWk.getId());
        if(inquiryWk1!=null){
            //已经存在
            //抛出异常，异常内容就是已经存在
            ExceptionCast.cast(WkCode.Wk_ADD_EXISTSNAME);
        }

        //调用dao新增
        inquiryWk.setId(null);//id自增
        inquiryWkMapper.insert(inquiryWk);

        QueryWrapper<Grjbxx> wrapper = new QueryWrapper<>();
        Grjbxx grjbxx = new Grjbxx();
        if(inquiryWk.getTmh()!=null&&inquiryWk.getTmh().length()>0){
            grjbxx.setTmh(inquiryWk.getTmh());
            wrapper.setEntity(grjbxx);
            Grjbxx grjbxx2 = new Grjbxx();
            grjbxx2.setTjflagwk("1");
            grjbxxMapper.update(grjbxx2,wrapper);
        }


        return new WkResult(CommonCode.SUCCESS,inquiryWk);

    }

    //修改
    public WkResult update(Integer id,InquiryWk inquiryWk){
        //根据id从数据库查询页面信息
        InquiryWk one = inquiryWkMapper.selectById(id);
        if(one!=null){
            //提交修改
            inquiryWkMapper.updateById(inquiryWk);
            return new WkResult(CommonCode.SUCCESS,inquiryWk);
        }
        //修改失败
        return new WkResult(WkCode.Wk_NOTEXISTS,null);

    }

    //根据id删除
    public WkResult delete(Integer id){
        //先查询一下
        InquiryWk inquiryWk = inquiryWkMapper.selectById(id);
        if (inquiryWk!=null){
            inquiryWkMapper.deleteById(id);
            return new WkResult(CommonCode.SUCCESS,inquiryWk);
        }
        return new WkResult(WkCode.Wk_NOTEXISTS,null);
    }

    /**
     * 查询方法
     * @param current 页码，从1开始记数
     * @param size 每页记录数
     * @param inquiryWk 查询条件
     * @return
     */
    public QueryResponseResult findList(int current, int size, InquiryWk inquiryWk){
        if(inquiryWk == null){
            inquiryWk = new InquiryWk();
        }

        //分页参数
        if(current <=0){
            current = 1;
        }
        current = current -1;
        if(size<=0){
            size = 10;
        }

        IPage<InquiryWk> page = new Page<>(current, size);
        QueryWrapper<InquiryWk> wrapper = new QueryWrapper<>();
        wrapper.setEntity(inquiryWk);

        IPage<InquiryWk> inquiryWkIPage = inquiryWkMapper.selectPage(page, wrapper);

        QueryResult queryResult = new QueryResult();

        queryResult.setList(inquiryWkIPage.getRecords());//数据列表
        queryResult.setTotal(inquiryWkIPage.getTotal());//数据总记录数
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS,queryResult);
        return queryResponseResult;
    }





}
