package com.as.publichealth.dao;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.as.publichealth.common.response.QueryResponseResult;
import com.as.publichealth.service.TjycnumService;
import com.as.publichealth.utils.NoModelDataListener;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDao {

    @Resource
    private TjycnumMapper tjycnumMapper;



    @Autowired
    TjycnumService tjycnumService;


    @Test
    private void getUrl(){
        //获取跟目录
        File path = null;
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if(!path.exists()) path = new File("");
        System.out.println("path:"+path.getAbsolutePath());

        //如果上传目录为/static/images/upload/，则可以如下获取：
        File upload = new File(path.getAbsolutePath(),"static");
        if(!upload.exists()) upload.mkdirs();
        System.out.println("upload url:"+upload.getAbsolutePath());
        //在开发测试模式时，得到的地址为：{项目跟目录}/target/static
        //在打包成jar正式发布时，得到的地址为：{发布jar包目录}/static

    }

    /**
     * 不创建对象的读
     */
    @Test
    public void noModelRead() {
        String fileName = "D:\\model6.xls";
        // 这里 只要，然后读取第一个sheet 同步读取会自动finish

        ExcelReaderBuilder read = EasyExcel.read(fileName, new NoModelDataListener());
      /*  read.*/

/*        String fileName2 = "D:\\test.xls";
        ExcelWriterBuilder write = EasyExcel.write(fileName2);
        ExcelWriterBuilder head = write.head(objects);
        head.sheet("模板").doWrite(null);*/

    }

    /**
     * 不创建对象的写
     */
    @Test
    public void noModelWrite() {
        // 写法1
        String fileName = "D:\\test.xls";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(fileName).head(head()).sheet("模板").doWrite(dataList());
    }

    private List<List<String>> head() {
        List<List<String>> list = new ArrayList<List<String>>();
        List<String> head0 = new ArrayList<String>();
        head0.add("字符串" + System.currentTimeMillis());
        List<String> head1 = new ArrayList<String>();
        head1.add("数字" + System.currentTimeMillis());
        List<String> head2 = new ArrayList<String>();
        head2.add("日期" + System.currentTimeMillis());
        list.add(head0);
        list.add(head1);
        list.add(head2);
        return list;
    }

    /**
     * 读取表头数据
     */
    @Test
    public void headerRead() {
        String fileName = "D:\\模板.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet
        EasyExcel.read(fileName,  new NoModelDataListener()).sheet().doRead();
    }

    private List<List<Object>> dataList() {
        List<List<Object>> list = new ArrayList<List<Object>>();
        for (int i = 0; i < 10; i++) {
            List<Object> data = new ArrayList<Object>();
            data.add("字符串" + i);
            data.add(new Date());
            data.add(0.56);
            list.add(data);
        }
        return list;
    }

    /**
     * 根据模板写入
     */
    @Test
    public void templateWrite() {
        /*String templateFileName = "D:\\模板.xlsx";
        String fileName = "D:\\test.xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(fileName, Tjycnum.class).withTemplate(templateFileName).sheet().doWrite(data());*/
        data();
    }

    private void data(){
        /*List<Tjycnum> all = new ArrayList<>();
        Tjycnum tjycnum = tjycnumMapper.selectById(3);
        all.add(tjycnum);
        System.out.println(all.toString());
        return all;*/
        QueryResponseResult list = tjycnumService.findList(1, 2, null);
        System.out.println(list.getMessage());
    }



}
