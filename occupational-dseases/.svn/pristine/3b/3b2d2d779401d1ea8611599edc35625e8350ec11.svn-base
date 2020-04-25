package com.as.occupationaldseases.dao;

import com.as.occupationaldseases.domain.grjbxx.Grjbxx_xm;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GrjbxxXmMapper  extends BaseMapper<Grjbxx_xm> {

    @Select("Select jcxm,jcxmname from grjbxx_xm where tmh = #{tmh}")
    public List<Grjbxx_xm> findByTmh(String tmh);
}
