package com.as.publichealth.service;

import com.as.publichealth.common.response.CommonCode;
import com.as.publichealth.common.response.QueryResponseResult;
import com.as.publichealth.common.response.QueryResult;
import com.as.publichealth.common.response.ResponseResult;
import com.as.publichealth.dao.HealthPersonMapper;
import com.as.publichealth.dao.HealthZytzMapper;
import com.as.publichealth.domain.labtest.HealthLabtest;
import com.as.publichealth.domain.labtest.HealthLabtestVo;
import com.as.publichealth.domain.main.HealthMainVo;
import com.as.publichealth.domain.person.HealthPerson;
import com.as.publichealth.domain.person.responce.HealthPersonCode;
import com.as.publichealth.domain.person.responce.HealthPersonResult;
import com.as.publichealth.domain.tjwz.HealthTjwzVo;
import com.as.publichealth.domain.zytz.HealthZytzVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


@Service
public class HealthPersonService {

    @Resource
    private HealthPersonMapper healthPersonMapper;

    @Resource
    private HealthZytzService healthZytzService;

    @Resource
    private HealthPersonIService healthPersonIService;

    @Resource
    private HealthMainService healthMainService;

    @Resource
    private HealthLabtestService healthLabtestService;

    @Resource
    private HealthTjwzService healthTjwzService;

