package com.smart.house.apigateway.common.Interceptor;

import com.smart.house.apigateway.model.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;


@Component
public class AuthActionInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//获取当前线程的user变量
		User user = UserContext.getUser();
		if (user == null) {
			String msg = URLEncoder.encode("请先登录","utf-8");
			String target = URLEncoder.encode(request.getRequestURL().toString(),"utf-8");   //获取当前请求
			if ("GET".equalsIgnoreCase(request.getMethod())) {
				request.setAttribute("errorMsg",msg);
				request.setAttribute("target",target);
				response.sendRedirect("/user/login");
				return false;//修复bug,未登录要返回false
			}else {
				request.setAttribute("errorMsg",msg);
				response.sendRedirect("/user/login");
				return false;//修复bug,未登录要返回false
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
