package com.smart.house.apigateway.controller;

import com.google.common.base.Objects;
import com.smart.house.apigateway.common.Interceptor.UserContext;
import com.smart.house.apigateway.common.page.PageData;
import com.smart.house.apigateway.common.page.PageParams;
import com.smart.house.apigateway.model.Agency;
import com.smart.house.apigateway.model.House;
import com.smart.house.apigateway.model.User;
import com.smart.house.apigateway.service.AgencyService;
import com.smart.house.apigateway.service.HouseService;

import com.smart.house.apigateway.service.otherService.MailService;
import com.smart.house.apigateway.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("agency")
public class AgencyController {
    @Autowired
    private AgencyService agencyService;

    @Autowired
    private HouseService houseService;

    @Autowired
    private MailService mailService;
    @Autowired
    private RecommendService recommendService;


    /* ------------------------------经纪人模块------------------------------*/
    /**
     *经纪人列表
     */
    @RequestMapping("agentList")
    public String agentList(Integer pageSize, Integer pageNum, Model model){
        PageData<User> pageData=agencyService.selectAgentList(PageParams.build(pageSize,pageNum));
        model.addAttribute("ps",pageData);
        //热门房产
        List<House> houseList=recommendService.getHotHouse(3);
        model.addAttribute("recomHouses",houseList);
        return "user/agent/agentList";
    }

    /**
     * 经纪人详情
        ·经纪人拥有的房产
     */
    @RequestMapping("agentDetail")
    public String agentDetail(Integer id, Model model,String successMsg){
        User agent=agencyService.selectAgentDetail(id);   //查询经纪人详情
        House house=new House();
        house.setUserId(id);
        house.setBookmarked(false);   //true:收藏；false:售卖
        //经纪人拥有的房产
        PageData<House> bindHouses=houseService.selectHouseList(house,PageParams.build(3,1));
        //热门房产
        List<House> houseList=recommendService.getHotHouse(3);
        if(successMsg!=null)
        {
        model.addAttribute("successMsg","留言成功");
        }
        if (bindHouses!=null){
        model.addAttribute("bindHouses",bindHouses.getList());//经纪人拥有的房产
        }
        model.addAttribute("agent",agent);  //保存经纪人详情
        model.addAttribute("recomHouses",houseList);//热门房产
        return "user/agent/agentDetail";
    }

    /**用户留言**/
    @RequestMapping("/leaveMsg")
    public String agentMsg(Integer id,String msg,String name,String email, Model model){
        User agent =  agencyService.selectAgentDetail(id);
        //直接发送邮箱给经纪人
        mailService.sendMail("咨询", "email:"+email+",msg:"+msg, agent.getEmail());
        return "redirect:/agency/agentDetail?id="+id +"&successMsg=留言成功";
    }

    /* ------------------------------经纪机构模块------------------------------*/

    /**
     * 经济机构列表
     */
    @RequestMapping("agencyList")
    public String agencyList(Model model){
        List<Agency> agencyList = agencyService.selectAgencyList();
        //热门房产
        List<House> houseList=recommendService.getHotHouse(3);
        model.addAttribute("agencyList", agencyList);  //经济机构列表
        model.addAttribute("recomHouses",houseList);   //热门房产
        return "/user/agency/agencyList";
    }
    /**
     * 经济机构详情
     */
    @RequestMapping("agencyDetail")
    public String agencyDetail(Integer id,Model model ){
        Agency agency =  agencyService.selectAgencyDetail(id);
        //热门房产
        List<House> houseList=recommendService.getHotHouse(3);

        model.addAttribute("agency", agency);
        model.addAttribute("recomHouses",houseList);
        return "/user/agency/agencyDetail";
    }

    /* ------------------------------添加经济机构------------------------------*/

    /**
     *进入添加页面
     */
    @RequestMapping("createAgency")
    public String agencySubmit(Model model){
        User user =  UserContext.getUser();
        //只有超级管理员可以添加,拟定2771109447@qq.com为超管
        if (user == null || !Objects.equal(user.getEmail(), "2771109447@qq.com")) {
            model.addAttribute("errorMsg","此处为管理员权限,请先登入");
            return "user/accounts/login";
        }
        return "user/agency/create";
    }

    /**
     *添加经济机构
     */
    @RequestMapping("submitAgency")
    public String agencySubmit(Agency agency,Model model){
        User user =  UserContext.getUser();
        //只有超级管理员可以添加,拟定2771109447@qq.com为超管
        if (user == null || !Objects.equal(user.getEmail(), "2771109447@qq.com")) {
            model.addAttribute("errorMsg","此处为管理员权限,请先登入");
            return "user/accounts/register";
        }
        agencyService.addAgency(agency);
        model.addAttribute("successMsg","创建成功");
        return "redirect:/user/index?successMsg="+1;
    }

}