    //新增个人体检异常
    public ResponseResult add(String orgno, String stratTjrq, String endTjrq) {
        List<HealthPerson> healthPersonList = new ArrayList<>();
        List<HealthMainVo> healthMainVoList = healthMainService.findList(orgno, stratTjrq, endTjrq);
        List<HealthLabtestVo> healthLabtestVoList = healthLabtestService.findList(orgno, stratTjrq, endTjrq);
        List<HealthTjwzVo> healthTjwzVoList = healthTjwzService.findList(orgno, stratTjrq, endTjrq);
        List<HealthZytzVo> healthZytzVoList = healthZytzService.findList(orgno, stratTjrq, endTjrq);
        List<String> tmhList = this.findTmhList(orgno, stratTjrq, endTjrq);

        if(healthMainVoList==null || healthMainVoList.size() == 0){
            return new ResponseResult(CommonCode.EMPTY);
        }
       //重复的去除，使用Iterator迭代器
        Iterator<HealthMainVo> it = healthMainVoList.iterator();
        while(it.hasNext()){
            HealthMainVo healthMainVo = it.next();
            for (String tmh : tmhList) {
                if (tmh.equals(healthMainVo.getTmh())){
                    it.remove();
                }
            }
        }

        for (HealthMainVo healthMainVo : healthMainVoList) {
            //体检编号、条码号、体检日期、机构号、机构名、姓名、性别、年龄
            HealthPerson healthPerson = new HealthPerson();
            healthPerson.setTjbh(healthMainVo.getTjbh());
            healthPerson.setTmh(healthMainVo.getTmh());
            healthPerson.setTjrq(healthMainVo.getTjrq());
            healthPerson.setOrgno(healthMainVo.getOrgno().toString());
            healthPerson.setOrgname(healthMainVo.getOrgname());
            healthPerson.setXm(healthMainVo.getXm());
            healthPerson.setSex(healthMainVo.getSex());
            if (healthMainVo.getAge()!=null && healthMainVo.getAge()>=65 && healthMainVo.getAge()<= 70){
                healthPerson.setS65("√");
            }
            if (healthMainVo.getAge()!=null && healthMainVo.getAge()>=71 && healthMainVo.getAge()<= 80){
                healthPerson.setS71("√");
            }
            if (healthMainVo.getAge()!=null && healthMainVo.getAge()>=81){
                healthPerson.setS80("√");
            }
            //高血压，体质指数、血常规、尿常规、血糖、心电图、肝功能、肾功能、血脂、B超
            for (HealthLabtestVo healthLabtestVo : healthLabtestVoList) {
                if (healthLabtestVo.getTmh().equals(healthMainVo.getTmh())){
                    //高血压
                    if ((healthLabtestVo.getYcgy() >= 140 && healthLabtestVo.getYcgy() >= healthLabtestVo.getZcgy()) || (healthLabtestVo.getZcgy() >= 140 && healthLabtestVo.getZcgy() > healthLabtestVo.getYcgy())){
                        healthPerson.setXyyc("√");
                        healthPerson.setGxy("√");
                        healthPerson.setMxgxy("√");
                    }
                    //体质指数
                    if (healthLabtestVo.getBmi()>=28){
                        healthPerson.setTzzs("√");
                    }
                    //血常规
                    if (healthLabtestVo.getWbc()<4.0 || healthLabtestVo.getWbc()>10.0 || healthLabtestVo.getHgb() <110.0 || healthLabtestVo.getHgb() >160 || healthLabtestVo.getPlt() < 100.0 || healthLabtestVo.getPlt() >315){
                        healthPerson.setXcgyc("√");
                    }
                    //尿常规
                    if (healthLabtestVo.getNblo().contains("+") || healthLabtestVo.getNglu().contains("+") || healthLabtestVo.getNket().contains("+") || healthLabtestVo.getNpro().contains("+")){
                        healthPerson.setNcgyc("√");
                    }
                    //血糖
                    if (healthLabtestVo.getGlu()<3.9 ||healthLabtestVo.getGlu()>6.1){
                        healthPerson.setXtyc("√");
                    }
                    //心电图
                    if (healthLabtestVo.getXdt() !=null && !"1".equals(healthLabtestVo.getXdt())){
                        healthPerson.setXdtyc("√");
                    }
                    //肝功能
                    if (healthLabtestVo.getAst() < 0.0 || healthLabtestVo.getAst() > 40.0 || healthLabtestVo.getTbil() < 1.7 || healthLabtestVo.getTbil() > 20.0){
                        healthPerson.setGgnyc("√");
                    }
                    //肾功能
                    if (healthLabtestVo.getCrea() < 53.0 || healthLabtestVo.getCrea() >115.0){
                        healthPerson.setSgnyc("√");
                    }
                    //血脂
                    if (healthLabtestVo.getCho() < 3.0 || healthLabtestVo.getCho() >6.5 || healthLabtestVo.getTg() < 0.71 ||  healthLabtestVo.getTg() > 3.12 || healthLabtestVo.getLdlc() < 0.0 || healthLabtestVo.getLdlc()>3.12 || healthLabtestVo.getHdlc() <0.9 || healthLabtestVo.getHdlc() <1.8){
                        healthPerson.setXzyc("√");
                    }
                    //B超
                    if ("2".equals(healthLabtestVo.getBc())){
                        healthPerson.setBcyc("√");
                    }

                }
            }

            //自理能力评估、脑血管疾病、肾脏疾病、心血管疾病、眼部疾病、神经系统疾病、糖尿病、慢性支气管炎、慢性阻塞性肺病、恶性肿瘤、老年性骨关节病、其他、
            for (HealthTjwzVo healthTjwzVo : healthTjwzVoList) {
                if (healthTjwzVo.getTmh().equals(healthMainVo.getTmh())){
                    //自理能力评估
                    if (healthTjwzVo.getZlnlpg()!=null && healthTjwzVo.getZlnlpg() == 4){
                        healthPerson.setZlnlpg("√");
                    }
                    //脑血管疾病
                    if (healthTjwzVo.getNxgjb()!=null && !"1".equals(healthTjwzVo.getNxgjb())){
                        healthPerson.setNxgjb("√");
                    }
                    //肾脏疾病
                    if (healthTjwzVo.getSzjb()!=null && !"1".equals(healthTjwzVo.getSzjb())){
                        healthPerson.setSzjb("√");
                    }
                    //心血管疾病
                    if ((healthTjwzVo.getXzjb() != null && !"1".equals(healthTjwzVo.getXzjb())) || (healthTjwzVo.getXgjb() != null && !"1".equals(healthTjwzVo.getXgjb()))){
                        healthPerson.setXxgjb("√");
                    }
                    //眼部疾病
                    if (healthTjwzVo.getYbjb() != null && !"1".equals(healthTjwzVo.getYbjb())){
                        healthPerson.setYbjb("√");
                    }
                    //神经系统疾病
                    if (healthTjwzVo.getSjxtjb() != null && !"1".equals(healthTjwzVo.getSjxtjb())){
                        healthPerson.setQtsjxtjb("√");
                    }
                    //糖尿病
                    if (healthTjwzVo.getQtxtjb()!=null && "2".equals(healthTjwzVo.getQtxtjb())){
                        healthPerson.setTnb("√");
                        healthPerson.setMxtnb("√");
                    }
                    //慢性支气管炎
                    if (healthTjwzVo.getQtxtjb()!=null && "3".equals(healthTjwzVo.getQtxtjb())){
                        healthPerson.setMxzqgy("√");
                    }
                    //慢性阻塞性肺病
                    if (healthTjwzVo.getQtxtjb()!=null && "4".equals(healthTjwzVo.getQtxtjb())){
                        healthPerson.setMxzsxfb("√");
                    }
                    //恶性肿瘤
                    if (healthTjwzVo.getQtxtjb()!=null && "5".equals(healthTjwzVo.getQtxtjb())){
                        healthPerson.setExzl("√");
                    }
                    //老年性骨关节病
                    if (healthTjwzVo.getQtxtjb()!=null && "6".equals(healthTjwzVo.getQtxtjb())){
                        healthPerson.setLnxggjb("√");
                    }
                    //其他
                    if (healthTjwzVo.getQtxtjb()!=null && "7".equals(healthTjwzVo.getQtxtjb())){
                        healthPerson.setQt("√");
                    }
                    //慢性脑卒中
                    if (healthTjwzVo.getNxgjbqt()!= null && healthTjwzVo.getNxgjbqt().contains("脑卒中")){
                        healthPerson.setMxnzz("√");
                    }
                    //慢性冠心病
                    if (healthTjwzVo.getXzjbqt()!= null && healthTjwzVo.getXzjbqt().contains("冠心病")){
                        healthPerson.setMxgxb("√");
                    }
                }

            }
            //中医体质辨识异常（非平和质）
            for (HealthZytzVo healthZytzVo : healthZytzVoList) {
                if (healthZytzVo.getTmh().equals(healthMainVo.getTmh())){
                    if (healthZytzVo.getPhzjl()!= null && (healthZytzVo.getPhzjl()==1 || healthZytzVo.getPhzjl() == 2)){
                        healthPerson.setZytzyc("√");
                    }
                }
            }

            healthPersonList.add(healthPerson);
        }

//        long start = System.currentTimeMillis();
        //批量新增
        boolean flag = healthPersonIService.saveOrUpdateBatch(healthPersonList); //28903 1500
//        long end = System.currentTimeMillis();
//        System.out.println("插入耗时:--------------------------" + (start - end) + "--------------------------");

        if (flag){
            return new ResponseResult(CommonCode.SUCCESS);
        }else {
            return new ResponseResult(CommonCode.FAIL);
        }


    }

