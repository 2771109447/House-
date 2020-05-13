package com.smart.house.apigateway.service;

import com.smart.house.apigateway.dao.HouseDao;
import com.smart.house.apigateway.model.House;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RecommendService {

    @Autowired
    private HouseDao houseDao;
    /* ------------------------------热门房产------------------------------*/

    public List<House> getHotHouse(int size) {
        return houseDao.getHotHouse(size);

    }


    /* ------------------------------新品上市------------------------------*/
    public List<House> selectRecommendHouses() {
        List<House> list=houseDao.selectRecommendHouses();
        return list;
    }
    /* ------------------------------点击增加热门------------------------------*/

    public void increase(Integer id) {
        houseDao.increase(id);
    }
}
