package com.smart.house.servicehouse.dao;

import com.smart.house.servicehouse.common.page.PageParams;
import com.smart.house.servicehouse.model.Community;
import com.smart.house.servicehouse.model.House;
import com.smart.house.servicehouse.model.HouseUser;
import com.smart.house.servicehouse.model.UserMsg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HouseDao {
    //房产列表（分页）
    List<House> selectHouseList(@Param("house") House house, @Param("pageParams") PageParams pageParams);
   //查询房产总数
    Integer selectPageCount(@Param("house") House house);
    //查询小区
    List<Community> selectCommunity(Community community);

    //更新房产信息(只修改评分)
    void updateHouse(House updateHouse);
    //添加房产信息
    void addHouse(House house);
    //房产下架
    void downHouse(Integer id);
}
