package com.smart.house.serviceuser.controller;

import com.smart.house.serviceuser.model.User;
import com.smart.house.serviceuser.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("user")
public class UserController {
    public  static final Logger LOGGER= LoggerFactory.getLogger(UserController.class);


    @Autowired
    private UserService userService;


    //通过id查询用户
    @RequestMapping("getUserById")
    public User getUserById(Integer id){
        User user=userService.selectUserById(id);
      return user;
    }
    //通过多属性查询用户
    @RequestMapping(value = "getList")
    public List<User> selectUsersByUser(@RequestBody(required=false) User user){
        List<User> userList=userService.selectUsersByUser(user);
        return userList;
    }
    /* ------------------------------用户注册------------------------------*/

    /**
     * 添加用户
     */

    @RequestMapping("register")
    public boolean add(@RequestBody User user){
          userService.addUser(user,user.getEnableUrl());
          return true;
    }
    /**
     *点击链接，激活用户
     */

    @RequestMapping("activate")
    public boolean activate(String key){
        userService.enable(key);
        return true;

    }
    /* ------------------------------用户登入-------------------------------------------*/

    /**
     *登录验证
     */
    @RequestMapping("login")
    public User  login(@RequestBody User user){
        User user1= userService.auth(user.getEmail(),user.getPasswd());

        return user1;
    }
    /**
     * 身份验证(每次请求都会执行)
     */
    @RequestMapping("auth")
    public User getUser(String token){
        User user= userService.getUserByToken(token);
        return user;
    }

    /* ------------------------------用户登出-----------------------------------*/
    @RequestMapping("logout")
    public Boolean logout(String token){
              userService.invalidate(token);
        return true;
    }
    /* ------------------------------个人信息页-----------------------------------*/
    /**
     * 更新用户信息
     */

    @RequestMapping("profile")
    public User logout(@RequestBody  User user){
        User user1=userService.updateUser(user);
        return user1;
    }
}
