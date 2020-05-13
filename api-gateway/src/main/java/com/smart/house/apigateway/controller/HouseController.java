package com.smart.house.apigateway.controller;

import com.smart.house.apigateway.common.Interceptor.UserContext;
import com.smart.house.apigateway.common.page.PageData;
import com.smart.house.apigateway.common.page.PageParams;
import com.smart.house.apigateway.common.resultMsg.ResultMsg;
import com.smart.house.apigateway.model.*;
import com.smart.house.apigateway.service.AgencyService;
import com.smart.house.apigateway.service.CommentService;
import com.smart.house.apigateway.service.HouseService;
import com.smart.house.apigateway.service.otherService.MailService;
import com.smart.house.apigateway.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("house")
public class HouseController {
    @Autowired
    private RecommendService recommendService;
    @Autowired
    private HouseService houseService;
    @Autowired
    private AgencyService agencyService;
    @Autowired
    private MailService mailService;
    @Autowired
    private CommentService commentService;
    @RequestMapping(" ")
    public String morenIndex(){
        return "redirect:house/index";
    }

    @RequestMapping("index")
    public String index(Model model,String errorMsg){
        //最新上市
        List<House> houses=recommendService.selectRecommendHouses();
        model.addAttribute("recomHouses",houses);
        if(errorMsg!=null&&errorMsg.equals("1")){
            model.addAttribute("errorMsg","无经纪人权限,无法添加房产信息");
        }
        return "homepage/index";
    }
    /* ------------------------------------房产列表(分页)------------------------------------*/

    /**
     * 房产列表
     ·1，排序列表
     ·2，小区查询
     ·3，分类查询（1，售卖 2，出租）
     */
    @RequestMapping("list")
    public String houseList(Integer pageSize,Integer pageNum, House house, Model model){
        PageData<House> ps=houseService.selectHouseList(house, PageParams.build(pageSize,pageNum));
        model.addAttribute("ps",ps);      //分页对象及列表
        model.addAttribute("vo",house);   //为了传递排序
        //热门房产
        List<House> houseList=recommendService.getHotHouse(3);
        model.addAttribute("recomHouses",houseList);
        return "house/listing";
    }
    /* ------------------------------------房产详情页------------------------------------*/

    /**
     * 房产详情
     ·1，获取房产详情(前端显示)
     ·2，查询房产拥有者
     ·3，获取房产所属经纪人(前端显示)
     ·4，热门房产更新（每点击一次加一）
       5，获取房产评论
     */
    @RequestMapping("detail")
    public String houseDetail(Integer id,String successMsg, Model model){
        House house=houseService.selectOneHouse(id);    //查询房产详请
        HouseUser houseUser = houseService.getHouseUser(house);     //查询房产拥有者
        User agent=null;
        if(houseUser.getUserId()!=null&&!houseUser.getUserId().equals(0)){
           agent=agencyService.selectAgentDetail(houseUser.getUserId());  //查询经纪人
        }
        //获取前8条房产评论
        List<Comment> commentList=commentService.selectAllHouseComment(id);
        //热门房产（点击更新）
        recommendService.increase(id);
        List<House> houseList=recommendService.getHotHouse(3);

        if(successMsg!=null){
            model.addAttribute("successMsg","留言成功");
        }
        model.addAttribute("commentList",commentList);  //房产评论列表
        model.addAttribute("house",house);//房产详情显示
        model.addAttribute("agent",agent);//房产经纪人显示
        model.addAttribute("recomHouses",houseList);//热门房产显示
        return "house/detail";
    }
    /**
     *房产收藏
     */
    @RequestMapping(value="bookmark")
    @ResponseBody
    public ResultMsg bookmark(Integer id,Model model){
        User user = UserContext.getUser();
        /**先判断是否已经收藏*/
        House house=new House();
        house.setBookmarked(true);      //类型为收藏
        house.setUserId(user.getId());  //当前用户
        house.setId(id);          //房产id
        PageData<House> pageData=houseService.selectHouseList(house, PageParams.build(1,1));
        if(!pageData.getList().isEmpty()){
            return ResultMsg.errorMsg("该房产已收藏");
        }
        houseService.bookmark(id, user.getId(), true);
        return ResultMsg.successMsg("收藏成功");
    }

