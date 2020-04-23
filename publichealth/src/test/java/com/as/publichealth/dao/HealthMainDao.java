package com.as.publichealth.dao;

import com.as.publichealth.domain.main.HealthMain;
import com.as.publichealth.domain.main.HealthMainVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HealthMainDao {

    @Resource
    private HealthMainMapper healthMainMapper;

    @Resource
    private HealthPersonMapper healthPersonMapper;

    @Test
    public void Main() throws ParseException {
        Date startTjrj = java.sql.Date.valueOf("2019-02-22");
        Date endTjrj = java.sql.Date.valueOf("2019-03-27");

        String orgname = "人和街道卫生服务中心";

        List<HealthMainVo> list = healthMainMapper.findList(orgname, startTjrj, endTjrj);

        for (HealthMainVo healthMain : list) {
            System.out.println(healthMain.getXm());
        }
    }

    @Test
    public void test1(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("param1","s65");
        map.put("orgname","松山街道卫生院");
        map.put("sex","1");

        int i = healthPersonMapper.queryCount(map);
        System.out.println(i);
        /*Integer integer = healthPersonMapper.queryCount(null);
        System.out.println(integer);*/
    }
}
