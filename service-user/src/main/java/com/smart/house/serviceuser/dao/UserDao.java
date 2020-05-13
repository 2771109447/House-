package com.smart.house.serviceuser.dao;

import com.smart.house.serviceuser.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {
    //多属性查找用户
    List<User> selectUsers(User user);
    //添加用户
    Integer addUser(User user);
    //删除用户
    Integer deleteUser(String email);
    //更新用户
    Integer updateUser(User user);
}