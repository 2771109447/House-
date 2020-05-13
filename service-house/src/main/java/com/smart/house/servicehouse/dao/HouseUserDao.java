package com.smart.house.servicehouse.dao;

import com.smart.house.servicehouse.model.HouseUser;
import com.smart.house.servicehouse.model.UserMsg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface HouseUserDao {
    //增添房产用户
    void addHouseUser(HouseUser houseUser);
    //插入用户留言
    Integer addHouseMsg(UserMsg userMsg);
    //删除房产用户
    void deleteHouseUser(HouseUser houseUser);
    //查询房产拥有者
    HouseUser selectHouseUser(@Param("id") Integer id, @Param("userId") Integer userId, @Param("type") Integer type);
}
