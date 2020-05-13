package com.smart.house.serviceuser.dao;

import com.smart.house.serviceuser.common.page.PageParams;
import com.smart.house.serviceuser.model.Agency;
import com.smart.house.serviceuser.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AgencyDao {
    //查询经纪人列表
    List<User> selectAgent(@Param("user") User user, @Param("pageParams") PageParams pageParams);
    //查询经纪人总数
    Integer selectAgentCount(@Param("user") User user);
    //查询经济机构列表
    List<Agency> selectAgencyList(Agency agency);
    //创建经济机构
    void addAgency(Agency agency);
}
