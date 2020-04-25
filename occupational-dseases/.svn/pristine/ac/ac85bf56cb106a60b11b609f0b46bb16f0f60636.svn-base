package com.as.occupationaldseases.dao;

import com.as.occupationaldseases.domain.hazardinfo.Hazardinfo;
import com.as.occupationaldseases.domain.hazardinfo.HazardinfoRelation;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface HazardinfoMapper extends BaseMapper<Hazardinfo> {

    @Select("select MAX(hazard_code) FROM hazardinfo ")
    public Integer maxCode();

    @Select("SELECT\n" +
            "\th.hazard_name,h.hazard_code,h.hazard_type,h.hazard_tj,h.companyname,h.companycode\n" +
            "FROM\n" +
            "\thazardinfo h , tj_hazard_job hj\n" +
            "WHERE\n" +
            "\th.hazard_code in (select hazard_code from tj_hazard_job where jobcode = #{jobCode}) \n" +
            "\tand h.hazard_code = hj.hazard_code group by h.hazard_code LIMIT #{page},#{size}")
    public List<HazardinfoRelation> selectRelation(String jobCode, int page, int size);

    @Select("SELECT\n" +
            "\tcount(1)\n" +
            "FROM\n" +
            "\ttj_hazard_job\n" +
            "WHERE\n" +
            "\tjobcode = #{jobCode} \n" )
    public Integer countRelation(String jobCode);


    @Select("SELECT\n" +
            "\th.hazard_name,h.hazard_code,h.hazard_type,h.hazard_tj,h.companyname,h.companycode\n" +
            "FROM\n" +
            "\thazardinfo h , tj_hazard_job hj\n" +
            "WHERE\n" +
            "\th.hazard_code in (select hazard_code from tj_hazard_job where jobcode in(${jobCode})) \n" +
            "\tand h.hazard_code = hj.hazard_code group by h.hazard_code")
    public List<HazardinfoRelation> selectNoPaging(@Param("jobCode") String jobCode);





    @Select("select * from hazardinfo where hazard_code not in (select hazard_code from tj_hazard_job where jobcode = #{jobCode}) and companycode= #{companycode} LIMIT #{page},#{size}")
    public List<HazardinfoRelation> selectRelationNotin(String jobCode,String companycode, int page, int size);


    @Select("select count(1) from hazardinfo where hazard_code not in (select hazard_code from tj_hazard_job where jobcode = #{jobCode}) and companycode= #{companycode}")
    public Integer countRelationNotin(String jobCode, String companycode);

}
