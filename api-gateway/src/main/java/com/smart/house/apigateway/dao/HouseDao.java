package com.smart.house.apigateway.dao;

import com.smart.house.apigateway.common.page.PageData;
import com.smart.house.apigateway.common.page.PageParams;
import com.smart.house.apigateway.model.*;
import com.smart.house.apigateway.model.otherModel.HousePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Repository
public class HouseDao {

    @Autowired
    private  RestTemplate restTemplate;

    /* ------------------------------热门房产------------------------------*/

    public List<House> getHotHouse(int size) {
        String url="http://house/house/hotHouse?size="+size;
        House[] houseList=restTemplate.getForObject(url,House[].class,size);
        return Arrays.asList(houseList);

    }

    /* ------------------------------新品上市------------------------------*/

    public List<House> selectRecommendHouses() {
        String url="http://house/house/selectRecommendHouses";
        House[] houseList=restTemplate.getForObject(url,House[].class);
        return Arrays.asList(houseList);
    }
    /* ------------------------------房产列表（分页）------------------------------*/
    public PageData<House> selectHouseList(House house, PageParams pageParams) {
        HousePage housePage=new HousePage(house,pageParams);
        String url="http://house/house/HouseList";
        PageData pageData=restTemplate.postForObject(url,housePage,PageData.class);
        return pageData;
   }
    /* ------------------------------房产详情------------------------------*/
    //房产详情
    public House selectOneHouse(Integer id) {
        String url="http://house/house/houseDetail?id="+id;
        House house=restTemplate.getForObject(url,House.class,id);
        return house;
    }
    //房产拥有者
    public HouseUser getHouseUser(House house) {
        String url="http://house/house/houseUser";
        HouseUser houseUser=restTemplate.postForObject(url,house,HouseUser.class);
        return houseUser;
    }
    //增添热门
    public void increase(Integer id) {
        String url="http://house/house/increase?id="+id;
        restTemplate.getForObject(url,String.class,id);
    }
    //用户留言
    public void addUserMsg(UserMsg userMsg) {
        String url="http://house/house/addUserMsg";
        restTemplate.postForObject(url,userMsg,String.class);
    }
    //用户评分
    public void updateRating(Integer id, Double rating) {
        String url="http://house/house/updateRating?id="+id+"&rating="+rating;
        restTemplate.getForObject(url,String.class,id,rating);
    }
    //房产收藏
    public void bookmark(Integer houseId, Integer userId, boolean b) {
        String url="http://house/house/bookmark?houseId="+houseId+"&userId="+userId+"&b="+b;
        restTemplate.getForObject(url,String.class,houseId,userId,b);
    }
    /* ------------------------------增加房产页------------------------------*/
    //查询城市
    public List<City> getAllCitys() {
        String url="http://house/house/getAllCitys";
        City[] cities=restTemplate.getForObject(url,City[].class);
        return Arrays.asList(cities);
    }
    //查询小区
    public List<Community> getAllCommunitys() {
        String url="http://house/house/getAllCommunitys";
        Community[] cities=restTemplate.getForObject(url,Community[].class);
        return Arrays.asList(cities);
    }
    //添加房产
    public void addHouse(House house) {
        String url="http://house/house/addHouse";
        restTemplate.postForObject(url,house,String.class);
    }
    /* ----------------------------------------个人信息页------------------------------------*/
    //房产下架
    public void downHouse(Integer id) {
        String url="http://house/house/downHouse?id="+id;
        restTemplate.getForObject(url,String.class,id);
    }
    //取消收藏
    public void unbookmark(Integer houseId, Integer userId, boolean b) {
        String url="http://house/house/unbookmark?houseId="+houseId+"&userId="+userId+"&b="+b;
        restTemplate.getForObject(url,String.class,houseId,userId,b);
    }
}
