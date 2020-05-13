package com.smart.house.apigateway.common.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

//配置spring监控与统计
@Configuration
public class FilterBeanConfig {
    /**
     * 1,构造filter
     * 2,配置拦截urlPattern
     * 3,利用FilterRegistrationBean控制器包装
     * @return
     */
    @Bean
    public FilterRegistrationBean logFiler(){
        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
        //封装日志过滤器
        filterRegistrationBean.setFilter(new LogFilter());
        //默认拦截所有的请求
        List<String> urlList=new ArrayList<>();
        urlList.add("*");
        filterRegistrationBean.setUrlPatterns(urlList);       //拦截所有请求路径
        return filterRegistrationBean;
    }
}
