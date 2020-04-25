package com.as.occupationaldseases.dao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.as.occupationaldseases.common.response.QueryResultSta;
import com.as.occupationaldseases.domain.grjbxx_fc.Grjbxx_fc;
import com.as.occupationaldseases.domain.user.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTests {
    @Resource
    private UserMapper userMapper;

    @Resource
    private GrjbxxMapper grjbxxMapper;

    @Resource
    private GrjbxxFcMapper grjbxxFcMapper;



    @Test
    public void testInsert() {
    /*    User user = new User();
        user.setName("测试01");
        user.setAge(18);
        user.setEmail("测试01@xxx.com");
        user.setAddr("山东省");
        user.setRemark("喝酱油的");

        int insert = userMapper.insert(user);
        Assert.assertEquals(insert , 1);*/


   /*     grjbxxMapper.update()*/



    }

    @Test
    public void testSelect(){

        Grjbxx_fc grjbxxFc = new Grjbxx_fc();
        QueryWrapper<Grjbxx_fc> wrapper = new QueryWrapper<>();
        grjbxxFc.setCompanycode("100001");
        grjbxxFc.setTjflagzj("2");
        grjbxxFc.setTjflagzj(null);
        wrapper.setEntity(grjbxxFc);
        List<Grjbxx_fc> grjbxx_fcs = grjbxxFcMapper.selectList(wrapper.isNull("tjflagzj"));
/*        grjbxx_fcs.forEach(System.out::println);
        Assert.assertEquals(2, grjbxx_fcs.size())*/;
    }



    @Test
    public void testSelect2(){

        User user = new User();

        user.setAge(10);

        List<User> users = userMapper.queryAllByXml(user);
        System.out.println("user:"+user);

/*        users.forEach(System.out::println);
        Assert.assertEquals(2, users.size());*/
    }

    @Test
    public void testSelect3(){

        Map<String, Object> params = new HashMap<>();
        QueryResultSta queryResultSta = new QueryResultSta();
        params.put("companycode", "1001");
        System.out.println("=======企业进度查询======");
        //未体检人数
        params.put("res1", "2");
        int weitijiancount =  grjbxxMapper.queryCount(params);
        queryResultSta.setWeitijian(weitijiancount);
        //已体检人数
        params.remove("res1");
        params.put("res1","1");//
        int yitijiancount = grjbxxMapper.queryCount(params);
        queryResultSta.setYitijiancount(yitijiancount);
        //应体检人数
        int yingtijiancount = weitijiancount + yitijiancount;
        queryResultSta.setYingtjiancount(yingtijiancount);
        queryResultSta.setPhone("10086");
        queryResultSta.setCompanyname("中国移动");
        queryResultSta.setLinkman("张三");

        System.out.println("---------体检进度--------："+queryResultSta.toString());

    }



    @Test
    public void queryUserForPage(){
        IPage<User> userPage = new Page<>(0, 2);//参数一是当前页，参数二是每页个数
        userPage = userMapper.selectPage(userPage, null);
        List<User> list = userPage.getRecords();
        for(User user : list){
            System.out.println(user);
        }
    }

    @Test
    public void testJson(){
        String tjxm = "内科问诊,外科问诊,五官科问诊,神经科问诊,症状信息";
        JSONObject jsonObject = new JSONObject();
        String[] arr = tjxm.split(","); // 用,分割
        for (String s : arr) {
            jsonObject.put(s,0);
        }
        String str = jsonObject.toString();
        Map<String,Integer> map = JSON.parseObject(str,Map.class);
        System.out.println(map.toString());
    }

    @Test
    public void test01(){
        String str = "五官科问诊内科问诊外科问诊神经科问诊症状信息采集";
        String s = "外科问诊";
        System.out.println(str.contains(s));
    }

}
