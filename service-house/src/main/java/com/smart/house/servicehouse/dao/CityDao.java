package com.smart.house.servicehouse.dao;

import com.smart.house.servicehouse.model.City;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface CityDao {

    List<City> getAllCitys(City city);
}