    //修改个人体检异常
    public HealthPersonResult update(Long id, HealthPerson healthPerson) {
        //根据id从数据库查询页面信息
        HealthPerson one = healthPersonMapper.selectById(id);
        if (one != null) {
            //提交修改
            healthPersonMapper.updateById(healthPerson);
            return new HealthPersonResult(CommonCode.SUCCESS, healthPerson);
        }
        //修改失败
        return new HealthPersonResult(HealthPersonCode.HealthPerson_NOTEXISTS, null);

    }

    //根据id删除个人体检异常
    public HealthPersonResult delete(Long id) {
        //先查询一下
        HealthPerson healthPerson = healthPersonMapper.selectById(id);
        if (healthPerson != null) {
            healthPersonMapper.deleteById(id);
            return new HealthPersonResult(CommonCode.SUCCESS, healthPerson);
        }
        return new HealthPersonResult(HealthPersonCode.HealthPerson_NOTEXISTS, null);
    }

    /**
     * 个人体检异常查询方法
     *
     * @param current 页码，从1开始记数
     * @param size    每页记录数
     * @param healthPerson    查询条件
     * @return
     */
    public QueryResponseResult findList(int current, int size, HealthPerson healthPerson) {
        if (healthPerson == null) {
            healthPerson = new HealthPerson();
        }

        //分页参数
        if (current <= 0) {
            current = 1;
        }
        if (size <= 0) {
            size = 10;
        }

        IPage<HealthPerson> page = new Page<>(current, size);
        QueryWrapper<HealthPerson> wrapper = new QueryWrapper<>();
        wrapper.setEntity(healthPerson);

        IPage<HealthPerson> healthPersonIPage = healthPersonMapper.selectPage(page, wrapper);

        QueryResult queryResult = new QueryResult();

        queryResult.setList(healthPersonIPage.getRecords());//数据列表
        queryResult.setTotal(healthPersonIPage.getTotal());//数据总记录数
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS, queryResult);
        return queryResponseResult;
    }

    public List<String> findTmhList(String orgname, String stratTjrq, String endTjrq){
        Date startTjrj = java.sql.Date.valueOf(stratTjrq);
        Date endTjrj = java.sql.Date.valueOf(endTjrq);
        List<String> list = healthPersonMapper.findTmhList(orgname, startTjrj, endTjrj);
        return list;
    }


}
