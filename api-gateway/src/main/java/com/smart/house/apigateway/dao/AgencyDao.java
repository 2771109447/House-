package com.smart.house.apigateway.dao;

import com.smart.house.apigateway.common.page.PageData;
import com.smart.house.apigateway.common.page.PageParams;
import com.smart.house.apigateway.model.Agency;
import com.smart.house.apigateway.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Repository
public class AgencyDao {

    @Autowired
    RestTemplate restTemplate;

    /* ------------------------------经纪人模块------------------------------*/
    /**
     *经纪人列表
     */
    public PageData<User> selectAgentList(PageParams pageParams) {
         String url="http://user/agency/agentList";
         PageData<User> pageData= restTemplate.postForObject(url,pageParams, PageData.class);
        return pageData;
    }
    /**
     *  经纪人详情
     */

    public  User selectAgentDetail(Integer userId) {
        String url="http://user/agency/agentDetail?userId="+userId;
        User user=restTemplate.getForObject(url,User.class,userId);
        return user;
    }
    /* ------------------------------经纪机构模块------------------------------*/

    /**
     *经济机构列表
     */
    public List<Agency> selectAgencyList() {
        String url="http://user/agency/agencyList";
        Agency[] agencies=restTemplate.getForObject(url,Agency[].class);
        return Arrays.asList(agencies);
    }

    /**
     *经纪机构详情
     */
    public Agency selectAgencyDetail(Integer id) {
        String url="http://user/agency/agencyDetail?id="+id;
        Agency agency=restTemplate.getForObject(url,Agency.class,id);
        return agency;
    }
    /**
     * 创建经济机构
     */
    public void addAgency(Agency agency) {
        String url="http://user/agency/createAgency";
        restTemplate.postForObject(url,agency,String.class);
    }
}
