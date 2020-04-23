package com.as.publichealth.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.as.publichealth.common.response.CommonCode;
import com.as.publichealth.common.response.QueryResponseResult;
import com.as.publichealth.common.response.QueryResult;
import com.as.publichealth.dao.HealthPersonMapper;
import com.as.publichealth.dao.TjycnumMapper;
import com.as.publichealth.domain.person.HealthPerson;
import com.as.publichealth.domain.tjycnum.Tjycnum;
import com.as.publichealth.domain.tjycnum.responce.TjycnumCode;
import com.as.publichealth.domain.tjycnum.responce.TjycnumResult;
import com.as.publichealth.exception.ExceptionCast;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


@Service
public class TjycnumService {
    private final static Logger log = LoggerFactory.getLogger(TjycnumService.class);
    @Resource
    private TjycnumMapper tjycnumMapper;
    @Resource
    private HealthPersonMapper healthPersonMapper;

    //新增体检异常统计
    public TjycnumResult add(String orgno,String tjrq,String orgname) {
        if (orgno == null) {
            //抛出异常，非法参数异常..指定异常信息的内容
            return new TjycnumResult(CommonCode.INVALID_PARAM, null);
        }
        Date tjrj = java.sql.Date.valueOf(tjrq);
        Tjycnum tjycnum = new Tjycnum();
        tjycnum.setOrgno(orgno);
        tjycnum.setTjrq(tjrj);

        //查询所有的数据
       List<Integer> list = selectPerson(tjycnum,8);
       log.info("生成的数据是:{}"+list);
        //把集合中的数据存到实体类中
        Tjycnum tjycnum1 = insertBean(list);
        //调用dao新增体检异常统计
        tjycnum1.setId(null);//id自增
        tjycnum1.setOrgno(orgno);
        tjycnum1.setTjrq(tjrj);
        tjycnum1.setOrgname(orgname);
        tjycnumMapper.insert(tjycnum1);
        return new TjycnumResult(CommonCode.SUCCESS, tjycnum1);

    }

    //新增用户
    public TjycnumResult add2(Tjycnum tjycnum) {
        if (tjycnum == null) {
            //抛出异常，非法参数异常..指定异常信息的内容
            return new TjycnumResult(CommonCode.INVALID_PARAM, null);
        }
        //校验用户Id的唯一性
        //根据用户Id集合，如果查到说明此用户已经存在，如果查询不到再继续添加
        Tjycnum tjycnum1 = tjycnumMapper.selectById(tjycnum.getId());
        if (tjycnum1 != null) {
            //用户已经存在
            //抛出异常，异常内容就是用户已经存在
            ExceptionCast.cast(TjycnumCode.Tjycnum_ADD_EXISTSNAME);
        }

        //调用dao新增用户
        tjycnum.setId(null);//id自增
        tjycnumMapper.insert(tjycnum);
        return new TjycnumResult(CommonCode.SUCCESS, tjycnum);

    }

    /**
     * 把集合中的数据存到实体类中
     * @param list
     * @return
     */
    private Tjycnum  insertBean(List<Integer> list){
        Field[] fields = Tjycnum.class.getDeclaredFields();
        HashMap<String, Object> map = new HashMap<>();
        int k = 0;
        for (int i = 8; i < fields.length; i++) {
            if (fields[i].getName().contains("jknum")||fields[i].getName().contains("jkm")||fields[i].getName().contains("jkw")) {
                continue;
            }
                 log.info("生成的key为{},value为{}"+fields[i].getName(),list.get(k));
                map.put(fields[i].getName(),list.get(k));
                 k++;
            if(fields[i].getName().equals("s80mxnzzw"))
            break;
        }
        Tjycnum tjycnum1 = JSON.parseObject(JSON.toJSONString(map), Tjycnum.class);
        log.info("生成的对象为:{}",tjycnum1);
        return tjycnum1;
    }

