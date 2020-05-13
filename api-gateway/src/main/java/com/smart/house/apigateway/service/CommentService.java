package com.smart.house.apigateway.service;

import com.smart.house.apigateway.dao.CommentDao;
import com.smart.house.apigateway.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentDao commentDao;
    /**
     *添加房产评论
     */
    public void addHouseComment(Integer houseId, String content, Integer userId) {
        commentDao.addHouseComment(houseId,content,userId);
    }

    /**
     * 添加博客评论
     */

    public void addBlogComment(Integer blogId, String content, Integer userId) {
        commentDao.addBlogComment(blogId,content,userId);
    }

    /**
     *获取房产评论（前8条）
     */
    public List<Comment> selectAllHouseComment(int id) {
        return  commentDao.selectAllHouseComment(id);
    }
    /**
     *获取博客评论（前8条）
     */
    public List<Comment> selectAllBlogComment(int id) {
        return  commentDao.selectAllBlogComment(id);
    }
}
