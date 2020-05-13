package com.smart.house.servicehouse.controller;

import com.smart.house.servicehouse.common.page.PageData;
import com.smart.house.servicehouse.common.page.PageParams;
import com.smart.house.servicehouse.model.*;
import com.smart.house.servicehouse.model.otherModel.HousePage;
import com.smart.house.servicehouse.service.HouseService;
import com.smart.house.servicehouse.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("house")
public class HouseController {

    @Autowired
    private RecommendService recommendService;
    @Autowired
    private HouseService houseService;
    /* ------------------------------热门房产------------------------------------------*/

    @RequestMapping("hotHouse")
    public List<House> hotHouse(Integer size){

        return recommendService.getHotHouse(size);
    }
    /* ------------------------------新品上市-----------------------------------------*/
    @RequestMapping("selectRecommendHouses")
    public List<House> selectRecommendHouses(){
      List<House> list= recommendService.getLastest();
        return list;
    }
    /* ------------------------------房产列表（分页显示）------------------------------*/
    @RequestMapping("HouseList")
    public PageData<House> HouseList(@RequestBody HousePage housePage){
         House house=housePage.getHouse();
         PageParams pageParams=housePage.getPageParams();
         PageData pageData=houseService.selectHouseList(house,pageParams);
        return pageData;
    }
    /* ---------------------------------房产详情页------------------------------------*/
    //房产详情
    @RequestMapping("houseDetail")
    public House  houseDetail( Integer id){
      House house=houseService.selectOneHouse(id);
        return house;
    }
    //房产拥有者
    @RequestMapping("houseUser")
    public HouseUser houseDetail(@RequestBody House house){
        HouseUser houseUser=houseService.getHouseUser(house);
        return houseUser;
    }
    //增添热门
    @RequestMapping("increase")
    public void increase( Integer id){
        recommendService.increaseHot(id);
    }
    //用户留言
    @RequestMapping("addUserMsg")
    public void addUserMsg( @RequestBody  UserMsg userMsg) {
       houseService.addUserMsg(userMsg);
    }
    //用户评分
    @RequestMapping("updateRating")
    public void updateRating(Integer id, Double rating) {
        houseService.updateRating(id,rating);

    }
    //房产收藏
    @RequestMapping("bookmark")
    public void bookmark(Integer houseId, Integer userId, boolean b) {
       houseService.bookmark(houseId,userId,b);
    }
    /* ---------------------------------增加房产页------------------------------------*/

    //查询城市
    @RequestMapping("getAllCitys")
    public List<City> getAllCitys() {
        List<City> cities=houseService.getAllCitys();
        return cities;
    }
    //查询城市
    @RequestMapping("getAllCommunitys")
    public List<Community> getAllCommunitys() {
        List<Community> communities=houseService.getAllCommunitys();
        return communities;
    }
    //添加房产
    @RequestMapping("addHouse")
    public void addHouse(@RequestBody  House house) {
        houseService.addHouse(house);

    }
    /* ----------------------------------------个人信息页------------------------------------*/
    //房产下架
    @RequestMapping("downHouse")
    public void downHouse(Integer id) {
        houseService.downHouse(id);
    }
    //取消收藏
    @RequestMapping("unbookmark")
    public void unbookmark(Integer houseId, Integer userId, boolean b) {
        houseService.unbookmark(houseId,userId,b);
    }
}