    /**
     * 用户评星
     */
    @RequestMapping("rating")
    @ResponseBody
    public ResultMsg rating(Double rating, Integer id, Model model){
        houseService.updateRating(id,rating);

        return ResultMsg.successMsg("ok");
    }
    /**
     * 用户留言
     */
    @RequestMapping("leaveMsg")
    public String houseMsg(UserMsg userMsg){
        //添加留言
        houseService.addUserMsg(userMsg);
        //获取经纪人
        User  agent =agencyService.selectAgentDetail(userMsg.getAgentId());
        //发送邮件
        mailService.sendMail("来自用户"+userMsg.getEmail()+"的留言",userMsg.getMsg(),agent.getEmail());
        return "redirect:/house/detail?id=" + userMsg.getHouseId()+"&successMsg=留言成功";
    }
    /* ----------------------------------------添加房产页------------------------------------*/

    /**
     * 跳转添加房产页面
     */
@RequestMapping(value="toAdd")
public String toAdd(Model model){
    //只有经纪人才能添加房产
    User user =UserContext.getUser();
    if (user==null||user.getType()!=2){
        return "redirect:index?errorMsg="+1;
    }
    model.addAttribute("citys", houseService.getAllCitys());//查询所有城市
    model.addAttribute("communitys", houseService.getAllCommunitys());//查询所有小区
    return "/house/add";
}
    /**
     * 添加房产
     */
    @RequestMapping(value="add")
    public String doAdd(House house){
        User user = UserContext.getUser();
        houseService.addHouse(house,user);
        return "redirect:ownlist?successMsg="+1;
    }
    /* ----------------------------------------个人信息页------------------------------------*/

    /**
     * 我的房产(只属于经纪人拥有)
     */
    @RequestMapping(value="ownlist")
    public String ownlist(House house,PageParams pageParams,Model model,String successMsg){
        User user = UserContext.getUser();
        house.setUserId(user.getId());  //用户id
        house.setBookmarked(false); //售卖或出租
        model.addAttribute("ps", houseService.selectHouseList(house, PageParams.build(pageParams.getPageSize(),pageParams.getPageNum()))) ;
        //添加房产是否成功
        if(successMsg!=null&&successMsg.equals("1")){
            model.addAttribute("successMsg","添加成功");
        }
        return "house/ownlist";
    }

    /**
     *房产下架
     */
    @RequestMapping(value="under")
    public String delsale(Integer id){
        //房产下架
        houseService.downHouse(id);
        return "redirect:ownlist";
    }

    /**
     *收藏列表
     */
    @RequestMapping(value="bookmarked")
    public String bookmarked(House house,PageParams pageParams,Model model){
        User user = UserContext.getUser();
        house.setBookmarked(true);      //类型为收藏
        house.setUserId(user.getId());
        model.addAttribute("ps", houseService.selectHouseList(house, PageParams.build(pageParams.getPageSize(),pageParams.getPageNum()))) ;
        return "house/collect";
    }

    /**
     *取消收藏
     */
    @RequestMapping(value="unbookmark1")
    @ResponseBody
    public ResultMsg unbookmark1(Integer id,Model model){
        User user = UserContext.getUser();
        houseService.unbookmark(id, user.getId(), true);
        return ResultMsg.successMsg("取消成功");
    }
    @RequestMapping(value="unbookmark")
    public String unbookmark(Integer id,Model model){
        User user = UserContext.getUser();
        houseService.unbookmark(id, user.getId(), true);
        return "redirect:bookmarked";
    }
}
