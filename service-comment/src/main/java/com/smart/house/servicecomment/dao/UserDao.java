package com.smart.house.servicecomment.dao;

import com.smart.house.servicecomment.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class UserDao {
    @Autowired
    private RestTemplate restTemplate;
    /**
     *通过userId获取用户详情
     */
    public User getUserById(Integer id) {
        String url="http://user/user/getUserById?id="+id;
        User user=restTemplate.getForObject(url,User.class,id);
        return  user;
    }
}
