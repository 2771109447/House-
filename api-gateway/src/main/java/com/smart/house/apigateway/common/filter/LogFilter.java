package com.smart.house.apigateway.common.filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

public class LogFilter implements Filter {

    private Logger looger= LoggerFactory.getLogger(LogFilter.class);
    //启动器启动时候执行
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    //请求拦截时候执行
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        looger.info("Hello!");
        filterChain.doFilter(servletRequest,servletResponse);
    }
    //容器销毁时候执行
    @Override
    public void destroy() {

    }
}
