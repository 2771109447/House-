package com.smart.house.servicehouse.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.google.common.collect.Lists;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DruidConfig {
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    @Bean(initMethod = "init",destroyMethod = "close")
    public DruidDataSource dataSource(){        //创建数据源
        DruidDataSource dataSource=new DruidDataSource();
        dataSource.setProxyFilters(Lists.newArrayList(statFilter1()));   //将慢日志加载到Druid连接池中
        return dataSource;
    }
    //打印慢日志
    @Bean
    public Filter statFilter1(){
        StatFilter filter=new StatFilter();
        filter.setSlowSqlMillis(5000);   //指定慢sql的阙值5000ms
        filter.setLogSlowSql(true);      //打印慢日志
        filter.setMergeSql(true);        //合并慢日志
        return filter;
    }

}
