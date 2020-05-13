package com.smart.house.apigateway.dao;

import com.smart.house.apigateway.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Repository
public class CommentDao {
   @Autowired
   private RestTemplate restTemplate;
    //添加房产评论
    public void addHouseComment(Integer houseId, String content, Integer userId) {
        String url="http://comment/comment/addHouseComment?houseId="+houseId+"&content="+content+"&userId="+userId;
        restTemplate.getForObject(url,String.class,houseId,content,userId);
    }

    //添加博客评论
    public void addBlogComment(Integer blogId, String content, Integer userId) {
        String url="http://comment/comment/addBlogComment?blogId="+blogId+"&content="+content+"&userId="+userId;
        restTemplate.getForObject(url,String.class,blogId,content,userId);
    }
    //获取房产评论
    public List<Comment> selectAllHouseComment(int id) {
        String url="http://comment/comment/selectAllHouseComment?id="+id;
        Comment[] comments=restTemplate.getForObject(url,Comment[].class,id);
        return Arrays.asList(comments);
    }
    //获取博客评论
    public List<Comment> selectAllBlogComment(int id) {
        String url="http://comment/comment/selectAllBlogComment?id="+id;
        Comment[] comments=restTemplate.getForObject(url,Comment[].class,id);
        return Arrays.asList(comments);
    }
}
