package com.smart.house.serviceuser.controller;

import com.smart.house.serviceuser.common.page.PageData;
import com.smart.house.serviceuser.common.page.PageParams;
import com.smart.house.serviceuser.model.Agency;
import com.smart.house.serviceuser.model.User;
import com.smart.house.serviceuser.service.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("agency")
public class AgencyController {
     @Autowired
     private AgencyService agencyService;

    /* ------------------------------经纪人模块------------------------------*/
    /**
     *经纪人列表
     */
    @RequestMapping("agentList")
    public PageData<User> agentList(@RequestBody PageParams pageParams){
        PageData<User> pageData=agencyService.selectAgentList(pageParams);
        return pageData;
    }
    /**
     *经纪人详情
     */
    @RequestMapping("agentDetail")
    public User agentDetail(Integer userId){
        User user=agencyService.selectAgentDeail(userId);
        return user;
    }

    /* ------------------------------经纪机构模块------------------------------*/
    /**
     *经济机构列表
     */
    @RequestMapping("agencyList")
    public List<Agency> agencyList(){
       List<Agency> agencyList=agencyService.selectAgencyList();
        return agencyList;
    }
    /**
     * 经济机构详情
     */
    @RequestMapping("agencyDetail")
    public   Agency agencyDetail(Integer id){
        Agency agency=agencyService.selectAgency(id);
        return agency;
    }
    /**
     * 创建经济机构
     */
    @RequestMapping("createAgency")
    public void createAgency(@RequestBody  Agency agency) {
        agencyService.createAgency(agency);
    }
}
