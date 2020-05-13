package com.smart.house.servicehouse.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.smart.house.servicehouse.common.page.PageParams;
import com.smart.house.servicehouse.model.House;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RecommendService {
    /* ------------------------------热门房产------------------------------*/

    @Autowired
    private HouseService houseService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String HOT_HOUSE_KEY = "_hot_house";
    /**获取热门房产**/
    public List<House> getHotHouse(Integer size) {
        //从redis中获取热门,从高到低
        Set<String> idSet =  redisTemplate.opsForZSet().reverseRange(HOT_HOUSE_KEY, 0, -1);
        List<Integer> ids = idSet.stream().map(b -> Integer.parseInt(b)).collect(Collectors.toList());

        House house = new House();
        house.setIds(ids);
       if (ids.isEmpty()){
           return Lists.newArrayList();
       }
        final List<Integer> order =ids;
        //查询热门前三的房产
        List<House> houses = houseService.queryAndSetImg(house, PageParams.build(size, 1));
        //自然排序,从高到低
        Ordering<House> houseSort = Ordering.natural().onResultOf(hs -> {
            return order.indexOf(hs.getId());
        });
        return houseSort.sortedCopy(houses);
    }

    /**热门点击增加**/
    public void increaseHot(Integer id) {
        //每点击房产详情增加一分
        redisTemplate.opsForZSet().incrementScore(HOT_HOUSE_KEY, ""+id, 1.0D);
        //只保留前10个
        redisTemplate.opsForZSet().removeRange(HOT_HOUSE_KEY, 0, -11);
    }

    /* ------------------------------新品上市------------------------------*/

    public List<House> getLastest() {
        House house = new House();
        house.setSort("create_time");
        //按上市时间最新排序，获取最新上市
        return houseService.queryAndSetImg(house, PageParams.build(8, 1));
    }



}
