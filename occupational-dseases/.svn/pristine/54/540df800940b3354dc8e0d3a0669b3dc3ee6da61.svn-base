package com.as.occupationaldseases.service;

import com.as.occupationaldseases.common.response.CommonCode;
import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.common.response.QueryResult;
import com.as.occupationaldseases.dao.GrjbxxMapper;
import com.as.occupationaldseases.dao.InquirySjkMapper;
import com.as.occupationaldseases.domain.grjbxx.Grjbxx;
import com.as.occupationaldseases.domain.inquiry_sjk.InquirySjk;
import com.as.occupationaldseases.domain.inquiry_sjk.responce.SjkCode;
import com.as.occupationaldseases.domain.inquiry_sjk.responce.SjkResult;
import com.as.occupationaldseases.exception.ExceptionCast;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class InquirySjkService {

    @Resource
    private InquirySjkMapper inquirySjkMapper;

    @Resource
    private GrjbxxMapper grjbxxMapper;

    //新增
    public SjkResult add(InquirySjk inquirySjk) {
        if(inquirySjk == null){
            //抛出异常，非法参数异常..指定异常信息的内容
            return new SjkResult(CommonCode.INVALID_PARAM,null);
        }
        //校验Id的唯一性
        //根据Id集合，如果查到说明已经存在，如果查询不到再继续添加
        InquirySjk inquirySjk1 = inquirySjkMapper.selectById(inquirySjk.getId());
        if(inquirySjk1!=null){
            //已经存在
            //抛出异常，异常内容就是已经存在
            ExceptionCast.cast(SjkCode.Sjk_ADD_EXISTSNAME);
        }

        //调用dao新增
        inquirySjk.setId(null);//id自增
        inquirySjkMapper.insert(inquirySjk);
        //更新个人信息主表
        QueryWrapper<Grjbxx> wrapper = new QueryWrapper<>();
        Grjbxx grjbxx = new Grjbxx();
        if(inquirySjk.getTmh()!=null&&inquirySjk.getTmh().length()>0){
            grjbxx.setTmh(inquirySjk.getTmh());
            wrapper.setEntity(grjbxx);
            Grjbxx grjbxx2 = new Grjbxx();
            grjbxx2.setTjflagsjk("1");
            grjbxxMapper.update(grjbxx2,wrapper);
        }


        return new SjkResult(CommonCode.SUCCESS,inquirySjk);

    }

    //修改
    public SjkResult update(Integer id,InquirySjk inquirySjk){
        //根据id从数据库查询页面信息
        InquirySjk one = inquirySjkMapper.selectById(id);
        if(one!=null){
            //提交修改
            inquirySjkMapper.updateById(inquirySjk);
            return new SjkResult(CommonCode.SUCCESS,inquirySjk);
        }
        //修改失败
        return new SjkResult(SjkCode.Sjk_NOTEXISTS,null);

    }

    //根据id删除
    public SjkResult delete(Integer id){
        //先查询一下
        InquirySjk inquirySjk = inquirySjkMapper.selectById(id);
        if (inquirySjk!=null){
            inquirySjkMapper.deleteById(id);
            return new SjkResult(CommonCode.SUCCESS,inquirySjk);
        }
        return new SjkResult(SjkCode.Sjk_NOTEXISTS,null);
    }

    /**
     * 查询方法
     * @param current 页码，从1开始记数
     * @param size 每页记录数
     * @param inquirySjk 查询条件
     * @return
     */
    public QueryResponseResult findList(int current, int size, InquirySjk inquirySjk){
        if(inquirySjk == null){
            inquirySjk = new InquirySjk();
        }

        //分页参数
        if(current <=0){
            current = 1;
        }
        current = current -1;
        if(size<=0){
            size = 10;
        }

        IPage<InquirySjk> page = new Page<>(current, size);
        QueryWrapper<InquirySjk> wrapper = new QueryWrapper<>();
        wrapper.setEntity(inquirySjk);

        IPage<InquirySjk> inquirySjkIPage = inquirySjkMapper.selectPage(page, wrapper);

        QueryResult queryResult = new QueryResult();

        queryResult.setList(inquirySjkIPage.getRecords());//数据列表
        queryResult.setTotal(inquirySjkIPage.getTotal());//数据总记录数
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS,queryResult);
        return queryResponseResult;
    }

}
