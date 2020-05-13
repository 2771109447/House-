package com.smart.house.serviceuser.service;

import com.smart.house.serviceuser.common.page.PageData;
import com.smart.house.serviceuser.common.page.PageParams;
import com.smart.house.serviceuser.dao.AgencyDao;
import com.smart.house.serviceuser.model.Agency;
import com.smart.house.serviceuser.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgencyService {
    @Autowired
    private AgencyDao agncyDao;
    @Value("${file.prefix}")
    private String  imgPrefix;

    /* --------------------------------------经纪人模块---------------------------------------*/
    /**
     * 经纪人详情
     */
    public User selectAgentDeail(Integer userId) {
        User user=new User();
        user.setId(userId);
        user.setType(2);            //经纪人
        List<User> agentList=agncyDao.selectAgent(user, PageParams.build(1,1));
        setImg(agentList);  //添加经纪人头像前缀
        if(!agentList.isEmpty()&&agentList.get(0).getAgencyId()!=null){
           User user1=agentList.get(0);
            //添加经纪机构名字
            user1.setAgencyName(selectAgency(user1.getAgencyId()).getName());

            return   user1;
        }
        return null;
    }
    //添加经纪人头像前缀
    private void setImg(List<User> list) {
        list.forEach(i -> {
            i.setAvatar(imgPrefix + i.getAvatar());
        });
    }

    /**
     *  经济人列表(分页)
     */
    public PageData<User> selectAgentList(PageParams pageParams) {
        User user=new User();
        List<User> agents=agncyDao.selectAgent(user,pageParams);
        setImg(agents);  //添加经纪人头像前缀

        Integer count =agncyDao.selectAgentCount(user);
        return PageData.buildPage(agents,count,pageParams.getPageSize(),pageParams.getPageNum());
    }
    /* ------------------------------经纪机构模块------------------------------*/

    /**
     * 经济机构列表
     **/
    public List<Agency> selectAgencyList() {
        Agency agency=new Agency();
        List<Agency> agencyList=agncyDao.selectAgencyList(agency);
        return agencyList;
    }
    /**
     * 经纪机构详情
     **/
    public Agency selectAgency(Integer id) {
        Agency agency=new Agency();
        agency.setId(id);
        List<Agency> agencyList=agncyDao.selectAgencyList(agency);
        if (!agencyList.isEmpty()){
            return agencyList.get(0);
        }
        return null;
    }
    /**
     * 创建经济机构
     */
    public void createAgency(Agency agency) {
        agncyDao.addAgency(agency);
    }
}
