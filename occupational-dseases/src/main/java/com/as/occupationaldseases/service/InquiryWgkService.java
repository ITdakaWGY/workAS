package com.as.occupationaldseases.service;

import com.as.occupationaldseases.common.response.CommonCode;
import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.common.response.QueryResult;
import com.as.occupationaldseases.dao.GrjbxxMapper;
import com.as.occupationaldseases.dao.InquiryWgkMapper;
import com.as.occupationaldseases.domain.grjbxx.Grjbxx;
import com.as.occupationaldseases.domain.inquiry_wgk.InquiryWgk;
import com.as.occupationaldseases.domain.inquiry_wgk.responce.WgkCode;
import com.as.occupationaldseases.domain.inquiry_wgk.responce.WgkResult;
import com.as.occupationaldseases.exception.ExceptionCast;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class InquiryWgkService {

    @Resource
    private InquiryWgkMapper inquiryWgkMapper;
    @Resource
    private GrjbxxMapper grjbxxMapper;

    //新增
    public WgkResult add(InquiryWgk inquiryWgk) {
        if(inquiryWgk == null){
            //抛出异常，非法参数异常..指定异常信息的内容
            return new WgkResult(CommonCode.INVALID_PARAM,null);
        }
        //校验Id的唯一性
        //根据Id集合，如果查到说明已经存在，如果查询不到再继续添加
        InquiryWgk inquiryWgk1 = inquiryWgkMapper.selectById(inquiryWgk.getId());
        if(inquiryWgk1!=null){
            //已经存在
            //抛出异常，异常内容就是已经存在
            ExceptionCast.cast(WgkCode.Wgk_ADD_EXISTSNAME);
        }

        //调用dao新增
        inquiryWgk.setId(null);//id自增
        inquiryWgkMapper.insert(inquiryWgk);

        QueryWrapper<Grjbxx> wrapper = new QueryWrapper<>();
        Grjbxx grjbxx = new Grjbxx();
        if(inquiryWgk.getTmh()!=null&&inquiryWgk.getTmh().length()>0){
            grjbxx.setTmh(inquiryWgk.getTmh());
            wrapper.setEntity(grjbxx);
            Grjbxx grjbxx2 = new Grjbxx();
            grjbxx2.setTjflag("1");
            grjbxxMapper.update(grjbxx2,wrapper);
        }

        return new WgkResult(CommonCode.SUCCESS,inquiryWgk);

    }

    //修改
    public WgkResult update(Integer id,InquiryWgk inquiryWgk){
        //根据id从数据库查询页面信息
        InquiryWgk one = inquiryWgkMapper.selectById(id);
        if(one!=null){
            //提交修改
            inquiryWgkMapper.updateById(inquiryWgk);
            return new WgkResult(CommonCode.SUCCESS,inquiryWgk);
        }
        //修改失败
        return new WgkResult(WgkCode.Wgk_NOTEXISTS,null);

    }

    //根据id删除
    public WgkResult delete(Integer id){
        //先查询一下
        InquiryWgk inquiryWgk = inquiryWgkMapper.selectById(id);
        if (inquiryWgk!=null){
            inquiryWgkMapper.deleteById(id);
            return new WgkResult(CommonCode.SUCCESS,inquiryWgk);
        }
        return new WgkResult(WgkCode.Wgk_NOTEXISTS,null);
    }

    /**
     * 查询方法
     * @param current 页码，从1开始记数
     * @param size 每页记录数
     * @param inquiryWgk 查询条件
     * @return
     */
    public QueryResponseResult findList(int current, int size, InquiryWgk inquiryWgk){
        if(inquiryWgk == null){
            inquiryWgk = new InquiryWgk();
        }

        //分页参数
        if(current <=0){
            current = 1;
        }
        current = current -1;
        if(size<=0){
            size = 10;
        }

        IPage<InquiryWgk> page = new Page<>(current, size);
        QueryWrapper<InquiryWgk> wrapper = new QueryWrapper<>();
        wrapper.setEntity(inquiryWgk);

        IPage<InquiryWgk> inquiryWgkIPage = inquiryWgkMapper.selectPage(page, wrapper);

        QueryResult queryResult = new QueryResult();

        queryResult.setList(inquiryWgkIPage.getRecords());//数据列表
        queryResult.setTotal(inquiryWgkIPage.getTotal());//数据总记录数
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS,queryResult);
        return queryResponseResult;
    }

}
