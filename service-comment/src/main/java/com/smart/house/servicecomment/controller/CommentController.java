package com.smart.house.servicecomment.controller;

import com.smart.house.servicecomment.model.Comment;
import com.smart.house.servicecomment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    /**
     * 添加房产评论
     */

    @RequestMapping("addHouseComment")
    public void addHouseComment(Integer houseId, String content, Integer userId){
        commentService.addHouseComment(houseId,content,userId);
    }
    /**
     * 房产评论列表
     */
    @RequestMapping("selectAllHouseComment")
    public List<Comment> selectAllHouseComment(Integer id){
        List<Comment> comments=commentService.selectAllHouseComment(id);
        return comments;
    }
    /**
     *添加博客评论
     */
    @RequestMapping("addBlogComment")
    public void addBlogComment(Integer blogId, String content, Integer userId){
        commentService.addBlogComment(blogId,content,userId);
    }
    /**
     * 博客评论列表
     */
    @RequestMapping("selectAllBlogComment")
    public List<Comment> selectAllBlogComment(Integer id){
       List<Comment> comments=commentService.selectAllBlogComment(id);
       return comments;
    }

}
