package com.as.android.service;

import com.as.android.common.response.CommonCode;
import com.as.android.common.response.QueryResponseResult;
import com.as.android.common.response.QueryResult;
import com.as.android.dao.MainMapper;
import com.as.android.domain.main.Main;
import com.as.android.domain.main.responce.MainCode;
import com.as.android.domain.main.responce.MainResult;
import com.as.android.exception.ExceptionCast;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class MainService {
    @Resource
    private MainMapper mainMapper;

    public MainResult add(Main mian){
        if (mian == null) {
            //抛出异常，非法参数异常..指定异常信息的内容
            return new MainResult(CommonCode.INVALID_PARAM, null);
        }
        //校验Id的唯一性
        //如果查到说明此已经存在，如果查询不到再继续添加
        Main main = mainMapper.selectById(mian.getAppId());
        if (main != null) {
            //已经存在
            //抛出异常，异常内容就是已经存在
            ExceptionCast.cast(MainCode.Main_ADD_EXISTSNAME);
        }

        //调用dao新增
        mian.setAppId(null);//id自增
        mian.setCreatTime(new Date());
        mainMapper.insert(mian);
        return new MainResult(CommonCode.SUCCESS, mian);

    }

    /**
     * 查询方法
     *
     * @param current 页码，从1开始记数
     * @param size    每页记录数
     * @param main    查询条件
     * @return
     */
    public QueryResponseResult findList(int current, int size, Main main) {
        if (main == null) {
            main = new Main();
        }

        //分页参数
        if (current <= 0) {
            current = 1;
        }

        if (size <= 0) {
            size = 10;
        }

        IPage<Main> page = new Page<>(current, size);
        QueryWrapper<Main> wrapper = new QueryWrapper<>();
        wrapper.setEntity(main);

        IPage<Main> mainIPage = mainMapper.selectPage(page, wrapper);

        QueryResult queryResult = new QueryResult();

        queryResult.setList(mainIPage.getRecords());//数据列表
        queryResult.setTotal(mainIPage.getTotal());//数据总记录数
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS, queryResult);
        return queryResponseResult;
    }

}
