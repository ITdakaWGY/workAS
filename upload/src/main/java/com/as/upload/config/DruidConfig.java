package com.as.upload.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;


//druid配置，监控访问地址：http://localhost:8080/druid/login.html
@Configuration
public class DruidConfig {
    private Logger logger = LoggerFactory.getLogger(DruidConfig.class);

    /**
     * 把配置文件中自己配置的数据源值注入到druid
     *
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druid() {
        return new DruidDataSource();
    }

    /**
     * 配置Druid的监控，如果不配置这个类，将连接不上druid后台。http://localhost:8080/druid/
     * 配置一个管理后台的Servlet
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String, String> params = new HashMap<String, String>();
        params.put("loginUsername", "admin");//设置后台登录名
        params.put("loginPassword", "123456");//密码
        params.put("allow", "");//设置默认就是允许所有访问
        params.put("deny", "192.168.84.129");//设置黑名单
        bean.setInitParameters(params);
        return bean;
    }

    /**
     * 配置一个web监控的filter,如果不配置这个类，在页面的Web模块功能不会开启
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean frb = new FilterRegistrationBean();
        frb.setFilter(new WebStatFilter());
        Map<String, String> params = new HashMap<String, String>();
        params.put("exclusions", "*.js,*.css,/druid/*");//设置不拦截请求
        frb.setInitParameters(params);
        frb.setUrlPatterns(Arrays.asList("/*"));//拦截请求
        return frb;
    }


}