    /**
     * 查询数据
     * @param tjycnum
     * @return
     */
    private List<Integer>  selectPerson(Tjycnum tjycnum,int k){
        List<Integer> list = new ArrayList<>();
        HashMap<String, Object> map = new HashMap<>();
        map.put("orgno",tjycnum.getOrgno());
        map.put("tjrq",tjycnum.getTjrq().toString());
        Field[] fields = HealthPerson.class.getDeclaredFields();
        for (int i = k+1; i < fields.length; i++) {
                switch (k){
                    case 8:
                        map.put("age","65");
                        if(i==10||i==11) continue;
                        break;
                    case 9:
                        map.put("age","71");
                        if(i==11) continue;
                        break;
                    case 10:
                        map.put("age","80");
                        break;
                }
                if(map.containsKey("sex"))
                    map.remove("sex");
               log.info("传入的param1参数：{}",fields[i].getName());
                  map.put("param1",fields[i].getName());
                log.info("传入的参数：{}",map);
                //查询全部的
                Integer whole = healthPersonMapper.queryCount(map);
                list.add(whole);
                //查询男的
                map.put("sex","1");
                Integer male = healthPersonMapper.queryCount(map);
                list.add(male);
                //查询女的
                map.put("sex","2");
                Integer female = healthPersonMapper.queryCount(map);
                list.add(female);

            if(fields[i].getName().equals("mxnzz"))
                break;
        }
        if(k<10)
            list.addAll(selectPerson(tjycnum,++k));

        return  list;
    }


    //修改体检异常统计
    public TjycnumResult update(Long id, Tjycnum tjycnum) {
        //根据id从数据库查询页面信息
        Tjycnum one = tjycnumMapper.selectById(id);
        if (one != null) {
            //提交修改
            tjycnumMapper.updateById(tjycnum);
            return new TjycnumResult(CommonCode.SUCCESS, tjycnum);
        }
        //修改失败
        return new TjycnumResult(TjycnumCode.Tjycnum_NOTEXISTS, null);

    }

    //根据id删除体检异常统计
    public TjycnumResult delete(Long id) {
        //先查询一下
        Tjycnum tjycnum = tjycnumMapper.selectById(id);
        if (tjycnum != null) {
            tjycnumMapper.deleteById(id);
            return new TjycnumResult(CommonCode.SUCCESS, tjycnum);
        }
        return new TjycnumResult(TjycnumCode.Tjycnum_NOTEXISTS, null);
    }

    /**
     * 体检异常统计查询方法
     *
     * @param current 页码，从1开始记数
     * @param size    每页记录数
     * @param tjycnum    查询条件
     * @return
     */
    public QueryResponseResult findList(int current, int size, Tjycnum tjycnum) {
        if (tjycnum == null) {
            tjycnum = new Tjycnum();
        }

        //分页参数
        if (current <= 0) {
            current = 1;
        }
        if (size <= 0) {
            size = 10;
        }

        IPage<Tjycnum> page = new Page<>(current, size);
        QueryWrapper<Tjycnum> wrapper = new QueryWrapper<>();
        wrapper.setEntity(tjycnum);

        IPage<Tjycnum> tjycnumIPage = tjycnumMapper.selectPage(page, wrapper);

        QueryResult queryResult = new QueryResult();

        queryResult.setList(tjycnumIPage.getRecords());//数据列表
        queryResult.setTotal(tjycnumIPage.getTotal());//数据总记录数
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS, queryResult);
        return queryResponseResult;
    }

    public String exportExcel(){
        try {
            //当前用户桌面路径
            FileSystemView fsv = FileSystemView.getFileSystemView();
            File com=fsv.getHomeDirectory(); //这便是读取桌面路径的方法了
            String path = com.getPath();
            //转换路径格式
            String replace = path.replace("\\", "\\\\");
            //指定文件输出位置
            String fileName =replace+ "\\"+"报表.xlsx";

            //获取resource下文件路径
            InputStream inputStream=this.getClass().getResourceAsStream("/模板.xlsx");

            // 保存路径和指定写用哪个class去写，然后文件流会自动关闭
            EasyExcel.write(fileName, Tjycnum.class)
                    //指定模板路径
                    .withTemplate(inputStream)
                    //不需要写入表头
                    .needHead(false)
                    //然后写到第一个sheet，名字为模板
                    .sheet("模板")
                    //写入的数据
                    .doWrite(tjycnumMapper.findAll());

            return "已成功导出到桌面";
        } catch (Exception e) {
            e.printStackTrace();
            return "导出失败";
        }
    }



}
