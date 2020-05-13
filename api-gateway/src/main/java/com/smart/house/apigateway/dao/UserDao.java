package com.smart.house.apigateway.dao;

import com.smart.house.apigateway.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class UserDao {


    @Autowired
    private RestTemplate restTemplate;

    /* ------------------------------用户注册------------------------------*/
    //添加用户
    public Boolean addUser(User user) {
        String url="http://user/user/register";
        Boolean b = restTemplate.postForObject(url,user, Boolean.class);
        return b;
    }
    //激活用户
    public Boolean enable(String key) {
        String url="http://user/user/activate?key="+key;
        Boolean b=restTemplate.getForObject(url,Boolean.class,key);
        return b;
    }
    /* ------------------------------用户登入------------------------------*/
    //登入
    public User selectUsers(User user) {
        String url="http://user/user/login";
        User user1=restTemplate.postForObject(url,user,User.class);
        return user1;
    }
    //身份验证
    public User getUserByToken(String jwt) {
        String url="http://user/user/auth?token="+jwt;
        User user=restTemplate.getForObject(url,User.class,jwt);
        return user;

    }


    /* ------------------------------用户登出------------------------------*/
    public void logout(String token) {
        String url="http://user/user/logout?token="+token;
         Boolean b=restTemplate.getForObject(url,Boolean.class,token);

    }
    /* ------------------------------修改个人信息------------------------------*/

    public User updateUser(User user) {
        String url="http://user/user/profile";
        User user1=restTemplate.postForObject(url,user,User.class);
        return user1;
    }
}
