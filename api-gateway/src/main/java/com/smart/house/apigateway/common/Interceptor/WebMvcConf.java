package com.smart.house.apigateway.common.Interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConf implements WebMvcConfigurer {

	@Autowired
	private AuthActionInterceptor authActionInterceptor;
	
	@Autowired
	private AuthInterceptor authInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry){
		//释放静态拦截
		 registry.addInterceptor(authInterceptor).excludePathPatterns("/static").addPathPatterns("/**");
		 //拦截指定请求
		 registry.addInterceptor(authActionInterceptor).addPathPatterns("/user/profile").addPathPatterns("/agency/leaveMsg")
				 .addPathPatterns("/house/toAdd").addPathPatterns("/house/leaveMsg")
				 .addPathPatterns("/house/bookmark").addPathPatterns("/house/bookmarked")
				 .addPathPatterns("/house/ownlist").addPathPatterns("/comment/addHouseComment")
		 .addPathPatterns("/comment/addBlogComment");
		    WebMvcConfigurer.super.addInterceptors(registry);
	}
//    //跨域全局支持
//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping("/**")//拦截所有的url
//				.allowedOrigins("*")// 放行哪些原始域，比如"http://domain1.com,https://domain2.com"
//				.allowCredentials(true)// 是否发送Cookie信息
//				.allowedMethods("GET", "POST", "PUT", "DELETE") // 放行哪些原始域(请求方式)
//				.allowedHeaders("*");// 放行哪些原始域(头部信息)
//		WebMvcConfigurer.super.addCorsMappings(registry);
//	}
}
