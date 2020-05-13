package com.smart.house.apigateway.common.Interceptor;

import com.smart.house.apigateway.model.User;

public class UserContext {
    //创建一个User类型的线程存储，用来存储User类型的变量
    private static final ThreadLocal<User> USER_HODLER = new ThreadLocal<>();
    //设置当前线程的局部变量
    public static void setUser(User user){
        USER_HODLER.set(user);
    }
    //移除当前线程的局部变量
    public static void remove(){
        USER_HODLER.remove();
    }
    //获取当前线程的局部变量
    public static User getUser(){
        return USER_HODLER.get();
    }
}
