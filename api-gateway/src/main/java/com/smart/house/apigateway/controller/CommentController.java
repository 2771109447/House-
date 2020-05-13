package com.smart.house.apigateway.controller;

import com.smart.house.apigateway.common.Interceptor.UserContext;
import com.smart.house.apigateway.model.User;
import com.smart.house.apigateway.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("comment")
public class CommentController {

    @Autowired
    private CommentService commentService;
    /**
     * 添加房产评论
     */
    @RequestMapping("addHouseComment")
    public String leaveHouseComment(String content, Integer houseId, Model model){
        User user = UserContext.getUser();
        Integer userId =  user.getId();
        commentService.addHouseComment(houseId,content,userId);
        return "redirect:/house/detail?id=" + houseId;
    }

    /**
     * 添加博客评论
     */
    @RequestMapping(value="addBlogComment")
    public String leaveBlogComment(String content,Integer blogId,Model model){
        User user = UserContext.getUser();
        Integer userId =  user.getId();
        commentService.addBlogComment(blogId,content,userId);
        return "redirect:/blog/detail?id=" + blogId;
    }
}
