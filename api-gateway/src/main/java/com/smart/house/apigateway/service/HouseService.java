package com.smart.house.apigateway.service;

import com.google.common.base.Joiner;
import com.smart.house.apigateway.common.page.PageData;
import com.smart.house.apigateway.common.page.PageParams;
import com.smart.house.apigateway.dao.HouseDao;
import com.smart.house.apigateway.model.*;
import com.smart.house.apigateway.service.otherService.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class HouseService {

    @Autowired
    private HouseDao houseDao;
    @Autowired
    private FileService fileService;
    /* ------------------------------------房产列表(分页)------------------------------------*/

    public PageData<House> selectHouseList(House house, PageParams pageParams) {
        return houseDao.selectHouseList(house,pageParams);
    }
    /* ------------------------------------房产详情页------------------------------------*/
    //房产详情
    public House selectOneHouse(Integer id) {
        return  houseDao.selectOneHouse(id);

    }
    //房产拥有者
    public HouseUser getHouseUser(House house) {
        return houseDao.getHouseUser(house);
    }
    //用户留言
    public void addUserMsg(UserMsg userMsg) {
        houseDao.addUserMsg(userMsg);
    }
    //用户评分
    public void updateRating(Integer id, Double rating) {
        houseDao.updateRating(id,rating);
    }
    //房产收藏
    public void bookmark(Integer houseId, Integer userId, boolean b) {
              houseDao.bookmark(houseId,userId,b);
    }
    /* ------------------------------------添加房产页------------------------------------*/
    //查询城市
    public List<City> getAllCitys() {
        return houseDao.getAllCitys();
    }
    //查询小区
    public List<Community> getAllCommunitys() {
        return houseDao.getAllCommunitys();
    }
    //添加房产
    @Transactional
    public void addHouse(House house,User user) {
        //房产图片分割
        if (house.getHouseFiles() != null && !house.getHouseFiles().isEmpty()) {
            List<MultipartFile> files = house.getHouseFiles();
            String imags = Joiner.on(",").join(fileService.getImgPaths(files));//以逗号分割
            house.setHouseFiles(null);
         //设置相对路径
            house.setImages(imags);
        }
        //户型图分割
        if (house.getFloorPlanFiles() != null && !house.getFloorPlanFiles().isEmpty()) {
            List<MultipartFile> files = house.getFloorPlanFiles();
            String floorPlans = Joiner.on(",").join(fileService.getImgPaths(files));//以逗号分割
            house.setFloorPlanFiles(null);
            //设置相对路径
            house.setFloorPlan(floorPlans);
        }

        house.setUserId(user.getId());
        houseDao.addHouse(house);
    }
    /* ----------------------------------------个人信息页------------------------------------*/
    //房产下架
    public void downHouse(Integer id) {
        houseDao.downHouse(id);
    }
    //取消收藏
    public void unbookmark(Integer houseId, Integer userId, boolean b) {
        houseDao.unbookmark(houseId,userId,b);

    }
}
