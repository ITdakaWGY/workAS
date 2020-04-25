package com.as.occupationaldseases.service;

import com.as.occupationaldseases.common.response.CommonCode;
import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.common.response.QueryResult;
import com.as.occupationaldseases.dao.DiseaseDeviceMapper;
import com.as.occupationaldseases.dao.DiseaseItemMapper;
import com.as.occupationaldseases.domain.diseaseDevice.DiseaseDevice;
import com.as.occupationaldseases.domain.diseaseDevice.responce.DiseaseDeviceCode;
import com.as.occupationaldseases.domain.diseaseDevice.responce.DiseaseDeviceResult;
import com.as.occupationaldseases.exception.ExceptionCast;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DiseaseDeviceService {
    @Resource
    private DiseaseDeviceMapper diseaseDeviceMapper;

    //新增
    public DiseaseDeviceResult add(DiseaseDevice diseaseDevice) {
        if (diseaseDevice == null) {
            //抛出异常，非法参数异常..指定异常信息的内容
            return new DiseaseDeviceResult(CommonCode.INVALID_PARAM, null);
        }
        //校验用户Id的唯一性
        //根据用户Id集合，如果查到说明此用户已经存在，如果查询不到再继续添加
        DiseaseDevice diseaseDevice1 = diseaseDeviceMapper.selectById(diseaseDevice.getId());
        if (diseaseDevice1 != null) {
            //用户已经存在
            //抛出异常，异常内容就是用户已经存在
            ExceptionCast.cast(DiseaseDeviceCode.DISEASEDEVICE_ADD_EXISTSNAME);
        }
        Integer row = diseaseDeviceMapper.maxCode();
        if(row==null||row ==0){
            row=1000;
        }
        diseaseDevice.setDeviceCode(String.valueOf(row+1));
        //调用dao新增用户
        diseaseDeviceMapper.insert(diseaseDevice);
        return new DiseaseDeviceResult(CommonCode.SUCCESS, diseaseDevice);

    }


    //根据id修改
    public DiseaseDeviceResult update(int id, DiseaseDevice diseaseDevice) {
        //根据id从数据库查询页面信息
        DiseaseDevice one = diseaseDeviceMapper.selectById(id);
        if (one != null) {
            //提交修改
            diseaseDeviceMapper.updateById(diseaseDevice);
            return new DiseaseDeviceResult(CommonCode.SUCCESS, diseaseDevice);
        }
        //修改失败
        return new DiseaseDeviceResult(DiseaseDeviceCode.DISEASEDEVICE_NOTEXISTS, null);
    }


    //根据id删除
    public DiseaseDeviceResult delete(int id) {
        //先查询一下
        DiseaseDevice diseaseDevice = diseaseDeviceMapper.selectById(id);
        if (diseaseDevice != null) {
            diseaseDeviceMapper.deleteById(id);
            return new DiseaseDeviceResult(CommonCode.SUCCESS, diseaseDevice);
        }
        return new DiseaseDeviceResult(DiseaseDeviceCode.DISEASEDEVICE_NOTEXISTS, null);
    }


    /**
     * 分页查询方法
     *
     * @param current       页码，从1开始记数
     * @param size          每页记录数
     * @param diseaseDevice 查询条件
     * @return
     */
    public QueryResponseResult findList(int current, int size, DiseaseDevice diseaseDevice) {
        if (diseaseDevice == null) {
            diseaseDevice = new DiseaseDevice();
        }
        //分页参数
        if (current <= 0) {
            current = 1;
        }
//        current = current - 1;

        if (size <= 0) {
            size = 10;
        }
        IPage<DiseaseDevice> page = new Page<>(current, size);
        QueryWrapper<DiseaseDevice> wrapper = new QueryWrapper<>();
        wrapper.setEntity(diseaseDevice);

        IPage<DiseaseDevice> companyinfoIPage = diseaseDeviceMapper.selectPage(page, wrapper);
        QueryResult queryResult = new QueryResult();

        queryResult.setList(companyinfoIPage.getRecords());//数据列表
        queryResult.setTotal(companyinfoIPage.getTotal());//数据总记录数
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS, queryResult);
        return queryResponseResult;
    }


    //查询单条数据
    public DiseaseDeviceResult findBySingle(DiseaseDevice diseaseDevice) {
        QueryWrapper<DiseaseDevice> wrapper = new QueryWrapper<>();
        wrapper.setEntity(diseaseDevice);
        Integer row = diseaseDeviceMapper.selectCount(wrapper);
        if (row == 1) {
            return new DiseaseDeviceResult(CommonCode.SUCCESS, diseaseDeviceMapper.selectOne(wrapper));
        } else if (row > 1) {
            return new DiseaseDeviceResult(DiseaseDeviceCode.DISEASEDEVICE_GREATERTHAN, null);
        }
        return new DiseaseDeviceResult(DiseaseDeviceCode.DISEASEDEVICE_NOTEXISTS, null);
    }


    //根据条件查询数据条数
    public Integer count(DiseaseDevice diseaseDevice) {
        QueryWrapper<DiseaseDevice> wrapper = new QueryWrapper<>();
        wrapper.setEntity(diseaseDevice);
        return diseaseDeviceMapper.selectCount(wrapper);
    }
}
