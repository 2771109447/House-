package com.smart.house.servicehouse.service;

import com.google.common.base.Strings;

import com.smart.house.servicehouse.common.page.PageData;
import com.smart.house.servicehouse.common.page.PageParams;
import com.smart.house.servicehouse.dao.CityDao;
import com.smart.house.servicehouse.dao.HouseDao;
import com.smart.house.servicehouse.dao.HouseUserDao;
import com.smart.house.servicehouse.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HouseService {
    /* ------------------------------房产模块------------------------------*/
    /**
     * 1,查询小区
     * 2，添加图片路径前缀
     * 3，构建分页结果
     */
    @Autowired
    private HouseDao houseDao;
    @Autowired
    private CityDao cityDao;
    @Autowired
    private HouseUserDao houseUserDao;

    @Value("${file.prefix}")
    private String imgPrefix;
    /* ------------------------------房产列表（分页显示）------------------------------*/

    //查询房产列表分页显示
    @Transactional
    public PageData<House> selectHouseList(House house, PageParams pageParams) {
        if (!Strings.isNullOrEmpty(house.getName())) {  //小区名是否为空
            Community community = new Community();
            community.setName(house.getName());
            List<Community> communities = houseDao.selectCommunity(community);  //搜索小区
            if (!communities.isEmpty()) {
                house.setCommunityId(communities.get(0).getId());  //设置小区号
            }
        }
        //获取房屋列表
        List<House> houseList = queryAndSetImg(house, pageParams);
        //获取房产总数
        Integer count = houseDao.selectPageCount(house);
        //返回pageData对象
        return PageData.buildPage(houseList, count, pageParams.getPageSize(), pageParams.getPageNum());
    }

    //查询房屋列表，添加图片路径前缀
    public List<House> queryAndSetImg(House house, PageParams pageParams) {
        List<House> houses = houseDao.selectHouseList(house, pageParams);
        houses.forEach(h -> {
            //首张图片
            h.setFirstImg(imgPrefix + h.getFirstImg());
            //图片列表
            h.setImageList(h.getImageList().stream().map(img -> imgPrefix + img).collect(Collectors.toList()));
            //户型图
            h.setFloorPlanList(h.getFloorPlanList().stream().map(img -> imgPrefix + img).collect(Collectors.toList()));
        });
        return houses;
    }
    /*------------------------------------房产详情页-------------------------------------*/

    //获取房产详情
    public House selectOneHouse(Integer id) {
        House house = new House();
        house.setId(id);
        //获取房厂列表
        List<House> houses = queryAndSetImg(house, PageParams.build(1, 1));
        if (!houses.isEmpty()) {
            return houses.get(0);
        }
        return null;
    }
    //获取房产拥有者
    public HouseUser getHouseUser(House house) {
         Integer housUserType =1;
         //house的类型type为售卖或者出租，都将房产拥有者houseUser的type设置为1
         if(house.getType()==1||house.getType()==2){
             housUserType=1;
         }
         HouseUser houseUser=houseUserDao.selectHouseUser(house.getId(),house.getUserId(),housUserType);
         return houseUser;
    }
    //添加用户留言
    public void addUserMsg(UserMsg userMsg) {
        userMsg.setCreateTime(new Date(System.currentTimeMillis()));
        houseUserDao.addHouseMsg(userMsg);
    }

    //用户评星
    public void updateRating(Integer id, Double rating) {
        House house = selectOneHouse(id);
        Double oldRating = house.getRating();  //获取原来的评分
        //计算新评分(取平均值)
        Double newRating = oldRating.equals(0D) ? rating : Math.min(Math.round(oldRating + rating)/2, 5);
        House updateHouse =  new House();
        updateHouse.setId(id);
        updateHouse.setRating(newRating);
        houseDao.updateHouse(updateHouse);
    }
    //房产收藏
    public void bookmark(Integer houseId, Integer userId, boolean b) {
        HouseUser houseUser=new HouseUser();
        houseUser.setUserId(userId);   //设置用户id
        houseUser.setHouseId(houseId); //设置房产id
        houseUser.setCreateTime(new Date(System.currentTimeMillis()));
        if(b==true) {
            houseUser.setType(2);      //收藏
        }
        houseUserDao.addHouseUser(houseUser);
    }

    /* ---------------------------------增加房产页------------------------------------*/

    //查询城市
    public List<City> getAllCitys() {
        City city=new City();
        return cityDao.getAllCitys(city);
    }
    //查询小区
    public List<Community> getAllCommunitys() {
        Community community=new Community();
        return houseDao.selectCommunity(community);
    }
    //添加房产
    @Transactional
    public void addHouse(House house) {
        house.setCreateTime(new Date(System.currentTimeMillis()));  //设置创建时间
        house.setRating(0.0);  //评分默认为零
        house.setState(1);    //设置为上架状态
        houseDao.addHouse(house);
        HouseUser houseUser=new HouseUser();
        houseUser.setHouseId(house.getId());   //设置houseId
        houseUser.setCreateTime(new Date(System.currentTimeMillis()));  //设置创建时间
        houseUser.setUserId(house.getUserId());  //设置userId
        houseUser.setType(1); //type=1表示售卖或出租
        houseUserDao.addHouseUser(houseUser) ;
    }
    /* ----------------------------------------个人信息页------------------------------------*/
    //房产下架
    public void downHouse(Integer id) {
        houseDao.downHouse(id);
    }
    //取消收藏
    public void unbookmark(Integer houseId, Integer userId, boolean b) {
        HouseUser houseUser=new HouseUser();
        houseUser.setHouseId(houseId); //房产id
        houseUser.setUserId(userId);//用户id
        houseUser.setType(2);      //该房产为收藏
        houseUserDao.deleteHouseUser(houseUser);
    }
}