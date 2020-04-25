package com.as.occupationaldseases.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.as.occupationaldseases.common.response.CommonCode;
import com.as.occupationaldseases.common.response.QueryResponseResult;
import com.as.occupationaldseases.common.response.QueryResult;
import com.as.occupationaldseases.dao.GrjbxxMapper;
import com.as.occupationaldseases.dao.GrjbxxXmMapper;
import com.as.occupationaldseases.dao.PersonalProgressMapper;
import com.as.occupationaldseases.domain.grjbxx.Grjbxx;
import com.as.occupationaldseases.domain.grjbxx.Grjbxx_xm;
import com.as.occupationaldseases.domain.personal_progress.PersonalProgress;
import com.as.occupationaldseases.domain.personal_progress.responce.PersonalProgressCode;
import com.as.occupationaldseases.domain.personal_progress.responce.PersonalProgressResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

@Service
public class PersonalProgressService {
    @Resource
    private PersonalProgressMapper mapper;
    @Resource
    private GrjbxxXmMapper grjbxxXmMapper;




    //新增
    public PersonalProgressResult add(String flag, PersonalProgress progress) {
        if (progress == null) {
            //抛出异常，非法参数异常..指定异常信息的内容
            return new PersonalProgressResult(CommonCode.INVALID_PARAM, null);
        }
        //根据身份证查询是否已经新增了此人的进度，新增后就进行更新操作
        if (StringUtils.isEmpty(progress.getSfz())) {
            //抛出异常，非法参数异常..指定异常信息的内容
            return new PersonalProgressResult(CommonCode.SFZ_NOTEXIST, null);
        } else {
            PersonalProgress one = mapper.selectBySfz(progress.getSfz());
            if (one != null){
                //存在执行更新操作
                return this.update(flag,progress);
            }else {
                //存在执行新增操作
                //调用dao新增用户
                Grjbxx_xm grjbxxXm =  new Grjbxx_xm();
                grjbxxXm.setSfz(progress.getSfz());
                QueryWrapper<Grjbxx_xm> wrapper = new QueryWrapper<>();
                wrapper.setEntity(grjbxxXm);
                Grjbxx_xm grjbxx_xms = grjbxxXmMapper.selectOne(wrapper);
                if(grjbxx_xms==null||grjbxx_xms.getJcxmname()==null)
                    return new PersonalProgressResult( PersonalProgressCode.PersonalProgress_NODJ, progress);

                String[] arr = grjbxx_xms.getJcxmname().split(","); // 用,分割
                HashMap<String, Integer> map = new HashMap<>();
                for (String str:arr)
                    map.put(str,0);
                progress.setTjxm(grjbxx_xms.getJcxmname());
                progress.setZcount(arr.length);//总检项目数为体检项目的个数
                int yjcount = 0;//已检项目数初始为0
                int wjcount = progress.getZcount();//未检项目数初始为项目总数
                progress.setWjcount(wjcount);
                progress.setYjcount(yjcount);
                progress.setTmh(grjbxx_xms.getTmh());
                progress.setCompanycode(grjbxx_xms.getCompanycode());
                progress.setCompanyname(grjbxx_xms.getCompanyname());
                progress.setXm(grjbxx_xms.getName());
                progress.setTjrq(new Date());
                progress.setTjxmflag(JSON.toJSONString(map));
                progress.setId(null);//id自增
                mapper.insert(progress);
                return new PersonalProgressResult(CommonCode.SUCCESS, progress);
            }
        }

    }

    //修改用户
    public PersonalProgressResult update(String flag,PersonalProgress progress) {
        //从数据库查询页面信息
        PersonalProgress one = mapper.selectBySfz(progress.getSfz());
        Map<String,Integer> map = JSON.parseObject(one.getTjxmflag(),Map.class);
        String[] arr = one.getTjxm().split(","); // 用,分割
        progress.setTjxm(one.getTjxm());
        progress.setZcount(arr.length);//总检项目数为体检项目的个数
        int yjcount = 0;
        int wjcount = one.getZcount();//未检项目数初始为项目总数
        if (one != null) {
            if (flag.equals("nk")){
                map.put("内科问诊",1);
            }
            if (flag.equals("wk")){
                map.put("外科问诊",1);
            }
            if (flag.equals("wgk")){
                map.put("五官科问诊",1);
            }
            if (flag.equals("sjk")){
                map.put("神经科问诊",1);
            }
            if (flag.equals("zz")){
                map.put("症状信息",1);
            }
            Collection<Integer> values = map.values();
            for (Integer value : values) {
                if (value == 1){
                    ++yjcount;
                    --wjcount;
                }
            }
            progress.setYjcount(yjcount);
            progress.setWjcount(wjcount);
            progress.setTjxmflag(JSON.toJSONString(map));//重新转成json字符串
            //提交修改
            QueryWrapper<PersonalProgress> wrapper = new QueryWrapper<>();
            wrapper.eq("sfz",progress.getSfz());
            mapper.update(progress,wrapper);
            return new PersonalProgressResult(CommonCode.SUCCESS, progress);
        }
        //修改失败
        return new PersonalProgressResult(PersonalProgressCode.PersonalProgress_NOTEXISTS, null);

    }

    /**
     * 分页查询方法
     *
     * @param current  页码，从1开始记数
     * @param size     每页记录数
     * @param progress 查询条件
     * @return
     */
    public QueryResponseResult findList(int current, int size, PersonalProgress progress) {
        if (progress == null) {
            progress = new PersonalProgress();
        }

        //分页参数
        if (current <= 0) {
            current = 1;
        }
   //     current = current - 1;
        if (size <= 0) {
            size = 10;
        }

        IPage<PersonalProgress> page = new Page<>(current, size);
        QueryWrapper<PersonalProgress> wrapper = new QueryWrapper<>();
        wrapper.setEntity(progress);

        IPage<PersonalProgress> progressIPage = mapper.selectPage(page, wrapper);

        QueryResult queryResult = new QueryResult();

        queryResult.setList(progressIPage.getRecords());//数据列表
        queryResult.setTotal(progressIPage.getTotal());//数据总记录数
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS, queryResult);
        return queryResponseResult;
    }


    /**
     * 查询体检项目
     * @param flag，0总检，1未检，2已检
     * @param sfz
     * @return
     */
    public List<String> getTjxm(Integer flag, String sfz){
        PersonalProgress personalProgress = mapper.selectBySfz(sfz);
        Map<String,Integer> map = JSON.parseObject(personalProgress.getTjxmflag(),Map.class);
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        List<String> wtjxm = new ArrayList<>();  //未体检项目
        List<String> ytjxm = new ArrayList<>();  //已体检项目
        List<String> ztjxm = new ArrayList<>();  //总体检项目
        for (Map.Entry<String, Integer> entry : entries) {
            //当value等于0时为未体检
            if (entry.getValue()==0){
                wtjxm.add(entry.getKey());
            }
            //当value等于1时为已体检
            if (entry.getValue()==1){
                ytjxm.add(entry.getKey());
            }
            ztjxm.add(entry.getKey());
        }
        if (flag == 0){
            return ztjxm;
        }
        if (flag == 1){
            return wtjxm;
        }
        if (flag == 2){
            return ytjxm;
        }
        return new ArrayList<>();
    }

}
