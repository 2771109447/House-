package com.smart.house.apigateway.service;

import com.smart.house.apigateway.common.page.PageData;
import com.smart.house.apigateway.common.page.PageParams;
import com.smart.house.apigateway.dao.AgencyDao;
import com.smart.house.apigateway.model.Agency;
import com.smart.house.apigateway.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgencyService {
    @Autowired
    private AgencyDao agencyDao;
    /* ------------------------------经纪人模块------------------------------*/
    /**
     * 经济人列表
     */

    public PageData<User> selectAgentList(PageParams pageParams) {
        return agencyDao.selectAgentList(pageParams);

    }
    /**
     * 经纪人详情
     */

    public User selectAgentDetail(Integer userId) {
        return agencyDao.selectAgentDetail(userId);
    }
    /* ------------------------------经纪机构模块------------------------------*/
    /**
     * 经济机构列表
     */

    public List<Agency> selectAgencyList() {
        return agencyDao.selectAgencyList();
    }

    /**
     *经济机构详情
     */
    public Agency selectAgencyDetail(Integer id) {
        return agencyDao.selectAgencyDetail(id);
    }

    /**
     * 创建经济机构
     */
    public void addAgency(Agency agency) {
        agencyDao.addAgency(agency);
    }
}
