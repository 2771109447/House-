package com.smart.house.servicecomment.dao;

import com.smart.house.servicecomment.common.page.PageParams;
import com.smart.house.servicecomment.model.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Mapper
public interface BlogDao {

    //查询博客列表
    List<Blog> selectBlogList(@Param("blog") Blog blog, @Param("pageParams") PageParams pageParams);
    //查询博客总数
    Integer selectBlogCount(Blog blog);
    //查询博客详情

}
