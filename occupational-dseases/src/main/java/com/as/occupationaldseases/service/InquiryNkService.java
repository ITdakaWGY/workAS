package com.as.occupationaldseases.service;

import com.as.occupationaldseases.common.response.CommonCode;
import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.common.response.QueryResult;
import com.as.occupationaldseases.dao.GrjbxxMapper;
import com.as.occupationaldseases.dao.InquiryNkMapper;
import com.as.occupationaldseases.domain.grjbxx.Grjbxx;
import com.as.occupationaldseases.domain.inquiry_nk.InquiryNk;
import com.as.occupationaldseases.domain.inquiry_nk.responce.NkCode;
import com.as.occupationaldseases.domain.inquiry_nk.responce.NkResult;
import com.as.occupationaldseases.exception.ExceptionCast;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class InquiryNkService {

    @Resource
    private InquiryNkMapper inquiryNkMapper;
    @Resource
    private GrjbxxMapper grjbxxMapper;

    //新增
    public NkResult add(InquiryNk inquiryNk) {
        if(inquiryNk == null){
            //抛出异常，非法参数异常..指定异常信息的内容
            return new NkResult(CommonCode.INVALID_PARAM,null);
        }
        //校验Id的唯一性
        //根据Id集合，如果查到说明已经存在，如果查询不到再继续添加
        InquiryNk inquiryNk1 = inquiryNkMapper.selectById(inquiryNk.getId());
        if(inquiryNk1!=null){
            //已经存在
            //抛出异常，异常内容就是已经存在
            ExceptionCast.cast(NkCode.Nk_ADD_EXISTSNAME);
        }

        //调用dao新增
        inquiryNk.setId(null);//id自增
        inquiryNkMapper.insert(inquiryNk);

        QueryWrapper<Grjbxx> wrapper = new QueryWrapper<>();
        Grjbxx grjbxx = new Grjbxx();
        if(inquiryNk.getTmh()!=null&&inquiryNk.getTmh().length()>0){
            grjbxx.setTmh(inquiryNk.getTmh());
            wrapper.setEntity(grjbxx);
            Grjbxx grjbxx2 = new Grjbxx();
            grjbxx2.setTjflagnk("1");
            grjbxxMapper.update(grjbxx2,wrapper);
        }

        return new NkResult(CommonCode.SUCCESS,inquiryNk);

    }

    //修改
    public NkResult update(Integer id,InquiryNk inquiryNk){
        //根据id从数据库查询页面信息
        InquiryNk one = inquiryNkMapper.selectById(id);
        if(one!=null){
            //提交修改
            inquiryNkMapper.updateById(inquiryNk);
            return new NkResult(CommonCode.SUCCESS,inquiryNk);
        }
        //修改失败
        return new NkResult(NkCode.Nk_NOTEXISTS,null);

    }

    //根据id删除
    public NkResult delete(Integer id){
        //先查询一下
        InquiryNk inquiryNk = inquiryNkMapper.selectById(id);
        if (inquiryNk!=null){
            inquiryNkMapper.deleteById(id);
            return new NkResult(CommonCode.SUCCESS,inquiryNk);
        }
        return new NkResult(NkCode.Nk_NOTEXISTS,null);
    }

    /**
     * 查询方法
     * @param current 页码，从1开始记数
     * @param size 每页记录数
     * @param inquiryNk 查询条件
     * @return
     */
    public QueryResponseResult findList(int current, int size, InquiryNk inquiryNk){
        if(inquiryNk == null){
            inquiryNk = new InquiryNk();
        }

        //分页参数
        if(current <=0){
            current = 1;
        }
        current = current -1;
        if(size<=0){
            size = 10;
        }

        IPage<InquiryNk> page = new Page<>(current, size);
        QueryWrapper<InquiryNk> wrapper = new QueryWrapper<>();
        wrapper.setEntity(inquiryNk);

        IPage<InquiryNk> inquiryNkIPage = inquiryNkMapper.selectPage(page, wrapper);

        QueryResult queryResult = new QueryResult();

        queryResult.setList(inquiryNkIPage.getRecords());//数据列表
        queryResult.setTotal(inquiryNkIPage.getTotal());//数据总记录数
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS,queryResult);
        return queryResponseResult;
    }


}
