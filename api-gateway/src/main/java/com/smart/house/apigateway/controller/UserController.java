package com.smart.house.apigateway.controller;

import com.netflix.client.http.HttpRequest;
import com.smart.house.apigateway.common.Interceptor.UserContext;
import com.smart.house.apigateway.common.Salt;
import com.smart.house.apigateway.common.resultMsg.ResultMsg;
import com.smart.house.apigateway.common.resultMsg.UserHelper;
import com.smart.house.apigateway.model.House;
import com.smart.house.apigateway.model.User;

import com.smart.house.apigateway.service.AgencyService;
import com.smart.house.apigateway.service.RecommendService;
import com.smart.house.apigateway.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ResultMsg resultMsg;
    @Autowired
    private AgencyService agencyService;
    @Autowired
    private RecommendService recommendService;

    @RequestMapping("index")
    public String index(Model model,String successMsg){
        //最新上市
        List<House> houses=recommendService.selectRecommendHouses();
        model.addAttribute("recomHouses",houses);
        if (successMsg!=""&&successMsg!=null){
            if(successMsg.equals("1")){
                model.addAttribute("successMsg","创建成功");
            }
            if(successMsg.equals("2")){
                model.addAttribute("successMsg","激活成功");
            }
        }
        return "homepage/index";
    }
    /**
     * 1,用户信息合法性验证
     * 2，插入用户，未激活，发送邮箱
     * 3，邮箱来链接点击激活
     */

    /* ------------------------------用户注册------------------------------*/
    //插入用户，邮件发送激活链接
    @RequestMapping(value = "Register")
    public String  register(User user, Model model){
    if (user==null||user.getUserName()==null) {
        //经济机构
        model.addAttribute("agencyList",agencyService.selectAgencyList());
        return "user/accounts/register";
    }
    //用户数据合法性验证
    ResultMsg resultMsg= UserHelper.validate(user);
     if(resultMsg.isSuccess()&&userService.addUser(user)) {     //添加用户
    //注册成功
         model.addAttribute("email",user.getEmail());
        return "user/accounts/registerSubmit";
    }else {
     //重定向注册页面
        model.addAttribute("errorMsg",resultMsg.getErrorMsg());
        return "user/accounts/register";
      }
    }
    //点击链接，激活用户
    @RequestMapping("activate")
    public String activate(String key,Model model){
         Boolean result=userService.enable(key);
         if (result){
             model.addAttribute("successMsg","激活成功");
             return "redirect:index?successMsg="+2;
         }else {
             model.addAttribute("errorMsg","激活失败，请确认链接是否过期");
             return "user/accounts/register";
         }
    }
    /* ------------------------------用户登入------------------------------*/
    @RequestMapping("login")
    public String Login(String username, String password, String target, Model model){
        model.addAttribute("target",target);
        if(username==null||password==null){
            return "user/accounts/login";
        }
        User user=userService.selectUsers(username,password);          //验证用户
        if(user==null){
            model.addAttribute("username",username);
            model.addAttribute("errorMsg","账号或密码错误");
            return "user/accounts/login";

        }else {
            //保存登录用户，用于拦截器鉴权
            UserContext.setUser(user);
            //目标页不为空，返回target,否则返回首页
            return StringUtils.isNotBlank(target)?"redirect:"+target:"redirect:/index";
        }


    }
    /*  */
    @RequestMapping("logout")
    public String logout(HttpServletRequest request) throws ServletException {
        User user = UserContext.getUser();
        userService.logout(user.getToken());

        return "redirect:index";
    }
    /* ------------------------------个人信息------------------------------*/

    /**
     * 个人信息
        ·1，请求个人信息页
        ·2，更新用户信息
     */
    @RequestMapping("profile")
    public String prefile(User user, HttpServletRequest request){
        //请求个人信息页
        if(user.getEmail()==null){
            return "user/accounts/profile";
        }
        //更新用户信息
        User user1=userService.updateUser(user);
        request.setAttribute("loginUser",user1);
        return "user/accounts/profile";
    }

    /**
     *修改密码
     */
    @RequestMapping("changePassword")
    public String  changePassword(User user,Model model){
        //验证用户
        User user1=userService.selectUsers(user.getEmail(),user.getPasswd());
        if(user1==null||!user.getConfirmPasswd().equals(user.getNewPassword())) {
            model.addAttribute("errorMsg","密码错误");
            return "user/accounts/profile";
        }

        //新密码加密
        user1.setPasswd(Salt.encryPassword(user.getNewPassword()));
        //更新密码
        userService.updateUser(user1);
        model.addAttribute("successMsg","更新成功");
        //产生了新的token,重新创建cookies
        UserContext.setUser(user1);
        return "user/accounts/profile";
    }


}
