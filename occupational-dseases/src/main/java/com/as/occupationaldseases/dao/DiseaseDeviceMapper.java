package com.as.occupationaldseases.dao;

import com.as.occupationaldseases.domain.diseaseDevice.DiseaseDevice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DiseaseDeviceMapper extends BaseMapper<DiseaseDevice> {

    @Select("select MAX(DEVICE_CODE) FROM disease_device ")
    public Integer maxCode();
}
