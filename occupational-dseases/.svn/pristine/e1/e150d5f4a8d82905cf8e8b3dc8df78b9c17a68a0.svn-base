package com.as.occupationaldseases.dao;

import com.as.occupationaldseases.domain.diseaseItem.DiseaseItem;
import com.as.occupationaldseases.domain.diseaseItem.DiseaseItemRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DiseaseItemMapper extends BaseMapper<DiseaseItem> {

    @Select("select MAX(item_code) FROM disease_item ")
    public Integer maxCode();

    @Select("SELECT\n" +
            "\td.iteam_name,d.item_code,d.orgcode,d.orgname,d.state,d.price,d.remarks\n" +
            "FROM\n" +
            "\tdisease_item d, tj_hazard_item hi\n" +
            "WHERE\n" +
            "\td.item_code in (select item_code from tj_hazard_item where hazard_code = #{hazardCode}) and d.item_code = hi.item_code group by d.item_code LIMIT #{page},#{size} ")
    public List<DiseaseItemRelation> selectRelation(String hazardCode, int page, int size);

    @Select("SELECT\n" +
            "\tcount(1)\n" +
            "FROM\n" +
            "\ttj_hazard_item\n" +
            "WHERE\n" +
            "\thazard_code = #{hazardCode}")
    public Integer countRelation(String hazardCode);


    @Select("SELECT\n" +
            "\td.iteam_name,d.item_code,d.orgcode,d.orgname,d.state,d.price,d.remarks\n" +
            "FROM\n" +
            "\tdisease_item d, tj_hazard_item hi \n" +
            "WHERE\n" +
            "\td.item_code in (select item_code from tj_hazard_item where hazard_code in (${hazardCode})) and d.item_code = hi.item_code group by d.item_code")
    public List<DiseaseItemRelation> selectNoPaging(@Param("hazardCode") String hazardCode);

    @Select("select * from disease_item where item_code not in (select item_code from tj_hazard_item where hazard_code = #{hazardCode})  LIMIT #{page},#{size}")
    public List<DiseaseItemRelation> selectRelationNotin(String hazardCode, int page, int size);

    @Select("select count(1) from disease_item where item_code not in (select item_code from tj_hazard_item where hazard_code = #{hazardCode})")
    public Integer countRelationNotin(String hazardCode);
}
