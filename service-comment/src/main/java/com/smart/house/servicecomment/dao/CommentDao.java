package com.smart.house.servicecomment.dao;

import com.smart.house.servicecomment.model.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentDao {
    //添加房产评论
    void addHouseComment(Comment comment);

    //查询房产评论
    List<Comment> selectHouseComments(@Param("houseId") Integer houseId,@Param("size") Integer size);
    //查询博客评论
    List<Comment> selectBlogComments(@Param("blogId") Integer blogId, @Param("size") Integer size);

}
