package com.smart.house.apigateway.service;

import com.smart.house.apigateway.common.page.PageData;
import com.smart.house.apigateway.common.page.PageParams;
import com.smart.house.apigateway.dao.BlogDao;
import com.smart.house.apigateway.model.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogService {

    @Autowired
    private BlogDao blogDao;


    /**
     *博客列表
     */
    public PageData<Blog> selectBlogs(Blog blog, PageParams pageParams) {
        return blogDao.selectBlogs(blog,pageParams);
    }

    /**
     *博客详情
     */
    public Blog selectOneBlog(int id) {
         return blogDao.selectOneBlog(id);
    }


}
