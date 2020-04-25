package com.as.occupationaldseases.service;

import com.as.occupationaldseases.common.response.CommonCode;
import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.common.response.QueryResult;
import com.as.occupationaldseases.dao.TemplateMapper;
import com.as.occupationaldseases.domain.template.Template;
import com.as.occupationaldseases.domain.template.responce.TemplateCode;
import com.as.occupationaldseases.domain.template.responce.TemplateResult;
import com.as.occupationaldseases.exception.ExceptionCast;
import com.as.occupationaldseases.utils.StringUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class TemplateService {

    @Resource
    private TemplateMapper templateMapper;
    //新增模板
    public TemplateResult add(Template template) {
        if(template == null){
            //抛出异常，非法参数异常..指定异常信息的内容
            return new TemplateResult(CommonCode.INVALID_PARAM,null);
        }
        //校验模板Id的唯一性
        //根据模板Id集合，如果查到说明此模板已经存在，如果查询不到再继续添加
        Template template1 = templateMapper.selectById(template.getId());
        if(template1!=null){
            //模板已经存在
            //抛出异常，异常内容就是模板已经存在
            ExceptionCast.cast(TemplateCode.Template_ADD_EXISTSNAME);
        }

        //先删除再增加
        templateMapper.del(template.getYsxx());
        //调用dao新增模板
        template.setId(null);//id自增
        templateMapper.insert(template);
        return new TemplateResult(CommonCode.SUCCESS,template);

    }

    //修改模板
    public TemplateResult update(Long id,Template template){
        //根据id从数据库查询页面信息
        Template one = templateMapper.selectById(id);
        if(one!=null){
            //提交修改
            templateMapper.updateById(template);
            return new TemplateResult(CommonCode.SUCCESS,template);
        }
        //修改失败
        return new TemplateResult(TemplateCode.Template_NOTEXISTS,null);

    }

    //根据id删除模板
    public TemplateResult delete(Long id){
        //先查询一下
        Template template = templateMapper.selectById(id);
        if (template!=null){
            templateMapper.deleteById(id);
            return new TemplateResult(CommonCode.SUCCESS,template);
        }
        return new TemplateResult(TemplateCode.Template_NOTEXISTS,null);
    }

    /**
     * 模板查询方法
     * @param current 页码，从1开始记数
     * @param size 每页记录数
     * @param template 查询条件
     * @return
     */
    public QueryResponseResult findList(int current, int size, Template template){
        if(template == null){
            template = new Template();
        }

        //分页参数
        if(current <=0){
            current = 1;
        }
        current = current -1;
        if(size<=0){
            size = 10;
        }

        IPage<Template> page = new Page<>(current, size);
        QueryWrapper<Template> wrapper = new QueryWrapper<>();
        wrapper.setEntity(template);

        IPage<Template> templateIPage = templateMapper.selectPage(page, wrapper);

        QueryResult queryResult = new QueryResult();

        queryResult.setList(templateIPage.getRecords());//数据列表
        queryResult.setTotal(templateIPage.getTotal());//数据总记录数
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS,queryResult);
        return queryResponseResult;
    }

    /**
     * 模板查询所有方法
     * @param template 查询条件
     * @return
     */
    public QueryResponseResult findAll(Template template){
        if(template == null){
            template = new Template();
        }
        if (StringUtil.isEmpty(template.getYsxx())){
            return new QueryResponseResult(CommonCode.INVALID_PARAM,null);
        }else {
            List<Template> all = templateMapper.findAll(template.getYsxx());
            QueryResult queryResult = new QueryResult();
            queryResult.setList(all);
            QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS,queryResult);
            return queryResponseResult;
        }
    }

}
