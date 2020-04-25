package com.as.occupationaldseases.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

    @Value("${newbase64png}")
    private String path;

    @Value("${newpany}")
    private String newpany;

    @Value("${headPortrait}")
    private String headPortrait;
    @Value("${newheadPortrait}")
    private String newheadPortrait;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //上传的图片在D盘下的OTA目录下，访问路径如：http://localhost:8081/OTA/d3cf0281-bb7f-40e0-ab77-406db95ccf2c.jpg
        //其中OTA表示访问的前缀。"file:D:/OTA/"是文件真实的存储路径
        registry.addResourceHandler("/newregister/**").addResourceLocations("file:"+path);

        registry.addResourceHandler("/NewCompany/**").addResourceLocations("file:"+newpany);

        registry.addResourceHandler("/headPortrait/**").addResourceLocations("file:"+headPortrait);

        registry.addResourceHandler("/newheadPortrait/**").addResourceLocations("file:"+newheadPortrait);

    }
}
