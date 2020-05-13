package com.smart.house.apigateway.common.Interceptor;

import com.smart.house.apigateway.dao.UserDao;
import com.smart.house.apigateway.model.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    private static final String TOKEN_COOKIE = "token";


    @Autowired
    private UserDao userDao;
    //执行之前拦截
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求url
        String reqUri =	request.getRequestURI();
        //释放静态资源请求
        if (reqUri.startsWith("/static") || reqUri.startsWith("/error") ) {
            return true;
        }
        //从请求当中获取带有TOKEN_COOKIE名的cookie
        Cookie cookie = WebUtils.getCookie(request, TOKEN_COOKIE);
        if (cookie != null && StringUtils.isNotBlank(cookie.getValue())) {
            //验证token是否过期
            User user = userDao.getUserByToken(cookie.getValue());
            if (user != null) {
                //存入request域中，以供前端响应
                request.setAttribute("loginUser", user);
                //存入线程池中
                UserContext.setUser(user);
            }
        }
        return true;
    }
    //执行之后拦截
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        //释放静态资源请求
        String requestURI = request.getRequestURI();
        if (requestURI.startsWith("/static") || requestURI.startsWith("/error")) {
            return ;
        }
        //获取user
        User user = UserContext.getUser();
        if (user != null && StringUtils.isNotBlank(user.getToken())) {
            //登出
            String token = requestURI.startsWith("logout")? "" : user.getToken();
            //创建一个cookie,将用户的token存储到cookie中
            Cookie cookie = new Cookie(TOKEN_COOKIE, token);
            //设置js也能发送
            cookie.setPath("/");
            cookie.setHttpOnly(false);
            //返回cookie
            response.addCookie(cookie);
        }
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        //移除当前线程的user变量
        UserContext.remove();

    }


}
